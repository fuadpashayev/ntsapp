package azweb.ntsapp

import android.support.v7.widget.RecyclerView
import android.view.View

class CustomerModel{
    var child:String?=null
    var name:String?=null
    var user:String?=null

    constructor()
    constructor(child:String?,name:String?,user:String?){
        this.child = child
        this.name = name
        this.user = name
    }
}
class CustomerHolder(view: View?): RecyclerView.ViewHolder(view!!)
