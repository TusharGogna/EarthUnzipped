package com.mdoc.earthunzipped.domain

class GetZippedCountriesUseCase(private val countryClient: CountryClient) {

    suspend fun execute(): List<CountryZipped>{
        return countryClient.getCountries().sortedBy { it.name }
    }
}