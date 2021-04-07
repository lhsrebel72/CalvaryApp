package com.example.calvaryapp.ui.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.calvaryapp.R
import com.example.calvaryapp.Service

class ServiceTimesFragment : Fragment() {

    private lateinit var servicesViewModel: ServicesViewModel

    companion object {
        private val ARG_CAUGHT = "myFragment_caught"

        fun newInstance(service: Service): ServiceTimesFragment {
            val args: Bundle = Bundle();
            args.putStringArrayList("key",service.serviceTimes);
            val fragment = ServiceTimesFragment();
            fragment.arguments = args;
            return fragment;
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        servicesViewModel =
                ViewModelProvider(this).get(ServicesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_service_times, container, false)
        val textView: TextView = root.findViewById(R.id.text_service_times)
        val args = arguments;
        textView.text = args?.getStringArrayList("key")?.get(0);
        return root
    }
}