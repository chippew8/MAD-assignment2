package nyp.sit.movieviewer.basic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_view_list_of_movies.*
import nyp.sit.movieviewer.basic.data.SimpleMovieSampleData
import nyp.sit.movieviewer.basic.data.SimpleMovieSampleData.Companion.simpleMovieitemArray
import nyp.sit.movieviewer.basic.entity.SimpleMovieItem


class SimpleViewListOfMoviesActivity : AppCompatActivity() {

    val test = SimpleMovieSampleData.Companion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list_of_movies)
        val myIntent = Intent(this, SimpleItemDetailActivity::class.java)


        val arrayMoviesAdapter = MovieListAdapter(this, simpleMovieitemArray)
        movielist.adapter = arrayMoviesAdapter


        movielist.onItemClickListener= object: AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position:Int, id:Long){
                val title = test.simpleMovieitemArray[position].title
                val overview = test.simpleMovieitemArray[position].overview
                val language = test.simpleMovieitemArray[position].original_langauge
                val date = test.simpleMovieitemArray[position].release_date
                myIntent.putExtra("title",title)
                myIntent.putExtra("overview",overview)
                myIntent.putExtra("lang",language)
                myIntent.putExtra("date",date)
                startActivity(myIntent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.movie_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if (item?.itemId == R.id.fav){
            var Fav_Intent = Intent(this, FavMoviesActivity::class.java)
            startActivity(Fav_Intent)
        }
        else if (item?.itemId == R.id.sign_out){
            var SignOut_Intent = Intent(this, LoginActivity::class.java)
            startActivity(SignOut_Intent)
        }
        return super.onOptionsItemSelected(item)
    }

    class MovieListAdapter(context: Context, data:ArrayList<SimpleMovieItem>):BaseAdapter() {
        internal val sList:ArrayList<SimpleMovieItem> = data
        private val mInflater : LayoutInflater

        init{
            this.mInflater = LayoutInflater.from(context)
        }

        fun addAll(data: Collection<SimpleMovieItem>){
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
            val label: TextView = v.findViewById(R.id.movie_name)
            label.text = sList?.get(position)?.title

            return v
        }
    }
}

