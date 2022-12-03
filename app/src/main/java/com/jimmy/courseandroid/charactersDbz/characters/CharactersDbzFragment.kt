package com.jimmy.courseandroid.charactersDbz.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jimmy.courseandroid.charactersDbz.characters.adapter.CharacterDbzAdapter
import com.jimmy.courseandroid.charactersDbz.createCharacterDialog.CreateCharacterDbzDialog
import com.jimmy.courseandroid.databinding.FragmentCharactersDbzBinding

class CharactersDbzFragment : Fragment() {

    private var _binding: FragmentCharactersDbzBinding? = null
    private val binding get() = _binding!!
    val adapter = CharacterDbzAdapter()

    private fun showDialog() {
        val dialog = CreateCharacterDbzDialog()
        dialog.showDialog(
            onCancel = { it.dismiss() },
            onSaved = { d, character ->
                adapter.addCharacter(character)
                d.dismiss()
            },
            fragmentManager = parentFragmentManager
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersDbzBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        binding.btnAdd.setOnClickListener { showDialog() }
    }

    private fun setUpRecycler() {
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvCharactersDbz.adapter = adapter
        binding.rvCharactersDbz.layoutManager = layoutManager
        //adapter.setList(providerCharacter)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = CharactersDbzFragment()
    }
}