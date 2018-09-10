package azweb.ntsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import azweb.ntsapp.R
import azweb.ntsapp.SecondLevelAdapter
import azweb.ntsapp.SecondLevelExpandableListView

import java.util.ArrayList
import java.util.HashMap
import java.util.LinkedHashMap

class ThreeLevelListAdapter(private val context: Context, internal var parentHeaders: Array<String>, internal var secondLevel: List<Array<String>>, internal var data: List<LinkedHashMap<String, Array<String>>>) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return parentHeaders.size
    }
    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getGroup(groupPosition: Int): Any {
        return groupPosition
    }

    override fun getChild(group: Int, child: Int): Any {
        return child
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        convertView = inflater.inflate(R.layout.row_first, null)
        val text = convertView.findViewById(R.id.rowParentText) as TextView
        text.text = this.parentHeaders[groupPosition]

        return convertView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        val secondLevelELV = SecondLevelExpandableListView(context)
        val headers = secondLevel[groupPosition]
        val childData = ArrayList<Array<String>>()
        val secondLevelData = data[groupPosition]
        for (key in secondLevelData.keys) {
            childData.add(secondLevelData[key]!!)

        }
        secondLevelELV.setAdapter(SecondLevelAdapter(context, headers, childData))

        secondLevelELV.setGroupIndicator(null)
        return secondLevelELV
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
