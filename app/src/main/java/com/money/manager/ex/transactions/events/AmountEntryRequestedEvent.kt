package com.money.manager.ex.transactions.events

import info.javaperformance.money.Money

data class AmountEntryRequestedEvent(
    @JvmField var requestId: Int,
    @JvmField var amount: Money
)
