package com.jimmy.courseandroid.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jimmy.courseandroid.R
import com.jimmy.courseandroid.databinding.FragmentLoginLayoutBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginLayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        binding.checkSession.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(IS_AUTH_PREFERENCE, isChecked).apply()
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (email.isNotBlank() && password.isNotBlank()) {
                sharedPreferences.edit().putString(EMAIL_PREFERENCE, email).apply()
                findNavController().navigate(R.id.action_loginFragment_to_formFragment)
            }
        }
    }

    companion object {
        const val IS_AUTH_PREFERENCE = "isAuth"
        const val EMAIL_PREFERENCE = "email"
    }

}