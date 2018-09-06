package azweb.ntsapp

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.data_item.view.*

class dataAdapter(var data:ArrayList<String>):RecyclerView.Adapter<DataViewHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.data_item,parent,false)
        val holder = DataViewHolder(cell)
        return holder
    }



    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val Data = data[position]
        val hidden = arrayListOf<String>("a1","a2","b1","b2")
        if(hidden.contains(Data)) {
            holder.itemView.visibility = View.GONE
            holder.itemView.layoutParams = LinearLayout.LayoutParams(0,0)
        }else {
            holder.itemView.dataText.text = Data
        }


        holder.itemView.setOnClickListener {
            if(Data=="a"){

            }
        }
    }


}

class DataViewHolder(view: View):RecyclerView.ViewHolder(view){

}