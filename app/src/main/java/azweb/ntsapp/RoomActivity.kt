package azweb.ntsapp


import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.EditText
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.layout_customer.view.*


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
        val roomAdapter = object:FirebaseRecyclerAdapter<RoomModel,RoomHolder>(RoomModel::class.java,R.layout.layout_customer,RoomHolder::class.java,query){
            override fun populateViewHolder(viewHolder: RoomHolder?, model: RoomModel?, position: Int) {
                loader.visibility = View.GONE
                viewHolder!!.itemView.customerText.text = model!!.name
                viewHolder.itemView.setOnClickListener {
                    loader.visibility = View.VISIBLE
                    loader.bringToFront()
                    val intent = Intent(this@RoomActivity,DetailActivity::class.java)
                    intent.putExtra("roomId",model.child)
                    intent.putExtra("roomName",model.name)
                    startActivity(intent)
                    Handler().postDelayed({
                        loader.visibility = View.GONE
                    },500)
                }
            }

        }

        roomList.adapter = roomAdapter
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
                data["name"] = roomName
                query.setValue(data)
                val intent = Intent(this@RoomActivity,DetailActivity::class.java)
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
