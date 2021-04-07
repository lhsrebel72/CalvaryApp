package com.raywenderlich.favoritemovies

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.calvaryapp.Service
import com.example.calvaryapp.ui.home.ServiceTimesFragment


// 1
class ServicesPagerAdapter(fragment: Fragment, private val movies: ArrayList<Service>) :
    FragmentStateAdapter(fragment) {

    // 2
    override fun createFragment(position: Int): Fragment {
        return ServiceTimesFragment.newInstance(movies[position])
    }

    // 3
    override fun getItemCount(): Int {
        return movies.size
    }
}