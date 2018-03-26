package incture.com.roomexample.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import incture.com.roomexample.NoteDao;
import incture.com.roomexample.models.Note;

/**
 * Created by Ritwik.Jain on 3/20/2018.
 */
@Database(entities = {Note.class},version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public static AppDatabase INSTANCE;
    public abstract NoteDao getnoteDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "note-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
