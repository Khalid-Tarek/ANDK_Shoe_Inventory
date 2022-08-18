package com.example.shoeinventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.shoeinventory.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        listOf(binding.createButton, binding.loginButton).forEach{
            it.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_welcomeFragment)
            )
        }

        return binding.root
    }
}