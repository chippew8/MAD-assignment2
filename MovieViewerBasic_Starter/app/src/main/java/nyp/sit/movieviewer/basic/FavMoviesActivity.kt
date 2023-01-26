package nyp.sit.movieviewer.basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_favourite_list.*
import nyp.sit.movieviewer.basic.data.MyMovies

class FavMoviesActivity : AppCompatActivity() {
    var moviesAdapter: ArrayAdapter<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_list)
    }

    private fun toggleVisibility() {
        if (moviesLV.count > 0) {
            noitemsTV.visibility = View.GONE
            moviesLV.visibility = View.VISIBLE
        } else {
            moviesLV.visibility = View.GONE
            noitemsTV.visibility = View.VISIBLE
        }
    }
    private fun retrieveMovies(){
        val movieList: List<String>
        val mc = MyMovies.ourInstance

        movieList = mc.retrieveTitle(applicationContext)

        moviesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, movieList)
        moviesLV.adapter = moviesAdapter
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val mc = MyMovies.ourInstance
        mc.deleteFrmDatabase(info.position, applicationContext)
        retrieveMovies()
        toggleVisibility()
        return super.onContextItemSelected(item)
    }

    override fun onResume(){
        retrieveMovies()
        toggleVisibility()
        super.onResume()
    }
}


