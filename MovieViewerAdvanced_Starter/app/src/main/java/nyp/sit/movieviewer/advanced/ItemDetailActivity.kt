package nyp.sit.movieviewer.advanced

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        var poster_path = intent.getStringExtra("poster_path")
        var overview = intent.getStringExtra("overview")
        var release_date = intent.getStringExtra("release_date")
        var popularity = intent.getStringExtra("popularity")
        var vote_count = intent.getStringExtra("vote_count")
        var vote_average = intent.getStringExtra("vote_average")
        var original_language = intent.getStringExtra("original_language")
        var adult = intent.getStringExtra("adult")
        var video = intent.getStringExtra("video")

        Picasso.get().load(NetworkUtils.buildImageUrl(poster_path).toString()).into(posterIV)
        movie_overview.text = overview
        movie_release_date.text = release_date
        movie_popularity.text = popularity
        movie_vote_count.text = vote_count
        movie_vote_avg.text = vote_average
        movie_language.text = original_language
        movie_is_adult.text = adult
        movie_hasvideo.text = video
    }

}