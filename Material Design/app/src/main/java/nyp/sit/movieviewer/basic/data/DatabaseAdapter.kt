package nyp.sit.movieviewer.basic.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseAdapter(c: Context) {

    private val DATABASE_NAME = "simplemovie.db"
    private val DATABASE_TABLE = "simplemovie"
    private var _db: SQLiteDatabase? = null
    private val context: Context ?= null

    val KEY_ID = "_id"
    val COLUMN_KEY_ID = 0
    val MOVIE_NAME = "movie_name"
    val COLUMN_NAME_ID = 1
    val OVERVIEW = "overview"
    val COLUMN_OVERVIEW_ID = 2
    val LANGUAGE = "language"
    val COLUMN_LANGUAGE_ID = 3
    val RELEASE_DATE = "release_date"
    val COLUMN_DATE_ID = 4

    private val MYDBADAPTER_LOG_CAT = "MY_LOG"

    private var dbHelper: SimpleMovieDbHelper? = null

    init {
        dbHelper = SimpleMovieDbHelper(c)
    }

    fun close() {
        _db?.close()
    }


    fun open() {
        try{
            _db = dbHelper?.getWritableDatabase()
        }catch(e: SQLiteException){
            _db = dbHelper?.getReadableDatabase()
        }

    }

    fun insertEntry(movieName: String, movieOverview: String, movieLang: String, movieRelease: String): Long? {
        val newEntryValues = ContentValues()

        newEntryValues.put(MOVIE_NAME, movieName)
        newEntryValues.put(OVERVIEW, movieOverview)
        newEntryValues.put(LANGUAGE, movieLang)
        newEntryValues.put(RELEASE_DATE, movieRelease)


        return _db?.insert(DATABASE_TABLE, null, newEntryValues)
    }

    fun removeEntry(_rowIndex: Int): Boolean {

        if (_db!!.delete(DATABASE_TABLE, KEY_ID + " = " + _rowIndex, null) <= 0 ){
            Log.w(MYDBADAPTER_LOG_CAT, "Removing entry where id = $_rowIndex failed")
            return false
        }
        return true
    }

    fun retrieveAllEntriesCursor(): Cursor? {
        var c: Cursor? = null
        try{
            c = _db?.query(DATABASE_TABLE,
                        arrayOf(KEY_ID, MOVIE_NAME, OVERVIEW, LANGUAGE, RELEASE_DATE),
                        null,
                        null,
                        null,
                        null,
                        null)
        }catch(e:SQLiteException){
            Log.w(DATABASE_TABLE, "Retrieve fail")
        }

        return c
    }
}