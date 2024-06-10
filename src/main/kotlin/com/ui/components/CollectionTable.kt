package com.ui.components

import com.data.models.Collection
import kotlinx.html.FlowContent

fun FlowContent.CollectionTable(collections: MutableList<Collection>) {
    Column {
        Text(
            text = "Collection",
            style = buildString {
                appendTabbed("text-xl mt-4")
            }
        )

        Table(
            tableHeaders = buildList {
                add(TableHeader(label = "Image"))
                add(TableHeader(label = "Title"))
                add(TableHeader(label = "Products"))
            },
            tableItems = buildList {
                collections.onEach { collection ->
                    add(
                        buildList {
                            add(TableItem(label = collection.imageUrl))
                            add(TableItem(label = collection.title))
                            add(TableItem(label = "0"))
                        }
                    )
                }
            },
            style = buildString {
                appendTabbed("mt-4")
            }
        )
    }

}
