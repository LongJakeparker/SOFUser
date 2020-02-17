package com.example.sofuser.template

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(
    val status: Status,
    val data: T?,
    val exception: BaseException?,
    val progressDone: Int?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(exception: BaseException): Resource<T> {
            return Resource(Status.ERROR, null, exception, null)
        }

        fun <T> loading(progressDone: Int): Resource<T> {
            return Resource(Status.LOADING, null, null, progressDone)
        }
    }
}