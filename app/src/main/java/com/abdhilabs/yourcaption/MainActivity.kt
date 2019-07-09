package com.abdhilabs.yourcaption

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.abdhilabs.yourcaption.adapter.MyViewPagerAdapter
import com.abdhilabs.yourcaption.ui.AddFragment
import com.abdhilabs.yourcaption.ui.SavedFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Include for make Tab Pager Slide
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AddFragment(), "Add")
        adapter.addFragment(SavedFragment(), "Saved")
        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)

    }
}
