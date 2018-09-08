package azweb.ntsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val header = ArrayList<String>()
    val body = ArrayList<ArrayList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val season1 = ArrayList<String>()
        season1.add("Winter")
        season1.add("Spring")
        season1.add("Summer")
        season1.add("Autumn")

        val season2 = ArrayList<String>()
        season2.add("The")
        season2.add("Life")
        season2.add("Is")
        season2.add("Very")
        season2.add("Beautiful")

        header.add("Seasons")
        header.add("Life information")

        body.add(season1)
        body.add(season2)

        dataList.setAdapter(listAdapter(this,header,body))

    }
}
