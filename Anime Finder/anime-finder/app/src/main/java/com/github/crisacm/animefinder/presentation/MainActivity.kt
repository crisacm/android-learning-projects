package com.github.crisacm.animefinder.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.github.crisacm.animefinder.navigation.Navigation
import com.github.crisacm.animefinder.ui.theme.AnimeFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      AnimeFinderTheme {
        Navigation()
      }
    }
  }
}
