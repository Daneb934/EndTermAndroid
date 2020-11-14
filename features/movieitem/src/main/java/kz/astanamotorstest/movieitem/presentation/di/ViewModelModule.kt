package kz.astanamotorstest.movieitem.presentation.di

import kz.astanamotorstest.movieitem.entity.args.ItemArgs
import kz.astanamotorstest.movieitem.presentation.ui.ItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val itemViewModelModule = module {
    viewModel { (itemArgs: ItemArgs) ->
        ItemViewModel(
            itemArgs = itemArgs,
            getMovieItemUseCase = get(),
            addFavoriteUseCase = get(),
            deleteFavoriteUseCase = get()
        )
    }
}