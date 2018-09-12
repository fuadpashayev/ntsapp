package azweb.ntsapp

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_customer.view.*
import java.util.ArrayList

class CustomerModel{
    var child:String?=null
    var name:String?=null
    var user:String?=null

    constructor()
    constructor(child:String?,name:String?,user:String?){
        this.child = child
        this.name = name
        this.user = name
    }
}


class CustomerAdapter(var data: ArrayList<String?>): RecyclerView.Adapter<CustomerHolder>(){

    override fun getItemCount(): Int {
        Log.d("------a",data.size.toString())
        return data.size

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.layout_customer,parent,false)
        parent.loader.visibility = View.GONE
        Log.d("------a","createad")
        return CustomerHolder(cell)
    }


    override fun onBindViewHolder(holder: CustomerHolder, position: Int) {
        Log.d("------a","binded")
        holder.itemView.loader.visibility = View.GONE
        val data = data[position]
        holder.itemView.customerText.text = data
    }

}

class CustomerHolder(view: View?):RecyclerView.ViewHolder(view!!)