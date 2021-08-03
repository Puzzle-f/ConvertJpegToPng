package com.example.formatconversion.presenter

import com.example.formatconversion.view.IScreens
import com.example.formatconversion.view.MainFragment
import com.example.formatconversion.view.MainView
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.masterScreen())
    }

    fun backClicked() {
        router.exit()
    }
}