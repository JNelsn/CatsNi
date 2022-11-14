package com.example.catsni

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.catsni.databinding.ActivityMainBinding
import com.example.catsni.rest.RetrofitService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        backgroundAnimation()


        binding.floatingActionButton.setOnClickListener {

            binding.floatingActionButton.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.start()

            makeApiRequest()

        }

    }

    private fun backgroundAnimation() {
        val animationDrawable: AnimationDrawable = binding.rlLayout.background as AnimationDrawable

        animationDrawable.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(1000)
            start()
        }
    }

    private fun makeApiRequest() {


        CoroutineScope(Dispatchers.IO).launch {
            val response = try {

                RetrofitService.api.getRandomCat()

            } catch (e: IOException) {
                Log.e(RetrofitService.TAG, e.message.toString())
                return@launch
            } catch (e: HttpException) {
                Log.e(RetrofitService.TAG, "HttpException, unexpected response.")
                return@launch
            }

            if (response.isSuccessful) {
                Log.i("Sucesso!!", "dados retornados da api")
                withContext(Dispatchers.Main) {

                    Picasso.get().load(response.body()?.get(0)?.url).into(binding.ivRandomCat)


                }


            } else {
                Log.e(RetrofitService.TAG, "Ops, something went wrong here...")
            }


        }


    }


}