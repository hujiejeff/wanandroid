package com.hujiejeff.wanadnroid.module.base.data.remote.adapter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class FlowCallAdapter<T>(private val responseType: Type): CallAdapter<T, Flow<T>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<T>): Flow<T> {
        return flow {  }
    }
}