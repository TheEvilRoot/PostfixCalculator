package com.theevilroot.postfixcalculator.links.impl

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.theevilroot.postfixcalculator.links.LinkRowView
import kotlinx.android.synthetic.main.link_row_layout.view.*

class LinkViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), LinkRowView {

    override fun setTitle(title: String) {
        itemView.run {
            titleView.text = title
        }
    }

    override fun setUrl(url: String) {
        itemView.run {
            urlView.text = url
        }
    }

    override fun setImage(res: Int) {
        itemView.run {
            imageView.setImageResource(res)
        }
    }

}