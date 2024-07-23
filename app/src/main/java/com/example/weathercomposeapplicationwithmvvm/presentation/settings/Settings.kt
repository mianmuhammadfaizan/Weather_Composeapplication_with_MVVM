/**
 *
 *	MIT License
 *
 *	Copyright (c) 2023 Gautam Hazarika
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 *
 **/

package com.example.weathertoday.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Settings(
    navHostController: NavHostController, state: SettingsState, event: (SettingsEvent) -> Unit
) {
    

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {


            IconButton(onClick = {
                navHostController.navigateUp()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")

            }
            Text(
                text = "Setting",
                modifier = Modifier,
                style = TextStyle(color = Color.Black),
                fontWeight = FontWeight.W600,
                fontSize = 18.sp
            )

        }


        Column(modifier = Modifier
            .clickable {
                event(SettingsEvent.DismissTempUnitMenu)
            }
            .fillMaxWidth()
            .padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth().background(Color.LightGray).padding(10.dp)) {
                Text(text = "Temperature", style = MaterialTheme.typography.headlineSmall)
            }

            Text(
                text = if (state.tempUnit == "celsius") "Celsius -°C" else "Fahrenheit -°F",
                style = MaterialTheme.typography.labelMedium
            )

            DropdownMenu(expanded = state.tempUnitMenuState,
                onDismissRequest = { event(SettingsEvent.SetTempUnitMenuToFalse) }) {
                DropdownMenuItem(text = { Text(text = "Celsius -°C") }, onClick = {
                    event(SettingsEvent.ChangeTemp("celsius"))
                })
                DropdownMenuItem(text = { Text(text = "Fahrenheit -°F") }, onClick = {
                    event(SettingsEvent.ChangeTemp("fahrenheit"))
                })

            }

        }


        Column(modifier = Modifier
            .clickable {
                event(SettingsEvent.DismissWindSpeedMenu)
            }
            .fillMaxWidth()
            .padding(16.dp)) {

            Row(modifier = Modifier.fillMaxWidth().background(Color.LightGray).padding(10.dp)) {
                Text(text = "Wind", style = MaterialTheme.typography.headlineSmall)
            }


            Text(
                text = getWindSpeedLabel(state.windSpeed),
                style = MaterialTheme.typography.labelMedium
            )

            val list = listOf("kmh", "mph", "ms", "kn")

            DropdownMenu(expanded = state.windSpeedMenuState, onDismissRequest = {
                event(SettingsEvent.SetWindSpeedMenuToFalse)
            }) {
                list.forEach { tempUnit ->
                    DropdownMenuItem(
                        text = { Text(text = getWindSpeedLabel(tempUnit)) },
                        onClick = { event(SettingsEvent.ChangeWindSpeed(tempUnit)) })

                }

            }

        }


    }

}

fun getWindSpeedLabel(windSpeed: String): String {

    return when (windSpeed) {
        "mph" -> "Miles Per Hour -mph"
        "kmh" -> "Kilometer Per Hour -kmh"
        "kn" -> "Knots"
        "ms" -> "Meter Per Second -ms"
        else -> "Kilometer Per Hour -kmh"
    }

}
