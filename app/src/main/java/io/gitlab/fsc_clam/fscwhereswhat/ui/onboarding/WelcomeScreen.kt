/*
 *  Copyright (c) 2023 TEAM CLAM
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.gitlab.fsc_clam.fscwhereswhat.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.gitlab.fsc_clam.fscwhereswhat.R
import io.gitlab.fsc_clam.fscwhereswhat.ui.theme.titleFont

/**
 * Opening screen
 */
@Composable
fun WelcomeScreen() {
	//Creates background
	Box(
		modifier = with(Modifier) {
			fillMaxSize()
				.paint(
					painterResource(id = R.drawable.welcome_screen_background),
					contentScale = ContentScale.FillBounds
				)
		}) {
		//App logo
		Image(
			painter = painterResource(id = R.drawable.wheres_what_logo),
			contentDescription = stringResource(id = R.string.app_logo_description),
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.padding(vertical = 15.dp)
				.size(320.dp)
				.clip(RoundedCornerShape(25))
				.align(Alignment.Center)
		)
		//Contains greeting text
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(vertical = 50.dp),
			verticalArrangement = Arrangement.Bottom,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = stringResource(id = R.string.welcome_label),
				fontFamily = titleFont,
				fontWeight = FontWeight.Bold,
				fontStyle = FontStyle.Italic,
				fontSize = 32.sp,
			)
			Text(
				text = stringResource(id = R.string.headline),
				fontFamily = titleFont,
				fontWeight = FontWeight.Bold,
				fontStyle = FontStyle.Italic,
				fontSize = 46.sp,
			)
		}
	}
}

@Preview
@Composable
fun PreviewWelcomeScreen() {
	WelcomeScreen()
}