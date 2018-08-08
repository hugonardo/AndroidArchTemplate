package net.hugonardo.archtemplate.domain.di

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * An scope of Dependency Injection limited by some piece of application, like an Activity or Fragment of an
 * Android Application.
 *
 * @see [javax.inject.Scope]
 */
@Scope
@MustBeDocumented
@Retention(RUNTIME)
annotation class MicroScope
