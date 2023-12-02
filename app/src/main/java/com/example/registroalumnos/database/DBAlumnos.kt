package com.example.registroalumnos.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Alumno::class), version = 1)
abstract class DBAlumnos: RoomDatabase() {
    abstract fun alumnoDao(): AlumnoDao
}