package com.example.registroalumnos

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.example.registroalumnos.database.Alumno
import com.example.registroalumnos.database.MiAlumnoApp
import com.example.registroalumnos.databinding.ActivityDeleteBinding
import com.example.registroalumnos.databinding.ActivityUpdateBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateActivity : ActivityWithMenus() {

    lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_delete)

        binding.actualizar.setOnClickListener {
            val nombre = binding.nombreAlumno.text.toString()
            val nuevoCurso = binding.nuevoCurso.text.toString()

            updateAlumno(nombre,nuevoCurso)
            clearFocus()
            hideKeyboard()
        }
    }


    fun clearFocus(){
        binding.nombreAlumno.setText("")
        binding.nuevoCurso.setText("")
    }

    //Oculta el teclado cuando terminamos de escribir en el cuadro de texto
    fun Context.hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.nombreAlumno.windowToken, 0)
    }

    fun updateAlumno(nombreAlumno: String, nuevoCurso: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val alumnos = MiAlumnoApp.database.alumnoDao().obtenerAlumnoPorNombre(nombreAlumno)

            if (alumnos.isNotEmpty()) {
                val alumno = alumnos[0]
                alumno.curso = nuevoCurso

                MiAlumnoApp.database.alumnoDao().updateAlumno(alumno)
            }
        }
    }
}