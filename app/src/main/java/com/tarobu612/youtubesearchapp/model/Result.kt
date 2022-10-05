package com.tarobu612.youtubesearchapp.model

sealed class Result<out T, out E> {

    /**
     * 成功
     *
     * @param T レスポンス型
     * @property value レスポンス
     */
    data class Success<out T : Any?>(val value: T) : Result<T, Nothing>()

    /**
     * 失敗
     *
     * @property error
     */
    data class Failure<out E>(val error: E) : Result<Nothing, E>()
}