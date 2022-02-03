package com.example.coso.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coso.data.models.CardModel
import com.example.coso.data.models.CoffeeModel
import com.example.coso.data.models.OrderLocalModel

@Database(entities = [CardModel::class, CoffeeModel::class, OrderLocalModel::class], version = 1)
abstract class DataBaseCoffee: RoomDatabase() {
    abstract val cardDao: CardDao
    abstract val coffeeDao: CoffeeDao
    abstract val orderLocalDao: OrderLocalDao

}