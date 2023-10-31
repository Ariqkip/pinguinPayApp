package dev.eric.binaria

import android.icu.util.TimeUnit
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.eric.binaria.common.Route
import dev.eric.binaria.screen.BinariaProgressDialog
import dev.eric.binaria.screen.HomeScreen
import dev.eric.binaria.screen.SendMoneyFormScreen
import dev.eric.binaria.screen.SendMoneySuccessScreen
import dev.eric.binaria.ui.theme.green70
import org.koin.androidx.viewmodel.ext.android.viewModel
import dev.eric.binaria.viewmodel.ExchangeRatesViewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import okio.Timeout
import kotlin.concurrent.timer


class MainActivity : ComponentActivity() {

    private val exchangeRatesViewModel by viewModel<ExchangeRatesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            window.statusBarColor = green70.toArgb()

            LaunchedEffect(key1 = Unit) {
                exchangeRatesViewModel.onAction(ExchangeRatesViewModel.Action.OnFetchRates)
            }

            val state = exchangeRatesViewModel.exchangeRatesState

            if (state.isLoading) {

                BinariaProgressDialog()
            }

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Route.Home.Home
                ) {
                    composable(Route.Home.Home) {
                        HomeScreen(navController = navController)
                    }

                    composable(Route.Home.SendMoneyScreen) {
                        SendMoneyFormScreen(
                            viewModel = exchangeRatesViewModel,
                            navController = navController
                        )
                    }

                    composable(Route.Home.SendMoneySuccessScreen) {
                        SendMoneySuccessScreen(
                            navController = navController,
                            viewModel = exchangeRatesViewModel
                        )
                    }
                }
            }
        }
    }
}