package com.cm.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by Villegas on 06/05/2018.
 */

public class DB {

    private String dbName = "tarjetas_rusia";
    private int dbVersion = 1;

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    Context context;

    TableConfiguracion tableConfiguracion;
    TableTarjeta tableTarjeta;

    public DB(Context context) {
        this.context = context;

        ArrayList<Pair<String, String>> tables = generateTables();
        sqLiteOpenHelper = new CreateDB(context, dbName, dbVersion, tables);
    }



    public ArrayList<Pair<String, String>> generateTables() {
        tableConfiguracion = new TableConfiguracion(context);
        tableTarjeta = new TableTarjeta(context);


        ArrayList<Pair<String, String>> arrayList = new ArrayList<>();

        arrayList.add(new Pair<String, String>(tableConfiguracion.createTable(), tableConfiguracion.tableName));
        arrayList.add(new Pair<String, String>(tableTarjeta.createTable(), tableTarjeta.tableName));

        return arrayList;
    }

    //Creamos un objeto de lectura/escritura para abrir la base de datos

    public void openBD() {
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    //Cerramos la base de datos

    public void closeDB() {
        sqLiteOpenHelper.close();
    }

    public void updateDB() {
        ArrayList<Pair<String, String>> tables = generateTables();

        for (int i = 0; i < tables.size(); i++) {
            try {
                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + tables.get(i).second, null);
                cursor.close();
            } catch (Exception e) {
                Log.e("Error", "No existe tabla y no se puede seleccionar " + tables.get(i).second);
            }
            if (tableExists(tables.get(i).second)) {
                Log.e("Error", "Existe tabla " + tables.get(i).second);
            } else {
                Log.e("Error", "No existe tabla " + tables.get(i).second + " Se creará");
                createTable(tables.get(i).first);
            }
        }
    }

    private void createTable(String query) {
        sqLiteDatabase.execSQL(query);
    }

    private boolean tableExists(String tableName) {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT 1 FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }


    public ArrayList<ArrayList<String>> select(String table, ArrayList<String> fields, String where, String order) {

        String fieldSelect = new String("");

        if (fields.size() <= 0) {
            fieldSelect = "*";
        } else {
            for (int i = 0; i < fields.size() - 1; i++) {
                fieldSelect = fieldSelect + fields.get(i) + ", ";
            }

            fieldSelect = fieldSelect + fields.get(fields.size() - 1);
        }

        String querySelect = new String("SELECT " + fieldSelect + " FROM " + table);

        if (!where.equals("")) {
            querySelect = querySelect + (" WHERE " + where);
        }

        if (!order.equals("")) {
            querySelect = querySelect + (" ORDER BY " + order);
        }

        Cursor cursor = sqLiteDatabase.rawQuery(querySelect, null);

        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                ArrayList<String> aux = new ArrayList<>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    if (cursor.getType(i) == Cursor.FIELD_TYPE_INTEGER) {
                        aux.add("" + cursor.getInt(i));
                    } else {
                        if (cursor.getType(i) == Cursor.FIELD_TYPE_STRING) {
                            aux.add(cursor.getString(i));
                        }
                    }
                }

                arrayLists.add(aux);
            } while (cursor.moveToNext());

            cursor.moveToNext();
        }

        cursor.close();

        return arrayLists;
    }

    public long insert(String table, ArrayList<Pair<String, String>> values) {
        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < values.size(); i++) {
            contentValues.put(values.get(i).first, values.get(i).second);
        }

        return sqLiteDatabase.insert(table, null, contentValues);
    }

    public long update(String table, ArrayList<Pair<String, String>> values, String where) {
        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < values.size(); i++) {
            contentValues.put(values.get(i).first, values.get(i).second);
        }
        return sqLiteDatabase.update(table, contentValues, where, null);
    }

    public int delete(String table, String where) {
        return sqLiteDatabase.delete(table, where, null);
    }

    public void execQuery(String query) {
        sqLiteDatabase.execSQL(query);
    }

    public Cursor execSelect(String select) {
        return sqLiteDatabase.rawQuery(select, null);
    }


    public class TableConfiguracion {

        String tableName = "CONFIGURACION";

        String id_configuracion = "id_configuracion";
        String nombre = "nombre";
        String ap_paterno = "ap_paterno";
        String ap_materno = "ap_materno";
        String correo = "correo";
        String ruta_foto = "ruta_foto";


        Context context;

        public TableConfiguracion(Context context) {
            this.context = context;
        }

        public String createTable() {
            StringBuilder table = new StringBuilder("CREATE TABLE " + tableName + "(");
            table.append(id_configuracion + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
            table.append(nombre + " TEXT, ");
            table.append(ap_paterno + " TEXT, ");
            table.append(ap_materno + " TEXT, ");
            table.append(correo + " TEXT, ");
            table.append(ruta_foto + " TEXT");
            table.append(")");

            return table.toString();
        }
    }


    public class TableTarjeta {

        String tableName = "TARJETA";

        String id_tarjeta = "id_tarjeta";
        String nombre = "nombre";
        String numero_tarjeta = "numero_tarjeta";
        String ruta_drawable = "ruta_drawable";

        Context context;

        public TableTarjeta(Context context) {
            this.context = context;
        }

        public String createTable() {
            StringBuilder table = new StringBuilder("CREATE TABLE " + tableName + "(");
            table.append(id_tarjeta + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
            table.append(nombre + " TEXT, ");
            table.append(numero_tarjeta + " TEXT, ");
            table.append(ruta_drawable + " TEXT ");
            table.append(")");

            return table.toString();
        }
    }


}
