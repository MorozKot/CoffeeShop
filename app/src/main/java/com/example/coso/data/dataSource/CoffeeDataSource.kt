package com.example.coso.data.dataSource

import androidx.lifecycle.LiveData
import com.example.coso.data.models.CoffeeModel

interface CoffeeDataSource {
    fun insert(coffeeModel: CoffeeModel)
    fun loadCoffee(): LiveData<List<CoffeeModel>>
    suspend fun clear()
}