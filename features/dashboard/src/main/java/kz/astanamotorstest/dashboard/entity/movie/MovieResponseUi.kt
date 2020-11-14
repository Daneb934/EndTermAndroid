package kz.astanamotorstest.dashboard.entity.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponseUi(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<MovieUi>
) : Parcelable