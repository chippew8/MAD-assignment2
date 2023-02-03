package nyp.sit.movieviewer.intermediate

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import nyp.sit.movieviewer.intermediate.entity.MovieItem


//TODO 5:
// - Implement the data access object (DAO)
// - Add in the methods to retrieve all contacts, insert and delete of contacts
// - Add in the annotations for each functions

//TODO 6:
// - Make use of Flow from Coroutines to observe data changes to the contacts list.
@Dao
interface MoviesDao{
    @Query("Select * from `movies_table`")
    fun retrieveAllMovies(): Flow<List<MovieItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newMovies : MovieItem)

    @Delete
    fun delete(delMovies: MovieItem)

    @Query("DELETE FROM movies_table")
    fun dropDB()

}