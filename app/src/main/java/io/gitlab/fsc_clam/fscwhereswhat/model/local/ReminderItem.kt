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
 * ReminderItem holds data for the UI Reminder
 * @param eventId is the id of the RamCentral Event
 * @param eventName name for the event
 * @param imageURL the link to the organization profile pic
 * @param remind time of the reminder
 * @param date of the event
 */
data class ReminderItem(
	val eventId: Long,
	val eventName: String,
	val imageURL: URL,
	val remind: ReminderTime,
	val date: String
)
