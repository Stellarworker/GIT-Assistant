package com.stellarworker.gitassistant.di

import kotlin.reflect.KClass

private const val ERROR_MESSAGE = "No such dependence!"

object DiKeeper {
    private val dependenciesHolder = HashMap<KClass<*>, DependencyFabric>()

    fun <T : Any> get(className: KClass<T>): T =
        (dependenciesHolder[className]?.get() ?: throw IllegalArgumentException(ERROR_MESSAGE)) as T

    fun <T : Any> add(className: KClass<T>, dependencyFabric: DependencyFabric) {
        dependenciesHolder[className] = dependencyFabric
    }
}

abstract class DependencyFabric(protected val creator: () -> Any) {
    abstract fun get(): Any
}

inline fun <reified T : Any> get(): T = DiKeeper.get(T::class)

inline fun <reified T : Any> inject() = lazy {
    get<T>()
}

class Singleton(creator: () -> Any) : DependencyFabric(creator) {
    private val dependency: Any by lazy { creator.invoke() }
    override fun get(): Any = dependency
}

class Fabric(creator: () -> Any) : DependencyFabric(creator) {
    override fun get(): Any = creator.invoke()
}