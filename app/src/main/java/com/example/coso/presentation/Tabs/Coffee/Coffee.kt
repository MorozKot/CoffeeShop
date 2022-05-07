package com.example.coso.presentation.Tabs.Coffee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coso.R
import com.example.coso.data.models.CoffeeModel
import com.example.coso.databinding.CoffeeBinding
import com.example.coso.presentation.viewModels.CardViewModel
import com.example.coso.presentation.viewModels.CoffeeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class Coffee : Fragment() {

    private var binding: CoffeeBinding? = null
    private var coffeeAdapter: CoffeeAdapter? = null
    private val coffeeViewModel: CoffeeViewModel by viewModel()
    private val cardViewModel: CardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.coffee, container, false)

        initRecyclerCoffee()
        loadCoffee()

        return binding?.root
    }

    private fun initRecyclerCoffee() {

        binding?.catalogCoffee?.layoutManager =
            LinearLayoutManager(context)
        coffeeAdapter =
            CoffeeAdapter({ coffeeModel: CoffeeModel ->
                addToCard(
                    coffeeModel
                )
            }, { coffeeModel: CoffeeModel ->
                removeFromCard(
                    coffeeModel
                )
            }, { idProduct: Int, addToBasket: AppCompatImageButton,
                 removeFromBasket: AppCompatImageButton ->
                loadCoffeeToCardFromCardProduct(
                    idProduct, addToBasket, removeFromBasket
                )
            })
        binding?.catalogCoffee?.adapter = coffeeAdapter
    }

    private fun loadCoffee() {

        coffeeViewModel.loadCoffee.observe(viewLifecycleOwner, Observer {
            coffeeAdapter?.setList(it)
            coffeeAdapter?.notifyDataSetChanged()
        })
    }

    private fun addToCard(coffeeModel: CoffeeModel) {
        cardViewModel.startInsert(
            coffeeModel.name, coffeeModel.image, coffeeModel.price, coffeeModel.id.toString(),
            "1"
        )
    }

    private fun removeFromCard(coffeeModel: CoffeeModel) {
        cardViewModel.deleteProductToCardFromCardProduct(coffeeModel.id.toString())
    }

    private fun loadCoffeeToCardFromCardProduct(
        idProduct: Int, addToBasket: AppCompatImageButton,
        removeFromBasket: AppCompatImageButton
    ) {

        cardViewModel.loadCoffeeToCardFromCardProduct(idProduct.toString())
            .observe(viewLifecycleOwner, Observer {

                val count = it.count()

                if (count > 0) {
                    addToBasket.visibility = View.GONE
                    removeFromBasket.visibility = View.VISIBLE
                } else {
                    addToBasket.visibility = View.VISIBLE
                    removeFromBasket.visibility = View.GONE
                }
            })
    }
}