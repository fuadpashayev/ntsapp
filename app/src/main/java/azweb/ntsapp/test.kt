//package azweb.ntsapp
//
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.ExpandableListView
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.database.FirebaseDatabase
//import kotlinx.android.synthetic.main.activity_main.*
//import java.util.ArrayList
//import java.util.LinkedHashMap
//
//
///**
// * Add in parent for more main category
// * Define array for genre (subcategory) for each parent category added
// * Define LinkedHasMap for each subcategory where key is subcategory name, and value is a string array
// */
//class test : AppCompatActivity() {
//
//    lateinit var expandableListView: ExpandableListView
//    var user:FirebaseUser?=null
//    var auth:FirebaseAuth?=null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        auth = FirebaseAuth.getInstance()
//        auth?.signInWithEmailAndPassword("ios.app@azweb.dk","azweb@ios.app")
//        user = auth?.currentUser
//        // val ones = HashMap<String,Any>()
//
//        //listView.adapter = listAdapter(this,ones)
//
//
//        val twos = LinkedHashMap<String,Any>()
//        val twos_inside = LinkedHashMap<String,Any>()
//
//
//        //tekler
//        twos["kvm"] = 0
//        twos["afdækning"] = false
//        twos["fodpaneler"] = false
//        twos["opsætning_af"] = "Title"
//        twos["plet_spartling"] = false
//        twos["vask_for_nikotin"] = false
//
//
//        //dør
//        twos_inside["antal"] = 0
//        twos_inside["grundes"] = false
//        twos_inside["males"] = false
//        twos_inside["pletspartles"] = false
//        twos_inside["slibes"] = false
//        twos_inside["spartles"] = false
//        twos_inside["vaskes"] = false
//        twos["dør"] = twos_inside
//        twos_inside.clear()
//
//
//        //filt
//        twos_inside["loft"] = false
//        twos_inside["vaeg"] = false
//        twos["filt"] = twos_inside
//        twos_inside.clear()
//
//        //fototapet
//        twos_inside["loft"] = false
//        twos_inside["vaeg"] = false
//        twos["fototapet"] = twos_inside
//        twos_inside.clear()
//
//        //fuldspartling
//        twos_inside["antal"] = 0
//        twos["fuldspartling"] = twos_inside
//        twos_inside.clear()
//
//        //glasvæv
//        twos_inside["loft"] = false
//        twos_inside["vaeg"] = false
//        twos["glasvæv"] = twos_inside
//        twos_inside.clear()
//
//        //grunding
//        twos_inside["knaste_grundes"] = false
//        twos_inside["loft"] = false
//        twos_inside["nikotin_stop_gr"] = false
//        twos_inside["vaeg"] = false
//        twos["grunding"] = twos_inside
//        twos_inside.clear()
//
//        //magnetfilt
//        twos_inside["loft"] = false
//        twos_inside["vaeg"] = false
//        twos["magnetfilt"] = twos_inside
//        twos_inside.clear()
//
//        //radiator
//        twos_inside["antal"] = 0
//        twos_inside["grundes"] = false
//        twos_inside["males"] = false
//        twos_inside["pletspartles"] = false
//        twos_inside["slibes"] = false
//        twos_inside["spartles"] = false
//        twos_inside["vaskes"] = false
//        twos["radiator"] = twos_inside
//        twos_inside.clear()
//
//        //rør
//        twos_inside["antal"] = 0
//        twos_inside["grundes"] = false
//        twos_inside["males"] = false
//        twos_inside["pletspartles"] = false
//        twos_inside["slibes"] = false
//        twos_inside["spartles"] = false
//        twos_inside["vaskes"] = false
//        twos["rør"] = twos_inside
//        twos_inside.clear()
//
//        //slibning
//        twos_inside["loft"] = false
//        twos_inside["vaeg"] = false
//        twos["slibning"] = twos_inside
//        twos_inside.clear()
//
//        //specieltapet
//        twos_inside["loft"] = false
//        twos_inside["vaeg"] = false
//        twos["specieltapet"] = twos_inside
//        twos_inside.clear()
//
//        //tapet
//        twos_inside["loft"] = false
//        twos_inside["vaeg"] = false
//        twos["tapet"] = twos_inside
//        twos_inside.clear()
//
//        //tapet_skrubning
//        twos_inside["antal"] = false
//        twos["tapet_skrubning"] = twos_inside
//        twos_inside.clear()
//
//
//
//
//        //maling
//        val threes = HashMap<String,HashMap<String,HashMap<String,Any>>>()
//        val threes_inside_child = HashMap<String,Any>()
//        val threes_inside_parent = HashMap<String,HashMap<String,Any>>()
//        threes_inside_child["antal_gange"] = 0
//        threes_inside_child["glans"] = false
//        threes_inside_parent["loft"] = threes_inside_child
//        threes_inside_parent["vaeg"] = threes_inside_child
//        threes["maling"] = threes_inside_parent
//
//
//
//
//
//
//
//
//
//
//        // expandableListView = expandable_listview
//
//        val threeLevelListAdapterAdapter = ThreeLevelListAdapter(this, twos, threes)
//
//
//        expandableListView.setAdapter(threeLevelListAdapterAdapter)
//        expandableListView.setGroupIndicator(null)
//        expandableListView.setChildIndicator(null)
//
//
//
//    }
//
//    fun ucwords(str:String):String{
//        val str = str
//        val listStr:List<String>
//        listStr = str.split(" ")
//        var returnStr = ""
//        for(list in listStr){
//            returnStr+=list.substring(0,1).toUpperCase()+list.substring(1,list.length)+" "
//        }
//        returnStr = returnStr.substring(0,returnStr.length-1)
//        return returnStr
//    }
//}
