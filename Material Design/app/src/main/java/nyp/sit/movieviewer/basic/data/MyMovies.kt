package nyp.sit.movieviewer.basic.data

import android.app.Application
import android.content.Context
import android.database.Cursor
import java.util.ArrayList

class MyMovies() : Application() {

    private var movieList: ArrayList<String> = ArrayList<String>()
    private var movieIdList: ArrayList<Int> = ArrayList<Int>()

    companion object{
        val ourInstance = MyMovies()
    }

    fun addToDatabase(movieName: String, movieOverview: String, movieLang: String, movieRelease: String, c: Context): Long? {
        val db = DatabaseAdapter(c)
        db.open()
        val rowIDofInsertedEntry = db.insertEntry(movieName, movieOverview, movieLang, movieRelease)
        db.close()

        return rowIDofInsertedEntry
    }

    fun deleteFrmDatabase(rowID: Int, c: Context): Boolean {
        val db = DatabaseAdapter(c)
        db.open()
        val id = movieIdList[rowID]
        val updateStatus = db.removeEntry(id)
        db.close()

        return updateStatus
    }

    fun retrieveTitle(c: Context): List<String>{
        val myCursor: Cursor?
        var myString = ""
        val db = DatabaseAdapter(c)
        db.open()
        movieIdList.clear()
        movieList.clear()
        myCursor = db.retrieveAllEntriesCursor()
        if (myCursor != null && myCursor!!.count > 0){
            myCursor!!.moveToFirst()
            do{
                movieIdList.add(myCursor.getInt(db.COLUMN_KEY_ID))
                myString = myCursor.getString(db.COLUMN_NAME_ID)
                movieList.add(myString)
            }while(myCursor.moveToNext())
        }
        db.close()
        return movieList
    }

    fun retrieveAll(c: Context): List<String> {

        val myCursor: Cursor?
        var myString = ""
        val db = DatabaseAdapter(c)
        db.open()
        movieIdList.clear()
        movieList.clear()
        myCursor = db.retrieveAllEntriesCursor()
        if (myCursor != null && myCursor!!.count > 0){
            myCursor!!.moveToFirst()
            do{
                movieIdList.add(myCursor.getInt(db.COLUMN_KEY_ID))
                myString = myCursor.getString(db.COLUMN_NAME_ID) + " - " + myCursor.getString(db.COLUMN_OVERVIEW_ID) + " - " + myCursor.getString(db.COLUMN_LANGUAGE_ID) + " - " + myCursor.getString(db.COLUMN_DATE_ID) + "\n"
                movieList.add(myString)
            }while(myCursor.moveToNext())
        }
        db.close()
        return movieList

    }
}