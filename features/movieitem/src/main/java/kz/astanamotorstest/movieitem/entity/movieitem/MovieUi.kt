package kz.astanamotorstest.movieitem.entity.movieitem

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kz.astanamotorstest.movieitem.entity.general.GenreUi
import kz.astanamotorstest.movieitem.entity.general.LanguageUi
import kz.astanamotorstest.movieitem.entity.production.ProductionCompanyUi
import kz.astanamotorstest.movieitem.entity.production.ProductionCountryUi

@Parcelize
data class MovieUi(
    val id: Long,
    val adult: Boolean,
    val backdrop_path: String,
    val budget: Long,
    val genres: List<GenreUi>,
    val homepage: String?,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompanyUi>,
    val production_countries: List<ProductionCountryUi>,
    val release_date: String,
    val revenue: Long,
    val runtime: Int,
    val spoken_languages: List<LanguageUi>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Long
) : Parcelable {
    var isFavorite: Boolean = false
}