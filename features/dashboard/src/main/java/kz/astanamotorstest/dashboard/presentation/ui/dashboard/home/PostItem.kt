package kz.astanamotorstest.dashboard.presentation.ui.dashboard.home

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import kz.astanamotorstest.dashboard.R
import kz.astanamotorstest.dashboard.databinding.ItemMovieBinding
import kz.astanamotorstest.dashboard.entity.movie.MovieUi
import kz.astanamotorstest.dashboard.entity.movie.post.PostUi
import kz.astanamotorstest.ui_components.extensions.loadImage
import kz.astanamotorstest.ui_components.extensions.setReleaseDt

class MovieItem(val movieUi: MovieUi) : BindableItem<ItemMovieBinding>() {

    override fun bind(viewBinding: ItemMovieBinding, position: Int) {
        viewBinding.movieIconIv.loadImage("https://image.tmdb.org/t/p/w500${movieUi.poster_path}")
        viewBinding.titleTv.text = movieUi.title
        viewBinding.releaseDt.setReleaseDt(movieUi.release_date)
    }

    override fun getLayout() = R.layout.item_movie

    override fun initializeViewBinding(view: View): ItemMovieBinding = ItemMovieBinding.bind(view)
}

class PostItem(val post: PostUi) : BindableItem<ItemMovieBinding>() {

    override fun bind(viewBinding: ItemMovieBinding, position: Int) {
        viewBinding.movieIconIv.loadImage("https://image.tmdb.org/t/p/w500${movieUi.poster_path}")
        viewBinding.titleTv.text = movieUi.title
        viewBinding.releaseDt.setReleaseDt(movieUi.release_date)
    }

    override fun getLayout() = R.layout.item_movie

    override fun initializeViewBinding(view: View): ItemMovieBinding = ItemMovieBinding.bind(view)
}