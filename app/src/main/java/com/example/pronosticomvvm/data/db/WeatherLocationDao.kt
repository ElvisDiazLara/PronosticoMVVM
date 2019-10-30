package com.example.pronosticomvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pronosticomvvm.data.db.entity.WEATHER_LOCATION_ID

@Dao
interface WeatherLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(location: com.example.pronosticomvvm.data.db.entity.Location)

    @Query("select * from weather_location where id = $WEATHER_LOCATION_ID")
    fun getLocation(): LiveData<com.example.pronosticomvvm.data.db.entity.Location>

}