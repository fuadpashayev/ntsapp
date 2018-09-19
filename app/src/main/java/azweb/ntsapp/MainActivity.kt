package azweb.ntsapp


import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Html
import android.view.View
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.layout_customer.view.*
import android.view.Gravity
import android.widget.TextView





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
        val deleteList = arrayListOf<String?>()
        val customerAdapter = object:FirebaseRecyclerAdapter<CustomerModel,CustomerHolder>(CustomerModel::class.java,R.layout.layout_customer,CustomerHolder::class.java,query){
            override fun populateViewHolder(viewHolder: CustomerHolder?, model: CustomerModel?, position: Int) {
                loader.visibility = View.GONE
                viewHolder!!.itemView.customerText.text = model!!.name
                viewHolder.itemView.deleteCheckBox.visibility = View.GONE
                viewHolder.itemView.setOnClickListener {
                    if(deleteList.size==0) {
                        val intent = Intent(this@MainActivity, RoomActivity::class.java)
                        intent.putExtra("customerId", model.child)
                        intent.putExtra("customerName", model.name)
                        startActivity(intent)
                    }else{
                        if(deleteList.contains(model.child)){
                            viewHolder.itemView.deleteCheckBox.visibility = View.GONE
                            deleteList.remove(model.child)
                            if(deleteList.size==0)
                                deleteCustomer.visibility = View.GONE
                        }else{
                            viewHolder.itemView.deleteCheckBox.visibility = View.VISIBLE
                            deleteList.add(model.child)
                        }

                    }
                }
                viewHolder.itemView.setOnLongClickListener {
                    if(deleteList.size==0){
                        deleteCustomer.visibility = View.VISIBLE
                        viewHolder.itemView.deleteCheckBox.visibility = View.VISIBLE
                        deleteList.add(model.child)
                    }
                    true
                }
            }

        }

        customerList.adapter = customerAdapter

        deleteCustomer.setOnClickListener {
            if(deleteList.size>0){
                val builder = AlertDialog.Builder(this@MainActivity)
                val title = TextView(this@MainActivity)
                title.text = Html.fromHtml("Are you sure to delete <font color='#3F51B5'>selected customers</font> ?")
                title.setPadding(15, 100, 15, 20)
                title.gravity = Gravity.CENTER_HORIZONTAL
                title.textSize = 15f
                title.setTextColor(resources.getColor(R.color.semiBlack))
                builder.setCustomTitle(title)

                builder.setNegativeButton("Cancel") { _, _ -> }
                builder.setPositiveButton("Delete") { dialogInterface, i ->
                    loader.visibility = View.VISIBLE
                    for(customer in deleteList) {
                        val ref = FirebaseDatabase.getInstance()
                        ref.getReference("customer/$customer").removeValue()
                        ref.getReference("rooms/$customer").removeValue()
                        ref.getReference("detail/$customer").removeValue()
                    }
                    loader.visibility = View.GONE
                    deleteList.clear()
                    deleteCustomer.visibility = View.GONE
                }
                builder.setCancelable(true)
                val dialog = builder.create()
                dialog.show()
                dialog.getButton(Dialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.colorPrimary))
                dialog.getButton(Dialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.colorPrimary))

            }

        }
        addCustomer.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(Html.fromHtml("<font color='#5a5a5a'>Create Work</font>"))
            val inflater = layoutInflater.inflate(R.layout.dialog,null)
            builder.setView(inflater)
            builder.setPositiveButton("OK", object:DialogInterface.OnClickListener{
               override fun onClick(dialog:DialogInterface, which:Int) {
                    val customerName = inflater.customerName.text.toString()
                    val data = LinkedHashMap<String,Any>()
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
