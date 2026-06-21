package com.money.manager.ex.account.events

import info.javaperformance.money.Money
import java.util.HashMap

data class RunningBalanceCalculatedEvent(
    @JvmField var balances: HashMap<Long, Money>
)
