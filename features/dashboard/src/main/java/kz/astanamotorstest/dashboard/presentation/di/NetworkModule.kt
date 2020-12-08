package kz.astanamotorstest.dashboard.presentation.di

import kz.astanamotorstest.dashboard.data.remote.MovieApi
import org.koin.dsl.module
import retrofit2.Retrofit

private fun movieApi(retrofit: Retrofit) = retrofit.create(MovieApi::class.java)

val movieNetworkModule = module {
    single { movieApi(retrofit = get()) }
}