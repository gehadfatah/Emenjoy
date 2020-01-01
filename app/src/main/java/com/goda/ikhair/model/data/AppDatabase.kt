package eg.naqaaee.core.data

import android.content.Context
import android.provider.MediaStore
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.goda.ikhair.model.data.VideoDao
import com.goda.ikhair.model.data_model.Video


@Database(
    entities = [Video::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun videoDao(): VideoDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "product_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}