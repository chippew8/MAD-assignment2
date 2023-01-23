package nyp.sit.movieviewer.basic

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_view_list_of_movies.*
import nyp.sit.movieviewer.basic.data.SimpleMovieSampleData
import nyp.sit.movieviewer.basic.data.SimpleMovieSampleData.Companion.populateSimpleMovieItem
import nyp.sit.movieviewer.basic.data.SimpleMovieSampleData.Companion.simpleMovieitemArray
import nyp.sit.movieviewer.basic.entity.SimpleMovieItem


class SimpleViewListOfMoviesActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list_of_movies)

//        val arrayMoviesAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1)
        var arrayMoviesAdapter = CustListAdapter(this, populateSimpleMovieItem())
        movielist.adapter = arrayMoviesAdapter


        movielist.onItemClickListener =object: AdapterView.OnItemClickListener{

            override fun onItemClick(p0:AdapterView<*>?, p1: View?, p2:Int, p3:Long){

            }
        }
    }
    class CustListAdapter(context: Context, data:ArrayList<SimpleMovieItem>):BaseAdapter() {


        internal val sList:ArrayList<SimpleMovieItem> = SimpleMovieSampleData.simpleMovieitemArray

        private val mInflater : LayoutInflater
        init{

            this.mInflater = LayoutInflater.from(context)
            sList?.addAll(data)
        }


        fun addAll(data: Collection<SimpleMovieItem>){

            sList?.addAll(data)

        }
        fun clear(){

            sList?.clear()

        }
        override fun getItem(p0: Int): Any? {

            return sList?.get(p0)
        }

        override fun getItemId(p0: Int): Long {

            return 0
        }

        override fun getCount(): Int {
            return if(sList == null) 0 else sList?.size
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

