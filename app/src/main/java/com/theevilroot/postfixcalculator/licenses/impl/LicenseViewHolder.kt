package com.theevilroot.postfixcalculator.licenses.impl

import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.theevilroot.postfixcalculator.licenses.LicenseRowView
import com.theevilroot.postfixcalculator.links.LinkRowView
import kotlinx.android.synthetic.main.license_row_layout.view.*
import kotlinx.android.synthetic.main.link_row_layout.view.*

class LicenseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), LicenseRowView {

    override fun setProduct(product: String) {
        itemView.run {
            productView.text = product
        }
    }

    override fun setText(@StringRes textRes: Int) {
        itemView.run {
            textView.setText(textRes)
        }
    }

}