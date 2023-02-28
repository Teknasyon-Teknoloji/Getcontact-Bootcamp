package com.gtc.getcamp.utility.resource

sealed interface Resource<out T> {

    class Success<T>(val data: T) : Resource<T>
    class Error(val message: String, val code: Int) : Resource<Nothing>

}