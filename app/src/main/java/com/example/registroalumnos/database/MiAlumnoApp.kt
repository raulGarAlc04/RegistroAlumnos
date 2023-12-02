package com.example.registroalumnos.database

import android.app.Application
import androidx.room.Room

class MiAlumnoApp: Application() {
    companion object {
        lateinit var database: DBAlumnos
    }

    override fun onCreate() {
        super.onCreate()
        MiAlumnoApp.database = Room.databaseBuilder(this, DBAlumnos::class.java, "DBAlumnos").build()
    }
}