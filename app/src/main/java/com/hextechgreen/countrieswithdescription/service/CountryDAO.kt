package com.hextechgreen.countrieswithdescription.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hextechgreen.countrieswithdescription.model.Country
import java.util.*

@Dao
interface CountryDAO {

    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long>

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()

    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM country WHERE uuid = :uuid")
    suspend fun getCountry(uuid: UUID) : Country
}