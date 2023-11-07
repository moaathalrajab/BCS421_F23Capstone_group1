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

package io.gitlab.fsc_clam.fscwhereswhat.model.local

/**
 * OSM Opening Hours for a node
 * Days are if opening hours are applied on those days
 * @param startHour in what hour is opened
 * @param startMinute in what minute is opened
 * @param endHour in what hour is closed
 * @param endMinute in what minute is closed
 */
data class OpeningHours(
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