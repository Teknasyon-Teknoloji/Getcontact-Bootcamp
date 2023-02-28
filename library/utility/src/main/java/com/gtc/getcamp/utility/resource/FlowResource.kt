package com.gtc.getcamp.utility.resource

import kotlinx.coroutines.flow.Flow

typealias FlowResource<T> = Flow<Resource<T>>