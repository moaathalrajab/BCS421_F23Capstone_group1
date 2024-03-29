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

package io.gitlab.fsc_clam.fscwhereswhat.viewmodel.base

import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import io.gitlab.fsc_clam.fscwhereswhat.model.local.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * For login view in onboarding
 */
abstract class LoginViewModel : ViewModel() {

	/**
	 * Used to display user information when login successful
	 */
	abstract val user: StateFlow<User?>

	/**
	 * Displays any errors to the user safely
	 */
	abstract val exception: Flow<Throwable>

	/**
	 * Handle a sign in result
	 */
	abstract fun handleSignInResult(result: ActivityResult)
}