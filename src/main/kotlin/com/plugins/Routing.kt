package com.plugins

import com.data.di.Injector
import com.data.models.Collection
import com.plugins.paths.RoutePath
import com.ui.page.admin.collection.collectionPage
import com.ui.page.admin.collection.createCollectionPage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import java.util.UUID


fun Application.configureRouting() {
    routing {
        staticResources(RoutePath.Data.STATIC, "static")

        get(RoutePath.View.HOME) {
            getCollectionHtml()
        }

        get(RoutePath.View.COLLECTION_CREATE_PAGE) {
            createCollectionHtml()
        }

        post(RoutePath.Data.COLLECTION_CREATE) {
            val params = call.receiveParameters()
            println("ch8n\t" + params.entries())
            val title = params.get("title") ?: throw IllegalArgumentException()
            val description = params.get("description") ?: throw IllegalArgumentException()
            val newCollection = Collection(
                id = UUID.randomUUID().toString(),
                title = title,
                description = description,
                imageUrl = UUID.randomUUID().toString(),
            )
            val appDataBase = Injector.appDB
            appDataBase.insertCollection(newCollection)
            call.respond(HttpStatusCode.OK)
        }

    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.getCollectionHtml() {
    val appDataBase = Injector.appDB
    val collections = appDataBase.getCollections()
    call.respondHtml(HttpStatusCode.OK) {
        collectionPage(collections)
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.createCollectionHtml() {
    call.respondHtml(HttpStatusCode.OK) {
        createCollectionPage()
    }
}

