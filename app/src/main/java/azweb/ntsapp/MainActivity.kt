package azweb.ntsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import java.util.LinkedHashMap


/**
 * Add in parent for more main category
 * Define array for genre (subcategory) for each parent category added
 * Define LinkedHasMap for each subcategory where key is subcategory name, and value is a string array
 */
class MainActivity : AppCompatActivity() {

    lateinit var expandableListView: ExpandableListView
    internal var parent = arrayOf("MOVIES", "GAMES") // comment this when uncomment bottom
    internal var movies = arrayOf("Horror")
    internal var games = arrayOf("Fps")
    internal var horror = arrayOf("Conjuring", "Insidious")
    internal var fps = arrayOf("CS: GO", "Team Fortress 2")
    internal var thirdLevelMovies = LinkedHashMap<String, Array<String>>()
    internal var thirdLevelGames = LinkedHashMap<String, Array<String>>()
    internal var secondLevel: MutableList<Array<String>> = ArrayList()
    internal var data: MutableList<LinkedHashMap<String, Array<String>>> = ArrayList()
    var user:FirebaseUser?=null
    var auth:FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        auth?.signInWithEmailAndPassword("ios.app@azweb.dk","azweb@ios.app")
        user = auth?.currentUser
        val ones = HashMap<String,Any>()
        ones["afdækning"] = false
        ones["fodpaneler"] = false
        ones["kvm"] = 30
        ones["opsætning_af"] = "Title"
        ones["plet_spartling"] = false
        ones["vask_for_nikotin"] = false

        secondLevel.add(movies)
        secondLevel.add(games)
        thirdLevelMovies[movies[0]] = horror


        thirdLevelGames[games[0]] = fps

        data.add(thirdLevelMovies)
        data.add(thirdLevelGames)

        expandableListView = expandable_listview

        val threeLevelListAdapterAdapter = ThreeLevelListAdapter(this, parent, secondLevel, data)


        expandableListView.setAdapter(threeLevelListAdapterAdapter)
        expandableListView.setGroupIndicator(null)
        expandableListView.setChildIndicator(null)



    }
}
