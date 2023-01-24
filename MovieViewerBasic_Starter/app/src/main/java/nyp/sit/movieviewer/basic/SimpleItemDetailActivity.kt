package nyp.sit.movieviewer.basic

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.simple_activity_item_detail.*
import nyp.sit.movieviewer.basic.data.MyMovies

class SimpleItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_activity_item_detail)

        var MovieTitle = intent.getStringExtra("title")
        var MovieOverview = intent.getStringExtra("overview")
        var MovieDate = intent.getStringExtra("date")
        var MovieLanguage = intent.getStringExtra("lang")

        movie_title.text = MovieTitle
        movie_overview.text = MovieOverview
        movie_langauge.text = MovieLanguage
        movie_release_date.text = MovieDate
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.movie_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if(item?.itemId == R.id.add_fav){
            val mc = MyMovies.ourInstance

            mc.addToDatabase(movie_title.text.toString(), movie_overview.text.toString(), movie_langauge.text.toString(), movie_release_date.text.toString(), applicationContext)

            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
