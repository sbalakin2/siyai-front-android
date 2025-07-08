package com.example.siyai_front_android.presentation.my_state.common_cycles

import com.example.siyai_front_android.domain.dto.Cycle

data class SelectCyclesState(
    val cycles: List<Cycle> = emptyList(),
    val tempStartDate: Long? = null,
    val deleteCycleId: Int? = null,
    val isVisibleDialog: Boolean = false
)