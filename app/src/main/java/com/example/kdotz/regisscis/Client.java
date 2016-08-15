package com.example.kdotz.regisscis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by kdotz on 8/8/2016.
 */
public class Client extends AsyncTask<Void, Void, Void> {

    String dstAddress = "www.regis.edu";
    int dstPort = 8080;
    String response = "";
    TextView textResponse;

    @Override
    protected Void doInBackground(Void... arg0) {

        Socket SocketManager = null;
        DataOutputStream os = null;
        DataInputStream is = null;

        try {
            SocketManager = new Socket(dstAddress, dstPort);
            os = new DataOutputStream(SocketManager.getOutputStream());
            is = new DataInputStream(SocketManager.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }


        if (SocketManager != null && os != null && is != null) {
            try {
                os.writeBytes("HELLO\n");
                os.writeBytes("THIS CLASS IS GREAT!\n");

                String responseLine;
                while ((responseLine = is.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("Ok") != -1) {
                        break;
                    }
                }

                // clean up:
                // close the output stream
                // close the input stream
                // close the socket

                os.close();
                is.close();
                SocketManager.close();
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }

        return null;

    }

    @Override
    protected void onPostExecute(Void result) {
        textResponse.setText(response);
        super.onPostExecute(result);
    }


}