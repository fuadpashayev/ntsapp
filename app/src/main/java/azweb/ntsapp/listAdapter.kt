//package azweb.ntsapp
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import java.security.AccessController.getContext
//import java.nio.file.Files.size
//import android.R.attr.keySet
//import android.support.v7.widget.SwitchCompat
//import android.widget.*
//import java.util.LinkedHashMap
//
//
//class listAdapter(var context:Context,var data: LinkedHashMap<String, Any>) : BaseAdapter() {
//
//    private var mData = LinkedHashMap<String, Any>()
//    private var mKeys: ArrayList<String>
//
//    init {
//        mData = data
//        mKeys = ArrayList(mData.keys)
//    }
//
//    override fun getCount(): Int {
//        return mData.size
//    }
//
//    override fun getItem(position: Int): Any? {
//        return mData[mKeys[position]]
//    }
//
//    override fun getItemId(arg0: Int): Long {
//        return arg0.toLong()
//    }
//
//    override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View? {
//        val key = mKeys[pos]
//        val Value = getItem(pos).toString()
//
//        var convertView = convertView
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        convertView = inflater.inflate(R.layout.row_first_one, null)
//        val text = convertView.findViewById(R.id.rowParentText) as TextView
//        val switch = convertView.findViewById<Switch>(R.id.first_switch)
//        val numberField = convertView.findViewById<EditText>(R.id.numberField)
//        mKeys[pos] = MainActivity().ucwords(mKeys[pos].replace("_"," "))
//        text.text = mKeys[pos]
//        if(this.mKeys[pos]=="Kvm"){
//            switch.visibility = View.GONE
//            numberField.setText("")
//        }else{
//            numberField.visibility = View.GONE
//            switch.isChecked = false
//        }
//
//
//        return convertView
//    }
//}