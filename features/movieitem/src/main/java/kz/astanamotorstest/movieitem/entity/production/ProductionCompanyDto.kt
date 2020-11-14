package kz.astanamotorstest.movieitem.entity.production

data class ProductionCompanyDto(
    val id: Long,
    val logo_path: String?,
    val name: String,
    val origin_country: String
)