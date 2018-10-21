package com.example.pepi.busmanagmentsystem.Services;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pepi_ on 10/07/2018.
 */

public class ApiHandler {


    public String getJsonString(String urlAddress)
    {
        URL url;
        HttpURLConnection urlConnection;
        String result="";

        try {
            url=new URL(urlAddress);
            urlConnection= (HttpURLConnection) url.openConnection();

            InputStream inputStream=new BufferedInputStream(urlConnection.getInputStream());
            result= convertToString(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    private String convertToString(InputStream inputStream)
    {
        StringBuilder builder=new StringBuilder();

        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);


        String line;

        try {
            while((line=bufferedReader.readLine())!=null)
            {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
