package com.leotoloza.tp4.ui.llamada;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
public class llamadaBroadcast extends BroadcastReceiver {

    // Variable para controlar si la aplicación ya se ha iniciado
    private static boolean appIniciada = false;
//tuve que modificar el broadcast original porque mi android al ser una version vieja, no me funcionaba
    //el codigo original:
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager gestorConexion =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo redActiva = gestorConexion.getActiveNetworkInfo();
        boolean estaConectado = redActiva != null &&
                redActiva.isConnectedOrConnecting();
        boolean esWiFi = estaConectado && redActiva.getType() == ConnectivityManager.TYPE_WIFI;

        Log.d("salida","LLAMADA BROADCAST");
        if (estaConectado && esWiFi && appIniciada) {
            Intent intencionLlamada = new Intent(Intent.ACTION_CALL);
            intencionLlamada.setData(Uri.parse("tel:" + "1133466839")); // Cambiar "2664553747" para llamar al profe
            intencionLlamada.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                context.startActivity(intencionLlamada);
            }
        } else {
            Toast.makeText(context, "WiFi Desactivado, No se puede Llamar", Toast.LENGTH_LONG).show();
        }

        // Establecer appIniciada en true después de la primera vez que se recibe el broadcast
        appIniciada = true;
    }
//    public void onReceive(Context context, Intent intent) {
//        boolean modoWifi = intent.getBooleanExtra("connected", false);
//        Log.d("salida","LLAMADA BROADCAST");
//        if (modoWifi) {
//            Intent intentLlamada = new Intent(Intent.ACTION_CALL);
//            intentLlamada.setData(Uri.parse("tel:" + "1133466839")); // Cambiar "2664553747" para llamar al profe
//            intentLlamada.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intentLlamada);
//        } else {
//            Toast.makeText(context, "WiFi Desactivado, No se puede Llamar", Toast.LENGTH_LONG).show();
//        }
//    }

}
