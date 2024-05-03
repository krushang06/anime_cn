package com.example.anime_cn

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anime_cn.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter: RvAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var animeviewModel: AnimeViewModel
    private lateinit var image_view_product: ImageView

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar1
        binding.progressBar1.visibility = View.VISIBLE

        recyclerView = binding.RVviewid
        animeviewModel = ViewModelProvider(this)[AnimeViewModel::class.java]
        recyclerView.layoutManager = LinearLayoutManager(this)

        rvAdapter = RvAdapter(arrayListOf())
        recyclerView.adapter = rvAdapter

        //        binding.buttonclick.setOnClickListener {
        animeviewModel.fetchAnime()
//        Toast
        //        }

        // for scrolling show in recyclerview in count
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        Log.e("addOnScrollListener", "Scroll end reached")
                        animeviewModel.getNextPage()
                    }
                }
            })
        addObservers()
    }

    override fun onResume() {
        super.onResume()
        showInitialPopup()

    }

    private fun addObservers() {
        lifecycleScope.launch {
            animeviewModel.animestion.observe(this@MainActivity) { animestion ->
                animestion?.let {
                    rvAdapter.setData(animestion)
                }


            }
        }

        animeviewModel.progressBar_.observe(this@MainActivity){isLoading ->
            isLoading?.let {
                binding.progressBar1.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    private fun showInitialPopup() {
        val dialogMessage = "Welcome to the Anime App!"
        val alertDialogBuilder = AlertDialog.Builder(this)

        alertDialogBuilder.setTitle("Hello!")
        alertDialogBuilder.setMessage(dialogMessage)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
//            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("close") { dialog, _ ->
//            dialog.cancel()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}

