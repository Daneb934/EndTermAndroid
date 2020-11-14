package kz.astanamotorstest.movieitem.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.button.MaterialButton
import kz.astanamotorstest.movieitem.R
import kz.astanamotorstest.movieitem.databinding.FragmentItemBinding
import kz.astanamotorstest.movieitem.entity.args.ItemArgs
import kz.astanamotorstest.movieitem.entity.movieitem.MovieUi
import kz.astanamotorstest.movieitem.presentation.ui.ItemViewModel.Actions
import kz.astanamotorstest.movieitem.presentation.ui.ItemViewModel.Actions.*
import kz.astanamotorstest.network_components.ResultState
import kz.astanamotorstest.ui_components.base.BaseFragment
import kz.astanamotorstest.ui_components.extensions.loadImage
import kz.astanamotorstest.ui_components.extensions.setReleaseDt
import kz.astanamotorstest.ui_components.extensions.showSnackBarWithoutButton
import kz.astanamotorstest.ui_components.utils.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class FragmentItem : BaseFragment<MovieUi, ItemViewModel>() {

    private val args by navArgs<FragmentItemArgs>()
    private lateinit var itemArgs: ItemArgs
    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!

    override val vm: ItemViewModel by viewModel { parametersOf(itemArgs) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.i("onCreate")
        itemArgs = args.itemArgs
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("onCreateView")
        _binding = FragmentItemBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.i("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        vm.pageResponse.observe(viewLifecycleOwner, Observer(::onChangedItem))
        vm.loading.observe(viewLifecycleOwner, Observer(::onProgressChanged))
        vm.action.observe(viewLifecycleOwner, EventObserver(::onActionChanged))
        vm.errorResponse.observe(viewLifecycleOwner, Observer(::onErrorChanged))
        binding.favorite.setOnClickListener {
            vm.clickFavorite()
        }
    }

    private fun onErrorChanged(isShow: Boolean) {
        Timber.i("onErrorChanged, isShown = $isShow")
        if (isShow) {
            binding.constraintLayout.visibility = View.GONE
            binding.contentError.visibility = View.VISIBLE
        } else {
            binding.constraintLayout.visibility = View.VISIBLE
            binding.contentError.visibility = View.GONE
        }
    }

    private fun onActionChanged(action: Actions) {
        Timber.i("onActionChanged, action = $action")
        when (action) {
            ON_ADD_FAVORITE_SUCCESS -> {
                showSnackBarWithoutButton(
                    binding.coordinatorLayout,
                    R.string.snackbar_success_added_favorite
                )
                binding.favorite.setImageResource(R.drawable.ic_heart_selected)
            }
            ON_DELETE_FAVORITE_SUCCESS -> {
                showSnackBarWithoutButton(
                    binding.coordinatorLayout,
                    R.string.snackbar_success_delete_favorite
                )
                binding.favorite.setImageResource(R.drawable.ic_heart_unselected)
            }
            ON_ADD_FAVORITE_ERROR -> {
                showSnackBarWithoutButton(
                    binding.coordinatorLayout,
                    R.string.snackbar_failure_added_favorite
                )
            }
            ON_DELETE_FAVORITE_ERROR -> {
                showSnackBarWithoutButton(
                    binding.coordinatorLayout,
                    R.string.snackbar_failure_delete_favorite
                )
            }
        }
    }

    private fun onProgressChanged(isShow: Boolean) {
        Timber.i("onProgressChanged, isShow = $isShow")
        if (isShow) {
            visibleContent(false)
            showProgress()
        } else {
            visibleContent(true)
            closeProgress()
        }
    }

    private fun visibleContent(isVisible: Boolean) {
        Timber.i("visibleContent")
        if (isVisible) {
            binding.constraintLayout.visibility = View.VISIBLE
        } else {
            binding.constraintLayout.visibility = View.GONE
        }
    }

    private fun onChangedItem(response: ResultState<MovieUi>) {
        Timber.i("onChangedItem, response = $response")
        if (response is ResultState.Success) {
            setMovieItemUi(response.data)
        }
    }

    private fun setMovieItemUi(movieUi: MovieUi) {
        Timber.i("setMovieItemUi, movieUi = &movieUi")
        binding.headerImage.loadImage("https://image.tmdb.org/t/p/w500${movieUi.backdrop_path}")
        binding.imageView.loadImage("https://image.tmdb.org/t/p/w500/${movieUi.poster_path}")
        binding.textView.text = movieUi.original_title
        binding.releaseDt.setReleaseDt(movieUi.release_date)
        binding.descTv.text = movieUi.overview
        if (movieUi.isFavorite) {
            binding.favorite.setImageResource(R.drawable.ic_heart_selected)
        }
    }

    override fun setClickUpdate() {
        Timber.i("setClickUpdate")
        binding.root.findViewById<MaterialButton>(R.id.btnUpdatePage).setOnClickListener {
            updatePage()
        }
    }

    override fun onDestroyView() {
        Timber.i("onDestroyView")
        super.onDestroyView()
        _binding = null
    }
}