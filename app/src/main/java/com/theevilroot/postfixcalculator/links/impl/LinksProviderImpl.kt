package com.theevilroot.postfixcalculator.links.impl

import com.theevilroot.postfixcalculator.R
import com.theevilroot.postfixcalculator.data.Link
import com.theevilroot.postfixcalculator.links.LinksProvider

class LinksProviderImpl (

): LinksProvider {

    private val links: List<Link> = listOf(
        Link("GitHub", "https://github.com/TheEvilRoot/", R.drawable.ic_github),
        Link("Web", "http://52.48.142.75/", R.drawable.ic_web),
        Link("VK", "https://vk.com/pelmen_it", R.drawable.vk_circle)
    )

    override fun getList(): List<Link> =
            links

    override fun count(): Int =
            links.count()

    override fun get(pos: Int): Link =
            links[pos]

}