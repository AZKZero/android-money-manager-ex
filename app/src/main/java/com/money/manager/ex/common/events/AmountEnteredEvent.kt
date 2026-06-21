package com.money.manager.ex.common.events

import info.javaperformance.money.Money

data class AmountEnteredEvent(
    @JvmField var requestId: String,
    @JvmField var amount: Money
)
