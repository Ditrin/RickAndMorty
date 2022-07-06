package com.example.rickandmorty.domain

interface Mapper<T, V> {
    fun map(t: T): V
}
