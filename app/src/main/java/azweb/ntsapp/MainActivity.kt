package azweb.ntsapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ExpandableListView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_first.view.*
import kotlinx.android.synthetic.main.row_first_one.view.*
import kotlinx.android.synthetic.main.row_second.view.*
import kotlinx.android.synthetic.main.row_second_one.view.*
import kotlinx.android.synthetic.main.row_third.view.*
import java.util.ArrayList
import java.util.LinkedHashMap


/**
 * Add in parent for more main category
 * Define array for genre (subcategory) for each parent category added
 * Define LinkedHasMap for each subcategory where key is subcategory name, and value is a string array
 */
class MainActivity : AppCompatActivity() {

    lateinit var expandableListView: ExpandableListView
    var user:FirebaseUser?=null
    var auth:FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        auth?.signInWithEmailAndPassword("ios.app@azweb.dk","azweb@ios.app")
        user = auth?.currentUser
       // val ones = HashMap<String,Any>()

        //listView.adapter = listAdapter(this,ones)


        val twos = LinkedHashMap<String,LinkedHashMap<String,Any>>()


        //tekler
        val ones = LinkedHashMap<String,Any>()
        ones["kvm"] = 0
        ones["afdækning"] = false
        ones["fodpaneler"] = false
        ones["opsætning_af"] = "Title"
        ones["plet_spartling"] = false
        ones["vask_for_nikotin"] = false


        //dør
        val twos_dor = LinkedHashMap<String,Any>()
        twos_dor["antal"] = 0
        twos_dor["grundes"] = false
        twos_dor["males"] = false
        twos_dor["pletspartles"] = false
        twos_dor["slibes"] = false
        twos_dor["spartles"] = false
        twos_dor["vaskes"] = false
        twos["dør"] = twos_dor

        //filt
        val twos_filt = LinkedHashMap<String,Any>()
        twos_filt["loft"] = false
        twos_filt["vaeg"] = false
        twos["filt"] = twos_filt

        //fototapet
        val twos_fototapet = LinkedHashMap<String,Any>()
        twos_fototapet["loft"] = false
        twos_fototapet["vaeg"] = false
        twos["fototapet"] = twos_fototapet

        //fuldspartling
        val twos_fuldspartling = LinkedHashMap<String,Any>()
        twos_fuldspartling["antal"] = 0
        twos["fuldspartling"] = twos_fuldspartling

        //glasvæv
        val twos_glasvaev = LinkedHashMap<String,Any>()
        twos_glasvaev["loft"] = false
        twos_glasvaev["vaeg"] = false
        twos["glasvæv"] = twos_glasvaev

        //grunding
        val twos_grunding = LinkedHashMap<String,Any>()
        twos_grunding["knaste_grundes"] = false
        twos_grunding["loft"] = false
        twos_grunding["nikotin_stop_gr"] = false
        twos_grunding["vaeg"] = false
        twos["grunding"] = twos_grunding

        //magnetfilt
        val twos_magnetfilt = LinkedHashMap<String,Any>()
        twos_magnetfilt["loft"] = false
        twos_magnetfilt["vaeg"] = false
        twos["magnetfilt"] = twos_magnetfilt

        //maling
        val twos_maling = LinkedHashMap<String,Any>()
        val twos_maling_inside = LinkedHashMap<String,Any>()
        twos_maling_inside["antal_gange"] = 0
        twos_maling_inside["glans"] = false
        twos_maling["loft"] = twos_maling_inside
        twos_maling["vaeg"] = twos_maling_inside
        twos["maling"] = twos_maling


        //radiator
        val twos_radiator = LinkedHashMap<String,Any>()
        twos_radiator["antal"] = 0
        twos_radiator["grundes"] = false
        twos_radiator["males"] = false
        twos_radiator["pletspartles"] = false
        twos_radiator["slibes"] = false
        twos_radiator["spartles"] = false
        twos_radiator["vaskes"] = false
        twos["radiator"] = twos_radiator

        //rør
        val twos_ror = LinkedHashMap<String,Any>()
        twos_ror["antal"] = 0
        twos_ror["grundes"] = false
        twos_ror["males"] = false
        twos_ror["pletspartles"] = false
        twos_ror["slibes"] = false
        twos_ror["spartles"] = false
        twos_ror["vaskes"] = false
        twos["rør"] = twos_ror

        //slibning
        val twos_slibning = LinkedHashMap<String,Any>()
        twos_slibning["loft"] = false
        twos_slibning["vaeg"] = false
        twos["slibning"] = twos_slibning

