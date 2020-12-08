package kz.astanamotorstest.dashboard.presentation.ui.dashboard.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_favorite.*
import kz.astanamotorstest.dashboard.R
import kz.astanamotorstest.dashboard.databinding.FragmentFavoriteBinding
import kz.astanamotorstest.dashboard.entity.movie.MovieUi
import kz.astanamotorstest.dashboard.presentation.ui.dashboard.home.MovieItem
import kz.astanamotorstest.movieitem.entity.args.ItemArgs
import kz.astanamotorstest.network_components.ResultState
import kz.astanamotorstest.ui_components.base.BaseFragment
import kz.astanamotorstest.ui_components.utils.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FavoriteFragment : BaseFragment<List<MovieUi>, FavoriteViewModel>() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override val vm: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("onCreateView")
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.pageResponse.observe(viewLifecycleOwner, Observer(::onChangMovieResponse))
        vm.loading.observe(viewLifecycleOwner, Observer(::onProgressChanged))
        vm.itemArgs.observe(viewLifecycleOwner, EventObserver(::onChangedItemArgs))
        vm.errorResponse.observe(viewLifecycleOwner, Observer(::onErrorChanged))
        initSwipeRefresh()
    }

    private fun onErrorChanged(isShow: Boolean) {
        Timber.i("onErrorChanged, isShown = $isShow")
        if (isShow)
            binding.contentError.visibility = View.VISIBLE
        else
            binding.contentError.visibility = View.GONE
    }


    private fun onChangedItemArgs(args: ItemArgs) {
        Timber.i("onChangedItemArgs, args = $args")
        val action = FavoriteFragmentDirections.actionToMovieItem(args)
        with(findNavController()) {
            currentDestination?.getAction(action.actionId)
                ?.let { navigate(action) }
        }
    }

    private fun onProgressChanged(isShow: Boolean) {
        Timber.i("onProgressChanged, isShow = $isShow")
        if (isShow)
            showProgress()
        else
            closeProgress()
    }

    private fun initSwipeRefresh() {
        Timber.i("initSwipeReflesh")
        binding.swipeRefresh.setOnRefreshListener {
            vm.loadData()
        }
    }

    private fun onChangMovieResponse(response: ResultState<List<MovieUi>>) {
        Timber.i("onChangMovieResponse, response = $response")
        binding.swipeRefresh.isRefreshing = false
        if (response is ResultState.Success) {
            val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
                setOnItemClickListener { item, view ->
                    vm.onClickMovie(item as MovieItem)
                }
            }
            if (response.data.isEmpty()) {
                groupAdapter.clear()
                include_no_favorites.visibility = View.VISIBLE

            } else {
                include_no_favorites.visibility = View.GONE
                groupAdapter.addAll(response.data.map { MovieItem(movieUi = it) })
            }
            binding.movieRv.adapter = groupAdapter
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