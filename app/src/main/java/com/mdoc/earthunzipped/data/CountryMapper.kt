package com.mdoc.earthunzipped.data

import com.mdoc.CountriesQuery
import com.mdoc.CountryQuery
import com.mdoc.earthunzipped.domain.Country
import com.mdoc.earthunzipped.domain.CountryZipped

fun CountriesQuery.Country.toZippedCountries(): CountryZipped {
    return CountryZipped(
        name = name,
        code = code,
        emoji = emoji,
        language = languages.mapNotNull { it.name },
        capital = capital ?: "N/A"
    )
}

fun CountryQuery.Country.toReadableCountry(): Country {
    return Country(
        name = name,
        code = code,
        emoji = emoji,
        language = languages.mapNotNull { it.name },
        state = states.map { it.name },
        capital = capital ?: "N/A"
    )
}