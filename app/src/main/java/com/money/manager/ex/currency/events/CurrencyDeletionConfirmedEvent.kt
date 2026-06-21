package com.money.manager.ex.currency.events

data class CurrencyDeletionConfirmedEvent(
    @JvmField var currencyId: Long,
    @JvmField var itemPosition: Long
)
