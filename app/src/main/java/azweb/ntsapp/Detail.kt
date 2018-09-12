package azweb.ntsapp

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_customer.view.*
import java.util.ArrayList

class DetailModel{
    var afdaekning:Boolean?=null
    var child:String?=null
    var dor:Any?=null
    var filt:Any?=null
    var fodpaneler:Boolean?=null
    var fototapet:Any?=null
    var fuldspartling:Any?=null
    var glasvaev:Any?=null
    var grunding:Any?=null
    var images:Any?=null
    var kvm:Int?=null
    var magnetfilt:Any?=null
    var maling:Any?=null
    var opsaetningaf:String?=null
    var pletspartling:Boolean?=null
    var radiator:Any?=null
    var ror:Any?=null
    var slibning:Any?=null
    var specialtapet:Any?=null
    var tapet:Any?=null
    var tapetskrubning:Any?=null
    var vaskfornikotin:Boolean?=null



    constructor()
    constructor(
            afdaekning:Boolean?,child:String?,dor:Any?, filt:Any?,fodpaneler:Boolean?,fototapet:Any?,
            fuldspartling:Any?,glasvaev:Any?,grunding:Any?,images:Any?,kvm:Int?,magnetfilt:Any?,maling:Any?,
            opsaetningaf:String?,pletspartling:Boolean?,radiator:Any?,ror:Any?,slibning:Any?,specialtapet:Any?,tapet:Any?,
            tapetskrubning:Any?,vaskfornikotin:Boolean?){

        this.afdaekning=afdaekning
        this.child=child
        this.dor=dor
        this.filt=filt
        this.fodpaneler=fodpaneler
        this.fototapet=fototapet
        this.fuldspartling=fuldspartling
        this.glasvaev=glasvaev
        this.grunding=grunding
        this.images=images
        this.kvm=kvm
        this.magnetfilt=magnetfilt
        this.maling=maling
        this.opsaetningaf=opsaetningaf
        this.pletspartling=pletspartling
        this.radiator=radiator
        this.ror=ror
        this.slibning=slibning
        this.specialtapet=specialtapet
        this.tapet=tapet
        this.tapetskrubning=tapetskrubning
        this.vaskfornikotin=vaskfornikotin
    }
}
