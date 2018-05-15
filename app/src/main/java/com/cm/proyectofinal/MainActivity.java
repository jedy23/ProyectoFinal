package com.cm.proyectofinal;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    DB db;

    ArrayList<String> arrayJugadores = new ArrayList<>();
    ArrayList<Integer> arrayRutas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FetchItemTask().execute();

        //iniciarDB();

        //Erik hizo commit
        //Viri hizo commit
        //El guapo nigga hizo commit
        //Otro guapo hizo commit

    }

    //Metodo para cargar la DB
    public void iniciarDB(){
        db = new DB(this);
        db.openBD();
        db.updateDB();
    }

    //Metodos para cargar los datos por primera vez
    public void cargarPrimerosDatos(ArrayList<String> nombreJugadores,  ArrayList<String> rutaDrawable) {

        for (int i = 0; i< nombreJugadores.size(); i++) {

            ArrayList<Pair<String, String>> list = new ArrayList<>();

            list.add(new Pair<String, String>(db.tableTarjeta.nombre, nombreJugadores.get(i)));
            list.add(new Pair<String, String>(db.tableTarjeta.numero_tarjeta, "0"));
            list.add(new Pair<String, String>(db.tableTarjeta.ruta_drawable, rutaDrawable.get(i)));

            db.insert(db.tableTarjeta.tableName, list);

            list.clear();
        }

    }

    public void cargarJugadores(){

        arrayJugadores.add("Nawaf Al Abed");
        arrayJugadores.add("Mohamed Salah");
        arrayJugadores.add("Emil Forsberg");
        arrayJugadores.add("Aleksandr Kokorin");
        arrayJugadores.add("Victor Moses");
        arrayJugadores.add("Philippe Coutinho");
        arrayJugadores.add("Eden Hazard");
        arrayJugadores.add("Olivier Giroud");
        arrayJugadores.add("James Rodríguez");
        arrayJugadores.add("Sadio Mané");
        arrayJugadores.add("Hirving Lozano");
        arrayJugadores.add("Cristiano Ronaldo");
        arrayJugadores.add("Andrés Iniesta");
        arrayJugadores.add("Branislav Ivanovic");
        arrayJugadores.add("Gylfi Sigurdsson");
        arrayJugadores.add("Lionel Messi");
        arrayJugadores.add("Paolo Guerrero");
        arrayJugadores.add("Marcelo Vieira");
        arrayJugadores.add("Shinji Kagawa");
        arrayJugadores.add("Radamel Falcao");
        arrayJugadores.add("Mesut Özil");
        arrayJugadores.add("Moussa Konaté");
        arrayJugadores.add("Luca Modric");
        arrayJugadores.add("Hamza Mendyl");
        arrayJugadores.add("Romelu Lukaku");
        arrayJugadores.add("Ferjani Sassi");
        arrayJugadores.add("Mehdi Benatia");
        arrayJugadores.add("Francisco Alarcón");
        arrayJugadores.add("Toni Kroos");
        arrayJugadores.add("Christian Eriksen");
        arrayJugadores.add("Luis Suárez");
        arrayJugadores.add("Xherdan Shaqiri");
        arrayJugadores.add("Kylian Mbappé");
        arrayJugadores.add("Thomas Müller");
        arrayJugadores.add("Mile Jedinak");
        arrayJugadores.add("Alex Iwobi");
        arrayJugadores.add("Carlos Vela");
        arrayJugadores.add("Manuel Neuer");
        arrayJugadores.add("Javier Hernández");
        arrayJugadores.add("Sergio Agüero");
        arrayJugadores.add("Andrés Guardado");
        arrayJugadores.add("Harry Kane");
        arrayJugadores.add("Son Heung-min");
        arrayJugadores.add("Paulo Dybala");
        arrayJugadores.add("Antoine Griezmann");
        arrayJugadores.add("Neymar Jr.");
        arrayJugadores.add("Keylor Navas");
        arrayJugadores.add("Sergio Ramos");
        arrayJugadores.add("Robert Lewandowski");
        arrayJugadores.add("Edinson Cavani");


    }

    public void cargarRutas(){

        arrayRutas.add(R.drawable.nawafalabed);
        arrayRutas.add(R.drawable.mohamedsalah);
        arrayRutas.add(R.drawable.emilforsberg);
        arrayRutas.add(R.drawable.aleksandrkokorin);
        arrayRutas.add(R.drawable.victormoses);
        arrayRutas.add(R.drawable.philippecoutinho);
        arrayRutas.add(R.drawable.edenhazard);
        arrayRutas.add(R.drawable.oliviergiroud);
        arrayRutas.add(R.drawable.jamesrodriguez);
        arrayRutas.add(R.drawable.sadiomane);
        arrayRutas.add(R.drawable.hirvinglozano);
        arrayRutas.add(R.drawable.cristianoronaldo);
        arrayRutas.add(R.drawable.andresiniesta);
        arrayRutas.add(R.drawable.branislavivanovic);
        arrayRutas.add(R.drawable.gylfisigurdsson);
        arrayRutas.add(R.drawable.lionelmessi);
        arrayRutas.add(R.drawable.paologuerrero);
        arrayRutas.add(R.drawable.marcelovieira);
        arrayRutas.add(R.drawable.shinjikagawa);
        arrayRutas.add(R.drawable.radamelfalcao);
        arrayRutas.add(R.drawable.mezutozil);
        arrayRutas.add(R.drawable.moussakonate);
        arrayRutas.add(R.drawable.lucamodric);
        arrayRutas.add(R.drawable.hamzamendyl);
        arrayRutas.add(R.drawable.romelulukaku);
        arrayRutas.add(R.drawable.ferjanisassi);
        arrayRutas.add(R.drawable.mehdibenatia);
        arrayRutas.add(R.drawable.franciscoalarcon);
        arrayRutas.add(R.drawable.tonikroos);
        arrayRutas.add(R.drawable.christianeriksen);
        arrayRutas.add(R.drawable.luissuarez);
        arrayRutas.add(R.drawable.xherdanshaqiri);
        arrayRutas.add(R.drawable.kylianmbappe);
        arrayRutas.add(R.drawable.thomasmuller);
        arrayRutas.add(R.drawable.milejedinak);
        arrayRutas.add(R.drawable.alexiwobi);
        arrayRutas.add(R.drawable.carlosvela);
        arrayRutas.add(R.drawable.manuelneuer);
        arrayRutas.add(R.drawable.javierhernandez);
        arrayRutas.add(R.drawable.sergioaguero);
        arrayRutas.add(R.drawable.andresguardado);
        arrayRutas.add(R.drawable.harrykane);
        arrayRutas.add(R.drawable.sonheumin);
        arrayRutas.add(R.drawable.antoinegriezmann);
        arrayRutas.add(R.drawable.neymarjr);
        arrayRutas.add(R.drawable.keylornavas);
        arrayRutas.add(R.drawable.sergioramos);
        arrayRutas.add(R.drawable.paulodybala);
        arrayRutas.add(R.drawable.robertlewandowski);
        arrayRutas.add(R.drawable.edinsoncavani);

    }

    private static class FetchItemTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params){
         try{
           String result = new PlayerFetcher().getUrlString("http://serverbpw.com/cm/cards.php?type=json");
           Log.i(TAG, "Archivos recuperados de URL: "+result);
         }catch(IOException ioe){
             Log.d(TAG, "Error al recuperar archivos de URL: ", ioe);
         }
         //new PlayerFetcher().FetchItems();
         return null;
        }
    }
}
