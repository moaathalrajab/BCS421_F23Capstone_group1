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

package io.gitlab.fsc_clam.fscwhereswhat.model.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * This is a tag associated with a given node
 */
@Entity(
	tableName = "osm_node_oh",
	foreignKeys = [ForeignKey(DBOSMNode::class, ["id"], ["parentId"], ForeignKey.CASCADE)],
)
data class DBOSMNodeOH(
	@PrimaryKey(autoGenerate = true)
	val id: Long,
	val parentId: Long,
	val monday: Boolean,
	val tuesday: Boolean,
	val wednesday: Boolean,
	val thursday: Boolean,
	val friday: Boolean,
	val saturday: Boolean,
	val sunday: Boolean,
	val startHour: Int,
	val startMinute: Int,
	val endHour: Int,
	val endMinute: Int
)
