package com.leonidustenko.architecture.data.mapper

interface OneWayObjectMapper<in A, out B> {

    fun mapTo(a: A): B

    fun mapTo(collection: Collection<A>): List<B> {
        return collection.map { mapTo(it) }.toList()
    }
}