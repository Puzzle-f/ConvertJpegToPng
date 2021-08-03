package com.example.formatconversion.presenter

import android.content.res.Resources
import android.util.Log
import com.example.formatconversion.App
import com.example.formatconversion.model.*
import com.example.formatconversion.scheduler.SchedulersFactory
import com.example.formatconversion.view.IScreens
import com.example.formatconversion.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class FragmentPresenter(
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    private var disposable: Disposable? = null
    private val resources: Resources = Resources.getSystem()

    //    private val resources2: Resources = MainFragment.newInstance().resources
    private val resources3: Resources = App.instance.resources

    fun convertJpgToPng(fileNamePng: String, fileNameJPG: String) {
        val defaultSchedulers = SchedulersFactory().create()
        disposable =
            Single.fromCallable { readFileToStream(fileNameJPG) }
                .subscribeOn(defaultSchedulers.threadIo())
                .observeOn(defaultSchedulers.threadComputation())
                .map { inputStream -> streamToBitmap(inputStream) }
                .subscribeOn(defaultSchedulers.threadIo())
                .observeOn(defaultSchedulers.threadComputation())
                .map { bitmap -> bitmap!!.toPng()  }
                .observeOn(defaultSchedulers.threadIo())
                .map { byteArrayOutputStream -> saveToFile(byteArrayOutputStream, fileNamePng) }
                .observeOn(defaultSchedulers.threadMain())
                .subscribe(
                    { onConvertSuccess() },
                    { error -> onConvertError(error) }
                )
//        disposable =
//            Single.fromCallable {
//                onProgress()
//                readFileToStreemNoClass(resources3)
//            }
//                .subscribeOn(defaultSchedulers.threadIo())
//                .observeOn(defaultSchedulers.threadMain())
//                .map { bitmap -> bitmap }
////
////                .subscribeOn(defaultSchedulers.threadMain())
//                .observeOn(defaultSchedulers.threadComputation())
//                .map { bitmap -> bitmap.toPng() }
//                .observeOn(defaultSchedulers.threadIo())
//                .map { byteArrayOutputStream -> saveToFile(byteArrayOutputStream, fileNamePng) }
//                .observeOn(defaultSchedulers.threadMain())
//                .subscribe(
//                    { onConvertSuccess() },
//                    { error -> onConvertError(error) }
//                )
    }

    // костыль для изменения статуса обработки
    private fun onProgress() {
        Log.d("", "костыль для изменения статуса обработки ${ConvertState.IN_PROGRESS.caption}")
        viewState.updateConvertStatus(ConvertState.IN_PROGRESS.caption)
    }

    private fun onConvertSuccess() {
        viewState.updateConvertStatus(ConvertState.END.caption)
        Log.d("", "Конвертация завершена ${ConvertState.END.caption}")
    }

    private fun onConvertError(error: Throwable) {
        viewState.updateConvertStatus("${ConvertState.ERROR.caption} ${error.message}")
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }


}