//package azweb.ntsapp
//
//import android.content.Context
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import azweb.ntsapp.R
//import azweb.ntsapp.SecondLevelAdapter
//import azweb.ntsapp.SecondLevelExpandableListView
//
//import java.util.ArrayList
//import java.util.HashMap
//import java.util.LinkedHashMap
//
//class ThreeLevelListAdapter(private val context: Context, var twoss: HashMap<String,*>, var threes: HashMap<String,HashMap<String,HashMap<String,Any>>>) : BaseExpandableListAdapter() {
//    var twos_keys:ArrayList<String>
//    var twos_keys_count:Int
//    var twos_inside_keys:ArrayList<String> = ArrayList()
//    var twos_inside_keys_count:Int = 0
//    var store_data = HashMap<String,Any>()
//    init {
//        store_data["kvm"] = 0
//        store_data["afdækning"] = false
//        store_data["fodpaneler"] = false
//        store_data["opsætning_af"] = "Title"
//        store_data["plet_spartling"] = false
//        store_data["vask_for_nikotin"] = false
//
//        twos_keys = ArrayList(twoss.keys)
//        twos_keys_count = twos_keys.size
//    }
//    override fun getGroupCount(): Int {
//        return twos_keys_count
//    }
//    override fun getChildrenCount(groupPosition: Int): Int {
//        return 1
//    }
//
//    override fun getGroup(groupPosition: Int): Any {
//        return groupPosition
//    }
//
//    override fun getChild(group: Int, child: Int): Any {
//        return child
//    }
//
//    override fun getGroupId(groupPosition: Int): Long {
//        return groupPosition.toLong()
//    }
//
//    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
//        return childPosition.toLong()
//    }
//
//    override fun hasStableIds(): Boolean {
//        return true
//    }
//
//    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View? {
//        var convertView = convertView
//
//
//        val twos_val = twoss[twos_keys[groupPosition]]
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        if (twos_val is HashMap<*, *>) {
//            convertView = inflater.inflate(R.layout.row_first, null)
//            twos_inside_keys = ArrayList((twoss[twos_keys[groupPosition]] as HashMap<String, *>).keys)
//            twos_inside_keys_count = twos_inside_keys.size
//        } else {
//            convertView = inflater.inflate(R.layout.row_first_one, null)
//            val switch = convertView.findViewById<Switch>(R.id.first_switch)
//            val numberField = convertView.findViewById<EditText>(R.id.numberField)
//            val textField = convertView.findViewById<EditText>(R.id.textField)
//            Log.d("------",twos_keys[groupPosition].toString())
//            if (twos_keys[groupPosition] == "kvm") {
//                switch.visibility = View.GONE
//                textField.visibility = View.GONE
//                numberField.setText("")
//            }else if(twos_keys[groupPosition] == "opsætning_af"){
//                numberField.visibility = View.GONE
//                switch.visibility = View.GONE
//                textField.setText("Title")
//            } else {
//                numberField.visibility = View.GONE
//                textField.visibility = View.GONE
//                switch.isChecked = false
//            }
//        }
//        val text = convertView.findViewById(R.id.rowParentText) as TextView
//        text.text = MainActivity().ucwords(twos_keys[groupPosition].replace("_"," "))
//
//        return convertView
//    }
//
//    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
////        val secondLevelELV = SecondLevelExpandableListView(context)
////        val headers = twos_inside_keys[groupPosition]
////        val childData = ArrayList<Array<String>>()
////        val secondLevelData = data[groupPosition]
////        for (key in secondLevelData.keys) {
////            childData.add(secondLevelData[key]!!)
////
////        }
////        secondLevelELV.setAdapter(SecondLevelAdapter(context, headers, childData))
////
////        secondLevelELV.setGroupIndicator(null)
////        return secondLevelELV
//
//        var convertView = convertView
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        convertView = inflater.inflate(R.layout.row_first, null)
//        val text = convertView.findViewById<TextView>(R.id.rowSecondText)
//        text.text = twos_inside_keys[groupPosition]
//        return convertView
//    }
//
//    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
//        return true
//    }
//}
