package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentCountriesBinding
import com.example.myapplication.databinding.FragmentServicesBinding

class ServicesFragment : Fragment() {
    private lateinit var viewModel: ServicesViewModel
    private lateinit var binding: FragmentServicesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServicesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ServicesViewModel::class.java)

        viewModel.services.observe(viewLifecycleOwner) { services ->
            val popularServices = services.filter { it.available > 10000 }
            val otherServices = services.filter { it.available <= 10000 }


            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = ServiceAdapter(popularServices + otherServices) { serviceId ->
                val action = servicesFragmentDirections.actionServicesToCountries(serviceId)
                findNavController().navigate(action)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }

        viewModel.loadServices()
    }
}

class CountriesFragment : Fragment() {
    private lateinit var viewModel: CountriesViewModel
    private lateinit var binding: FragmentCountriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)
        val serviceId = arguments?.getString("serviceId") ?: ""

        viewModel.countries.observe(viewLifecycleOwner) { countries ->
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = CountryAdapter(countries)
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }

        viewModel.loadCountries(serviceId)
    }
}