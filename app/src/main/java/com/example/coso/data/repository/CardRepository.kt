package com.example.coso.data.repository

import androidx.lifecycle.LiveData
import com.example.coso.data.localDB.CardDao
import com.example.coso.data.models.CardModel
import com.example.coso.domain.repository.CardCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardRepository (private val dao: CardDao): CardCall {

    override suspend fun insert(cardModel: CardModel) {
       dao.insert(cardModel)    }

    override suspend fun updateProductToCard(cardModel: CardModel){
        dao.updateProductToCard(cardModel)
    }

    override fun loadCoffeeFromCard(): LiveData<List<CardModel>> {
        return dao.loadCoffeeFromCard()    }

    override fun loadCoffeeToCardFromCardProduct(idProduct:String): LiveData<List<CardModel>> {
        return dao.loadCoffeeToCardFromCardProduct(idProduct)    }

    override suspend fun deleteProductFromCard(idProduct:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteProductFromCard(idProduct)}
    }

    override suspend fun deleteProductToCardFromCardProduct(idProduct:String) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteProductToCardFromCardProduct(idProduct)}
    }

    override suspend fun clear() {
        dao.clear()    }
}
