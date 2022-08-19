package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.shoeinventory.R
import com.squareup.picasso.Picasso
import models.Shoe

class ShoeListAdapter(context: Context, list: MutableList<Shoe>): BaseAdapter() {
    private var context: Context
    private var list: MutableList<Shoe>

    init {
        this.context = context
        this.list = list
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong();
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.shoe_layout, p2, false)

        val shoe = getItem(p0) as Shoe

        view.findViewById<TextView>(R.id.shoeName).text = shoe.name
        view.findViewById<TextView>(R.id.shoeBrand).text = shoe.company
        view.findViewById<TextView>(R.id.shoeSize).text = shoe.size.toString()
        view.findViewById<TextView>(R.id.shoeDescription).text = shoe.description
        Picasso.get()
            .load(if (shoe.images.isEmpty()) "https://files.letsrun.com/images/shoes/shoe-placeholder.png" else shoe.images[0])
            .into(view.findViewById<ImageView>(R.id.shoeImage))

        return view;
    }
}