package nyp.sit.movieviewer.advanced

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import nyp.sit.movieviewer.advanced.entity.MovieItem


//TODO 7:
//  - Update the ContactsRoomDatabase class to be abstract and extends RoomDatabase
//  - Annotate the class to be a room database
//  - Define a singleton to prevent having multiple instances of the database opened
@Database(entities = arrayOf(MovieItem::class), version = 1, exportSchema = false)
abstract class MoviesRoomDatabase : RoomDatabase(){
    abstract fun moviesDao(): MoviesDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesRoomDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope) : MoviesRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context,
                    MoviesRoomDatabase::class.java, "movies_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }
}