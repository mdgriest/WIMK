package commdgriest.httpsgithub.wimk;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jlisicky on 4/21/16.
 */
public class Database  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WIMK_DB";
    protected static final String FIRST_TABLE_NAME = "Inventory";
    private static Item item = new Item();

    public static final String CREATE_TABLE_INVENTORY = "CREATE TABLE "
            + FIRST_TABLE_NAME + " ( "
            + item.KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + item.KEY_name + " TEXT,"
            + item.KEY_quantity + "INTEGER, "
            + item.KEY_iconID + "INTEGER, "
            + item.KEY_shouldShow + "BOOLEAN, "
            + item.KEY_color + "INTEGER)";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_INVENTORY);
        //db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //THIS WILL BE EXECUTED WHEN YOU UPDATED VERSION OF DATABASE_VERSION
        //YOUR DROP AND CREATE QUERIES
        db.execSQL("DROP TABLE IF EXISTS " + FIRST_TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

   // SQLiteDatabase myDb = openOrCreateDatabase('inventory.db', Context.MODE_PRIVATE, null);


}
