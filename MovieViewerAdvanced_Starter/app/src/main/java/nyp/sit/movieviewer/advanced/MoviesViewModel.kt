package nyp.sit.movieviewer.advanced

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nyp.sit.movieviewer.advanced.entity.MovieItem
import java.lang.IllegalArgumentException

//TODO 10:
//  - Update ContactsViewModel to implement ViewModel

class MoviesViewModel(val repo: MoviesRepository) : ViewModel() {


    //TODO 11:
    // Create a list of contacts
    // - Use LiveData to cache all contacts
    var allMovies: LiveData<List<MovieItem>> = repo.allMovies.asLiveData()

    //TODO 12 :
    // - Launch a new coroutine to insert the contact
    fun insert(movieItem: MovieItem) = viewModelScope.launch(Dispatchers.IO){
        repo.insert(movieItem)
    }

    //TODO 13 :
    // - Launch a new coroutine to remove the contact
    fun remove(movieItem: MovieItem) = viewModelScope.launch(Dispatchers.IO){
        repo.delete(movieItem)
    }

    fun dropDB() = viewModelScope.launch(Dispatchers.IO){
        repo.dropDB()
    }

}

//TODO 14:
// - Implement ContactsViewModelFactory to create ContactsViewModel using the ContactsRepository

class MoviesViewModelFactory(private val repo : MoviesRepository):ViewModelProvider.Factory{
     override fun <T: ViewModel> create(modelClass: Class<T>):T{
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)){
            return MoviesViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}