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

package io.gitlab.fsc_clam.fscwhereswhat.model.database.entities

import androidx.room.*
import io.gitlab.fsc_clam.fscwhereswhat.model.local.EntityType
import io.gitlab.fsc_clam.fscwhereswhat.model.local.ReminderTime

class TypeConversion {
	/**
	 * Conversions for ReminderTime class
	 */
	@TypeConverter
	fun fromReminderTime(value: ReminderTime) = value.ordinal

	@TypeConverter
	fun toReminderTime(value: Int) = ReminderTime.values()[value]

	/**
	 * Conversions for EntityType
	 */

	@TypeConverter
	fun fromEntityType(value: EntityType) = value.ordinal

	@TypeConverter
	fun toEntityType(value: Int) = EntityType.values()[value]
}