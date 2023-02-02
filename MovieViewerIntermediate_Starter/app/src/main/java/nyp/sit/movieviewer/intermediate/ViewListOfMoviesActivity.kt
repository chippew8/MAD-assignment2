package nyp.sit.movieviewer.intermediate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.view.*
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_view_list_of_movies.*
import kotlinx.coroutines.*
import nyp.sit.movieviewer.intermediate.entity.MovieItem
import java.net.URL

class ViewListOfMoviesActivity : AppCompatActivity() {
    val SHOW_BY_TOP_RATED = 1
    val SHOW_BY_POPULAR = 2
    private var displayType = SHOW_BY_TOP_RATED
    var allMovies: List<MovieItem>? = null

    private val moviesViewModel: MoviesViewModel by viewModels{
        MoviesViewModelFactory((application as MyMovies).repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list_of_movies)

        loadMovieData(displayType)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {

        //TODO 19:
        //  - Make use of view model to remove the selected contact.
        var info = item.menuInfo as AdapterView.AdapterContextMenuInfo

        if (allMovies!=null){
            var movie = allMovies!!.get(info.position)
            moviesViewModel.remove(movie)
        }
        return super.onContextItemSelected(item)
    }

    //TODO 18 :
    // - Implement onActivityResult to insert the new contact using the ViewModel after receiving a response from AddContactActivity.

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1->{
                if(data!=null){
                    var name = data!!.getStringExtra("name")?:""
                    var num = data!!.getStringExtra("num")?:""

                }
            }
        }
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

        var movieJob = CoroutineScope(Job() + Dispatchers.IO).async() {

            val movieRequestUrl = NetworkUtils.buildUrl(showTypeStr!!, "90692aa32d311205476229d3a936b8ed").toString()

            try {
                val jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(URL(movieRequestUrl))

                var responseList = movieDBJsonUtils.getMovieDetailsFromJson(
                    this@ViewListOfMoviesActivity,
                    jsonMovieResponse!!
                )

                responseList
            } catch (e: Exception) {
                e.printStackTrace()

                null
            }

        }

        GlobalScope.launch(Dispatchers.Main) {
            val movieData = movieJob.await()

            if (movieData != null){
                for (movieEntry in movieData){
                    moviesViewModel.insert(movieEntry)
                }

            }

        }
        moviesViewModel.allMovies.observe(this, Observer {

            var movieTitles : List<String> = arrayListOf()
            var movieImageURL : List<String> = arrayListOf()
            allMovies = it
            for (movie in it){

                movieTitles += movie.original_title.toString()
                movieImageURL += movie.poster_path!!
            }
            it?.let {
                val arrayMoviesAdapter = MovieListAdapter(this, movieTitles as ArrayList<String>,
                    movieImageURL as ArrayList<String>
                )
                movielist.adapter = arrayMoviesAdapter
            }

        })
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

    class MovieListAdapter(context: Context, data:ArrayList<String>, image: ArrayList<String>): BaseAdapter() {
        internal val sList:ArrayList<String> = data
        internal val iList:ArrayList<String> = image
        private val mInflater : LayoutInflater

        init{
            this.mInflater = LayoutInflater.from(context)
        }

        fun addAll(data: Collection<String>){
            sList.addAll(data)
        }

        fun clear(){
            sList.clear()
        }

        override fun getItem(p0: Int): Any? {

            return sList.get(p0)
        }

        override fun getItemId(p0: Int): Long {

            return 0
        }

        override fun getCount(): Int {
            return if(sList == null) 0 else sList.size
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{
            val v : View
            v = this.mInflater.inflate(R.layout.simpleitem, parent, false)
            val label: TextView = v.findViewById(R.id.moviename)
            label.text = sList?.get(position)
            var str = NetworkUtils.buildImageUrl(iList?.get(position)).toString()
            val image : ImageView = v.findViewById(R.id.heroimage)
            Picasso.get().load(str).into(image)
            return v
        }
    }

}




