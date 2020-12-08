package kz.astanamotorstest.movieitem.entity.args

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemArgs(
    val movieId: Long
) : Parcelable
