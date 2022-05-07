package com.example.coso.domain.useCase

import androidx.lifecycle.LiveData
import com.example.coso.data.models.OrderLocalModel
import com.example.coso.domain.repository.OrderLocalCall

class OrderLocalUseCase(private val orderLocalCall: OrderLocalCall) {

    suspend fun insert(orderLocalModel: OrderLocalModel) {
        orderLocalCall.insert(orderLocalModel)
    }

    fun loadOrder(): LiveData<List<OrderLocalModel>> {
        return orderLocalCall.loadOrder()
    }

    suspend fun clear() {
        orderLocalCall.clear()
    }
}