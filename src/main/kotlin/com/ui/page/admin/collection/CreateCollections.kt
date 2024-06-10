package com.ui.page.admin.collection

import com.plugins.paths.RoutePath
import com.ui.components.*
import kotlinx.html.*


fun HTML.createCollectionPage() {
    TailwindLayout {
        Container {
            Column {

                Text(
                    text = "Create Collection",
                    style = buildString {
                        appendTabbed("text-xl")
                    }
                )

                form {

                    mapOf(
                        "hx-trigger" to "submit",
                        "hx-confirm" to "Create collection?",
                        "hx-post" to RoutePath.Data.COLLECTION_CREATE,
                    ).onEach {
                        attributes[it.key] = it.value
                    }

                    InputField(
                        fieldId = "title",
                        label = "title",
                        placeholder = "Enter Title",
                        required = true,
                        styles = buildString {
                            appendTabbed("mt-2")
                        }
                    )

                    InputField(
                        fieldId = "description",
                        label = "description",
                        placeholder = "Enter description",
                        required = true,
                        styles = buildString {
                            appendTabbed("mt-2")
                        }
                    )

                    Button(
                        label = "Create Collection",
                        buttonType = ButtonType.submit,
                        style = buildString {
                            appendTabbed("mt-2")
                        },
                    )
                }
            }
        }
    }
}



