package azweb.ntsapp

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class ListAdapter(context:Context, listDataHeader:List<String>,listChildData:HashMap<String, List<String>>):BaseExpandableListAdapter() {
    private val _context:Context
    private val _listDataHeader:List<String> // header titles
    // child data in format of header title, child title
    private val _listDataChild:HashMap<String, List<String>>
    init{
        this._context = context
        this._listDataHeader = listDataHeader
        this._listDataChild = listChildData
    }
    override fun getGroupCount():Int {
            return this._listDataHeader.size
    }

    override fun getChild(groupPosition:Int, childPosititon:Int):Any {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))!!.get(childPosititon)
    }
    override fun getChildId(groupPosition:Int, childPosition:Int):Long {
        return childPosition.toLong()
    }
    override fun getChildView(groupPosition:Int, childPosition:Int, isLastChild:Boolean, convertView:View, parent:ViewGroup):View {
        val childText = getChild(groupPosition, childPosition) as String
        val infalInflater = this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val ConvertView = infalInflater.inflate(R.layout.data_item, null)
        val txtListChild = convertView.findViewById(R.id.dataList) as TextView
        txtListChild.text = childText
        return ConvertView
    }
    override fun getChildrenCount(groupPosition:Int):Int {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))!!.size
    }
    override fun getGroup(groupPosition:Int):Any {
        return this._listDataHeader.get(groupPosition)
    }
    override fun getGroupId(groupPosition:Int):Long {
        return groupPosition.toLong()
    }
    override fun getGroupView(groupPosition:Int, isExpanded:Boolean,
                     convertView:View, parent:ViewGroup):View {
        val headerTitle = getGroup(groupPosition) as String
        val infalInflater = this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val convertView = infalInflater.inflate(R.layout.data_item, null)
        val lblListHeader = convertView
                .findViewById(R.id.dataList) as TextView
        lblListHeader.setTypeface(null, Typeface.BOLD)
        lblListHeader.setText(headerTitle)
        return convertView
    }
    override fun hasStableIds():Boolean {
        return false
    }
    override fun isChildSelectable(groupPosition:Int, childPosition:Int):Boolean {
        return true
    }
}