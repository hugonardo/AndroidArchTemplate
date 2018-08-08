package net.hugonardo.archtemplate.domain

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import net.hugonardo.archtemplate.domain.AppThrowable.Type
import net.hugonardo.archtemplate.domain.AppThrowable.Type.Companion.GENERIC

class AppThrowable(
        val type: Type = Type.GENERIC,

        message: String? = null,

        cause: Throwable? = null
) : Throwable(message, cause) {

    interface Type {

        companion object {
            val GENERIC = object : Type {
            }
        }
    }
}

fun Throwable.toAppThrowable(type: Type = GENERIC, message: String? = null): AppThrowable {
    return when (this) {
        is AppThrowable -> this
        else -> AppThrowable(type = type, message = message, cause = this)
    }
}

fun <T> Throwable.toSingleError(type: Type = GENERIC, message: String? = null): Single<T> =
        Single.error(toAppThrowable(type, message))

fun Throwable.toCompletableError(type: Type = GENERIC, message: String? = null): Completable =
        Completable.error(toAppThrowable(type, message))

fun <T> Throwable.toObservableError(type: Type = GENERIC, message: String? = null): Observable<T> =
        Observable.error(toAppThrowable(type, message))

fun <T> Throwable.toFlowableError(type: Type = GENERIC, message: String? = null): Flowable<T> =
        Flowable.error(toAppThrowable(type, message))
