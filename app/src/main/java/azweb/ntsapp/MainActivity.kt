package azweb.ntsapp


import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Html
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.layout_customer.view.*


class MainActivity : AppCompatActivity() {

    var user:FirebaseUser?=null
    var auth:FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        auth?.signInWithEmailAndPassword("ios.app@azweb.dk","azweb@ios.app")
        user = auth?.currentUser
        supportActionBar!!.hide()
        val query = FirebaseDatabase.getInstance().getReference("customer")
        val customerAdapter = object:FirebaseRecyclerAdapter<CustomerModel,CustomerHolder>(CustomerModel::class.java,R.layout.layout_customer,CustomerHolder::class.java,query){
            override fun populateViewHolder(viewHolder: CustomerHolder?, model: CustomerModel?, position: Int) {
                loader.visibility = View.GONE
                viewHolder!!.itemView.customerText.text = model!!.name
                viewHolder.itemView.setOnClickListener {
                    val intent = Intent(this@MainActivity,RoomActivity::class.java)
                    intent.putExtra("customerId",model.child)
                    intent.putExtra("customerName",model.name)
                    startActivity(intent)
                }
            }

        }

        customerList.adapter = customerAdapter
        addCustomer.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(Html.fromHtml("<font color='#5a5a5a'>Create Work</font>"))
            val input = EditText(this)
            val inflater = layoutInflater.inflate(R.layout.dialog,null)
            builder.setView(inflater)
            builder.setPositiveButton("OK", object:DialogInterface.OnClickListener{
               override fun onClick(dialog:DialogInterface, which:Int) {
                    val customerName = inflater.customerName.text.toString()
                    val data = HashMap<String,Any>()
                    val query = FirebaseDatabase.getInstance().getReference("customer").push()
                    data["child"] = query.key
                    data["name"] = customerName
                    data["user"] = user!!.uid
                    query.setValue(data)
                }
            })
            builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }


            val dialog = builder.create()

            dialog.show()
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.colorPrimary))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.colorPrimary))

        }
    }
}
