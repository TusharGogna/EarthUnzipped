package com.mdoc.earthunzipped.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdoc.earthunzipped.domain.Country
import com.mdoc.earthunzipped.domain.CountryZipped
import com.mdoc.earthunzipped.domain.GetCountryUseCase
import com.mdoc.earthunzipped.domain.GetZippedCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countryUseCase: GetCountryUseCase,
    private val countriesUseCase: GetZippedCountriesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            _state.update {
                it.copy(
                    countries = countriesUseCase.execute(),
                    isLoading = false
                )
            }
        }
    }

    fun openCountry(code: String) {
        viewModelScope.launch {
            _state.update { it.copy(selectedCountry = countryUseCase.execute(code)) }
        }
    }

    fun closeCountry() {
        _state.update { it.copy(selectedCountry = null) }
    }

    data class CountriesState(
        val countries: List<CountryZipped> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: Country? = null
    )
}