package kz.astanamotorstest.dashboard.presentation.ui.dashboard.home

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import kz.astanamotorstest.dashboard.R
import kz.astanamotorstest.dashboard.databinding.ItemPostBinding
import kz.astanamotorstest.dashboard.entity.movie.post.PostUi

//class MovieItem(val movieUi: MovieUi) : BindableItem<ItemMovieBinding>() {
//
//    override fun bind(viewBinding: ItemMovieBinding, position: Int) {
//        viewBinding.movieIconIv.loadImage("https://image.tmdb.org/t/p/w500${movieUi.poster_path}")
//        viewBinding.titleTv.text = movieUi.title
//        viewBinding.releaseDt.setReleaseDt(movieUi.release_date)
//    }
//
//    override fun getLayout() = R.layout.item_movie
//
//    override fun initializeViewBinding(view: View): ItemMovieBinding = ItemMovieBinding.bind(view)
//}

class PostItem(val post: PostUi) : BindableItem<ItemPostBinding>() {

    override fun bind(viewBinding: ItemPostBinding, position: Int) {
//        viewBinding.movieIconIv.loadImage("https://image.tmdb.org/t/p/w500${movieUi.poster_path}")
//        viewBinding.titleTv.text = movieUi.title
//        viewBinding.releaseDt.setReleaseDt(movieUi.release_date)
        viewBinding.titleTv.text = post.title
    }

    override fun getLayout() = R.layout.item_movie

    override fun initializeViewBinding(view: View): ItemPostBinding = ItemPostBinding.bind(view)
}