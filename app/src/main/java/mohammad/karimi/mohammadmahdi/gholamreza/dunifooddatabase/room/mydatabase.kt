package mohammad.karimi.mohammadmahdi.gholamreza.dunifooddatabase.room

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder

import androidx.room.RoomDatabase




@Database(version = 1 , exportSchema = false , entities = [ Food :: class]  )
abstract class mydatabase : RoomDatabase() {
    abstract val foodDao: foodDao


    companion object {
        @Volatile
        private var database: mydatabase? = null
        fun getdatabase(context: Context): mydatabase {
            synchronized(this) {

                if (database == null) {
                    database = Room.databaseBuilder(context.applicationContext,
                        mydatabase::class.java,
                        "mydatabase.db").allowMainThreadQueries().build()

                }



                return database!!
            }
        }
    }

    }

