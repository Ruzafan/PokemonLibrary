package com.example.pokemonlibrary.client

class ErrorResponse(val code: Int, message: String) : Throwable("($code) $message")
