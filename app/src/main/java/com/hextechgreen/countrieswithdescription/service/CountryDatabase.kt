package com.hextechgreen.countrieswithdescription.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hextechgreen.countrieswithdescription.model.Country

@Database(entities = [Country::class], version = 1)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDAO

    companion object {

        //Farklı thread lerden de erişilebilsin diye Volatile wrapper kullandık!!!
        @Volatile
        private var instance: CountryDatabase? = null

        private var lock = Any()

        //instance varsa instance döndürülecek ama eğer yoksa ve birden fazla yerden erişim yapılmaya çalışılıyorsa
        //senkronize bir şekilde sırayla işlem yapılacak!!!
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {

            instance ?: makeDatabase(context).also {

                instance = it

            }

        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context, CountryDatabase::class.java, "countryDatabase"
        ).build()

    }
}