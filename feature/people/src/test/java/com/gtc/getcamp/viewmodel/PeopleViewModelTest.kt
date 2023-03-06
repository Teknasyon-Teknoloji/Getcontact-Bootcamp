package com.gtc.getcamp.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.gtc.getcamp.navigator.Navigator
import com.gtc.getcamp.people.domain.model.PersonModel
import com.gtc.getcamp.people.domain.usecase.GetPeopleUseCase
import com.gtc.getcamp.people.ui.people.PeopleViewModel
import com.gtc.getcamp.people.ui.people.state.PeopleScreenState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PeopleViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var peopleViewModel: PeopleViewModel

    @MockK
    private lateinit var getPeopleUseCase: GetPeopleUseCase

    @MockK
    private lateinit var context: Context

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        Dispatchers.setMain(StandardTestDispatcher())
        peopleViewModel = PeopleViewModel(
            navigator = Navigator(context),
            getPeopleUseCase = getPeopleUseCase
        )
    }

    @Test
    fun test_init_state() = runTest {
        assertThat(peopleViewModel.uiState.value).isInstanceOf(PeopleScreenState.LoadingState::class.java)
    }


    @Test
    fun test_success_state() = runTest {
        coEvery { getPeopleUseCase.getUsers() } returns flow {
            emit(
                listOf(
                    PersonModel(
                        personId = "",
                        personName = "GTC",
                        personLinks = emptyList(),
                        personAbout = "Handsome",
                        personImage = ""
                    )
                )
            )
        }

        advanceTimeBy(1000L)

        assertThat(peopleViewModel.uiState.value).isInstanceOf(PeopleScreenState.SuccessState::class.java)
        assertThat((peopleViewModel.uiState.value as? PeopleScreenState.SuccessState)?.people?.get(0)?.personName.orEmpty()).isEqualTo(
            "GTC"
        )
    }
}