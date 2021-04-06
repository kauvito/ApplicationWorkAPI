package com.kautec.applicationworkapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kautec.applicationworkapi.Model.Product
import com.kautec.applicationworkapi.api.MyRetrofit
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback as Callback

class MainActivity : AppCompatActivity() {

    lateinit var recyclerProducts : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerProducts = findViewById(R.id.recycle_products)

        //recycler view cria um layoutmanager
        recyclerProducts.layoutManager = LinearLayoutManager(this)

        getDate()
    }

    private fun getDate() {
        val call: Call<List<Product>> =
            MyRetrofit.instance?.productApi()?.getProductApi() as Call<List<Product>>

        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val adapter =
                    ProductAdapter(this@MainActivity, response.body()?.toList() as List<Product>)
                recyclerProducts.adapter = adapter
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}

