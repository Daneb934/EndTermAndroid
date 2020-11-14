package kz.astanamotorstest.movieitem.data.local

import android.content.Context
import androidx.room.*
import kz.astanamotorstest.movieitem.data.local.dao.FavoriteDao
import kz.astanamotorstest.movieitem.data.local.entity.FavoriteRoom

@Database(
    entities = [
        FavoriteRoom::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DB : RoomDatabase(){

    abstract fun getFavoriteDao(): FavoriteDao

    companion object{

        @Volatile
        private lateinit var INSTANCE: DB

        fun getDatabase(context: Context): DB {
            synchronized(DB::class.java) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    DB::class.java, "astanamotors_kz"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return INSTANCE
        }
    }
}