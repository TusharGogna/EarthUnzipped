package com.mdoc.earthunzipped.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mdoc.earthunzipped.domain.Country
import com.mdoc.earthunzipped.domain.CountryZipped
import com.mdoc.earthunzipped.ui.theme.PurpleGrey80

@Composable
fun CountriesScreen(
    state: CountriesViewModel.CountriesState,
    onSelectedCountry: (code: String) -> Unit,
    onDismissSelection: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries) { countries ->
                    SingleCountryItem(
                        country = countries,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectedCountry(countries.code) }
                            .padding(6.dp)
                    )
                }
            }

            if (state.selectedCountry != null) {
                DialogForCountry(
                    country = state.selectedCountry,
                    onDismiss = onDismissSelection,
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(5.dp)
                        )
                        .background(PurpleGrey80)
                        .padding(12.dp)
                )
            }
        }
    }
}

@Composable
private fun DialogForCountry(
    country: Country,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val joinedLang = remember(country.language) {
        country.language.joinToString()
    }
    val joinedStates = remember(country.state) {
        country.state.sorted().joinToString()
    }
    Dialog(onDismissRequest = onDismiss) {
        Column(modifier = modifier) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = country.emoji, fontSize = 32.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = country.name, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "Capital:" + country.capital)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "Languages: $joinedLang")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "States: $joinedStates")
                }
            }
        }

    }
}

@Composable
private fun SingleCountryItem(country: CountryZipped, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = country.emoji, fontSize = 32.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = country.name, fontSize = 18.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = country.capital)
        }
    }
}