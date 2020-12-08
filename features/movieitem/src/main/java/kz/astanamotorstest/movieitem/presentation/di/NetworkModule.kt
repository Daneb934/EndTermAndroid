package kz.astanamotorstest.movieitem.presentation.di

import kz.astanamotorstest.movieitem.data.remote.MovieItemApi
import org.koin.dsl.module
import retrofit2.Retrofit

private fun movieItemApi(retrofit: Retrofit) = retrofit.create(MovieItemApi::class.java)

val itemNetworkModule = module{
    single { movieItemApi(retrofit = get()) }
}
