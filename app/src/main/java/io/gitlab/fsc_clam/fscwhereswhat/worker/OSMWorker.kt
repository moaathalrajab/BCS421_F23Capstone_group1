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

package io.gitlab.fsc_clam.fscwhereswhat.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import io.gitlab.fsc_clam.fscwhereswhat.model.local.NodeType
import io.gitlab.fsc_clam.fscwhereswhat.model.local.OSMEntity
import io.gitlab.fsc_clam.fscwhereswhat.model.local.OpeningHours
import io.gitlab.fsc_clam.fscwhereswhat.model.remote.OSMElement
import io.gitlab.fsc_clam.fscwhereswhat.model.remote.OSMType
import io.gitlab.fsc_clam.fscwhereswhat.providers.base.OpenStreetMapAPI
import io.gitlab.fsc_clam.fscwhereswhat.providers.impl.OkHttpOpenStreetMapAPI
import io.gitlab.fsc_clam.fscwhereswhat.repo.base.OSMRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient

/**
 * Downloads latest OSM data and updates database with the new data
 */
class OSMWorker(appContext: Context, params: WorkerParameters) :
	CoroutineWorker(appContext, params) {
	private val repo = OSMRepository

	private val client = OkHttpClient.Builder().addInterceptor {
		val request = it.request()
		Log.d("OKHttpClient", request.url.toString())
		runBlocking {
			delay(100)
		}
		it.proceed(request)
	}.build()

	private val api: OpenStreetMapAPI = OkHttpOpenStreetMapAPI(client)
	private val entities = ArrayList<OSMEntity>()
	override suspend fun doWork(): Result {
		val relation = api.getFullElement(OSMType.RELATION, FSC_RELATION)

		relation.elements.forEach { element ->
			process(element)
		}

		repo

		return Result.success()
	}

	private suspend fun process(element: OSMElement) {
		Log.d(LOG, "Processing OSMElement(${element.id})")
		when (element.type) {
			OSMType.WAY -> {
				Log.d(LOG, "OSMElement(${element.id}) is a way")
				parseWay(element)
			}

			OSMType.RELATION -> {
				Log.d(LOG, "OSMElement(${element.id}) is a relation, ignoring")
			}

			OSMType.NODE -> {
				Log.d(LOG, "OSMElement(${element.id}) is a node")
				parseNode(element)
			}
		}
	}

	private fun parseNode(element: OSMElement) {

		if (element.tags == null) {
			Log.d(LOG, "OSMElement(${element.id}) has no tags, skipping")
			return
		}

		val nodeType: NodeType

		when {
			element.tags.amenity == "drinking_water" ->
				nodeType = NodeType.WATER

			element.tags.amenity == "vending_machine" ->
				nodeType = NodeType.VENDING_MACHINE

			element.tags.amenity == "food_court" ||
					element.tags.amenity == "cafe" ||
					element.tags.amenity == "restaurant" ->
				nodeType = NodeType.FOOD

			element.tags.shop != null ->
				nodeType = NodeType.RETAIL

			element.tags.emergency == "phone" ->
				nodeType = NodeType.SOS

			element.tags.amenity == "bicycle_parking" ->
				nodeType = NodeType.BICYCLE

			else -> {
				Log.d(LOG, "OSMElement(${element.id}) does not match any known NodeTypes")
				return
			}
		}

		entities.add(
			OSMEntity.Node(
				id = element.id,
				lat = element.lat!!,
				long = element.lon!!,
				name = element.tags.name ?: "Element ${element.id}",
				description = "", // TODO OSM descriptions??
				hours = OpeningHours.everyDay,
				nodeType = nodeType
			)
		)
	}

	private suspend fun parseWay(element: OSMElement) {
		@Suppress("NullChecksToSafeCall")
		if (element.tags == null || element.tags.building == null) {
			Log.d(LOG, "OSMElement(${element.id}) is not a building")
		} else {
			Log.d(LOG, "OSMElement(${element.id}) is a building")
			entities.add(
				OSMEntity.Building(
					id = element.id,
					lat = 0.0,
					long = 0.0,
					name = element.tags.name?:"Building ${element.id}",
					description = "",
					hours = OpeningHours.everyDay,
					hasWater = true,
					hasFood = true
				)
			)
		}

		Log.d(LOG, "Parsing OSMElement(${element.id}) nodes")
		element.nodes.forEach {
			process(api.getElement(OSMType.NODE, it).elements.first())
		}
	}

	companion object {
		/**
		 * Relation ID for the entirety of FSC
		 */
		private const val FSC_RELATION: Long = 8288536

		private const val LOG = "OSMWorker"
	}
}