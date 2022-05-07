package com.example.coso.domain.repository

import androidx.lifecycle.LiveData
import com.example.coso.data.models.OrderLocalModel

interface OrderLocalCall {

    suspend fun insert(orderLocalModel: OrderLocalModel)

    fun loadOrder(): LiveData<List<OrderLocalModel>>

    suspend fun clear()
}