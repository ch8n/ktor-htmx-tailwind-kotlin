package com.ui.components

import kotlinx.html.*

fun HTML.TailwindLayout(body: BODY.() -> Unit) {
    head {
        script { src = "/static/tailwindcss-3.4.4.js" }
        script { src = "/static/htmx.min.js" }
    }

    body {
        body.invoke(this)
    }
}

fun StringBuilder.appendTabbed(string: String) {
    append(string)
    append(" ")
}

fun FlowContent.Text(
    text: String,
    style: String,
) {
    p(classes = style) {
        +text
    }
}

fun FlowContent.Column(
    style: String = "",
    content: FlowContent.() -> Unit,
) {
    div(classes = "flex flex-col $style") {
        content.invoke(this@Column)
    }
}

fun FlowContent.Container(
    style: String = "",
    content: FlowContent.() -> Unit
) {
    div(classes = "container mx-auto $style") {
        content.invoke(this@Container)
    }
}

fun FlowContent.InputField(
    fieldId: String = "",
    styles: String = "",
    label: String,
    placeholder: String,
    required: Boolean = false,
) {
    div(classes = styles) {
        Column {
            label(classes = buildString {
                appendTabbed("block")
                appendTabbed("mb-2")
                appendTabbed("text-sm")
                appendTabbed("font-medium")
                appendTabbed("text-gray-900")
            }) {
                +label
            }
            input(classes = buildString {
                appendTabbed("block")
                appendTabbed("w-full")
                appendTabbed("p-2.5")
                appendTabbed("border")
                appendTabbed("border-gray-600")
                appendTabbed("rounded-lg")
                appendTabbed("text-gray-900")
                appendTabbed("text-sm")
                appendTabbed("text-white")
                appendTabbed("placeholder-gray-400")
                appendTabbed("focus:ring-cyan-500")
                appendTabbed("focus:border-cyan-500 ")
                appendTabbed("bg-gray-700")
                appendTabbed("")

            }) {
                this.type = InputType.text
                this.id = fieldId
                this.name = fieldId
                this.placeholder = placeholder
                this.required = required
            }
        }
    }
}

fun FlowContent.Button(
    label: String,
    style: String = "",
    buttonType: ButtonType = ButtonType.button,
    attributes: Map<String, String> = emptyMap
) {
    button(classes = buildString {
        appendTabbed("text-white")
        appendTabbed("font-medium")
        appendTabbed("text-sm")

        appendTabbed("bg-blue-600")
        appendTabbed("rounded-lg")

        appendTabbed("hover:bg-blue-700")

        appendTabbed("focus:ring-4")
        appendTabbed("focus:outline-none")
        appendTabbed("focus:ring-blue-800")
        appendTabbed("px-5 py-2.5 me-2 mb-2")
        appendTabbed(style)
    }) {
        attributes.onEach { this.attributes[it.key] = it.value }
        type = buttonType
        +label
    }
}

fun FlowContent.LinkButton(
    link: String,
    label: String,
    style: String = "",
    attributes: Map<String, String> = emptyMap,
) {
    a(
        classes = buildString {
            appendTabbed("text-white")
            appendTabbed("font-medium")
            appendTabbed("text-sm")

            appendTabbed("bg-blue-600")
            appendTabbed("rounded-lg")

            appendTabbed("hover:bg-blue-700")

            appendTabbed("focus:ring-4")
            appendTabbed("focus:outline-none")
            appendTabbed("focus:ring-blue-800")
            appendTabbed("px-5 py-2.5 me-2 mb-2")
            appendTabbed(style)
        }
    ) {
        attributes.onEach { this.attributes[it.key] = it.value }
        href = link
        +label
    }
}


data class TableHeader(
    val label: String,
    val style: String = ""
)

data class TableItem(
    val label: String,
    val style: String = ""
)

fun FlowContent.Table(
    tableHeaders: List<TableHeader>,
    tableItems: List<List<TableItem>>,
    style: String = "",
) {
    div("relative overflow-x-auto $style") {
        table("w-full text-sm text-left rtl:text-right text-gray-400") {
            thead("text-xs uppercase bg-gray-700 text-gray-400") {
                tr {
                    tableHeaders.onEach { header ->
                        th(classes = "px-6 py-3 ${header.style}") {
                            scope = ThScope.col
                            +header.label
                        }
                    }
                }
            }
            tbody {
                tableItems.onEach { tableItem ->
                    tr("bg-white border-b dark:bg-gray-800 dark:border-gray-700") {
                        tableItem.onEachIndexed { index, item ->
                            if (index == 0) {
                                th(classes = "px-6 py-4 font-medium whitespace-nowrap text-white ${item.style}") {
                                    scope = ThScope.row
                                    +item.label
                                }
                            } else {
                                td("px-6 py-4 ${item.style}") { +item.label }
                            }
                        }
                    }
                }
            }
        }
    }
}

