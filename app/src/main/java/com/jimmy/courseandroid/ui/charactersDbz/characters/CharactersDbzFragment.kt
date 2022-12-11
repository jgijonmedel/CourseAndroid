package com.jimmy.courseandroid.ui.charactersDbz.characters

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jimmy.courseandroid.R
import com.jimmy.courseandroid.data.database.CharacterDbzDatabase
import com.jimmy.courseandroid.data.entity.CharacterDbzEntity
import com.jimmy.courseandroid.ui.charactersDbz.characters.adapter.CharacterDbzAdapter
import com.jimmy.courseandroid.ui.charactersDbz.createCharacterDialog.CreateCharacterDbzDialog
import com.jimmy.courseandroid.databinding.FragmentCharactersDbzBinding
import com.jimmy.courseandroid.ui.fragments.LoginFragment.Companion.EMAIL_PREFERENCE

class CharactersDbzFragment : Fragment() {

    private var _binding: FragmentCharactersDbzBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CharacterDbzAdapter

    private fun showDialog() {
        val dialog = CreateCharacterDbzDialog()
        dialog.showDialog(
            onCancel = { it.dismiss() },
            onSaved = { d, character ->
                insertCharacter(character)
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
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val email = sharedPreferences.getString(EMAIL_PREFERENCE, "")

        adapter = CharacterDbzAdapter(
            onDeleted = { entity ->
                deleteCharacter(entity)
            }
        )

        binding.tvEmail.text = email
        binding.btnLogout.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            findNavController().navigate(R.id.action_charactersDbzFragment_to_loginFragment)
        }
        binding.btnAdd.setOnClickListener { showDialog() }
        setUpRecycler()
        getAllCharacters()
    }

    private fun setUpRecycler() {
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvCharactersDbz.adapter = adapter
        binding.rvCharactersDbz.layoutManager = layoutManager
    }

    private fun deleteCharacter(entity: CharacterDbzEntity) {
        try {
            val database = CharacterDbzDatabase.getDatabase(requireContext())
            val dao = database.getDao()
            dao.deleted(entity)
            Toast.makeText(requireContext(), "se elimino el personaje ${entity.name}", Toast.LENGTH_SHORT).show()
            getAllCharacters()
        } catch (ex: Exception) {
            Log.e("error_database_delet", "message: ${ex.message}")
            Toast.makeText(requireContext(), "No se pudo eliminar el personaje", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertCharacter(entity: CharacterDbzEntity) {
        try {
            val database = CharacterDbzDatabase.getDatabase(requireContext())
            val dao = database.getDao()
            dao.insert(entity)
            Toast.makeText(requireContext(), "Se guardo el personaje ${entity.name}", Toast.LENGTH_SHORT).show()
            getAllCharacters()
        } catch (ex: Exception) {
            Log.e("error_database_insert", "message: ${ex.message}")
            Toast.makeText(requireContext(), "No se pudo insertar el personaje", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getAllCharacters() {
        try {
            val database = CharacterDbzDatabase.getDatabase(requireContext())
            val dao = database.getDao()
            val characters = dao.getAll()
            adapter.setList(characters)
        } catch (ex: Exception) {
            Log.e("error_database_getAll", "message: ${ex.message}")
            Toast.makeText(requireContext(), "No se pudieron obtener los personajes", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = CharactersDbzFragment()
    }
}