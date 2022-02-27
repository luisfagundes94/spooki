package com.luisfelipe.extensions

fun List<String>.convertListToString() = joinToString(", ")

fun String.Companion.empty() = ""
