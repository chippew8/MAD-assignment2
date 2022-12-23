package nyp.sit.movieviewer.advanced

import android.content.Context
import nyp.sit.movieviewer.advanced.entity.MovieItem
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