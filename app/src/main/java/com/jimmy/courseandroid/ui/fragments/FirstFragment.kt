package com.jimmy.courseandroid.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.jimmy.courseandroid.R
import com.jimmy.courseandroid.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private var fullName = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnShowResult.setOnClickListener {
            val nameString = binding.etName.text.toString()
            val lastNameString = binding.etLastName.text.toString()
            fullName =  getFullName(name = nameString, lastName = lastNameString)
            binding.tvResult.text = fullName
            Toast.makeText(requireContext(), fullName, Toast.LENGTH_SHORT).show()
        }
        binding.tvResult.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setMessage("¿Seguro quieres navegar a la siguiente pantalla?")
                .setPositiveButton("Si") { dialog, _ ->
                    val bundle = bundleOf("fullName" to fullName)
                   findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle)
                    dialog.dismiss()
                }.setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }.create().show()
        }

        binding.btnGoToDbz.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_charactersDbzFragment)
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