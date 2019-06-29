package com.theevilroot.postfixcalculator.links

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.theevilroot.postfixcalculator.R
import com.theevilroot.postfixcalculator.internal.openInBrowser
import com.theevilroot.postfixcalculator.links.impl.LinkViewHolder

class LinksAdapter(
    private val provider: LinksProvider
): RecyclerView.Adapter<LinkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder =
        LinkViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.link_row_layout, parent, false))

    override fun getItemCount(): Int =
        provider.count()

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        provider[position].run {
            holder.setTitle(title)
            holder.setUrl(url)
            holder.setImage(image)
            holder.itemView.setOnClickListener { openInBrowser(it.context, url) }
        }
    }

}