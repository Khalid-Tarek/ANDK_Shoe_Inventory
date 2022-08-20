package com.example.shoeinventory

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.shoeinventory.databinding.FragmentListingBinding
import com.example.shoeinventory.databinding.ShoeLayoutBinding
import com.squareup.picasso.Picasso
import models.Shoe
import viewModels.ListViewModel

class ListingFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    private lateinit var menuHost: MenuHost
    private lateinit var myMenuProvider: MenuProvider

    private lateinit var shoeListView: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentListingBinding.inflate(inflater)

        viewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]

        menuHost = requireActivity()

        myMenuProvider = getMenuProvider()
        menuHost.addMenuProvider(myMenuProvider)

        shoeListView = binding.shoeList

        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            Log.i("ListingFragment", "Called the observer, ${viewModel.shoeList.value}")
            shoeListView.removeAllViews()
            viewModel.shoeList.value!!.forEach {
                addShoeView(it)
            }
        })

        binding.fab.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_listingFragment_to_detailsFragment)
        )

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        menuHost.removeMenuProvider(myMenuProvider)
    }

    private fun addShoeView(shoe: Shoe) {

        val binding = ShoeLayoutBinding.inflate(layoutInflater, shoeListView, false)

        binding.shoeName.text = shoe.name
        binding.shoeBrand.text = shoe.company
        binding.shoeSize.text = shoe.size.toString()
        binding.shoeDescription.text = shoe.description

        //Use a placeholder image if the shoe doesn't have any pictures associated with it
        Picasso.get()
            .load(if (shoe.images.isEmpty()) "https://files.letsrun.com/images/shoes/shoe-placeholder.png" else shoe.images[0])
            .into(binding.shoeImage)

        val bundle = bundleOf("shoe" to shoe)

        binding.root.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_listingFragment_to_detailsFragment, bundle)
        )

        shoeListView.addView(binding.root)
    }

    private fun getMenuProvider(): MenuProvider {
        return object: MenuProvider {
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
    }
}