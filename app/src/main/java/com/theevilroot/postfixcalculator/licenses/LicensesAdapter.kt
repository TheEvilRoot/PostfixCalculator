package com.theevilroot.postfixcalculator.licenses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.theevilroot.postfixcalculator.R
import com.theevilroot.postfixcalculator.licenses.impl.LicenseViewHolder

class LicensesAdapter (
    private val provider: LicensesProvider
): RecyclerView.Adapter<LicenseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicenseViewHolder =
        LicenseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.license_row_layout, parent, false))

    override fun getItemCount(): Int =
        provider.count()

    override fun onBindViewHolder(holder: LicenseViewHolder, position: Int) {
        provider[position].run {
            holder.setProduct(product)
            holder.setText(license)
        }
    }
}