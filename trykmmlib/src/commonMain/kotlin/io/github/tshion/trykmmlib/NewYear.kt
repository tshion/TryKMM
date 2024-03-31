package io.github.tshion.trykmmlib

import kotlinx.datetime.*

private fun daysUntilNewYear(): Int {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val closestNewYear = LocalDate(today.year + 1, 1, 1)
    return today.daysUntil(closestNewYear)
}

internal fun daysPhrase(): String = "There are only ${daysUntilNewYear()} days left until New Year! 🎆"
