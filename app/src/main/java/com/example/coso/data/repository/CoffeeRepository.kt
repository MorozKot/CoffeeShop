package com.example.coso.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.coso.data.dataSource.CoffeeApiDataSource
import com.example.coso.data.dataSource.CoffeeDataSource
import com.example.coso.data.models.CoffeeModel
import com.example.coso.domain.repository.CoffeeCall

class CoffeeRepository (private val coffeeApiDataSource: CoffeeApiDataSource,
                        private val coffeeDataSource: CoffeeDataSource
): CoffeeCall {

    override fun loadCoffee(): LiveData<List<CoffeeModel>> {
        return coffeeDataSource.loadCoffee()    }

    override suspend fun startMigration(context: Context) {
        coffeeDataSource.clear()
        coffeeApiDataSource.startMigration(context)
    }
}
