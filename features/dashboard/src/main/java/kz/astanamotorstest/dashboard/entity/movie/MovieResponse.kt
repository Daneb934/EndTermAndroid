package kz.astanamotorstest.dashboard.entity.movie

data class MovieResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<MovieDto>
)