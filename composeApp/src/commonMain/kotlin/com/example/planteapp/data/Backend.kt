package com.example.planteapp.data

import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod



class Backend {
    val testUrl: String = "http://10.176.210.220:5000/plantreadings"

    // Create a client
    private val client: HttpClient = HttpClient()

    suspend fun backendTest(): String {
        try {
            val response: HttpResponse = client.request(testUrl) {
                method = HttpMethod.Get
            }
            return response.bodyAsText()
        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
    }
}