package kz.astanamotorstest.network_components

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
@Suppress("EqualsOrHashCode")
sealed class Failure(
    val exception: Exception = Exception("Failure"),
    val errorDto: BaseErrorDto? = null
) {

    object None : Failure()

    object NetworkConnection : Failure()

    object ServerError : Failure()

    /** * Extend this class for feature specific failures.*/
    open class FeatureFailure(
        featureException: Exception = Exception("Feature failure"),
        featureError: BaseErrorDto? = null
    ) : Failure(featureException, featureError)

    override fun equals(other: Any?): Boolean {
        return other is Failure
    }
}