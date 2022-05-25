package com.genta.kisahnabi.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.genta.kisahnabi.R
import com.genta.kisahnabi.data.KisahResponse
import com.genta.kisahnabi.databinding.ActivityDetailBinding
import com.genta.kisahnabi.ui.MainViewModel

class DetailActivity : AppCompatActivity() {

    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<KisahResponse>(EXTRA_DATA)
        data?.let {
            binding.apply {
                Glide.with(this@DetailActivity).load(data.imageUrl).into(detailImage)
                detailNama.text = data.name
                val usia = "Usia: " + data.usia + "Tahun"
                detailTahun.text = usia
                detailTempat.text = data.tmp
                detailDesk.text = data.description
            }
        }

    }

    companion object{
        const val EXTRA_DATA = "extra data"
    }
}