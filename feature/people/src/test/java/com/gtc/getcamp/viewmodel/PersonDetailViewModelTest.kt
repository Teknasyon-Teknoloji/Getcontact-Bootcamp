package com.gtc.getcamp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import com.gtc.getcamp.people.domain.model.PersonModel
import com.gtc.getcamp.people.domain.usecase.GetPersonDetailUseCase
import com.gtc.getcamp.people.ui.persondetail.PersonDetailViewModel
import com.gtc.getcamp.people.ui.persondetail.state.PersonDetailScreenState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
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
class PersonDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var personDetailViewModel: PersonDetailViewModel

    @MockK
    private lateinit var getPersonDetailUseCase: GetPersonDetailUseCase

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        Dispatchers.setMain(StandardTestDispatcher())
        personDetailViewModel = PersonDetailViewModel(
            getPersonDetailUseCase = getPersonDetailUseCase,
            savedStateHandle = savedStateHandle,
        )
    }

    @Test
    fun test_init_state() = runTest {
        Truth.assertThat(personDetailViewModel.uiState.value).isInstanceOf(PersonDetailScreenState.LoadingState::class.java)
    }

    @Test
    fun test_success_state() = runTest {
        every { savedStateHandle.get<String>("personId") } returns "1"

        coEvery { getPersonDetailUseCase.getPerson("1") } returns flow {
            emit(
                PersonModel(
                    personId = "",
                    personName = "GTC",
                    personLinks = emptyList(),
                    personAbout = "Handsome",
                    personImage = ""
                )
            )
        }

        advanceTimeBy(1000L)

        Truth.assertThat(personDetailViewModel.uiState.value).isInstanceOf(PersonDetailScreenState.SuccessState::class.java)
    }
}