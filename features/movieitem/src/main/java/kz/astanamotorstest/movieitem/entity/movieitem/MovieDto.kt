package kz.astanamotorstest.movieitem.entity.movieitem

import kz.astanamotorstest.movieitem.entity.general.GenreDto
import kz.astanamotorstest.movieitem.entity.general.LanguageDto
import kz.astanamotorstest.movieitem.entity.production.ProductionCompanyDto
import kz.astanamotorstest.movieitem.entity.production.ProductionCountryDto

data class MovieDto(
    val id: Long,
    val adult: Boolean,
    val backdrop_path: String?,
    val budget: Long,
    val genres: List<GenreDto>,
    val homepage: String?,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompanyDto>?,
    val production_countries: List<ProductionCountryDto>?,
    val release_date: String,
    val revenue: Long,
    val runtime: Int,
    val spoken_languages: List<LanguageDto>?,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Long
)