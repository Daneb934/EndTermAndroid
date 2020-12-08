package kz.astanamotorstest.dashboard.entity.movie.post

data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)