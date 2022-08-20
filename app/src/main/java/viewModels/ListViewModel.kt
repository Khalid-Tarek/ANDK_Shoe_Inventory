package viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import models.Shoe

class ListViewModel: ViewModel() {
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        initializeShoeList()
    }

    private fun initializeShoeList(){
        _shoeList.value = mutableListOf(Shoe("Killshot 2", 18.0, "Nike", "Amazing Sneakers"),
            Shoe("Blazer", 20.0, "Nike", "Awesome Vintage Sneakers"),
            Shoe("UltraBoost 22", 19.0, "Adidas", "Comfortable Sneakers"),
            Shoe("Forum 84", 18.0, "Adidas", "Low Sneakers"),
            Shoe("Chuck 70", 17.0, "Converse", "Mule"),
            Shoe("Zig Kinetica II", 21.0, "Rebook", "Stylish Shoes"))
    }

    fun addShoe(newShoe: Shoe) {
        _shoeList.value?.add(newShoe)
        _shoeList.value = _shoeList.value
    }
}