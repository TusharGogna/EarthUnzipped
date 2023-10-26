package com.mdoc.earthunzipped.domain

import com.mdoc.CountriesQuery

data class Country(
    val name: String,
    val code: String,
    val emoji: String,
    val language: List<String>,
    val capital: String,
    val state: List<String>
)
