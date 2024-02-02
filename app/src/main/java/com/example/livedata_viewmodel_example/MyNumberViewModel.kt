package com.example.livedata_viewmodel_example

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


// 데이터의 변경
// 뷰모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있고
class MyNumberViewModel : ViewModel() {

    private val TAG = "로그"

    // 뮤터블 라이브 데이터 - 수정 가능
    // 라이브 데이터 - 수정 불가능 (읽기전용)

    // 내부에서 설정하는 자료형은 뮤터블로
    // 변경가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()
    // 변경되지 않는 데이터를 가져 올 때 이름을 (_)언더스코어 없이 설정
    // 공개적으로 가져오는 변수는 private 이 아닌 public으로 외부에서도 접근하도록 설정 (옵저버로 지켜보게끔)
    // 접근할 수 있지만(읽기 가능) 직접 값을 뷰모델을 통해서 가져올 수 있도록 함(쓰기 불가)
    val currentValue:LiveData<Int> get() =  _currentValue

    // 뷰모델 생성될 때 초기값 설정
    init {
        Log.d(TAG, "MyNumberViewModel:  생성자 호출")
        _currentValue.value = 0
    }

    fun updateValue(type : Int, input:Int) {
        when(type) {
            0 -> _currentValue.value = _currentValue.value?.plus(input)
            1 -> _currentValue.value = _currentValue.value?.minus(input)
        }
    }

}