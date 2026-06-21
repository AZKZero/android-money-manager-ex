package com.money.manager.ex.budget.events

data class BudgetSelectedEvent(
    @JvmField var yearId: Long,
    @JvmField var name: String
)
