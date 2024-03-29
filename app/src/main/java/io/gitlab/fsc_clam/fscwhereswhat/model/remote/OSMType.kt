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

package io.gitlab.fsc_clam.fscwhereswhat.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Element types as stated by OSM.
 *
 * For use with OSM api.
 */
@Serializable
enum class OSMType {
	@SerialName("way")
	WAY,

	@SerialName("relation")
	RELATION,

	@SerialName("node")
	NODE;

	override fun toString(): String {
		return name.lowercase()
	}
}