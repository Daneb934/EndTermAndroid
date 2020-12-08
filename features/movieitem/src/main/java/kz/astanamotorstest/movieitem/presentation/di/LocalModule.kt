package kz.astanamotorstest.movieitem.presentation.di

import kz.astanamotorstest.movieitem.data.local.DB
import kz.astanamotorstest.movieitem.data.local.dao.FavoriteDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val itemLocalModule = module {
    single { DB.getDatabase(androidContext()) }
    single { provideItemFavoriteDao(get()) }
}

fun provideItemFavoriteDao(db: DB): FavoriteDao = db.getFavoriteDao()