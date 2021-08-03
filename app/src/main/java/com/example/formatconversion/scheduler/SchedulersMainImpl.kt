package com.example.formatconversion.scheduler

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class SchedulersMainImpl: SchedulersMain {
    override fun threadIo(): Scheduler = io.reactivex.rxjava3.schedulers.Schedulers.io()

    override fun threadComputation(): Scheduler = Schedulers.computation()

    override fun threadMain(): Scheduler = AndroidSchedulers.mainThread()
}