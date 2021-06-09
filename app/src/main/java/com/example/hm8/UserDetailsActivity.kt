package com.example.hm8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.hm8.RetrofitClient
import com.example.hm8.ReqresData
import com.example.hm8.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_details)

        val photo = findViewById<ImageView>(R.id.userDetailsImage)
        val name = findViewById<TextView>(R.id.userDetailsName)
        val surname = findViewById<TextView>(R.id.userDetailsLastName)
        val text = findViewById<TextView>(R.id.userName)

        val userId = intent.getLongExtra("id",1)

        RetrofitClient.reqResApi.getUserInfo(userId).enqueue(object : Callback<ReqresData<User>> {
            override fun onResponse(
                call: Call<ReqresData<User>>,
                response: Response<ReqresData<User>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    Glide.with(this@UserDetailsActivity)
                        .load(response.body()?.data?.avatar)
                        .centerCrop()
                        .into(photo)
                    name.text = response.body()?.data?.firstName
                    surname.text = response.body()?.data?.lastName
                    text.text = response.body()?.support?.text
                }
            }

            override fun onFailure(call: Call<ReqresData<User>>, t: Throwable) {

            }

        })
    }

}