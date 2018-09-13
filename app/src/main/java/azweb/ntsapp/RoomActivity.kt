package azweb.ntsapp


import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.text.Html
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.layout_customer.*
import kotlinx.android.synthetic.main.layout_customer.view.*
import java.util.LinkedHashMap


class RoomActivity : AppCompatActivity() {

    var user:FirebaseUser?=null
    var auth:FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        auth = FirebaseAuth.getInstance()
        user = auth?.currentUser
        supportActionBar!!.hide()
        val data = intent.extras
        val customerName = data.getString("customerName")
        val customerId = data.getString("customerId")
        roomHeaderText.text = customerName
        val query = FirebaseDatabase.getInstance().getReference("rooms/$customerId")
        query.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {}
            override fun onDataChange(snap: DataSnapshot?) {
                if(!snap!!.exists())
                    loader.visibility = View.GONE
            }

        })
        val deleteList = arrayListOf<String?>()
        val roomAdapter = object:FirebaseRecyclerAdapter<RoomModel,RoomHolder>(RoomModel::class.java,R.layout.layout_customer,RoomHolder::class.java,query){
            override fun populateViewHolder(viewHolder: RoomHolder?, model: RoomModel?, position: Int) {
                loader.visibility = View.GONE
                viewHolder!!.itemView.customerText.text = model!!.name
                viewHolder.itemView.setOnClickListener {
                    if(deleteList.size==0) {
                        loader.visibility = View.VISIBLE
                        loader.bringToFront()
                        val intent = Intent(this@RoomActivity, DetailActivity::class.java)
                        intent.putExtra("customerId", customerId)
                        intent.putExtra("roomId", model.child)
                        intent.putExtra("roomName", model.name)
                        startActivity(intent)

                        Handler().post{loader.visibility = View.GONE}
                    }else{
                        if(deleteList.contains(model.child)){
                            viewHolder.itemView.deleteCheckBox.visibility = View.GONE
                            deleteList.remove(model.child)
                            if(deleteList.size==0)
                                deleteRoom.visibility = View.GONE
                        }else{
                            viewHolder.itemView.deleteCheckBox.visibility = View.VISIBLE
                            deleteList.add(model.child)
                        }

                     }

                }

                viewHolder.itemView.setOnLongClickListener {
                    if(deleteList.size==0){
                        deleteRoom.visibility = View.VISIBLE
                        viewHolder.itemView.deleteCheckBox.visibility = View.VISIBLE
                        deleteList.add(model.child)
                    }
                    true
                }
            }

        }

        roomList.adapter = roomAdapter
        back.setOnClickListener {
            finish()
        }
        deleteRoom.setOnClickListener {
            if(deleteList.size>0){
                val builder = AlertDialog.Builder(this@RoomActivity)
                val title = TextView(this@RoomActivity)
                title.text = Html.fromHtml("Are you sure to delete <font color='#3F51B5'>selected rooms</font> ?")
                title.setPadding(15, 100, 15, 20)
                title.gravity = Gravity.CENTER_HORIZONTAL
                title.textSize = 15f
                title.setTextColor(resources.getColor(R.color.semiBlack))
                builder.setCustomTitle(title)

                builder.setNegativeButton("Cancel") { _, _ -> }
                builder.setPositiveButton("Delete") { dialogInterface, i ->
                    for(room in deleteList) {
                        val ref = FirebaseDatabase.getInstance()
                        ref.getReference("rooms/$customerId/$room").removeValue()
                        ref.getReference("detail/$customerId/$room").removeValue()
                    }
                    deleteList.clear()
                    deleteRoom.visibility = View.GONE
                }
                builder.setCancelable(true)
                val dialog = builder.create()
                dialog.show()
                dialog.getButton(Dialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.colorPrimary))
                dialog.getButton(Dialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.colorPrimary))

            }

        }
        addRoom.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(Html.fromHtml("<font color='#5a5a5a'>Select Room</font>"))
            val inflater = layoutInflater.inflate(R.layout.dialog,null)
            builder.setView(inflater)
            val items = arrayOf("Stue","Værelse","Bad","Køkken","Gang","Trappe","Kælder","Bryggers")
            builder.setItems(items) { _,item ->
                val roomName = items[item]
                val query = FirebaseDatabase.getInstance().getReference("rooms/${customerId}").push()
                val data = HashMap<String,Any>()
                data["child"] = query.key
                val roomId = query.key
                data["name"] = roomName
                query.setValue(data)







                val twos = LinkedHashMap<String, Any>()
                //tekler

                twos["kvm"] = 0
                twos["afdækning"] = false
                twos["vask_for_nikotin"] = false
                twos["plet_spartling"] = false
                twos["fodpaneler"] = false

                //tapet_skrubning
                val twos_tapet_skrubning = LinkedHashMap<String,Any>()
                twos_tapet_skrubning["antal"] = 0
                twos["tapet_skrubning"] = twos_tapet_skrubning

                //fuldspartling
                val twos_fuldspartling = LinkedHashMap<String,Any>()
                twos_fuldspartling["antal"] = 0
                twos["fuld_spartling"] = twos_fuldspartling

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
                twos["opsaetning_af"] = "Title"

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


                val queryDetails = FirebaseDatabase.getInstance().getReference("detail/$customerId/$roomId")
                twos["child"] = queryDetails.key
                queryDetails.setValue(twos)












                val intent = Intent(this@RoomActivity,DetailActivity::class.java)
                intent.putExtra("customerId",customerId)
                intent.putExtra("roomId",query.key)
                intent.putExtra("roomName",roomName)
                startActivity(intent)

            }

            builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
            inflater.customerName.visibility = View.GONE

            val dialog = builder.create()

            dialog.show()
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.colorPrimary))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.colorPrimary))

        }
    }
}
