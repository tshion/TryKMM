package io.github.tshion.devmenus

import androidx.fragment.app.FragmentActivity

/**
 * Activity の取得
 */
public fun DevMenuViewContract.getActivity(): FragmentActivity {
    return _hostPage as FragmentActivity
}
