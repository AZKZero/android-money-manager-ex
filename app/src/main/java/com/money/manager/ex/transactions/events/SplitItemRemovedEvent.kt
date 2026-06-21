package com.money.manager.ex.transactions.events

import com.money.manager.ex.database.ISplitTransaction

data class SplitItemRemovedEvent(
    @JvmField var entity: ISplitTransaction
)
