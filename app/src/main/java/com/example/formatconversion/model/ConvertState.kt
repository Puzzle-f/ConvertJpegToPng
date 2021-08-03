package com.example.formatconversion.model

enum class ConvertState(val caption: String) {
    STARTED("STARTED"),
    IN_PROGRESS("IN_PROGRESS"),
    END("END"),
    ERROR("error: ");
}