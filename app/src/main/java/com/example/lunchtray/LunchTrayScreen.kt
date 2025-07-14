/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import android.R.attr.text
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lunchtray.ui.OrderViewModel
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchtray.ui.StartOrderScreen


// TODO: Screen enum
enum class EnumScreen{
    START,
    ENTREE_MENU,
    SIDE_DISH_MENU,
    ACCOMPANIMENT_MENU,
    CHECKOUT,
}
// Chứ tiêu đề và nút back
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    currentScreen: EnumScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier
) {
    TopAppBar(
        navigationIcon = {
            if(canNavigateBack){
                IconButton (
                    onClick = navigateUp
                ) {
                    Icon (
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        title = {
            Box (
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text (
                    text = screenTitle(currentScreen),
                    fontSize = 30.sp,
                    modifier = modifier
                )
            }

        },

    )
}

// Mao từ EnumScreen sang String
fun screenTitle(screen: EnumScreen): String{
    return when (screen){
        EnumScreen.START -> "Lunch Tray"
        EnumScreen.ENTREE_MENU -> "Choose Entree"
        EnumScreen.SIDE_DISH_MENU -> "Choose Side Dish"
        EnumScreen.ACCOMPANIMENT_MENU -> "Choose Accompaniment"
        EnumScreen.CHECKOUT -> "Order Checkout"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LunchTrayApp() {
    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                currentScreen = uiState.currentScreen,
                canNavigateBack = uiState.currentScreen != EnumScreen.START,
                navigateUp = {
                    viewModel.goBack()
                },
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when(uiState.currentScreen){
                EnumScreen.START -> StartOrderScreen(
                    onStartOrderButtonClicked = {
                        viewModel.gotoScreen(EnumScreen.ENTREE_MENU)
                    },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .fillMaxSize()
                )

                else -> Text("Coming soon...")
            }
        }
    }
}
