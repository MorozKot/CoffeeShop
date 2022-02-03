package com.example.coso.presentation.di

import androidx.room.Room
import com.example.coso.data.dataSource.CoffeeApiDataSource
import com.example.coso.data.dataSource.CoffeeDataSource
import com.example.coso.data.dataSourceIMPL.CoffeeApiDataSourceIMPL
import com.example.coso.data.dataSourceIMPL.CoffeeDataSourceIMPL
import com.example.coso.data.localDB.DataBaseCoffee
import com.example.coso.data.repository.CardRepository
import com.example.coso.data.repository.CoffeeRepository
import com.example.coso.data.repository.OrderApiRepository
import com.example.coso.data.repository.OrderLocalRepository
import com.example.coso.domain.repository.CardCall
import com.example.coso.domain.repository.CoffeeCall
import com.example.coso.domain.repository.OrderApiCall
import com.example.coso.domain.repository.OrderLocalCall
import com.example.coso.domain.useCase.CardUseCase
import com.example.coso.domain.useCase.CoffeeUseCase
import com.example.coso.domain.useCase.OrderApiUseCase
import com.example.coso.domain.useCase.OrderLocalUseCase
import com.example.coso.presentation.viewModels.CardViewModel
import com.example.coso.presentation.viewModels.CoffeeViewModel
import com.example.coso.presentation.viewModels.OrderApiViewModel
import com.example.coso.presentation.viewModels.OrderLocalViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coffee = module{

    single {
        Room.databaseBuilder(androidContext(), DataBaseCoffee::class.java,
            "dbC").build()
    }

    single { get<DataBaseCoffee>().coffeeDao }


    single<CoffeeDataSource> {
        CoffeeDataSourceIMPL(
            get()
        )
    }

    single<CoffeeApiDataSource> {
        CoffeeApiDataSourceIMPL(
            get()
        )
    }

    single<CoffeeCall> { CoffeeRepository(get(),get()) }

    single { CoffeeUseCase(get()) }

    viewModel { CoffeeViewModel(get()) }

}

val card = module{

    single {
        Room.databaseBuilder(androidContext(), DataBaseCoffee::class.java,
            "dbO").build()
    }

    single { get<DataBaseCoffee>().cardDao }


    single<CardCall> { CardRepository(get()) }

    single { CardUseCase(get()) }

    viewModel { CardViewModel(get()) }

}

val order = module{

    single {
        Room.databaseBuilder(androidContext(), DataBaseCoffee::class.java,
            "dbS").build()
    }

    single { get<DataBaseCoffee>().orderLocalDao }


    single<OrderLocalCall> { OrderLocalRepository(get()) }

    single { OrderLocalUseCase(get()) }

    viewModel { OrderLocalViewModel(get()) }

}

val orderApi= module{

    single<OrderApiCall> { OrderApiRepository() }

    single { OrderApiUseCase(get()) }

    viewModel { OrderApiViewModel(get()) }

}

