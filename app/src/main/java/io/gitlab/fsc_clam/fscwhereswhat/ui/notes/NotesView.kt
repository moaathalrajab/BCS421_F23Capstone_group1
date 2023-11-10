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

package io.gitlab.fsc_clam.fscwhereswhat.ui.notes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.gitlab.fsc_clam.fscwhereswhat.R
import io.gitlab.fsc_clam.fscwhereswhat.model.local.EntityType
import io.gitlab.fsc_clam.fscwhereswhat.model.local.Image
import io.gitlab.fsc_clam.fscwhereswhat.model.local.NoteItem

@Composable
fun NotesView() {

}

@Composable
fun NotesContent(
	notes: List<NoteItem>, onUpdate: (NoteItem) -> Unit, onDelete: (NoteItem) -> Unit
) {
	Text(
		text = stringResource(id = R.string.notesHeading),
		style = MaterialTheme.typography.headlineLarge
	)
	LazyColumn(
		Modifier.fillMaxWidth(),
		contentPadding = PaddingValues(16.dp)
	) {
		items(notes) { note ->
			NotesCard(note = note, onUpdate = onUpdate, onDelete = onDelete)
		}
	}
}

@Preview
@Composable
fun PreviewNotesContent() {
	val img = Image.Drawable(R.drawable.flag_icon)
	val notes = listOf(
		NoteItem("This is a comment", 0, EntityType.EVENT, img, "Event Name"),
		NoteItem("This is a comment", 0, EntityType.BUILDING, img, "Building Name"),
		NoteItem("This is a comment", 0, EntityType.NODE, img, "Node Name")
	)
	NotesContent(notes, {}, {})
}