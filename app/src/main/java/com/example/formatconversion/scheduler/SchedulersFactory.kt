package com.example.formatconversion.scheduler

// фабричный класс
class SchedulersFactory {
fun create(): SchedulersMain = SchedulersMainImpl()
}