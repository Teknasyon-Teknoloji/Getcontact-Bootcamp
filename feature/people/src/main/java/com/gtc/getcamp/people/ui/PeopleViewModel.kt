package com.gtc.getcamp.people.ui

import androidx.lifecycle.ViewModel
import com.gtc.getcamp.people.domain.usecase.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase,
) : ViewModel() {
}