package com.example.localizacionyllamada.app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hector on 03/06/2014.
 */
public class TomarValores {
        public static final String  TAG =
                Llamar.class.getSimpleName();

        JSONArray TomarValores(int responseCode, String resultado, JSONArray jsonResponse) {
            try{
                URL apiURL =  new URL(
                        "http://codipaj.com/itchihuahuaii/eq5/HectorFrias/numeros.php");

                HttpURLConnection httpConnection = (HttpURLConnection)
                        apiURL.openConnection();
                httpConnection.connect();
                responseCode = httpConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK){
                    InputStream inputStream = httpConnection.getInputStream();
                    BufferedReader bReader = new BufferedReader(
                            new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bReader.readLine()) != null) {
                        //sBuilder.append(line + "\n");
                        sBuilder.append(line);
                    }
                    inputStream.close();
                    resultado = sBuilder.toString();
                    resultado = "[" + resultado + "]";
                    Log.d(TAG, resultado);
                    jsonResponse = new JSONArray(resultado);

                }else{
                    Log.i(TAG, "Error en el HTTP " + responseCode);
                }
            }
            catch (JSONException e){}
            catch (MalformedURLException e){}
            catch (IOException e){}
            catch (Exception e){}
            return jsonResponse;
        }
    }


