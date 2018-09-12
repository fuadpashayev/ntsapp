package azweb.ntsapp

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_customer.view.*
import java.util.ArrayList

class RoomModel{
    var child:String?=null
    var name:String?=null
    var imageUrl:String?=null
    var kvm:Int?=null

    constructor()
    constructor(child:String?,name:String?,imageUrl:String?,kvm:Int?){
        this.child = child
        this.name = name
        this.imageUrl = imageUrl
        this.kvm = kvm
    }
}


class RoomAdapter(var data: ArrayList<String?>): RecyclerView.Adapter<RoomHolder>(){

    override fun getItemCount(): Int {
        Log.d("------a",data.size.toString())
        return data.size

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.layout_customer,parent,false)
        parent.loader.visibility = View.GONE
        Log.d("------a","createad")
        return RoomHolder(cell)
    }


    override fun onBindViewHolder(holder: RoomHolder, position: Int) {
        Log.d("------a","binded")
        holder.itemView.loader.visibility = View.GONE
        val data = data[position]
        holder.itemView.customerText.text = data
    }

}

class RoomHolder(view: View?):RecyclerView.ViewHolder(view!!)