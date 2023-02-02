package nyp.sit.movieviewer.intermediate

import android.content.Context
import android.graphics.Movie
import android.util.Log
import nyp.sit.movieviewer.intermediate.entity.MovieItem
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener
import java.util.ArrayList

class movieDBJsonUtils() {

    companion object {

        @Throws(JSONException::class)
        fun getMovieDetailsFromJson(context: Context, movieDetailsJsonStr: String): ArrayList<MovieItem>? {
            val parsedMovieData = ArrayList<MovieItem>()
            val jsonObject = JSONTokener(movieDetailsJsonStr).nextValue() as JSONObject

            val jsonArray = jsonObject.getJSONArray("results")

            for (i in 0 until jsonArray.length()) {

                val vote_count = jsonArray.getJSONObject(i).getInt("vote_count")
                val id = jsonArray.getJSONObject(i).getInt("id")
                val video = jsonArray.getJSONObject(i).getBoolean("video")
                val vote_average = jsonArray.getJSONObject(i).getDouble("vote_average")
                val title = jsonArray.getJSONObject(i).getString("title")
                val popularity = jsonArray.getJSONObject(i).getDouble("popularity")
                val poster_path = jsonArray.getJSONObject(i).getString("poster_path")
                val original_language = jsonArray.getJSONObject(i).getString("original_language")
                val original_title = jsonArray.getJSONObject(i).getString("original_title")
                val genre_ids = jsonArray.getJSONObject(i).getString("genre_ids")
                val backdrop_path = jsonArray.getJSONObject(i).getString("backdrop_path")
                val adult = jsonArray.getJSONObject(i).getBoolean("adult")
                val overview = jsonArray.getJSONObject(i).getString("overview")
                val release_date = jsonArray.getJSONObject(i).getString("release_date")

                var movieitem = MovieItem(poster_path,adult,overview,release_date,genre_ids,id,original_title,original_language,title,backdrop_path,popularity,vote_count,video, vote_average)
                parsedMovieData.add(movieitem)
            }

// Pass adapter to the RecyclerView adapter



            return parsedMovieData
        }



    }

}