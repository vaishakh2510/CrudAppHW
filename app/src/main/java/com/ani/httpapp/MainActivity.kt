package com.ani.httpapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private val mainScope: CoroutineScope = CoroutineScope(Job() + Dispatchers.Main)
    private lateinit var crud: CRUDApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        retrofit = RetrofitClient.create()
        crud = retrofit.create(CRUDApp::class.java)

        val etCnt = findViewById<EditText>(R.id.editTextText)

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            createApiCall(etCnt.text.toString().toInt())
            showApiCall()
        }

        showApiCall()
    }

    private fun showApiCall() {
        val txtId = findViewById<TextView>(R.id.txt)
        val txtCnt = findViewById<TextView>(R.id.textView)

        mainScope.launch {
            val response = crud.show()
            val app: App? = response.body()
            Log.i("@HttpApp", "App Id : ${app?.id} , Cnt : ${app?.cnt}")
            txtId.text = "${app?.id}"
            txtCnt.text = "${app?.cnt}"
        }
    }

    private fun createApiCall(cnt: Int) {
        mainScope.launch {
            val app = App(
                id = "${System.currentTimeMillis()}",
                cnt = cnt
            )

            val response = crud.create(app)
            Log.i("@HttpApp", response.body() ?: "Problem in parsing body")
        }
    }
}