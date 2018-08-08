package net.hugonardo.archtemplate.domain

@Suppress("unused")
sealed class Event<out T> {

    data class Data<T>(val data: T) : Event<T>()

    data class Error(val error: AppThrowable) : Event<Nothing>()

    object Loading : Event<Nothing>()

    object Idle : Event<Nothing>()

    fun isData() = this is Data

    fun isError() = this is Error

    fun isLoading() = this is Loading

    fun isIdle() = this is Idle

    companion object {

        fun <T> data(data: T): Event<T> = Data(data)

        fun <T> error(error: AppThrowable): Event<T> = Error(error)

        fun <T> error(error: Throwable): Event<T> {
            return when (error) {
                is AppThrowable -> error(error)
                else -> error(AppThrowable(cause = error))
            }
        }

        fun <T> loading(): Event<T> = Loading

        fun <T> idle(): Event<T> = Idle
    }
}
