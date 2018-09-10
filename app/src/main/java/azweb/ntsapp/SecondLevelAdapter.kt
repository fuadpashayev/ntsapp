package azweb.ntsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

import java.util.HashMap


class SecondLevelAdapter(private val context: Context, internal var headers: Array<String>, internal var data: List<Array<String>>) : BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int): Any {
        return headers[groupPosition]
    }

    override fun getGroupCount(): Int {
        return headers.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        convertView = inflater.inflate(R.layout.row_second, null)
        val text = convertView.findViewById<View>(R.id.rowSecondText) as TextView
        val groupText = getGroup(groupPosition).toString()
        text.text = groupText

        return convertView
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        val childData: Array<String>
        childData = data[groupPosition]
        return childData[childPosition]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        convertView = inflater.inflate(R.layout.row_third, null)
        val textView = convertView.findViewById<TextView>(R.id.rowThirdText)
        val childArray = data[groupPosition]
        val text = childArray[childPosition]
        textView.text = text
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        val children = data[groupPosition]
        return children.size
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}