package kz.astanamotorstest.movieitem.entity.production

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCompanyUi(
    val id: Long,
    val logo_path: String,
    val name: String,
    val origin_country: String
): Parcelable