package kz.astanamotorstest.movieitem.entity.general

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LanguageUi(
    val iso_639_1: String,
    val name: String
): Parcelable