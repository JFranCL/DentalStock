package Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

// SQLiteOpenHelper hads a set of helpful API
class DbHelper (context: Context): SQLiteOpenHelper (context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(Tables.ProductTable.SQL_CREATE_PODUCTS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Tables.ProductTable.SQL_DELETE_PRODUCTS)
        onCreate(db)
    }

    //DataBase Properties
    companion  object{
        private val  DATABASE_VERSION = 1
        private val DATABASE_NAME = "DentalStock.db"
    }

    //Must be at sub process

    // Gets the data repository in write mode
    val writableDb = writableDatabase

    // Create a new map of values, where column names are the key
    val values = ContentValues().apply {

        put(Tables.ProductTable.COLUMN_PRODUCT_NAME, "product_name")
        put(Tables.ProductTable.COLUMN_TYPE, "type")
        put(Tables.ProductTable.COLUMN_STOCK, 20)

    }

    val newRowId = writableDb?.insert(
            Tables.ProductTable.TABLE_NAME,
            null,
            values
    )


    // Gets the data repository in read mode
    val redableDb = readableDatabase

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
    val projection = arrayOf(
            Tables.ProductTable.COLUMN_ID,
            Tables.ProductTable.COLUMN_PRODUCT_NAME,
            Tables.ProductTable.COLUMN_TYPE,
            Tables.ProductTable.COLUMN_STOCK
    )


    val cursor = redableDb.query(
            Tables.ProductTable.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,              // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
    )



    val itemIds = mutableListOf<Long>()
    fun cursormove{
        with(cursor) {
            while (cursor.moveToNext()) {
                val itemId = cursor.getLong(cursor.getColumnIndexOrThrow(Tables.ProductTable.COLUMN_ID))
                itemIds.add(itemId)
            }
        }
    }



}