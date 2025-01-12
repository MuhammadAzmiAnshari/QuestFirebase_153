package com.example.firebase.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebase.ui.viewmodel.InsertViewModel
import com.example.firebase.ui.viewmodel.PenyediaViewModel

@Composable
fun InsertMhsView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel:InsertViewModel = viewModel(factory = PenyediaViewModel.Factory)
){}