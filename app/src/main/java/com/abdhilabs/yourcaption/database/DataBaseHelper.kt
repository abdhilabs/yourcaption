package com.abdhilabs.yourcaption.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.abdhilabs.yourcaption.model.Caption

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Create Table SQL Query
    private val CREATE_CAPTION_TABLE = ("CREATE TABLE " + TABLE_CAPTION + "("
            + COLUMN_CAPTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CAPTION_ISI + " Text" + ")")

    // Drop Table SQL Query
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_CAPTION"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_CAPTION_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop Caption Table if exist
        db.execSQL(DROP_USER_TABLE)
        // Create Table again
        onCreate(db)
    }

    /**
     * Untuk mengambil semua caption
     */
    fun getAllCaption(): ArrayList<Caption> {
        // Array of column to fetch
        val columns = arrayOf(COLUMN_CAPTION_ID, COLUMN_CAPTION_ISI)

        // Sorting Orders
        val sortOrder = "$COLUMN_CAPTION_ISI ASC"
        val captionList = ArrayList<Caption>()

        val db = this.readableDatabase

// Query the caption table
        val cursor = db.query(
            TABLE_CAPTION,    //Table to Query
            columns,          // Columns to return
            null,    // Columns for the WHERE clause
            null, //The values for WHERE clauses
            null,     //Group the rows
            null,      //filter by row groups
            sortOrder
        )
        if (cursor.moveToFirst()) {
            do {
                val caption = Caption(
                    id = cursor.getString(cursor.getColumnIndex(COLUMN_CAPTION_ID)).toInt(),
                    isicaption = cursor.getString(cursor.getColumnIndex(COLUMN_CAPTION_ISI))
                )
                captionList.add(caption)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return captionList
    }

    /**
     * Membuat method (Create/Add) record
     */
    fun addCaption(caption: Caption) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_CAPTION_ISI, caption.isicaption)

        //Inserting Row
        db.insert(TABLE_CAPTION, null, values)
        db.close()

    }

    /**
     * Membuat method Update Record
     */
    fun updateCaption(caption: Caption) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_CAPTION_ISI, caption.isicaption)

        //Updating Row
        db.update(
            TABLE_CAPTION, values, "$COLUMN_CAPTION_ID = ?",
            arrayOf(caption.id.toString())
        )
        db.close()
    }

    /**
     * Membuat method Delete Record
     */

    fun deleteCaption(caption: Caption){

        val db = this.writableDatabase
        // Delete Caption by ID
        db.delete(
            TABLE_CAPTION,"$COLUMN_CAPTION_ID = ?",
            arrayOf(caption.id.toString())
        )
        db.close()
    }



    companion object {

        // Database Version
        private val DATABASE_VERSION = 2

        // Database Version
        private val DATABASE_NAME = "CaptionManager.db"

        // Caption Table Name
        private val TABLE_CAPTION = "caption"

        // Caption Table Column names
        private val COLUMN_CAPTION_ID = "caption_id"
        private val COLUMN_CAPTION_ISI = "caption_isi"

    }
}