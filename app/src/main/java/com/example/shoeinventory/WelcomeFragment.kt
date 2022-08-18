package com.example.shoeinventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.shoeinventory.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        binding.nextButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_instructionsFragment)
        )
        return binding.root
    }
}