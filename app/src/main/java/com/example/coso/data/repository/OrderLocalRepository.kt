package com.example.coso.data.repository

import androidx.lifecycle.LiveData
import com.example.coso.data.localDB.OrderLocalDao
import com.example.coso.data.models.OrderLocalModel
import com.example.coso.domain.repository.OrderLocalCall

class OrderLocalRepository (private val orderLocalDao: OrderLocalDao
): OrderLocalCall {

    override suspend fun insert(orderLocalModel: OrderLocalModel) {
        orderLocalDao.insert(orderLocalModel)    }

    override fun loadOrder(): LiveData<List<OrderLocalModel>> {
        return orderLocalDao.loadOrder()    }

    override suspend fun clear() {
        orderLocalDao.clear()    }



}