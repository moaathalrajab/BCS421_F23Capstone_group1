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

package io.gitlab.fsc_clam.fscwhereswhat.repo.impl

import io.gitlab.fsc_clam.fscwhereswhat.model.local.EntityType
import io.gitlab.fsc_clam.fscwhereswhat.model.local.Note
import io.gitlab.fsc_clam.fscwhereswhat.repo.base.NoteRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FakeNotesRepo: NoteRepo {

	private val notes = MutableStateFlow(listOf(Note("comment", 0, EntityType.EVENT)))
	override fun getNote(parentId: Int): Flow<Note?> =
		notes.map { notes -> notes.find { it.reference == parentId } }

	override fun getAllNotes(): Flow<List<Note>> {
		return notes
	}

	override suspend fun updateNote(note: Note) {
		flowOf(note)
	}

	override suspend fun deleteNote(note: Note) {
		flowOf(note)
	}

	override suspend fun createNote(note: Note) {
		flowOf(note)
	}
}