package com.example.firebase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase.model.Mahasiswa
import com.example.firebase.repository.MahasiswaRepository
import com.example.firebase.ui.navigasi.DestinasiDetail
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: MahasiswaRepository,
    private val mahasiswaRepository: MahasiswaRepository
) : ViewModel() {
    private val nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])

    var detailUiState: DetailUiState by mutableStateOf(DetailUiState())
        private set

    init {
        getMahasiswaByNim()
    }

    private fun getMahasiswaByNim() {
        viewModelScope.launch {
            detailUiState = DetailUiState(isLoading = true)
            try {
                val result = mahasiswaRepository.getMahasiswaByNim(nim)
                detailUiState = DetailUiState(
                    detailUiEvent = result.toDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiState = DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: InsertUiEvent = InsertUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertUiEvent()
}

fun Mahasiswa.toDetailUiEvent(): InsertUiEvent{
    return InsertUiEvent(
        nim = nim,
        nama = nama,
        jenisKelamin = jenisKelamin,
        alamat = alamat,
        kelas = kelas,
        angkatan = angkatan,
        dosen = dosen,
        skripsi = skripsi
    )
}