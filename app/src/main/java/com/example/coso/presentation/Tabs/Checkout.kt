package com.example.coso.presentation.Tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.example.coso.R
import com.example.coso.data.models.CardModel
import com.example.coso.databinding.ActivityMainBinding
import com.example.coso.databinding.CheckoutBinding
import com.example.coso.presentation.MainActivity
import com.example.coso.presentation.Tabs.Account.Account
import com.example.coso.presentation.viewModels.CardViewModel
import com.example.coso.presentation.viewModels.OrderApiViewModel
import com.example.coso.presentation.viewModels.OrderLocalViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class Checkout : BottomSheetDialogFragment() {

    private var binding: CheckoutBinding? = null
    private val cardViewModel: CardViewModel by viewModel()
    private val orderLocalViewModel: OrderLocalViewModel by viewModel()
    private val orderApiViewModel: OrderApiViewModel by viewModel()
    private var bindingMain: ActivityMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.checkout, container, false)

        binding?.submitCheckout?.setOnClickListener(View.OnClickListener {

            cardViewModel.loadCoffeeFromCard.observe(viewLifecycleOwner, Observer {

                val totalOrder: Int = it.sumOf<CardModel> { it.totalPrice.toInt() }

                val descriptionOrder =
                    it.map { it.name + ": Sum - " + it.count + ", Price - " + it.totalPrice + " $; " }
                        .joinToString("")

                orderLocalViewModel.startInsert(
                    binding?.enterNameCheckout?.text.toString(),
                    binding?.enterPhoneCheckout?.text.toString(), descriptionOrder,
                    totalOrder.toString()
                )

                orderApiViewModel.insert(
                    (context as FragmentActivity), binding?.enterNameCheckout?.text.toString(),
                    binding?.enterPhoneCheckout?.text.toString(), descriptionOrder,
                    totalOrder.toString()
                )

                binding?.enterNameCheckout?.setText("")
                binding?.enterPhoneCheckout?.setText("")

                MainActivity.bindingM?.bottomMainMenu?.selectedItemId  = R.id.accountBottomMainMenu
                (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.mainContent, Account()).commit()

                dismiss()

                cardViewModel.clearCard()
            })
        })

        return binding?.root
    }
}