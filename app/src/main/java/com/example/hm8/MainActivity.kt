package com.example.hm8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hm8.UserAdapter
import com.example.hm8.RetrofitClient
import com.example.hm8.ReqresData
import com.example.hm8.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() , UserAdapter.OnItemClickListener {
    var dataset : List<User>? = listOf()
    lateinit var recyclerView : RecyclerView
    var adapter = UserAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler)

        RetrofitClient.reqResApi.getUsers(2).enqueue(object : Callback<ReqresData<List<User>>> {
            override fun onResponse(
                call: Call<ReqresData<List<User>>>,
                response: Response<ReqresData<List<User>>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    dataset = response.body()?.data
                    adapter.setData(dataset!!)
                    response.body()!!.data?.forEach { i -> Log.d("Users", i.toString()) }
                }
            }

            override fun onFailure(call: Call<ReqresData<List<User>>>, t: Throwable) {

            }

        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onClick(id: Long) {
        val intent = Intent(this,UserDetailsActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }

}