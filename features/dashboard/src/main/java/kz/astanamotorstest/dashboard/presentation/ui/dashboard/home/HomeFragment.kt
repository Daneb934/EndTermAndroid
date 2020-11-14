package kz.astanamotorstest.dashboard.presentation.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kz.astanamotorstest.dashboard.R
import kz.astanamotorstest.dashboard.databinding.FragmentHomeBinding
import kz.astanamotorstest.dashboard.entity.movie.MovieUi
import kz.astanamotorstest.movieitem.entity.args.ItemArgs
import kz.astanamotorstest.network_components.ResultState
import kz.astanamotorstest.ui_components.base.BaseFragment
import kz.astanamotorstest.ui_components.extensions.onLoadMore
import kz.astanamotorstest.ui_components.utils.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment<List<MovieUi>, HomeViewModel>() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var groupAdapter: GroupAdapter<GroupieViewHolder>

    private var index = 0

    override val vm: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("onCreateView")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun updatePage() {
        Timber.i("updatePage")
        vm.loadNewData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("onViewCreated")
        groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            setOnItemClickListener { item, view ->
                vm.onClickMovie(item as MovieItem)
            }
        }
        setupRecycler()
        initSwipeRefresh()
        vm.loading.observe(viewLifecycleOwner, Observer(::onProgressChanged))
        vm.errorResponse.observe(viewLifecycleOwner, Observer(::onErrorChanged))
        vm.pageResponse.observe(viewLifecycleOwner, Observer(::onChangeMovieResponse))
        vm.itemArgs.observe(viewLifecycleOwner, EventObserver(::onChangedItemArgs))
    }

    private fun onErrorChanged(isShow: Boolean) {
        Timber.i("onErrorChanged, isShown = $isShow")
        if (isShow) {
            binding.contentError.visibility = View.VISIBLE
            binding.moviesRv.visibility = View.GONE
            groupAdapter.clear()
            vm.clearList()
        } else {
            binding.contentError.visibility = View.GONE
            binding.moviesRv.visibility = View.VISIBLE
        }
    }

    private fun initSwipeRefresh() {
        Timber.i("initSwipeReflesh")
        binding.swipeRefresh.setOnRefreshListener {
            vm.loadNewData()
        }
    }

    private fun onChangedItemArgs(args: ItemArgs) {
        Timber.i("onChangedItemArgs, args = $args")
        val action = HomeFragmentDirections.actionToMovieItem(args)
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

    private fun setupRecycler() {
        Timber.i("setupRecycler")

        binding.moviesRv.also {
            it.adapter = groupAdapter
            it.layoutManager
            it.onLoadMore {
                vm.loadData()
            }
        }
    }

    private fun onChangeMovieResponse(result: ResultState<List<MovieUi>>) {
        Timber.i("onChangeMovieResponse, result = $result")
        binding.swipeRefresh.isRefreshing = false
        if (result is ResultState.Success) {
            groupAdapter.clear()
            groupAdapter.addAll(result.data.map { MovieItem(movieUi = it) })
        }
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause")
        index = if (binding
                .moviesRv
                .layoutManager != null &&
            binding
                .moviesRv.layoutManager is LinearLayoutManager
        ) {
            (binding
                .moviesRv.layoutManager as LinearLayoutManager)
                .findFirstVisibleItemPosition()
        } else 0
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume")
        binding
            .moviesRv.scrollToPosition(index)
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