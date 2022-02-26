package com.luisfelipe.extensions

import androidx.annotation.NavigationRes
import androidx.navigation.NavController

fun NavController.setStartDestinationNav(@NavigationRes navGraph: Int, startDestination: Int) {
    val graph = this.navInflater.inflate(navGraph)
    graph.setStartDestination(startDestination)
    this.graph = graph
}