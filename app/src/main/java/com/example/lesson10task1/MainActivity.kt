package com.example.lesson10task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.lesson10task1.adapter.ViewPagerAdapter
import com.example.lesson10task1.fragments.HelloFragment
import com.example.lesson10task1.fragments.SafeFragment
import com.example.lesson10task1.fragments.UseFragment
import com.example.lesson10task1.fragments.WelcomeFragment
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator

class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        viewPager = findViewById(R.id.view_pager)
        val indicator = findViewById<SpringDotsIndicator>(R.id.indicator)
        val skip_text = findViewById<TextView>(R.id.skip)
        val start_btn = findViewById<Button>(R.id.start_btn)
        var fragments = ArrayList<Fragment>()
        fragments = loadFragments()
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments)
        viewPager.adapter = viewPagerAdapter
        indicator.setViewPager(viewPager)

        skip_text.setOnClickListener {
            viewPager.setCurrentItem(fragments.size-1,true)
        }

        start_btn.setOnClickListener {
            start_btn.visibility = GONE
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WelcomeFragment())
                .commit()
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                if(position == fragments.size-1){
                    skip_text.visibility = INVISIBLE
                    start_btn.visibility = VISIBLE
                } else {
                    skip_text.visibility = VISIBLE
                    start_btn.visibility = INVISIBLE
                }
            }

        })
    }

    private fun loadFragments(): ArrayList<Fragment> {
        val fragments = ArrayList<Fragment>()

        fragments.add(HelloFragment())
        fragments.add(SafeFragment())
        fragments.add(UseFragment())
        return fragments
    }
}