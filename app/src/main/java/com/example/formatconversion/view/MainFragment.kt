package com.example.formatconversion.view

import android.graphics.Color.RED
import android.graphics.Color.red
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.formatconversion.App
import com.example.formatconversion.R
import com.example.formatconversion.databinding.FragmentMainBinding
import com.example.formatconversion.presenter.FragmentPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class MainFragment : MvpAppCompatFragment(), UsersView {
    companion object{
        fun newInstance() = MainFragment()
    }

    val presenter: FragmentPresenter by moxyPresenter { FragmentPresenter(App.instance.router, AndroidScreens()) }
    private var vb: FragmentMainBinding? = null
    private val directoryDownloadsPath =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
            return FragmentMainBinding.inflate(inflater, container, false).also{
        vb = it
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun init() {
        vb?.convertButton?.setOnClickListener{
            val fileNamePng = "${directoryDownloadsPath}/testPng.png"
            val fileNameJPG = "${directoryDownloadsPath}/123.jpg"
            Log.d("bitmap", "жмакаем кнопку ${vb?.convertButton?.hashCode().toString()}")
            presenter.convertJpgToPng(fileNamePng, fileNameJPG)
//            conversionStatus()
        }
    }

    override fun updateConvertStatus(statusName: String) {
        vb?.status?.text = statusName
        Log.d("bitmap", "Статус загрузки $statusName")
    }


    override fun onDestroy() {
        super.onDestroy()
        vb = null
    }

    fun conversionStatus(){
        vb?.status?.text = "Конвертация"
        vb?.status?.setBackgroundColor(RED)    }


}