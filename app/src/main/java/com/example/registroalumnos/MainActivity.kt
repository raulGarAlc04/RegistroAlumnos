package com.example.registroalumnos

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.example.registroalumnos.database.Alumno
import com.example.registroalumnos.database.MiAlumnoApp
import com.example.registroalumnos.databinding.ActivityDeleteBinding
import com.example.registroalumnos.databinding.ActivityMainBinding
import com.google.android.material.internal.ViewUtils.hideKeyboard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ActivityWithMenus() {

    lateinit var binding: ActivityMainBinding
    lateinit var elementos: MutableList<Alumno>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_delete)

        elementos = ArrayList()

        binding.anadir.setOnClickListener {
            addAlumno(Alumno(
                nombre = binding.nombre.text.toString(),
                apellidos = binding.apellido.text.toString(),
                curso = binding.curso.text.toString()
            ))
        }
    }

    fun clearFocus(){
        binding.nombre.setText("")
        binding.apellido.setText("")
        binding.curso.setText("")
    }

    //Oculta el teclado cuando terminamos de escribir en el cuadro de texto
    fun Context.hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.curso.windowToken, 0)
    }

    fun addAlumno(elemento: Alumno) {
        CoroutineScope(Dispatchers.IO).launch {
            MiAlumnoApp.database.alumnoDao().addAlumno(elemento)
            val alumnos = MiAlumnoApp.database.alumnoDao().obtenerAlumnoPorNombre(elemento.nombre)
            runOnUiThread {
                elementos.clear()
                elementos.addAll(alumnos)
                clearFocus()
                hideKeyboard()
            }
        }
    }
}
