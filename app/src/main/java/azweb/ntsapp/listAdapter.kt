package azweb.ntsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import java.security.AccessController.getContext

internal class HashMapArrayAdapter(val context: Context, textViewResourceId:Int, objects:List<Map.Entry<String, Any>>): ArrayAdapter(context, textViewResourceId, objects) {
    private class ViewHolder {
        var tV1:TextView
        var tV2:TextView
    }
    fun getView(position:Int, convertView:View?, parent:ViewGroup):View {
        val viewHolder:ViewHolder
        var convertView = convertView
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)
            viewHolder = ViewHolder()
            viewHolder.tV1 = convertView.findViewById(android.R.id.text1) as TextView
            viewHolder.tV2 = convertView.findViewById(android.R.id.text2) as TextView
            convertView.setTag(viewHolder)
        }
        else
            viewHolder = convertView.getTag() as ViewHolder
        val entry = this.getItem(position) as Map.Entry<String, Any>
        viewHolder.tV1.setText(entry.getKey())
        viewHolder.tV2.setText(entry.getValue().toString())
        return convertView!!
    }
}