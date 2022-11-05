package com.example.terremotosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.terremotosapp.databinding.ActivityDetailBinding
import com.example.terremotosapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val MAGNITUD = "magnitud"
        const val LATITUD = "latitud"
        const val LONGITUD = "longitud"
        const val LUGAR = "lugar"
        const val HORA = "hora"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textMagnitud.text = intent.extras!!.getDouble(MAGNITUD).toString()
        binding.textLatitud.text = intent.extras!!.getDouble(LATITUD).toString()
        binding.textLongitud.text = intent.extras!!.getDouble(LONGITUD).toString()
        binding.textLugar.text = intent.extras!!.getString(LUGAR)
        val hora_sin_formato = intent.extras!!.getLong(HORA)
        binding.textHora.text = formatearHora(hora_sin_formato)
    }

    private fun formatearHora(horaSinFormato: Long): String {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Date(horaSinFormato)
        return simpleDateFormat.format(date)
    }
}