package com.mdoc.earthunzipped.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mdoc.earthunzipped.domain.Country
import com.mdoc.earthunzipped.domain.CountryZipped
import com.mdoc.earthunzipped.ui.theme.Purple40
import com.mdoc.earthunzipped.ui.theme.Purple80
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight

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
                            .fillMaxSize()
                            .clickable { onSelectedCountry(countries.code) }
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Purple80)
                            .align(Alignment.Center)
                    )
                }
            }

            if (state.selectedCountry != null) {
                DialogForCountry(
                    country = state.selectedCountry,
                    onDismiss = onDismissSelection,
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(Purple40)
                        .shadow(4.dp)
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
    modifier: Modifier
) {
    val joinedLang = remember(country.language) {
        country.language
    }
    val joinedStates = remember(country.state) {
        country.state.sorted()
    }

    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Column(modifier = modifier) {
            Text(text = country.emoji, fontSize = 52.sp)
            Text(
                text = country.name,
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Capital:" + country.capital, color = Color.White)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Language(s): ", color = Color.White)
            LazyRow {
                items(joinedLang) {
                    if (joinedLang.size > 1 && joinedLang.last() != it)
                        Text(text = "$it, ", color = Color.White)
                    else
                        Text(text = it, color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            if (joinedStates.isNotEmpty()) {
                Text(text = "States: ", color = Color.White)
                LazyRow {
                    items(joinedStates) {
                        if (joinedStates.size > 1 && joinedStates.last() != it)
                            Text(text = "$it, ", color = Color.White)
                        else
                            Text(text = it, color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
private fun SingleCountryItem(country: CountryZipped, modifier: Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = country.emoji,
            fontSize = 62.sp,
            modifier = Modifier
                .padding(4.dp)
        )
        Text(text = country.name, fontSize = 24.sp)
        Spacer(modifier = Modifier.width(6.dp))
        if (country.capital != "N/A")
            Text(
                text = "Capital is ${country.capital}",
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 12.dp)
            )
    }
}