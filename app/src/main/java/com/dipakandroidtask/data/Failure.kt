package com.dipakandroidtask.data


/**
 * Created by Dipak Vyas on 31-01-2021.
 */
/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object ListNotAvailable : Failure()


    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()
}
