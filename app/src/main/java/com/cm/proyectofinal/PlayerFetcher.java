package com.cm.proyectofinal;

import android.util.Log;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlayerFetcher {

    private static final String TAG = "PlayerFetcher";

    public byte[] getUrlBytes (String urlSpec) throws IOException{

        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw new IOException(connection.getResponseMessage() + ": con "+ urlSpec);
            }
            int bytesRead= 0;
            byte[] buffer = new byte[1024];
            while((bytesRead = in.read(buffer))>0){
                out.write(buffer, 0, bytesRead);
            }

            out.close();
            return out.toByteArray();
        }finally{
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException{
        return new String(getUrlBytes(urlSpec));
    }


    public void fetchItems(){

        try{
            String result = new PlayerFetcher().getUrlString("http://serverbpw.com/cm/cards.php?type=json");
            Log.i(TAG, "Archivos recuperados de URL: "+result);
            parseItemsWithGson(result);
        }catch(IOException ioe){
            Log.d(TAG, "Error al recuperar archivos de URL: ", ioe);
        }
        //AUN NO LO IMPLELEMTO
    }


    public void parseItemsWithJson() {

    }

    public void parseItemsWithGson(String result){

        /*Igual que en Json debemos tener una clase que tenga los mismos
          atributos que el JSONobject que estamos rebiendo.

          Entonces, creo que debería crear una clase "Sobre.java" que tenga la sig Estructura:

           class Sobre{
          List<Jugador> result;

            public class Jugador{
                int id;
            }//Termina clase interna Jugador

          }//Termina clase Sobre

        */
        Sobre sobre = new Gson().fromJson(result, Sobre.class);

    }
}
