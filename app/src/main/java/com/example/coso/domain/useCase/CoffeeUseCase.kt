package com.example.coso.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.coso.data.models.CoffeeModel
import com.example.coso.domain.repository.CoffeeCall

class CoffeeUseCase(private val coffeeCall: CoffeeCall) {

    fun loadCoffee(): LiveData<List<CoffeeModel>> {

        return coffeeCall.loadCoffee()
    }

    suspend fun startMigration(context: Context) {

        coffeeCall.startMigration(context)
    }
}