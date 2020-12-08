package kz.astanamotorstest.movieitem.entity.general

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreUi(
    val id: Long,
    val name: String
): Parcelable