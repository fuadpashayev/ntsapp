package azweb.ntsapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ExpandableListView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.android.synthetic.main.row_first.view.*
import kotlinx.android.synthetic.main.row_first_one.view.*
import kotlinx.android.synthetic.main.row_second.view.*
import kotlinx.android.synthetic.main.row_second_one.view.*
import kotlinx.android.synthetic.main.row_third.view.*
import java.util.ArrayList
import java.util.LinkedHashMap





class DetailActivity : AppCompatActivity() {

    var user:FirebaseUser?=null
    var auth:FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        auth = FirebaseAuth.getInstance()
        user = auth?.currentUser
        supportActionBar!!.hide()
        val data = intent.extras
        val roomName = data.getString("roomName")
        val roomId = data.getString("roomId")
        val customerId = data.getString("customerId")
        detailHeaderText.text = roomName

        FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId").addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {}
            override fun onDataChange(snap: DataSnapshot?) {
                Log.d("-------hola",snap.toString())
                for(datas in snap!!.children){
                    val data = datas.getValue(DetailModel::class.java)

                }
            }

        })

        val twos = LinkedHashMap<String,LinkedHashMap<String,Any>>()
        //tekler
        val ones = LinkedHashMap<String,Any>()
        ones["kvm"] = 0
        ones["afdækning"] = false
        ones["vask_for_nikotin"] = false
        ones["plet_spartling"] = false
        ones["fodpaneler"] = false

        //tapet_skrubning
        val twos_tapet_skrubning = LinkedHashMap<String,Any>()
        twos_tapet_skrubning["antal"] = false
        twos["tapet_skrubning"] = twos_tapet_skrubning

        //fuldspartling
        val twos_fuldspartling = LinkedHashMap<String,Any>()
        twos_fuldspartling["antal"] = 0
        twos["fuldspartling"] = twos_fuldspartling

        //slibning
        val twos_slibning = LinkedHashMap<String,Any>()
        twos_slibning["loft"] = false
        twos_slibning["vaeg"] = false
        twos["slibning"] = twos_slibning

        //grunding
        val twos_grunding = LinkedHashMap<String,Any>()
        twos_grunding["knaste_grundes"] = false
        twos_grunding["loft"] = false
        twos_grunding["nikotin_stop_gr"] = false
        twos_grunding["vaeg"] = false
        twos["grunding"] = twos_grunding
        //opsaetning af
        //twos["opsaetning_af"] = "Title"

        //filt
        val twos_filt = LinkedHashMap<String,Any>()
        twos_filt["loft"] = false
        twos_filt["vaeg"] = false
        twos["filt"] = twos_filt

        //tapet
        val twos_tapet = LinkedHashMap<String,Any>()
        twos_tapet["loft"] = false
        twos_tapet["vaeg"] = false
        twos["tapet"] = twos_tapet

        //glasvæv
        val twos_glasvaev = LinkedHashMap<String,Any>()
        twos_glasvaev["loft"] = false
        twos_glasvaev["vaeg"] = false
        twos["glasvæv"] = twos_glasvaev

        //fototapet
        val twos_fototapet = LinkedHashMap<String,Any>()
        twos_fototapet["loft"] = false
        twos_fototapet["vaeg"] = false
        twos["fototapet"] = twos_fototapet

        //specieltapet
        val twos_specieltapet = LinkedHashMap<String,Any>()
        twos_specieltapet["loft"] = false
        twos_specieltapet["vaeg"] = false
        twos["specieltapet"] = twos_specieltapet

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




        val okeys = ArrayList(ones.keys)
        for(key in okeys) {
            val rowParent = TableLayout(this)
            val row = TableRow(this)

            val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val cell = layoutInflater.inflate(R.layout.row_first_one, row, false)
            cell.text_first.text = ucwords(key.replace("_"," "))
            if(key=="kvm"){
                cell.switcher_first.visibility = View.GONE
                cell.numberField_first.visibility = View.VISIBLE
            }else if(key=="opsætning_af"){
                cell.switcher_first.visibility = View.GONE
                cell.numberField_first.visibility = View.GONE
            }else{
                cell.numberField_first.visibility = View.GONE
                cell.switcher_first.visibility = View.VISIBLE
            }



            rowParent.addView(cell)
            list.addView(rowParent)
        }


        val keys = ArrayList(twos.keys)

        for(key in keys){

            val rowParent = TableLayout(this)
            val row = TableRow(this)
            val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val cell = layoutInflater.inflate(R.layout.row_first, row, false)
            cell.rowParentText.text = ucwords(key.replace("_"," "))
            val ckeys = ArrayList((twos[key] as LinkedHashMap<String,Any>)?.keys)
            rowParent.addView(cell)


            for(ckey in ckeys){
                val rowChild = TableRow(this)
                val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var cell_child:View?=null
                if(key=="maling") {
                    cell_child = layoutInflater.inflate(R.layout.row_second, rowChild, false)
                    cell_child.rowSecondText.text = ucwords(ckey.replace("_", " "))
                }else {
                    cell_child = layoutInflater.inflate(R.layout.row_second_one, rowChild, false)
                    cell_child.text_second.text = ucwords(ckey.replace("_", " "))
                    if(ckey == "antal"){
                        cell_child.switcher_second.visibility = View.GONE
                        cell_child.numberField_second.visibility = View.VISIBLE
                    }else{
                        cell_child.numberField_second.visibility = View.GONE
                        cell_child.switcher_second.visibility = View.VISIBLE
                    }

                }
                cell_child.visibility = View.GONE





                rowParent.addView(cell_child)

                if(key == "maling"){
                    val prnt = (twos[key]!! as LinkedHashMap<String,Any>)[ckey] as HashMap<String,Any>
                    val itkeys = ArrayList(prnt.keys)

                    for(itkey in itkeys){
                        val rowChildInside = TableRow(this)
                        val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                        val cell = layoutInflater.inflate(R.layout.row_third, rowChildInside, false)
                        cell.rowThirdText.text = ucwords(itkey.replace("_", " "))
                        if(itkey == "antal_gange"){
                            cell.switcher_third.visibility = View.GONE
                            cell.numberField_third.visibility = View.VISIBLE
                        }else{
                            cell.numberField_third.visibility = View.GONE
                            cell.switcher_third.visibility = View.VISIBLE
                        }
                        cell.visibility = View.GONE
                        rowParent.addView(cell)

                    }
                }

                if(key=="maling") {
                    cell_child.setOnClickListener {
                        val index = ckeys.indexOf(ckey)
                        var inds:Array<Int> = arrayOf()
                        if(index==0)
                            inds = arrayOf(2, 3)
                        else if(index==1)
                            inds = arrayOf(5, 6)
                        for (ind in inds) {
                            val view = rowParent.getChildAt(ind)
                            if (view.visibility == View.VISIBLE) {
                                view.collapse()
                                cell_child.arrow_second.setImageResource(R.drawable.ic_right_arrow)
                            }else {
                                view.expand()
                                cell_child.arrow_second.setImageResource(R.drawable.ic_down_arrow)
                            }

                        }

                    }
                }
            }


            list.addView(rowParent)
            if(key == "grunding"){
                val rowChildInside = TableRow(this)
                val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val cell = layoutInflater.inflate(R.layout.row_first, rowChildInside, false)
                cell.rowParentText.text = "Opsaetning af"
                cell.arrow_first.visibility = View.GONE
                list.addView(cell)
            }

            if(key == "maling"){
                cell.setOnClickListener {
                    val inds = arrayOf(1,4)
                    for(ind in inds){
                        val view = rowParent.getChildAt(ind)
                        if(view.visibility == View.VISIBLE) {
                            view.collapse()
                            cell.arrow_first.setImageResource(R.drawable.ic_right_arrow)
                        }else {
                            view.expand()
                            cell.arrow_first.setImageResource(R.drawable.ic_down_arrow)
                        }

                    }

                }
            }else{
                cell.setOnClickListener {
                    for(ind in 1 until  ckeys.size+1){
                        val view = rowParent.getChildAt(ind)
                        if(view.visibility == View.VISIBLE) {
                            view.collapse()
                            cell.arrow_first.setImageResource(R.drawable.ic_right_arrow)
                        }else {
                            view.expand()
                            cell.arrow_first.setImageResource(R.drawable.ic_down_arrow)
                        }
                        if(ind == ckeys.size){
                            Log.d("-------a",ind.toString())
                            //scrollMain.post( { scrollMain.fullScroll(View.FOCUS_DOWN) })
                        }
                    }

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
