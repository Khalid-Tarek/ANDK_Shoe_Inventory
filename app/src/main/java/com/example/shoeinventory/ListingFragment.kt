package com.example.shoeinventory

import adapters.ShoeListAdapter
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoeinventory.databinding.FragmentListingBinding
import viewModels.ListViewModel

class ListingFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    private lateinit var menuHost: MenuHost
    private lateinit var myMenuProvider: MenuProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentListingBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]

        menuHost = requireActivity()

        myMenuProvider = object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.my_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.logout){
                    findNavController().navigate(R.id.action_listingFragment_to_loginFragment)
                    return true
                }
                return false
            }
        }
        menuHost.addMenuProvider(myMenuProvider)

        val shoeListAdapter = ShoeListAdapter(requireContext(), viewModel.shoeList.value!!)
        for(i in 0 until shoeListAdapter.count){
            binding.shoeList.addView(shoeListAdapter.getView(i, null, binding.shoeList))
        }

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        menuHost.removeMenuProvider(myMenuProvider)
    }
}