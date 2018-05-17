package com.cm.proyectofinal;

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


    public void FetchItems(){
        //AUN NO LO IMPLELEMTO
    }
}
