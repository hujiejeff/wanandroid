package com.hujiejeff.wanadnroid.module.base.data

import android.content.Context
import com.hujiejeff.wanadnroid.module.base.data.local.AppDatabase
import com.hujiejeff.wanadnroid.module.base.data.local.entity.ReadHistory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object DataRepository {
    private lateinit var db: AppDatabase

    fun init(context: Context) {
        initDatabase(context)
    }

    private fun initDatabase(context: Context) {
        db = AppDatabase.getInstance(context)
    }
}