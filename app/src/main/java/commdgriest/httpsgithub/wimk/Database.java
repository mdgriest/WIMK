package commdgriest.httpsgithub.wimk;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class Database  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WIMK_DB";

    /* Constructor */
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_INVENTORY = "CREATE TABLE " + Item.TABLE + "("
                + Item.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Item.KEY_name + " TEXT, "
                + Item.KEY_quantity + " INTEGER, "
                + Item.KEY_iconID + " INTEGER, "
                + Item.KEY_shouldShow + "BOOLEAN, "
                + Item.KEY_color + " INTEGER )";

        db.execSQL(CREATE_TABLE_INVENTORY);
    }

    /*
        Executed only when the database version is updated (drops and creates queries)
        (Should never execute in our app)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Item.TABLE);
        /* Create tables again */
        onCreate(db);
    }
}
