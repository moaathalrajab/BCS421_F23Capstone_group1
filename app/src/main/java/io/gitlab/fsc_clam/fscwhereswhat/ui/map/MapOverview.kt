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

package io.gitlab.fsc_clam.fscwhereswhat.ui.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.gitlab.fsc_clam.fscwhereswhat.R
import io.gitlab.fsc_clam.fscwhereswhat.model.local.EntityType
import io.gitlab.fsc_clam.fscwhereswhat.model.local.Image
import io.gitlab.fsc_clam.fscwhereswhat.model.local.User

/**
 * Creates the UI for Map Content
 * @param activeFilter is the current filter selected
 * @param setActiveFilter will change the active filter to selected filter type from viewmodel
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapOverview(
	user: User?,
	query: String?,
	activeFilter: EntityType?,
	setActiveFilter: (EntityType?) -> Unit,
	onRecenter: () -> Unit,
	openSearch: () -> Unit,
	navigateToMore: () -> Unit,
	login: () -> Unit
) {
	Box(
		modifier = Modifier.fillMaxSize()
	) {
		//Icon of the Account
		//WIP should include functionality to login
		Card(
			modifier = Modifier
				.padding(8.dp)
				.align(Alignment.TopStart),
			shape = CircleShape,
			onClick = login
		) {
			if (user != null) {
				AsyncImage(
					model = user.image,
					contentDescription = stringResource(id = R.string.account_icon),
					modifier = Modifier
						.size(50.dp)
						.padding(8.dp)
				)
			} else {
				Icon(
					Icons.Filled.AccountCircle,
					contentDescription = stringResource(id = R.string.account_icon),
					modifier = Modifier
						.size(50.dp)
						.padding(8.dp)
				)
			}
		}

		Card(
			modifier = Modifier
				.padding(8.dp)
				.align(Alignment.TopEnd),
			shape = CircleShape,
			onClick = onRecenter
		) {
			Icon(
				Icons.Filled.LocationOn,
				contentDescription = stringResource(id = org.osmdroid.library.R.string.my_location),
				modifier = Modifier
					.size(50.dp)
					.padding(8.dp)
			)
		}

		//Holds the search bar and filter buttons
		Column(
			modifier = Modifier
				.align(Alignment.BottomStart)
				.padding(8.dp),
			verticalArrangement = Arrangement.spacedBy(8.dp),
			horizontalAlignment = Alignment.Start
		) {

			//Creates the filter buttons
			FilterButtonRow(activeFilter, setActiveFilter)

			//bottom bar holds search and more button
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceEvenly,
				verticalAlignment = Alignment.CenterVertically
			) {
				//search bar on click expands to show search view
				SearchBar(openSearch, query)

				//The more button
				MoreButton(navigateToMore)
			}
		}
	}
}

@Composable
fun MoreButton(navigateToMore: () -> Unit) {
	Card(
		shape = CircleShape
	) {
		IconButton(
			onClick = navigateToMore,
			modifier = Modifier
		) {
			Icon(
				painter = painterResource(id = R.drawable.baseline_more_horiz_24),
				contentDescription = stringResource(id = R.string.more_button_desc),
			)
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(openSearch: () -> Unit, query: String?) {
	Card(
		onClick = openSearch,
		shape = CircleShape,
		modifier = Modifier
			.fillMaxWidth(.8f),
		elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(8.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(4.dp)
		) {
			Icon(
				Icons.Default.Search,
				stringResource(id = R.string.search_bar_label),
				modifier = Modifier
					.size(32.dp)
			)
			Text(text = let {
				if (!query.isNullOrEmpty())
					query
				else
					stringResource(id = R.string.search_bar_label)
			}
			)
		}
	}
}

@Composable
fun FilterButtonRow(
	activeFilter: EntityType?,
	setActiveFilter: (EntityType?) -> Unit
) {
	Card(
		Modifier.padding(8.dp),
		elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceEvenly,
			verticalAlignment = Alignment.CenterVertically
		) {
			FilterButton(
				filter = EntityType.BUILDING,
				img = Image.Drawable(R.drawable.building_icon),
				isSelected = activeFilter == EntityType.BUILDING,
				onSelected = { setActiveFilter(it) }
			)
			FilterButton(
				filter = EntityType.EVENT,
				img = Image.Drawable(R.drawable.event),
				isSelected = activeFilter == EntityType.EVENT,
				onSelected = { setActiveFilter(it) }
			)
			FilterButton(
				filter = EntityType.NODE,
				img = Image.Drawable(R.drawable.node),
				isSelected = activeFilter == EntityType.NODE,
				onSelected = { setActiveFilter(it) }
			)
		}
	}
}

@Preview
@Composable
fun PreviewMapUI() {
	Surface {
		MapOverview(
			user = null,
			query = null,
			activeFilter = null,
			setActiveFilter = {},
			onRecenter = {},
			openSearch = {},
			navigateToMore = {},
			login = {}
		)
	}
}

/**
 * Creates individual filter buttons for each Entity Type
 * @param filter is the entity type
 * @param img is the drawable for the icon
 * @param pinPointColor is the color for the entity type
 * @param isSelected is if the button is the active filter
 * @param onSelected is the function called when pressed to change active filter
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterButton(
	filter: EntityType,
	img: Image.Drawable,
	isSelected: Boolean,
	onSelected: (EntityType?) -> Unit
) {
	FilterChip(
		selected = isSelected,
		onClick = { onSelected(filter) },
		label = {
			Text(
				text = filter.toString(),
				style = MaterialTheme.typography.bodyMedium,
				color = Color.Black
			)
		},
		leadingIcon = {
			Icon(
				painter = painterResource(id = img.drawable),
				contentDescription = filter.name
			)
		}
	)
}

@Preview
@Composable
fun PreviewFilterButtons() {
	Surface {
		FilterButtonRow(activeFilter = null, setActiveFilter = {})
	}
}