package com.data.di

import com.data.database.AppDataBase

object Injector {


    val appDB by lazy { AppDataBase() }
}