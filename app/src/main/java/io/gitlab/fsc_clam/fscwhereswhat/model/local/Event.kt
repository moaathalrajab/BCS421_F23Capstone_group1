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

import java.net.URL

/**
 * @param locationId this does not correspond to RamCentral ID but to OSM ID
 */
data class Event(
	val id :Int,
	val name: String,
	val image: URL,
	val description: String,
	val instructions: String?,
	val locationName: String,
	val locationId: Int,
	val hasRsvp: Boolean,
	val url: URL,

)
