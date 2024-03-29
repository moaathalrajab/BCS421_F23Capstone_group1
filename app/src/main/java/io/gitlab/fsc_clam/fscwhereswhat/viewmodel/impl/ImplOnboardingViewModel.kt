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

package io.gitlab.fsc_clam.fscwhereswhat.viewmodel.impl

import android.app.Application
import io.gitlab.fsc_clam.fscwhereswhat.repo.base.PreferencesRepository
import io.gitlab.fsc_clam.fscwhereswhat.repo.impl.ImplPreferencesRepository.Companion.get
import io.gitlab.fsc_clam.fscwhereswhat.viewmodel.base.OnboardingViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ImplOnboardingViewModel(
	application: Application
) : OnboardingViewModel(application) {
	private val preferences = PreferencesRepository.get(application)

	override val currentPage = MutableStateFlow(0)

	override fun setPage(page: Int) {
		currentPage.value = page
	}

	override fun finish() {
		preferences.setNotFirst()
	}
}