package nyp.sit.movieviewer.intermediate

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async

class ViewListOfMoviesActivity : AppCompatActivity() {
    val moviedb_api_key = getString(R.string.moviedb_api_key)
    val SHOW_BY_TOP_RATED = 1
    val SHOW_BY_POPULAR = 2

    private var displayType = SHOW_BY_TOP_RATED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list_of_movies)
    }

    override fun onStart() {
        super.onStart()
        loadMovieData(displayType)
    }

    fun loadMovieData(viewType: Int) {
        var showTypeStr: String? = null
        when (viewType) {
            SHOW_BY_TOP_RATED -> showTypeStr = NetworkUtils.TOP_RATED_PARAM
            SHOW_BY_POPULAR -> showTypeStr = NetworkUtils.POPULAR_PARAM
        }

        if (showTypeStr != null) {
            displayType = viewType
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.sortPopular -> {
                loadMovieData(SHOW_BY_POPULAR)
            }
            R.id.sortTopRated -> {
                loadMovieData(SHOW_BY_TOP_RATED)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume(){
        super.onResume()

        val scope = CoroutineScope(Job() + Dispatchers.IO)

        var nwSingleItemJob = scope.async(){
            var nwURL = NetworkUtils.buildUrl("String", moviedb_api_key)

            var response = nwURL?.let { NetworkUtils.getResponseFromHttpUrl(it) }

            response
        }

    }

}
