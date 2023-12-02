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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteActivity : ActivityWithMenus() {

    lateinit var binding: ActivityDeleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_delete)

        binding.eliminar.setOnClickListener {
            val nombre = binding.alumnoBorrado.text.toString()

            deleteAlumno(nombre)
            clearFocus()
            hideKeyboard()
        }
    }

    fun clearFocus(){
        binding.alumnoBorrado.setText("")
    }

    //Oculta el teclado cuando terminamos de escribir en el cuadro de texto
    fun Context.hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.alumnoBorrado.windowToken, 0)
    }

    fun deleteAlumno(nombreAlumno: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val alumnos = MiAlumnoApp.database.alumnoDao().obtenerAlumnoPorNombre(nombreAlumno)

            if (alumnos.isNotEmpty()) {
                val alumnoEliminar = alumnos[0]
                MiAlumnoApp.database.alumnoDao().deleteAlumno(alumnoEliminar)
            }
        }
    }
}