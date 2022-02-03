package com.example.coso.data.dataSourceIMPL

import androidx.lifecycle.LiveData
import com.example.coso.data.dataSource.CoffeeDataSource
import com.example.coso.data.localDB.CoffeeDao
import com.example.coso.data.models.CoffeeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoffeeDataSourceIMPL(private val dao: CoffeeDao):
    CoffeeDataSource {


    override fun insert(coffeeModel: CoffeeModel) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(coffeeModel)}
    }

    override fun loadCoffee(): LiveData<List<CoffeeModel>> {
        return dao.loadCoffee()
    }


    override suspend fun clear() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.clear()}
    }



}