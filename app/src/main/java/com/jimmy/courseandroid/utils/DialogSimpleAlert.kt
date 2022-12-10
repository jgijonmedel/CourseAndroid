package com.jimmy.courseandroid.utils

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.jimmy.courseandroid.databinding.DialogSimpleAlertExampleBinding

class DialogSimpleAlert: DialogFragment() {

    companion object {
        const val TITLE = "TITLE"
        const val MESSAGE = "MESSAGE"
        const val PRIMARY = "PRIMARY"
        const val SECONDARY = "SECONDARY"
        const val SHOW_SECONDARY = "SHOW_SECONDARY"
    }

    private var _binding: DialogSimpleAlertExampleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogSimpleAlertExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dialogTitle.text = arguments?.getString(TITLE)
        binding.dialogMessage.text = arguments?.getString(MESSAGE)
        binding.primaryButton.text = arguments?.getString(PRIMARY)
        binding.secondaryButton.text = arguments?.getString(SECONDARY)
        val showButton =  arguments?.getBoolean(SHOW_SECONDARY) ?: false
        binding.secondaryButton.visibility = if (showButton) View.VISIBLE else View.GONE
        binding.primaryButton.setOnClickListener {
            dismiss()
        }
        binding.secondaryButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setCancelable(false)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    class Builder() {
        private val bundle = bundleOf()

        fun title(title: String): Builder {
            bundle.putString(TITLE, title)
            return this
        }

        fun message(message: String): Builder {
            bundle.putString(MESSAGE, message)
            return this
        }

        fun primaryButton(primary: String): Builder {
            bundle.putString(PRIMARY, primary)
            return this
        }

        fun secondaryButton(secondary: String): Builder {
            bundle.putString(SECONDARY, secondary)
            return this
        }

        fun showSecondaryButton(show: Boolean): Builder {
            bundle.putBoolean(SHOW_SECONDARY, show)
            return this
        }

        fun createDialog() = DialogSimpleAlert().apply { arguments = bundle }
    }
}