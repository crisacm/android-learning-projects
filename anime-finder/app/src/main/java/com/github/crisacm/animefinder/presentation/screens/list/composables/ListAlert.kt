package com.github.crisacm.animefinder.presentation.screens.list.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.github.crisacm.animefinder.R
import com.github.crisacm.animefinder.ui.theme.AnimeFinderTheme

@Composable
fun ListAlert(
  modifier: Modifier,
  typeAlert: TypeAlert
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(24.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Image(
      painter = painterResource(
        id = when (typeAlert) {
          TypeAlert.NETWORK_ISSUE -> R.drawable.no_connection
          TypeAlert.ERROR_FETCHING_DATA -> R.drawable.error
          TypeAlert.EMPTY -> R.drawable.empty_box
          TypeAlert.SEARCH_EMPTY -> R.drawable.find
        }
      ),
      contentDescription = null,
      modifier = Modifier.size(100.dp)
    )
    Text(
      text = stringResource(
        id = when (typeAlert) {
          TypeAlert.NETWORK_ISSUE -> R.string.network_issues
          TypeAlert.ERROR_FETCHING_DATA -> R.string.we_all_make_mistakes
          TypeAlert.EMPTY -> R.string.couldn_t_load_data
          TypeAlert.SEARCH_EMPTY -> R.string.We_re_sorry
        }
      ),
      fontWeight = FontWeight.SemiBold,
      fontSize = 24.sp,
      modifier = Modifier.padding(top = 24.dp)
    )
    Text(
      text = stringResource(
        id = when (typeAlert) {
          TypeAlert.NETWORK_ISSUE -> R.string.internet_connection_is_bad
          TypeAlert.ERROR_FETCHING_DATA -> R.string.it_s_my_bad_i_made_a_mistake_when_loading_your_files
          TypeAlert.EMPTY -> R.string.there_seems_to_be_no_information_to_display
          TypeAlert.SEARCH_EMPTY -> R.string.we_ve_searched_a_lot_of_animes_but_we_couldn_t_find
        }
      ),
      fontSize = 16.sp,
      lineHeight = 1.2.em,
      textAlign = TextAlign.Center,
      modifier = Modifier.padding(start = 48.dp, end = 48.dp, top = 24.dp)
    )
  }
}

enum class TypeAlert {
  NETWORK_ISSUE,
  ERROR_FETCHING_DATA,
  EMPTY,
  SEARCH_EMPTY
}

@Preview(showBackground = true)
@Composable
fun ListAlertPreviewNotConnection() {
  AnimeFinderTheme {
    ListAlert(
      modifier = Modifier,
      typeAlert = TypeAlert.NETWORK_ISSUE
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ListAlertPreviewErrorFetchingData() {
  AnimeFinderTheme {
    ListAlert(
      modifier = Modifier,
      typeAlert = TypeAlert.ERROR_FETCHING_DATA
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ListAlertPreviewEmpty() {
  AnimeFinderTheme {
    ListAlert(
      modifier = Modifier,
      typeAlert = TypeAlert.EMPTY
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ListAlertPreviewSearchEmpty() {
  AnimeFinderTheme {
    ListAlert(
      modifier = Modifier,
      typeAlert = TypeAlert.SEARCH_EMPTY
    )
  }
}
