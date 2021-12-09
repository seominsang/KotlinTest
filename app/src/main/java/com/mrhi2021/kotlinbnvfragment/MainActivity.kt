package com.mrhi2021.kotlinbnvfragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    val bnv:BottomNavigationView by lazy { findViewById(R.id.bnv) }

    var fragments: ArrayList<Fragment> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Fragment 동적 추가
        fragments.add( Tab1Fragment() )
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragments.get(0)).commit()

        //축약형으로 리스너 처리
        bnv.setOnItemSelectedListener {

            val tran:FragmentTransaction= supportFragmentManager.beginTransaction()
            fragments.forEach { tran.hide(it) }

            when(it.itemId){
                R.id.bnv_tab1-> tran.show(fragments.get(0))
                R.id.bnv_tab2-> {
                    if(fragments.size<2){
                        fragments.add(Tab2Fragment())
                        tran.add(R.id.fragment_container, fragments.get(1))
                    }
                    tran.show(fragments.get(1))
                }
                R.id.bnv_tab3->{
                    if(fragments.size<3){
                        fragments.add(Tab3Fragment())
                        tran.add(R.id.fragment_container, fragments.get(2))
                    }
                    tran.show(fragments.get(2))
                }
            }

            tran.commit()

            //return true //축약형을 사용할때는 return키워드 생략
            true
        }
    }
}

fun aaa(n:Int):Boolean{
    return true
}