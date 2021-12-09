package com.mrhi2021.kotlinbnvfragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class Tab2Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab2, container, false)
    }

    lateinit var recyclerview:RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview= view.findViewById(R.id.recycler)
    }
    
    //프레그먼트 객체가 처음 만들어질때 딱 1번 실행되는 메소드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        //네이버 검색 API 사용
        searchNaverShopping("패딩")
    }

    fun searchNaverShopping(query:String){

        //레트로핏 네트워크 작업 시작!
        val retrofit:Retrofit= Retrofit.Builder().baseUrl("https://openapi.naver.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()

        retrofit.create(NaverRetrofitService::class.java)
            .searchData(query).enqueue(object : Callback<NaverShoppingApiResponse>{
                override fun onResponse(
                    call: Call<NaverShoppingApiResponse>,
                    response: Response<NaverShoppingApiResponse>
                ) {

                    val shoppingApiResponse:NaverShoppingApiResponse?= response.body()

                    //아답터 만들어서 리사이클러뷰에 보이기
                    shoppingApiResponse?.let { recyclerview.adapter= MyAdapter(activity as Context, it.items) }

                }

                override fun onFailure(call: Call<NaverShoppingApiResponse>, t: Throwable) {
                    Toast.makeText(activity, "서버오류", Toast.LENGTH_SHORT).show()
                }
            })





    }
}