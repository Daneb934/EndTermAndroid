package kz.astanamotorstest.network_components

data class BaseErrorDto(
    val status_code: Int? = null,
    val status_message: String? = null,
    val success: Boolean = false
) {
}