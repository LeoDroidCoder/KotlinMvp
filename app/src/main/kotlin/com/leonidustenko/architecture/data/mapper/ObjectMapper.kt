package com.leonidustenko.architecture.data.mapper

interface ObjectMapper<A, B> : OneWayObjectMapper<A, B> {

    fun mapFrom(b: B) : A

    fun mapFrom(collection: Collection<B>): List<A> {
        return collection.map { mapFrom(it) }.toList()
    }
}