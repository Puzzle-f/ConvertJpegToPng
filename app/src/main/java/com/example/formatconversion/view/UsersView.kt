package com.example.formatconversion.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
// интерфейс для фрагмента
interface UsersView : MvpView {
    fun init()
    fun updateConvertStatus(statusName: String)
}