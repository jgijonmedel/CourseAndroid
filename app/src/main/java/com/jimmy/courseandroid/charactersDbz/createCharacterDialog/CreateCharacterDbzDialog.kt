package com.jimmy.courseandroid.charactersDbz.createCharacterDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.jimmy.courseandroid.charactersDbz.data.CharacterDbz
import com.jimmy.courseandroid.databinding.DialogCreateCharacterDbzBinding

class CreateCharacterDbzDialog : DialogFragment() {

    private lateinit var binding: DialogCreateCharacterDbzBinding
    private var onCancel: (DialogFragment) -> Unit = {}
    private var onSaved: (DialogFragment, CharacterDbz) -> Unit = { _, _ -> }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogCreateCharacterDbzBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        setUpEvents()
    }

    private fun setUpEvents() {
        binding.btnCancel.setOnClickListener { onCancel(this) }

        binding.btnSave.setOnClickListener {
            if (validateInputs()) {
                val character = CharacterDbz(
                    name = binding.etName.text.toString().trim(),
                    image = "https://firebasestorage.googleapis.com/v0/b/janus-course-android.appspot.com/o/characters_dbz%2Fgoku.jpg?alt=media",
                    race = binding.etRace.text.toString().trim(),
                    basePower = binding.etPowerLevel.text.toString().toIntOrNull() ?: 0
                )
                onSaved(this, character)
            } else {
                Toast.makeText(requireContext(), "Faltan datos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showDialog(
        onCancel: (dialog: DialogFragment) -> Unit,
        onSaved: (dialog: DialogFragment, character: CharacterDbz) -> Unit,
        fragmentManager: FragmentManager
    ) {
        this.onCancel = onCancel
        this.onSaved = onSaved
        show(fragmentManager, this::class.java.simpleName)
    }

    private fun validateInputs(): Boolean {
        val nameIsNotEmpty = binding.etName.text.trim().isNotEmpty()
        val raceIsNotEmpty = binding.etRace.text.trim().isNotEmpty()
        val levelIsNotEmpty = binding.etPowerLevel.text.trim().isNotEmpty()
        return (nameIsNotEmpty || raceIsNotEmpty || levelIsNotEmpty)
    }
}