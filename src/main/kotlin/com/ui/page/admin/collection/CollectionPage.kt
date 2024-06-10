package com.ui.page.admin.collection

import com.data.models.Collection
import com.plugins.paths.RoutePath
import com.ui.components.*
import kotlinx.html.HTML

fun HTML.collectionPage(collections: MutableList<Collection>) {
    TailwindLayout {
        Container {
            Column {
                CollectionTable(collections)

                LinkButton(
                    label = "Create",
                    link = RoutePath.View.COLLECTION_CREATE_PAGE,
                    style = buildString {
                        appendTabbed("mt-4")
                        appendTabbed("w-28")
                        appendTabbed("self-center")
                        appendTabbed("text-center")
                    }
                )
            }
        }
    }
}