package nyp.sit.movieviewer.advanced

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class MyMovies() : Application(){
    //TODO 15:
// - Create a database and repo instance
// - Make use of the "by lazy" delegation to state that the objects are tbe created when first needed. Instead of at start up.
    val appScope = CoroutineScope(SupervisorJob())
    val db by lazy { MoviesRoomDatabase.getDatabase(this, appScope) }
    val repo by lazy {MoviesRepository(db!!.moviesDao())}

}