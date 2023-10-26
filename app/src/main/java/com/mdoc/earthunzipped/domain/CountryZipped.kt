package com.mdoc.earthunzipped.domain

import com.mdoc.CountriesQuery

data class CountryZipped(
    val name: String,
    val code: String,
    val emoji: String,
    val capital: String,
    val language: List<String>
)
