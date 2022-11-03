package com.example.terremotosapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(application)
        )[MainViewModel::class.java]

        binding.recyclerTerremoto.layoutManager = LinearLayoutManager(this)

        viewModel.terremotoList.observe(this) { terremoto ->
            val adapter = TerremotoAdapter(this, viewModel.terremotoList.value!!)
            comprobarEmptyView(terremoto, adapter)
            binding.recyclerTerremoto.adapter = adapter
        }

        viewModel.status.observe(this){
            when(it!!){
                ApiResponseStatus.DONE -> binding.progressBar.visibility = View.GONE
                ApiResponseStatus.LOADING -> binding.progressBar.visibility = View.VISIBLE
                ApiResponseStatus.ERROR -> binding.progressBar.visibility = View.GONE
            }
        }

    }

    private fun comprobarEmptyView(terremoto: MutableList<Terremoto>?, adapter: TerremotoAdapter) {
        if (terremoto!!.size > 0 && terremoto!!.isNotEmpty()) {
            binding.emptyView.visibility = View.GONE
            adapter.onItemClick = { item ->
                Toast.makeText(this, item.lugar, Toast.LENGTH_SHORT).show()
            }
        } else {
            binding.emptyView.visibility = View.VISIBLE
        }
    }
}