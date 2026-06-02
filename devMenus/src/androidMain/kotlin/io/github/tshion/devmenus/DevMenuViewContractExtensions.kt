package io.github.tshion.devmenus

import androidx.fragment.app.FragmentActivity

public fun DevMenuViewContract.getActivity(): FragmentActivity {
    return getContext() as FragmentActivity
}
