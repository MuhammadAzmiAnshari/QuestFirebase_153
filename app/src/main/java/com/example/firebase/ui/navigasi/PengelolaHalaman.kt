package com.example.firebase.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebase.ui.view.DetailScreen
import com.example.firebase.ui.view.InsertMhsView
import com.example.firebase.ui.view.UpdateScreen
import com.example.firebaseandroid.ui.view.HomeScreen

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    )  {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsert.route)
                },
            )
        }
        composable(DestinasiInsert.route) {
            InsertMhsView(
                onBack = {navController.popBackStack()},
                onNavigate = {navController.navigate(DestinasiHome.route)}
            )
        }
        composable(DestinasiDetail.route) {
            DetailScreen(
                onBack = {navController.popBackStack()},
                onNavigate = {navController.navigate(DestinasiHome.route)}
            )
        }
        composable(DestinasiUpdate.route) {
            UpdateScreen(
                onBack = {navController.popBackStack()},
                onNavigate = {navController.navigate(DestinasiHome.route)}
            )
        }
    }
}