package com.jimmy.courseandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.jimmy.courseandroid.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnShowResult.setOnClickListener {
            val nameString = binding.etName.text.toString()
            val lastNameString = binding.etLastName.text.toString()
            val fullName =  getFullName(name = nameString, lastName = lastNameString)
            binding.tvResult.text = fullName
            Toast.makeText(requireContext(), fullName, Toast.LENGTH_SHORT).show()
        }
        binding.tvResult.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setMessage("¿Seguro quieres navegar a la siguiente pantalla?")
                .setPositiveButton("Si") { dialog, _ ->
                    Toast.makeText(requireContext(), "de momento no hay mas pantallas :(", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }.setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }.create().show()
        }
    }


    private fun getFullName(
        name: String,
        lastName: String
    ): String {
        return "mi nombre completo es: $name $lastName"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FirstFragment()
    }
}