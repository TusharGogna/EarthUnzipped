package com.mdoc.earthunzipped.domain


interface CountryClient {

    suspend fun getCountries(): List<CountryZipped>
    suspend fun getSingleCountry(code: String): Country?
}