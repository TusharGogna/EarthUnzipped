package com.mdoc.earthunzipped

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mdoc.earthunzipped.presentation.CountriesScreen
import com.mdoc.earthunzipped.presentation.CountriesViewModel
import com.mdoc.earthunzipped.ui.theme.EarthUnzippedTheme
import com.mdoc.earthunzipped.ui.theme.Purple80
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EarthUnzippedTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Purple80
                ) {
                    val viewModel = hiltViewModel<CountriesViewModel>()
                    val state by viewModel.state.collectAsState()
                    CountriesScreen(
                        state = state,
                        onSelectedCountry = viewModel::openCountry,
                        onDismissSelection = viewModel::closeCountry
                    )
                }
            }
        }
    }
}