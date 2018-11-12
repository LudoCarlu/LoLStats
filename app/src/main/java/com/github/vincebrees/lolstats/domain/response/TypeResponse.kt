package com.github.vincebrees.lolstats.domain.response

sealed class TypeResponse<T>

data class DataResponse<T>(val data: T) : TypeResponse<T>()

class ErrorResponse<T>() : TypeResponse<T>() {
    var errorType: ErrorCode? = null

    constructor(errorType: ErrorCode) : this() {
        this.errorType = errorType
    }
}

interface ErrorCode

enum class SummonerIdErrorCode : ErrorCode {
    NOT_FOUND,
    TECHNICAL_ERROR
}