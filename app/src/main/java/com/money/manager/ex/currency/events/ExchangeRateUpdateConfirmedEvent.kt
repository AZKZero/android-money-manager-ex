package com.money.manager.ex.currency.events

data class ExchangeRateUpdateConfirmedEvent(
    @JvmField var updateAll: Boolean
)
