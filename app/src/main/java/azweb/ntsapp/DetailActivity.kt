package azweb.ntsapp

import android.app.Activity
import android.content.Context
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.row_first.view.*
import kotlinx.android.synthetic.main.row_first_one.*
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
            override fun onDataChange(snap:DataSnapshot?) {
                loader.visibility = View.GONE
                val ones = LinkedHashMap<String,Any>()
                val twos = LinkedHashMap<String,LinkedHashMap<String,Any>>()
                for(datas in snap!!.children){
                    //tekler

                    ones["kvm"] = snap.child("kvm").getValue(Int::class.java)!!
                    ones["afdækning"] = snap.child("afdækning").getValue(Boolean::class.java)!!
                    ones["vask_for_nikotin"] = snap.child("vask_for_nikotin").getValue(Boolean::class.java)!!
                    ones["plet_spartling"] = snap.child("plet_spartling").getValue(Boolean::class.java)!!
                    ones["fodpaneler"] = snap.child("fodpaneler").getValue(Boolean::class.java)!!




                    //tapet_skrubning
                    val twos_tapet_skrubning = LinkedHashMap<String,Any>()
                    twos_tapet_skrubning["antal"] = snap.child("tapet_skrubning/antal").getValue(Int::class.java)!!
                    twos["tapet_skrubning"] = twos_tapet_skrubning

                    //fuldspartling
                    val twos_fuldspartling = LinkedHashMap<String,Any>()
                    twos_fuldspartling["antal"] = snap.child("fuld_spartling/antal").getValue(Int::class.java)!!
                    twos["fuld_spartling"] = twos_fuldspartling

                    //slibning
                    val twos_slibning = LinkedHashMap<String,Any>()
                    twos_slibning["loft"] = snap.child("slibning/loft").getValue(Boolean::class.java)!!
                    twos_slibning["vaeg"] = snap.child("slibning/vaeg").getValue(Boolean::class.java)!!
                    twos["slibning"] = twos_slibning

                    //grunding
                    val twos_grunding = LinkedHashMap<String,Any>()
                    twos_grunding["knaste_grundes"] = snap.child("grunding/knaste_grundes").getValue(Boolean::class.java)!!
                    twos_grunding["loft"] = snap.child("grunding/loft").getValue(Boolean::class.java)!!
                    twos_grunding["nikotin_stop_gr"] = snap.child("grunding/nikotin_stop_gr").getValue(Boolean::class.java)!!
                    twos_grunding["vaeg"] = snap.child("grunding/vaeg").getValue(Boolean::class.java)!!
                    twos["grunding"] = twos_grunding
                    //opsaetning af
                    //twos["opsaetning_af"] = "Title"

                    //filt
                    val twos_filt = LinkedHashMap<String,Any>()
                    twos_filt["loft"] = snap.child("filt/loft").getValue(Boolean::class.java)!!
                    twos_filt["vaeg"] = snap.child("filt/vaeg").getValue(Boolean::class.java)!!
                    twos["filt"] = twos_filt

                    //tapet
                    val twos_tapet = LinkedHashMap<String,Any>()
                    twos_tapet["loft"] = snap.child("tapet/loft").getValue(Boolean::class.java)!!
                    twos_tapet["vaeg"] = snap.child("tapet/vaeg").getValue(Boolean::class.java)!!
                    twos["tapet"] = twos_tapet

                    //glasvæv
                    val twos_glasvaev = LinkedHashMap<String,Any>()
                    twos_glasvaev["loft"] = snap.child("glasvæv/loft").getValue(Boolean::class.java)!!
                    twos_glasvaev["vaeg"] = snap.child("glasvæv/vaeg").getValue(Boolean::class.java)!!
                    twos["glasvæv"] = twos_glasvaev

                    //fototapet
                    val twos_fototapet = LinkedHashMap<String,Any>()
                    twos_fototapet["loft"] = snap.child("fototapet/loft").getValue(Boolean::class.java)!!
                    twos_fototapet["vaeg"] = snap.child("fototapet/vaeg").getValue(Boolean::class.java)!!
                    twos["fototapet"] = twos_fototapet

                    //specieltapet
                    val twos_specieltapet = LinkedHashMap<String,Any>()
                    twos_specieltapet["loft"] = snap.child("specieltapet/loft").getValue(Boolean::class.java)!!
                    twos_specieltapet["vaeg"] = snap.child("specieltapet/vaeg").getValue(Boolean::class.java)!!
                    twos["specieltapet"] = twos_specieltapet

                    //magnetfilt
                    val twos_magnetfilt = LinkedHashMap<String,Any>()
                    twos_magnetfilt["loft"] = snap.child("magnetfilt/loft").getValue(Boolean::class.java)!!
                    twos_magnetfilt["vaeg"] = snap.child("magnetfilt/vaeg").getValue(Boolean::class.java)!!
                    twos["magnetfilt"] = twos_magnetfilt

                    //maling
                    val twos_maling = LinkedHashMap<String,Any>()
                    val twos_maling_inside = LinkedHashMap<String,Any>()
                    val twos_maling_inside1 = LinkedHashMap<String,Any>()
                    twos_maling_inside["antal_gange"] = snap.child("maling/loft/antal_gange").getValue(Int::class.java)!!
                    twos_maling_inside["glans"] = snap.child("maling/loft/glans").getValue(Boolean::class.java)!!
                    twos_maling_inside1["antal_gange"] = snap.child("maling/vaeg/antal_gange").getValue(Int::class.java)!!
                    twos_maling_inside1["glans"] = snap.child("maling/vaeg/glans").getValue(Boolean::class.java)!!
                    twos_maling["loft"] = twos_maling_inside
                    twos_maling["vaeg"] = twos_maling_inside1
                    twos["maling"] = twos_maling

                    //radiator
                    val twos_radiator = LinkedHashMap<String,Any>()
                    twos_radiator["antal"] = snap.child("radiator/antal").getValue(Int::class.java)!!
                    twos_radiator["grundes"] = snap.child("radiator/grundes").getValue(Boolean::class.java)!!
                    twos_radiator["males"] = snap.child("radiator/males").getValue(Boolean::class.java)!!
                    twos_radiator["pletspartles"] = snap.child("radiator/pletspartles").getValue(Boolean::class.java)!!
                    twos_radiator["slibes"] = snap.child("radiator/slibes").getValue(Boolean::class.java)!!
                    twos_radiator["spartles"] = snap.child("radiator/spartles").getValue(Boolean::class.java)!!
                    twos_radiator["vaskes"] = snap.child("radiator/vaskes").getValue(Boolean::class.java)!!
                    twos["radiator"] = twos_radiator

                    //dør
                    val twos_dor = LinkedHashMap<String,Any>()
                    twos_dor["antal"] =  snap.child("dør/antal").getValue(Int::class.java)!!
                    twos_dor["grundes"] = snap.child("dør/grundes").getValue(Boolean::class.java)!!
                    twos_dor["males"] = snap.child("dør/males").getValue(Boolean::class.java)!!
                    twos_dor["pletspartles"] = snap.child("dør/pletspartles").getValue(Boolean::class.java)!!
                    twos_dor["slibes"] = snap.child("dør/slibes").getValue(Boolean::class.java)!!
                    twos_dor["spartles"] = snap.child("dør/spartles").getValue(Boolean::class.java)!!
                    twos_dor["vaskes"] = snap.child("dør/vaskes").getValue(Boolean::class.java)!!
                    twos["dør"] = twos_dor

                    //rør
                    val twos_ror = LinkedHashMap<String,Any>()
                    twos_ror["antal"] = snap.child("rør/antal").getValue(Int::class.java)!!
                    twos_ror["grundes"] = snap.child("rør/grundes").getValue(Boolean::class.java)!!
                    twos_ror["males"] = snap.child("rør/males").getValue(Boolean::class.java)!!
                    twos_ror["pletspartles"] = snap.child("rør/pletspartles").getValue(Boolean::class.java)!!
                    twos_ror["slibes"] = snap.child("rør/slibes").getValue(Boolean::class.java)!!
                    twos_ror["spartles"] = snap.child("rør/spartles").getValue(Boolean::class.java)!!
                    twos_ror["vaskes"] = snap.child("rør/vaskes").getValue(Boolean::class.java)!!
                    twos["rør"] = twos_ror

                }


                val okeys = ArrayList(ones.keys)
                for(key in okeys) {

                    val rowParent = TableLayout(this@DetailActivity)
                    val row = TableRow(this@DetailActivity)

                    val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val cell = layoutInflater.inflate(R.layout.row_first_one, row, false)
                    cell.text_first.text = ucwords(key.replace("_"," "))
                    if(key=="kvm"){
                        cell.switcher_first.visibility = View.GONE
                        cell.numberField_first.visibility = View.VISIBLE
                        cell.numberField_first.setText(ones[key].toString())
                        cell.numberField_first.addTextChangedListener(object:TextWatcher{
                            override fun afterTextChanged(p0: Editable?) {}
                            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {

                                Handler().postDelayed({
                                    val data = HashMap<String,Any>()
                                    val text = text.toString()
                                    val num = if(text!="") text.toInt() else 0
                                    data[key] = num
                                    FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId").updateChildren(data)
                                },500)
                            }

                        })
                    }else{
                        cell.numberField_first.visibility = View.GONE
                        cell.switcher_first.visibility = View.VISIBLE
                        cell.switcher_first.isChecked = ones[key] as Boolean
                        cell.switcher_first.setOnCheckedChangeListener { compoundButton, checked ->
                          Handler().postDelayed({
                              val data = HashMap<String,Any>()
                              data[key] = checked
                              FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId").updateChildren(data)
                          },200)

                        }
                    }



                    rowParent.addView(cell)
                    list.addView(rowParent)

                }


                val keys = ArrayList(twos.keys)

                for(key in keys){

                    val rowParent = TableLayout(this@DetailActivity)
                    val row = TableRow(this@DetailActivity)
                    val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val cell = layoutInflater.inflate(R.layout.row_first, row, false)
                    cell.rowParentText.text = ucwords(key.replace("_"," "))
                    val ckeys = ArrayList((twos[key] as LinkedHashMap<String,Any>)?.keys)
                    rowParent.addView(cell)


                    for(ckey in ckeys){
                        val rowChild = TableRow(this@DetailActivity)
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
                                cell_child.numberField_second.setText(twos[key]!![ckey].toString())
                                cell_child.numberField_second.addTextChangedListener(object:TextWatcher{
                                    override fun afterTextChanged(p0: Editable?) {}
                                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                                    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {

                                        Handler().postDelayed({
                                            val data = HashMap<String,Any>()
                                            val text = text.toString()
                                            val num = if(text!="") text.toInt() else 0
                                            data[ckey] = num
                                            FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId/$key").updateChildren(data)
                                        },500)
                                    }

                                })
                            }else{
                                cell_child.numberField_second.visibility = View.GONE
                                cell_child.switcher_second.visibility = View.VISIBLE
                                cell_child.switcher_second.isChecked = twos[key]!![ckey] as Boolean
                                cell_child.switcher_second.setOnCheckedChangeListener { compoundButton, checked ->
                                    Handler().postDelayed({
                                        val data = HashMap<String,Any>()
                                        data[ckey] = checked
                                        FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId/$key").updateChildren(data)
                                    },200)

                                }

                            }

                        }
                        cell_child.visibility = View.GONE





                        rowParent.addView(cell_child)

                        if(key == "maling"){
                            val prnt = (twos[key]!! as LinkedHashMap<String,Any>)[ckey] as HashMap<String,Any>
                            val itkeys = ArrayList(prnt.keys)

                            for(itkey in itkeys){
                                val rowChildInside = TableRow(this@DetailActivity)
                                val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                                val cell = layoutInflater.inflate(R.layout.row_third, rowChildInside, false)
                                cell.rowThirdText.text = ucwords(itkey.replace("_", " "))
                                if(itkey == "antal_gange"){
                                    cell.switcher_third.visibility = View.GONE
                                    cell.numberField_third.visibility = View.VISIBLE
                                    cell.numberField_third.setText(prnt[itkey].toString())
                                    cell.numberField_third.addTextChangedListener(object:TextWatcher{
                                        override fun afterTextChanged(p0: Editable?) {}
                                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                                        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {

                                            Handler().postDelayed({
                                                val data = HashMap<String,Any>()
                                                val text = text.toString()
                                                val num = if(text!="") text.toInt() else 0
                                                data[itkey] = num
                                                FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId/$key/$ckey").updateChildren(data)
                                            },500)
                                        }

                                    })
                                }else{
                                    cell.numberField_third.visibility = View.GONE
                                    cell.switcher_third.visibility = View.VISIBLE
                                    cell.switcher_third.isChecked = prnt[itkey] as Boolean
                                    cell.switcher_third.setOnCheckedChangeListener { compoundButton, checked ->
                                        Handler().postDelayed({
                                            val data = HashMap<String,Any>()
                                            data[itkey] = checked
                                            FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId/$key/$ckey").updateChildren(data)
                                        },200)

                                    }
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
                        val rowChildInside = TableRow(this@DetailActivity)
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
                                    view.expand(65.0)
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

                            }
                            val index = keys.indexOf(key)
                            val lastIndex = keys.size-1
                            if(index==lastIndex) {
                                scrollMain.postDelayed({ scrollMain.fullScroll(View.FOCUS_DOWN)},350)
                            }




                        }
                    }

                }


            }



        })
        FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId/images").addChildEventListener(object:ChildEventListener{
            override fun onCancelled(p0: DatabaseError?) {}
            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {}
            override fun onChildChanged(snap: DataSnapshot?, p1: String?) {

            }
            override fun onChildAdded(snap: DataSnapshot?, p1: String?) {
                class imageClass{
                    var imageURL:String?=null
                    constructor()
                    constructor(imageURL:String){
                        this.imageURL = imageURL
                    }
                }
                imageLabel.visibility = View.VISIBLE
                    val imageData = snap!!.getValue(imageClass::class.java)
                    val image = ImageView(this@DetailActivity)
                    image.setBackgroundResource(R.drawable.border_all)
                    Log.d("-------a",imageData!!.imageURL)
                    val params = ConstraintLayout.LayoutParams(150.dp,140.dp)
                    params.setMargins(10.dp,5.dp,10.dp,5.dp)
                    image.adjustViewBounds = true
                    image.scaleType = ImageView.ScaleType.CENTER_CROP
                    image.clipToOutline = true
                    image.elevation = (1.dp).toFloat()
                    image.layoutParams = params



                    Glide.with(this@DetailActivity)
                            .load(imageData.imageURL)
                            .thumbnail(Glide.with(this@DetailActivity).load(R.mipmap.image))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .crossFade()
                            .into(image)
                    imageListData.addView(image)



            }

            override fun onChildRemoved(p0: DataSnapshot?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        back.setOnClickListener {
            finish()
        }
        addImage.setOnClickListener {
            val newImg = "https://firebasestorage.googleapis.com/v0/b/ntsapp-83e49.appspot.com/o/images%2FAE3C3DC9-657A-400B-9C8B-7998516B02C2?alt=media&token=c8587017-c865-420d-9faa-eee34eaa0bf9"
            val data = HashMap<String,Any>()
            data["imageURL"] = newImg
            FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId/images").push().setValue(data)
        }

    }


    val Int.dp: Int get() = (this * resources.displayMetrics.density).toInt()


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
