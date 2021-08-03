package com.example.formatconversion.view

import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun masterScreen() = FragmentScreen { MainFragment.newInstance() }
}
