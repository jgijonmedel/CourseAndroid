package com.jimmy.courseandroid.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jimmy.courseandroid.R
import com.jimmy.courseandroid.databinding.FragmentFormBinding

class FormFragment : Fragment() {

    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val email = sharedPreferences.getString(LoginFragment.EMAIL_PREFERENCE, "")
        binding.tvEmail.text = email

        binding.btnSave.setOnClickListener {
            val user = hashMapOf(
                "email" to email,
                "telefono" to binding.etPhone.text.toString(),
                "direccion" to binding.etAddress.text.toString()
            )
            db.collection("usuarios")
                .document(email.orEmpty())
                .set(user)
                .addOnSuccessListener {
                    Toast.makeText(this@FormFragment.context, "usuario guardado", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this@FormFragment.context, it.message, Toast.LENGTH_SHORT).show()
                }
        }
        binding.btnGet.setOnClickListener {
            db.collection("usuarios")
                .document(email.orEmpty())
                .get()
                .addOnSuccessListener {
                    binding.tvInfo.text = "telefono: ${it.get("telefono") as String} - direccion: ${it.get("direccion") as String}"
                }
        }
        binding.btnDelete.setOnClickListener {
            db.collection("usuarios").document(email.orEmpty()).delete()
        }
   }
}