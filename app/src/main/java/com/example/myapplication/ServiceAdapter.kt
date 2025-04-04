package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ServiceAdapter(
    private val services: List<Service>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.service_name)
        val available: TextView = view.findViewById(R.id.service_available)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_service, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = services[position]
        holder.name.text = service.name
        holder.available.text = "${service.available} numbers available"
        holder.itemView.setOnClickListener { onClick(service.id) }
    }

    override fun getItemCount() = services.size
}

class CountryAdapter(private val countries: List<Country>)
    : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.country_name)
        val available: TextView = view.findViewById(R.id.country_available)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.name.text = country.name
        holder.available.text = "${country.available} numbers available"
    }

    override fun getItemCount() = countries.size
}