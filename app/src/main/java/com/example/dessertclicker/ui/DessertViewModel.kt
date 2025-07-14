package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class DessertUIState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessert: Dessert = Datasource.dessertList.first()
)

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUIState())
    val uiState: StateFlow<DessertUIState> = _uiState

    private val desserts = Datasource.dessertList

    fun onDessertClicked() {
        _uiState.update { currentState ->
            val newDessertsSold = currentState.dessertsSold + 1
            val newRevenue = currentState.revenue + currentState.currentDessert.price
            val nextDessert = determineDessertToShow(desserts, newDessertsSold)

            currentState.copy(
                dessertsSold = newDessertsSold,
                revenue = newRevenue,
                currentDessert = nextDessert
            )
        }
    }

    private fun determineDessertToShow(desserts: List<Dessert>, dessertsSold: Int): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }
        return dessertToShow
    }
}