        //specieltapet
        val twos_specieltapet = LinkedHashMap<String,Any>()
        twos_specieltapet["loft"] = false
        twos_specieltapet["vaeg"] = false
        twos["specieltapet"] = twos_specieltapet

        //tapet
        val twos_tapet = LinkedHashMap<String,Any>()
        twos_tapet["loft"] = false
        twos_tapet["vaeg"] = false
        twos["tapet"] = twos_tapet

        //tapet_skrubning
        val twos_tapet_skrubning = LinkedHashMap<String,Any>()
        twos_tapet_skrubning["antal"] = false
        twos["tapet_skrubning"] = twos_tapet_skrubning






        val okeys = ArrayList(ones.keys)
        for(key in okeys) {
            val rowParent = TableLayout(this)
            val row = TableRow(this)

            val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val cell = layoutInflater.inflate(R.layout.row_first_one, row, false)
            cell.text_first.text = ucwords(key.replace("_"," "))
            if(key=="kvm"){
                cell.textField_first.visibility = View.GONE
                cell.switcher_first.visibility = View.GONE
                cell.numberField_first.visibility = View.VISIBLE
            }else if(key=="opsætning_af"){
                cell.switcher_first.visibility = View.GONE
                cell.numberField_first.visibility = View.GONE
                cell.textField_first.visibility = View.VISIBLE
            }else{
                cell.numberField_first.visibility = View.GONE
                cell.textField_first.visibility = View.GONE
                cell.switcher_first.visibility = View.VISIBLE
            }



            rowParent.addView(cell)
            list.addView(rowParent)
        }


        val keys = ArrayList(twos.keys)
        for(key in keys){
            val rowParent = TableLayout(this)
            val rowOuter = TableLayout(this)
            val row = TableRow(this)
            val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val cell = layoutInflater.inflate(R.layout.row_first, row, false)
            cell.rowParentText.text = ucwords(key.replace("_"," "))
            val ckeys = ArrayList(twos[key]?.keys)
            if(key == "maling")
                rowOuter.addView(cell)
            else
                rowParent.addView(cell)


            for(ckey in ckeys){
                val rowChild = TableRow(this)
                val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val cell_child = layoutInflater.inflate(R.layout.row_second_one, rowChild, false)
                cell_child.text_second.text = ucwords(ckey.replace("_"," "))

                if(ckey == "antal"){
                    cell_child.textField_second.visibility = View.GONE
                    cell_child.switcher_second.visibility = View.GONE
                    cell_child.numberField_second.visibility = View.VISIBLE
                }else{
                    cell_child.textField_second.visibility = View.GONE
                    cell_child.numberField_second.visibility = View.GONE
                    cell_child.switcher_second.visibility = View.VISIBLE
                }

                cell_child.visibility = View.GONE

                if(key == "maling"){

                    val itkeys = ArrayList(twos[key]?.keys)
                    for(itkey in itkeys){
                        val rowChildInside = TableRow(this)
                        val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                        val cell_child = layoutInflater.inflate(R.layout.row_third, rowChildInside, false)
                        cell_child.rowThirdText.text = ucwords(itkey.replace("_", " "))
                        if(itkey == "antal_gange"){
                            cell_child.textField_third.visibility = View.GONE
                            cell_child.switcher_third.visibility = View.GONE
                            cell_child.numberField_third.visibility = View.VISIBLE
                        }else{
                            cell_child.textField_third.visibility = View.GONE
                            cell_child.numberField_third.visibility = View.GONE
                            cell_child.switcher_third.visibility = View.VISIBLE
                        }
                        rowOuter.addView(cell_child)
                    }

                    rowParent.addView(rowOuter)
                }else
                    rowParent.addView(cell_child)
                Log.d("-----a",key+" : "+ckey.toString())
            }

            list.addView(rowParent)
            cell.setOnClickListener {
                for(ind in 1 until  ckeys.size+1){
                    val view = rowParent.getChildAt(ind)
                    if(view.visibility == View.VISIBLE)
                        view.visibility = View.GONE
                    else
                        view.visibility = View.VISIBLE

                }

            }



        }


    }

    fun ucwords(str:String):String{
        val str = str
        val listStr:List<String>
        listStr = str.split(" ")
        var returnStr = ""
        for(list in listStr){
            returnStr+=list.substring(0,1).toUpperCase()+list.substring(1,list.length)+" "
        }
        returnStr = returnStr.substring(0,returnStr.length-1)
        return returnStr
    }
}
