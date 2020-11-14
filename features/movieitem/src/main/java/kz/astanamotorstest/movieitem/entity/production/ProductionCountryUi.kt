package kz.astanamotorstest.movieitem.entity.production

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCountryUi(
    val iso_3166_1: String,
    val name: String
): Parcelable