package com.example.registroalumnos.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AlumnoDao {
    @Query("SELECT * FROM alumnos WHERE nombre like :nombre")
    fun obtenerAlumnoPorNombre(nombre: String): MutableList<Alumno>

    @Insert
    fun addAlumno(elemento: Alumno)

    @Update
    fun updateAlumno(alumno: Alumno)

    @Delete
    fun deleteAlumno(alumno: Alumno)
}