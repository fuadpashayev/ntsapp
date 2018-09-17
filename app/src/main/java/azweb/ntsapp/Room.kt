package azweb.ntsapp

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_customer.view.*
import java.util.ArrayList

class RoomModel{
    var child:String?=null
    var name:String?=null
    var imageUrl:String?=null
    var kvm:Int?=null

    constructor()
    constructor(child:String?,name:String?,imageUrl:String?,kvm:Int?){
        this.child = child
        this.name = name
        this.imageUrl = imageUrl
        this.kvm = kvm
    }
}

class RoomHolder(view: View?):RecyclerView.ViewHolder(view!!)