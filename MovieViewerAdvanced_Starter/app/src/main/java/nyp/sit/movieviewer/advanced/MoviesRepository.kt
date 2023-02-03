package nyp.sit.movieviewer.advanced

import nyp.sit.movieviewer.advanced.entity.MovieItem

class MoviesRepository(private val MoviesDao: MoviesDao) {
//TODO 8:
//  - Update the repository class to hold a list of contacts from DAO.
    val allMovies = MoviesDao.retrieveAllMovies()
//TODO 9:
//  - Add in the necessary functions to do insert and delete of contacts using the DAO
    suspend fun insert(movie: MovieItem){
        MoviesDao.insert(movie)
    }

    suspend fun delete(movie: MovieItem){
        MoviesDao.delete(movie)
    }

    suspend fun dropDB(){
        MoviesDao.dropDB()
    }

}