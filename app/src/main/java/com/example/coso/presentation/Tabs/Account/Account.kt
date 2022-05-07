package com.example.coso.presentation.Tabs.Account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coso.R
import com.example.coso.databinding.AccountBinding
import com.example.coso.presentation.viewModels.OrderLocalViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class Account : Fragment() {

    private var binding: AccountBinding? = null
    private var orderAdapter: OrderAdapter? = null
    private val orderLocalViewModel: OrderLocalViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.account, container, false)

        initRecyclerOrder()

        loadOrders()

        binding?.clearOrders?.setOnClickListener(View.OnClickListener {
            orderLocalViewModel.clearOrders()
        })

        return binding?.root
    }

    private fun initRecyclerOrder() {

        binding?.listOrders?.layoutManager =
            LinearLayoutManager(context)
        orderAdapter = OrderAdapter()
        binding?.listOrders?.adapter = orderAdapter
    }

    private fun loadOrders() {

        orderLocalViewModel.loadOrder.observe(viewLifecycleOwner, Observer {
            orderAdapter?.setList(it)
            orderAdapter?.notifyDataSetChanged()
        })
    }
}