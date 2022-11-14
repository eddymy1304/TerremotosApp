package com.example.terremotosapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.terremotosapp.DetailActivity
import com.example.terremotosapp.R
import com.example.terremotosapp.databinding.ActivityMainBinding
import com.example.terremotosapp.model.ApiResponseStatus
import com.example.terremotosapp.model.Terremoto
import com.example.terremotosapp.view.adapter.TerremotoAdapter
import com.example.terremotosapp.viewmodel.MainViewModel
import com.example.terremotosapp.viewmodel.MainViewModelFactory
import java.util.zip.ZipEntry

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ordenarPor = obtenerOrdenarPor()

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(application, ordenarPor)
        )[MainViewModel::class.java]

        binding.recyclerTerremoto.layoutManager = LinearLayoutManager(this)

        viewModel.terremotoList.observe(this) { terremoto ->
            val adapter = TerremotoAdapter(this, viewModel.terremotoList.value!!)
            binding.recyclerTerremoto.adapter = adapter
            comprobarEmptyView(terremoto, adapter)
        }

        viewModel.status.observe(this) {
            when (it!!) {
                ApiResponseStatus.DONE -> binding.progressBar.visibility = View.GONE
                ApiResponseStatus.LOADING -> binding.progressBar.visibility = View.VISIBLE
                ApiResponseStatus.ERROR -> binding.progressBar.visibility = View.GONE
            }
        }

    }

    private fun obtenerOrdenarPor(): Boolean {
        val preferencias = getSharedPreferences("preferencias",MODE_PRIVATE)
        return preferencias.getBoolean("ordenar_por", false)
    }

    // IMPLEMENTANDO MENU
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_magnitud -> {
                viewModel.recargarTerremotoListDesdeBD(true, application)
                guardarOrdenarPor(true)
            }
            R.id.sort_time -> {
                viewModel.recargarTerremotoListDesdeBD(false, application)
                guardarOrdenarPor(false)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // PREFERENCIAS SE USA EN LA ACTIVITY PORQUE SE NECESITA UN CONTEXTO
    fun guardarOrdenarPor(ordenarPorMagnitud: Boolean) {
        val preferencias = getSharedPreferences("preferencias", MODE_PRIVATE)
        val editor = preferencias.edit()
        editor.putBoolean("ordenar_por", ordenarPorMagnitud)
        editor.apply()
    }

    private fun comprobarEmptyView(terremoto: MutableList<Terremoto>?, adapter: TerremotoAdapter) {
        if (terremoto!!.size > 0 && terremoto!!.isNotEmpty()) {
            binding.emptyView.visibility = View.GONE
            adapter.onItemClick = { item ->
                iniciarDetailActivity(item)
            }
        } else {
            binding.emptyView.visibility = View.VISIBLE
        }
    }

    private fun iniciarDetailActivity(terremoto: Terremoto) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.MAGNITUD, terremoto.magnitud)
        intent.putExtra(DetailActivity.LATITUD, terremoto.latitud)
        intent.putExtra(DetailActivity.LONGITUD, terremoto.longitud)
        intent.putExtra(DetailActivity.LUGAR, terremoto.lugar)
        intent.putExtra(DetailActivity.HORA, terremoto.hora)
        startActivity(intent)
    }
}