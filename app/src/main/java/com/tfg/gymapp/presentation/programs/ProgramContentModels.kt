package com.tfg.gymapp.presentation.programs

data class ProgramDayContent(
    val day: String,
    val title: String,
    val description: String,
    val imageUrl: String? = null,
    val isCompleted: Boolean = false
)

data class ProgramWeekContent(
    val weekNumber: Int,
    val days: List<ProgramDayContent>
)

data class FullProgramContent(
    val programId: String,
    val title: String,
    val weeks: List<ProgramWeekContent>
)
