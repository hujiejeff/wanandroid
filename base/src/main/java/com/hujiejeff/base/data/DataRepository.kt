package com.hujiejeff.base.data

import android.content.Context
import com.hujiejeff.base.data.local.AppDatabase

object DataRepository {
    private lateinit var db: AppDatabase

    fun init(context: Context) {
        initDatabase(context)
    }

    private fun initDatabase(context: Context) {
        db = AppDatabase.getInstance(context)
    }
}