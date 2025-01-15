package com.example.firebase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase.model.Mahasiswa
import com.example.firebase.repository.MahasiswaRepository
import com.example.firebase.ui.navigasi.DestinasiUpdate

import kotlinx.coroutines.launch

class UpdateViewModel(
    savedStateHandle: MahasiswaRepository,
    private val mhs: MahasiswaRepository
): ViewModel(){
    var updateUiState by mutableStateOf(InsertUiState())
        private set

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiUpdate.NIM])

    init {
        viewModelScope.launch {
            updateUiState = mhs.getMahasiswaByNim(_nim)
                .toUIStateMhs()
        }
    }

    fun updateInsertMhsState(insertUiEvent: MahasiswaEvent){
        updateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updateMhs(){
        viewModelScope.launch {
            try {
                mhs.updateMahasiswa(_nim, updateUiState.insertUiEvent.toMhs())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}


fun Mahasiswa.toUIStateMhs(): InsertUiState = InsertUiState(
    insertUiEvent = this.toDetailUiEvent(),
)