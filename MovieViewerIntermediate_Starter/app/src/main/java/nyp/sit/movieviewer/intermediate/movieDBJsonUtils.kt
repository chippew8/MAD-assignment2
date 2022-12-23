package nyp.sit.movieviewer.intermediate

import android.content.Context
import nyp.sit.movieviewer.intermediate.entity.MovieItem
import org.json.JSONException
import java.util.ArrayList

class movieDBJsonUtils() {

    companion object {

        @Throws(JSONException::class)
        fun getMovieDetailsFromJson(context: Context, movieDetailsJsonStr: String): ArrayList<MovieItem>? {
            val parsedMovieData = ArrayList<MovieItem>()
            return parsedMovieData
        }



    }

}