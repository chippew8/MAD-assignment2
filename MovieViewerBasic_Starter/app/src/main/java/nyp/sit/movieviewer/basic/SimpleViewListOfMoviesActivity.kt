package nyp.sit.movieviewer.basic

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_view_list_of_movies.*
import nyp.sit.movieviewer.basic.data.SimpleMovieSampleData.Companion.populateSimpleMovieItem
import nyp.sit.movieviewer.basic.data.SimpleMovieSampleData.Companion.simpleMovieitemArray



class SimpleViewListOfMoviesActivity : AppCompatActivity() {

    private val movies = populateSimpleMovieItem()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list_of_movies)

        val arrayMoviesAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, movies)
        movielist.adapter = arrayMoviesAdapter

        movielist.onItemClickListener =object: AdapterView.OnItemClickListener{

            override fun onItemClick(p0:AdapterView<*>?, p1: View?, p2:Int, p3:Long){

            }
        }
    }


}

fun title_array(movies){

    for(i in movies){

    }
}