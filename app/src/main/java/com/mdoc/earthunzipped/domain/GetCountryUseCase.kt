package com.mdoc.earthunzipped.domain

class GetCountryUseCase(private val countryClient: CountryClient) {

    suspend fun execute(code: String): Country? {
        return countryClient.getSingleCountry(code)
    }
}