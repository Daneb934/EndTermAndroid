package kz.astanamotorstest.dashboard.entity.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieUi(
    val id: Long,
    val popularity: Double,
    val vote_count: Long,
    val video: Boolean,
    val poster_path: String,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val genre_ids: List<Int>,
    val title: String,
    val vote_average: Double,
    val overview: String,
    val release_date: String
) : Parcelable