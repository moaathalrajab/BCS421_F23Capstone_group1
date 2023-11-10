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

package io.gitlab.fsc_clam.fscwhereswhat.database

import androidx.room.*
import io.gitlab.fsc_clam.fscwhereswhat.model.database.DBNote
import kotlinx.coroutines.flow.Flow

/**
 * The dao for user notes held in the room database
 * @property insert inserts a single note into the table
 * @property delete deletes a single note from the table
 * @property getAll returns a list of all notes as DBNotes
 * @property getById returns a specific note using its reference as a parameter
 * @property getAllFlow returns all notes as flows, allowing the notes view to react to changes
 */
@Dao
interface NoteDao {
	@Insert (onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(vararg note: DBNote)

	@Delete
	suspend fun delete(vararg note: DBNote)

	@Query("SELECT * FROM note")
	suspend fun getAll(): List<DBNote>

	/** Get note by reference **/
	@Query("SELECT * FROM note WHERE reference = :note")
	suspend fun getById(note: Int) : DBNote

	/** Returns all notes with Flow **/
	@Query("SELECT * FROM note")
	fun getAllFlow(): Flow<List<DBNote>>
}