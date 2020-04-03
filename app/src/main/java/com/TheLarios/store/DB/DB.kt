package com.TheLarios.store.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.TheLarios.store.Data.Item

class DB(context: Context?) : SQLiteOpenHelper(context, "Store", null, 1)
{
    companion object
    {
        private var instance : DB?= null

        fun getInstance(context : Context) : DB? =
            if (instance == null)
            {
                instance =
                    DB(context)
                instance
            }
            else
            {
                instance
            }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE PRODUCTS(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "NAME VARCHAR(200)," +
                    "DESCRIPTION VARCHAR(200)," +
                    "PRICE FLOAT," +
                    "URL VARCHAR(200))")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    val Context.database : DB?
        get() = getInstance(
            applicationContext
        )

    //---------------------------------------------------------------------------------------------
    fun insert(name : String, desc : String, price : Float, url : String?) : Boolean
    {
        try
        {
            val toInsert = this.writableDatabase
            val data = ContentValues()

            data.put("NAME", name)
            data.put("DESCRIPTION", desc)
            data.put("PRICE", price)
            data.put("URL", url)

            val answer = toInsert.insert("PRODUCTS", "ID", data)
            if(answer.toInt() == -1)
                return false
        }
        catch(e : SQLiteException)
        {
            return false
        }

        return true
    }

    fun select() : ArrayList<Item>
    {
        var data = ArrayList<Item>()

        try
        {
            var select = this.readableDatabase
            var columns = arrayOf("*")

            var cursor = select.query("PRODUCTS", columns, null, null, null, null, null)
            if(cursor.moveToFirst())
            {
                do
                {
                    var item = Item(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getFloat(3),
                        cursor.getString(4)
                    )
                    data.add(item)
                }while(cursor.moveToNext())
            }
        }
        catch(err:SQLiteException) { }

        return data
    }
}