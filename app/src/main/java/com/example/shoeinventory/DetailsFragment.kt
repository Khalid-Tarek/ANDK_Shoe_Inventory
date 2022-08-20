package com.example.shoeinventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.shoeinventory.databinding.FragmentDetailsBinding
import models.Shoe
import viewModels.ListViewModel

class DetailsFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var editsList: List<EditText>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.apply {
            editsList = listOf(editShoeName, editShoeBrand, editShoeSize, editShoeDescription)
        }

        val shoe = arguments?.getParcelable<Shoe>("shoe")

        shoe?.let {
            binding.myShoe = shoe
            turnOffEditing()
            hideButtons()
        } ?: run {
            binding.myShoe = Shoe("", 0.0, "", "")
            setupOnClickListeners()
        }

        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.apply {

            buttonCancel.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_detailsFragment_to_listingFragment)
            )

            buttonSave.setOnClickListener {
                if (anyEditIsEmpty())
                    Toast.makeText(context, "Please fill all fields!", Toast.LENGTH_SHORT).show()
                else {
                    viewModel.addShoe(myShoe!!)
                    findNavController().navigate(R.id.action_detailsFragment_to_listingFragment)
                }
            }

        }
    }

    private fun anyEditIsEmpty(): Boolean {
        editsList.forEach {
            if (it.text.isEmpty()) return true
        }
        return false
    }

    private fun turnOffEditing() {
        binding.apply {
            editsList.forEach { it.isEnabled = false }
        }
    }

    private fun hideButtons() {
        binding.apply {
            buttonCancel.visibility = View.INVISIBLE
            buttonSave.visibility = View.INVISIBLE
        }
    }
}