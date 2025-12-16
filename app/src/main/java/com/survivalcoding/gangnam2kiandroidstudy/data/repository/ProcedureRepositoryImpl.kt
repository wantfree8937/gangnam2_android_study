package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository

class ProcedureRepositoryImpl : ProcedureRepository {

    private val mockProcedures = listOf(
        Procedure(id = 1, step = 1, description = "First, chop the onions and garlic."),
        Procedure(id = 2, step = 2, description = "Next, heat the oil in a large pan over medium-high heat."),
        Procedure(id = 3, step = 3, description = "Add the chopped onions and garlic and cook until softened, about 5 minutes."),
        Procedure(id = 4, step = 4, description = "Finally, add the main ingredient and cook until golden brown.")
    )

    override suspend fun getProcedures(): List<Procedure> {
        return mockProcedures
    }
}
