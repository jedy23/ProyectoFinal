package com.cm.proyectofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by Villegas on 09/05/2018.
 */

public class CreateDB extends SQLiteOpenHelper {

    Context context;
    ArrayList<Pair<String, String>> tables;

    public CreateDB(Context context, String name, int version, ArrayList<Pair<String, String>> tables) {
        super(context, name, null, version);

        this.context = context;
        this.tables = tables;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*Como el primer elemento de cada par trae el query para crear la tabla, obtenemos
        dicho elemento para crear la tabla
         */
        for (int i = 0; i < tables.size(); i++) {
            sqLiteDatabase.execSQL(tables.get(i).first);

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int j, int j1) {

        /*Como el segundo elemento de cada par trae el nombre de cada tabla, obtenemos
        dicho elemento para indicar cuál tabla vamos a tirar si es que existe*/
        for (int i = 0; i < tables.size(); i++) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + tables.get(i).second);
        }

        onCreate(sqLiteDatabase);
    }

    //Este método lo sobreescribimos para indicar que se respeten las llaves foráneas en la base de datos
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
