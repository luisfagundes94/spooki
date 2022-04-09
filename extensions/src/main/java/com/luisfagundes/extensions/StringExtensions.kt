package com.luisfagundes.extensions

private const val EMPTY = ""

fun List<String>.convertListToString() = joinToString(", ")

fun String.Companion.empty() = EMPTY

fun String.formatDate() = split("-").reversed().joinToString("/")