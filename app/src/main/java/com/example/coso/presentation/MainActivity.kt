package com.example.coso.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.coso.R
import com.example.coso.databinding.ActivityMainBinding
import com.example.coso.presentation.Tabs.Account.Account
import com.example.coso.presentation.Tabs.Card.Card
import com.example.coso.presentation.Tabs.Coffee.Coffee
import com.example.coso.presentation.Tabs.Home
import com.example.coso.presentation.viewModels.CardViewModel
import com.example.coso.presentation.viewModels.CoffeeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(){

    private var binding:ActivityMainBinding? = null
    private val coffeeViewModel: CoffeeViewModel by viewModel()
    private val cardViewModel: CardViewModel by viewModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        coffeeViewModel.migration(this)

        setSupportActionBar(binding?.topMainMenu)

        loadCoffeeToCard()

        supportFragmentManager.beginTransaction().replace(R.id.mainContent, Home()).commit()

        binding?.bottomMainMenu?.setOnItemSelectedListener { item ->

            when(item.itemId) {
                R.id.homeBottomMainMenu -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, Home()).commit()
                R.id.coffeeBottomMainMenu -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, Coffee()).commit()
                R.id.cardBottomMainMenu -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, Card()).commit()
                R.id.accountBottomMainMenu -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, Account()).commit()
            }

            return@setOnItemSelectedListener true


        }
        binding?.bottomMainMenu?.selectedItemId = R.id.homeBottomMainMenu



    }

    private fun loadCoffeeToCard (){

        cardViewModel.loadCoffeeFromCard.observe(this, Observer {

            val count = it.count()

            val badge = binding?.bottomMainMenu?.getOrCreateBadge(R.id.cardBottomMainMenu)

            badge?.isVisible = count>0
            badge?.number = count

        })

    }




}