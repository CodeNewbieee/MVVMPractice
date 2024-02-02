package com.example.livedata_viewmodel_example

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.livedata_viewmodel_example.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val myNumberViewModel by viewModels<MyNumberViewModel>()
    private val TAG = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            myNumberViewModel.currentValue.observe(this@MainActivity, Observer {
                tvNumber.text = DecimalFormat("#,###").format(it.toString().toInt())
                Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            })
            //리스너 연결
            btnPlus.setOnClickListener(this@MainActivity)
            btnMinus.setOnClickListener(this@MainActivity)
        }
    }

    //클릭
    override fun onClick(v: View?) {
        val userInput = binding.etUserInput.text.toString().toInt()

        //뷰모델에 라이브데이터 값을 변경하는 메소드 실행
        when(v) {
            binding.btnPlus -> myNumberViewModel.updateValue(0,userInput)
            binding.btnMinus -> myNumberViewModel.updateValue(1,userInput)
        }
    }
}