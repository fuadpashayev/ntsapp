package azweb.ntsapp

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.row_first.view.*
import kotlinx.android.synthetic.main.row_first_one.view.*
import kotlinx.android.synthetic.main.row_second.view.*
import kotlinx.android.synthetic.main.row_second_one.view.*
import kotlinx.android.synthetic.main.row_third.view.*
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.StrictMode
import android.provider.MediaStore
import android.support.v4.view.ViewPager
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import com.davidmiguel.dragtoclose.DragListener
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.lang.reflect.Type
import java.text.FieldPosition
import java.util.*
import kotlin.collections.ArrayList


class DetailActivity : AppCompatActivity() {

    var user:FirebaseUser?=null
    var auth:FirebaseAuth?=null
    var customerId:String?=null
    var roomId:String?=null
    var addedNewImage = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        auth = FirebaseAuth.getInstance()
        user = auth?.currentUser
        supportActionBar!!.hide()
        val data = intent.extras
        val roomName = data.getString("roomName")
        roomId = data.getString("roomId")
        customerId = data.getString("customerId")
        detailHeaderText.text = roomName


        FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId").addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {}
            override fun onDataChange(snap:DataSnapshot?) {
                loader.visibility = View.GONE
                val ones = LinkedHashMap<String,Any>()
                val twos = LinkedHashMap<String,LinkedHashMap<String,Any>>()
                list.removeAllViews()
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
                        val number = if(ones[key]==0) "" else ones[key].toString()
                        cell.numberField_first.setText(number)
                        cell.numberField_first.addTextChangedListener(object:TextWatcher{
                            override fun afterTextChanged(p0: Editable?) {}
                            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {

                                val data = HashMap<String,Any>()
                                val text = text.toString()
                                val num = if(text!="") text.toInt() else 0
                                data[key] = num
                                FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId").updateChildren(data)

                            }

                        })
                    }else{
                        cell.numberField_first.visibility = View.GONE
                        cell.switcher_first.visibility = View.VISIBLE
                        cell.switcher_first.isChecked = ones[key] as Boolean
                        cell.switcher_first.setOnCheckedChangeListener { _, checked ->
                            val data = HashMap<String,Any>()
                            data[key] = checked
                            FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId").updateChildren(data)
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
                    val ckeys = ArrayList((twos[key] as LinkedHashMap<String,Any>).keys)
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
                                val number = if(twos[key]!![ckey]==0) "" else twos[key]!![ckey].toString()
                                cell_child.numberField_second.setText(number)
                                cell_child.numberField_second.addTextChangedListener(object:TextWatcher{
                                    override fun afterTextChanged(p0: Editable?) {}
                                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                                    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                        val data = HashMap<String,Any>()
                                        val text = text.toString()
                                        val num = if(text!="") text.toInt() else 0
                                        data[ckey] = num
                                        FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId/$key").updateChildren(data)
                                    }

                                })
                            }else{
                                cell_child.numberField_second.visibility = View.GONE
                                cell_child.switcher_second.visibility = View.VISIBLE
                                cell_child.switcher_second.isChecked = twos[key]!![ckey] as Boolean
                                cell_child.switcher_second.setOnCheckedChangeListener { _, checked ->
                                    val data = HashMap<String,Any>()
                                    data[ckey] = checked
                                    FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId/$key").updateChildren(data)
                                }

                            }

                        }
                        cell_child.visibility = View.GONE





                        rowParent.addView(cell_child)

                        if(key == "maling"){
                            val prnt = (twos[key]!!)[ckey] as HashMap<String,Any>
                            val itkeys = ArrayList(prnt.keys)

                            for(itkey in itkeys){
                                val rowChildInside = TableRow(this@DetailActivity)
                                val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                                val cell = layoutInflater.inflate(R.layout.row_third, rowChildInside, false)
                                cell.rowThirdText.text = ucwords(itkey.replace("_", " "))
                                if(itkey == "antal_gange"){
                                    cell.switcher_third.visibility = View.GONE
                                    cell.numberField_third.visibility = View.VISIBLE
                                    val number = if(prnt[itkey]==0) "" else prnt[itkey].toString()
                                    cell.numberField_third.setText(number)
                                    cell.numberField_third.addTextChangedListener(object:TextWatcher{
                                        override fun afterTextChanged(p0: Editable?) {}
                                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                                        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                            val data = HashMap<String,Any>()
                                            val text = text.toString()
                                            val num = if(text!="") text.toInt() else 0
                                            data[itkey] = num
                                            FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId/$key/$ckey").updateChildren(data)
                                        }

                                    })
                                }else{
                                    cell.numberField_third.visibility = View.GONE
                                    cell.switcher_third.visibility = View.VISIBLE
                                    cell.switcher_third.isChecked = prnt[itkey] as Boolean
                                    cell.switcher_third.setOnCheckedChangeListener { compoundButton, checked ->
                                        val data = HashMap<String,Any>()
                                        data[itkey] = checked
                                        FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId/$key/$ckey").updateChildren(data)
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
                                    if(rowParent.getChildAt(ind+1).visibility == View.VISIBLE)
                                        view.callOnClick()

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

                        }
                    }

                }

                imageLabel.visibility = View.VISIBLE
                imageList.visibility = View.VISIBLE
            }



        })
        var imagePos=0
        val imageAdapterList = ArrayList<String>()
        FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId/images").addChildEventListener(object:ChildEventListener{
            override fun onCancelled(p0: DatabaseError?) {}
            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {}
            override fun onChildRemoved(p0: DataSnapshot?) {}
            override fun onChildChanged(snap: DataSnapshot?, p1: String?) {}
            override fun onChildAdded(snap: DataSnapshot?, p1: String?) {
                class imageClass{
                    var imageURL:String?=null
                    constructor()
                    constructor(imageURL:String){
                        this.imageURL = imageURL
                    }
                }
                    val imageData = snap!!.getValue(imageClass::class.java)
                    imageAdapterList.add(imageData!!.imageURL!!)
                    val image = ImageView(this@DetailActivity)
                    image.setBackgroundResource(R.drawable.border_all)
                    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,100.dp)
                    params.setMargins(5.dp,5.dp,5.dp,5.dp)
                    image.adjustViewBounds = true
                    image.clipToOutline = true
                    image.scaleType = ImageView.ScaleType.FIT_XY
                    image.elevation = (1.dp).toFloat()
                    image.layoutParams = params
                    image.isClickable = true


                    Glide.with(applicationContext)
                            .load(imageData.imageURL)
                            .thumbnail(Glide.with(this@DetailActivity).load(R.mipmap.image))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .crossFade()
                            .into(image)
                    imageListData.addView(image)
                image.setOnClickListener(ImageClickListener(imageAdapterList,imageData.imageURL!!,applicationContext,this@DetailActivity))
                if(addedNewImage) {
                    Handler().postDelayed({
                        imageList.fullScroll(View.FOCUS_RIGHT)
                        addImage.visibility = View.VISIBLE
                        loaderImage.visibility = View.GONE
                    },350)
                }
                imagePos++
            }



        })
        back.setOnClickListener {
            finish()
        }
        closeImageGallery.setOnClickListener {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            allItems.visibility = View.VISIBLE
            allItems.alpha = 1f
            back.isClickable = true
            imageGallery.visibility = View.GONE

        }

        dragToClose.setOnTouchListener { p0, event ->
            if(event!!.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_POINTER_UP){

                Handler().postDelayed( {
                    if(imageGallery.alpha > 0.1f)
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                    else {
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                        imageGallery.visibility = View.GONE
                        allItems.visibility = View.VISIBLE
                        allItems.alpha = 1f
                        back.isClickable = true

                    }
                },350)
            }

            false
        }

        dragToClose.setDragListener(object:DragListener{
            override fun onViewCosed() {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                imageGallery.visibility = View.GONE
                allItems.visibility = View.VISIBLE
                allItems.alpha = 1f
                back.isClickable = true

            }

            override fun onStartDraggingView() {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                allItems.visibility = View.VISIBLE
                allItems.alpha = 0.5f
                back.isClickable = false
            }

        })
        addImage.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(true)
            builder.setNegativeButton("Cancel"){_,_->}
            val items = arrayOf("Select From Gallery","Select From Camera")

            builder.setItems(items){_,item->
                when(item){
                    0->{
                        val intent = Intent()
                        intent.type = "image/*"
                        intent.action = Intent.ACTION_PICK
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
                    }
                    1->{
                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        file = File(externalCacheDir,fileName)
                        fileUri = Uri.fromFile(file)
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri)

                        startActivityForResult(intent,PICK_CAMERA)
                    }
                }
                addImage.visibility = View.GONE
                loaderImage.visibility = View.VISIBLE
            }

            val dialog = builder.create()
            dialog.show()
            dialog.getButton(Dialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.colorPrimary))





        }

    }

    val PICK_IMAGE = 0
    val PICK_CAMERA = 1
    var file:File?=null
    var fileUri:Uri?=null
    val fileName = (System.currentTimeMillis()+rand(1000000,9999999)).toString()+".jpg"
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode==Activity.RESULT_CANCELED){
            addImage.visibility = View.VISIBLE
            loaderImage.visibility = View.GONE
        }
        if(resultCode == Activity.RESULT_OK && data!=null) {
            if (requestCode == PICK_IMAGE) {
                val image = data.data
                FirebaseStorage.getInstance().getReference("images/$fileName").putFile(image).addOnSuccessListener {
                    val data = HashMap<String,Any>()
                    data["imageURL"] = it.downloadUrl.toString()
                    FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId/images").push().setValue(data)
                    addedNewImage = true
                }

            }
        }else if(resultCode==-1 && fileUri!=null && requestCode == PICK_CAMERA){
            FirebaseStorage.getInstance().getReference("images/$fileName").putFile(fileUri!!).addOnSuccessListener {
                val data = HashMap<String,Any>()
                data["imageURL"] = it.downloadUrl.toString()
                FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId/images").push().setValue(data)
                addedNewImage = true
            }
        }

    }


    internal class ImageClickListener(val urls: ArrayList<String>,val current:String,val context:Context,val window:Activity): View.OnClickListener {
        override fun onClick(v:View) {
            window.loader.visibility = View.VISIBLE
            window.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            window.imageGallery.alpha = 1f
            Handler().postDelayed({
                window.loader.visibility = View.GONE
                window.allItems.visibility = View.GONE
                window.imageGallery.visibility = View.VISIBLE
                window.imagePager.adapter = imagePagerAdapter(window,urls)
                window.imagePager.setCurrentItem(urls.indexOf(current))
                window.imageGallery.bringToFront()
                window.imagePager.setOnPageChangeListener(object:ViewPager.OnPageChangeListener{
                    override fun onPageScrollStateChanged(state: Int) {}
                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                        window.imageList.scrolltoIndex(position,window.imageListData)
                    }

                    override fun onPageSelected(position: Int) {}

                })
            },350)


        }
        fun HorizontalScrollView.scrolltoIndex(position:Int,wrapper:LinearLayout){
            val x = wrapper.getChildAt(position).left
            this.scrollX = x
        }
    }
    val Int.dp: Int get() = (this * resources.displayMetrics.density).toInt()

    fun rand(min:Int,max:Int):Int{
        val rand = Random().nextInt(max-min)+min
        return rand
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
