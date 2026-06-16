package io.github.tshion.trykmp.sample.templates

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

/**
 * https://developer.android.com/guide/fragments/dialogs を流用し、一部改変
 */
class PurchaseConfirmationDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("購入しますか？")
            .setPositiveButton(getString(R.string.ok), null)
            .create()

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}
