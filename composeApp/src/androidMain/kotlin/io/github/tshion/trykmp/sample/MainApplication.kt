package io.github.tshion.trykmp.sample

import android.app.Application
import io.github.tshion.devmenus.DevMenuProvider
import io.github.tshion.devmenus.DevMenuSpec

internal class MainApplication : Application(), DevMenuProvider {

    override val devMenuList = listOf(
        DevMenuSpec.Group(
            "Group1 Title",
            DevMenuSpec.Group(
                "Group2 Title",
                DevMenuSpec.Action("Group2: Action Title1") {
                },
                DevMenuSpec.Action("Group2: Action Title2") {
                },
            ),
            DevMenuSpec.Action("Group1: Action Title1") {
            },
            DevMenuSpec.Action("Group1: Action Title2") {
            },
        ),
        DevMenuSpec.Action("Action Title1") {
        },
        DevMenuSpec.Action("Action Title2") {
        },
    )
}
