package com.example.requestpermission

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        go_to_third_permission.setOnClickListener { startActivity(Intent(this, ThirdActivity::class.java)) }
        go_to_fourth_permission.setOnClickListener { startActivity(Intent(this, FourthActivity::class.java)) }
    }

}
