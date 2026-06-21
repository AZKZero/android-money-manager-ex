package com.money.manager.ex.common.events

import android.view.View

data class ListItemClickedEvent(
    @JvmField var id: Long,
    @JvmField var name: String,
    @JvmField var view: View
)
