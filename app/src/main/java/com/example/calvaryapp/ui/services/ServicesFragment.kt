package com.example.calvaryapp.ui.services

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.calvaryapp.R
import com.example.calvaryapp.Service
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ServicesFragment : Fragment() {


    private lateinit var servicesViewModel: ServicesViewModel
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: ServicesPagerAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        servicesViewModel =
                ViewModelProvider(this).get(ServicesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_services, container, false)
        val textView: TextView = root.findViewById(R.id.text_services)
        val viewPager: ViewPager2 = root.findViewById(R.id.frameLayout)
        val tabLayout: TabLayout = root.findViewById(R.id.tabLayout)
        servicesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val db = Firebase.firestore
        val serviceTimes = arrayListOf<Service>()
        db.collection("Services")
            .get()
            .addOnSuccessListener { result ->
                Log.d("TAG", "SUCCESS")
                for (document in result) {
                    serviceTimes.add(document.toObject(Service::class.java))
                }
                pagerAdapter = ServicesPagerAdapter(this, serviceTimes)
                viewPager.adapter = pagerAdapter
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = serviceTimes.get(position).name
                }.attach()
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "FAIL")
            }
        return root
    }
}