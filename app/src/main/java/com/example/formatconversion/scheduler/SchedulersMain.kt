package com.example.formatconversion.scheduler

import io.reactivex.rxjava3.core.Scheduler

interface SchedulersMain {
    fun threadIo(): Scheduler
    fun threadComputation(): Scheduler
    fun threadMain(): Scheduler
}