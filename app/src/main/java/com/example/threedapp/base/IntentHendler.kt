package com.example.threedapp.base

interface IntentHendler<T> {
    fun obtainIntent(intent:T)
}