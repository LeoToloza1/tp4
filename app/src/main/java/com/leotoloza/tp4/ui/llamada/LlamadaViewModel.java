package com.leotoloza.tp4.ui.llamada;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LlamadaViewModel extends AndroidViewModel {
    private MutableLiveData<String> nroTelefono;
    private MutableLiveData<String> msjError;

    public LlamadaViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getNroTelefono() {
        if (nroTelefono == null) {
            nroTelefono = new MutableLiveData<>();
        }
        return nroTelefono;
    }
    public LiveData<String> getMsjError() {
        if (msjError == null) {
            msjError = new MutableLiveData<>();
        }
        return msjError;
    }
    public void iniciarLlamada(Context context, String phoneNumber) {
        Intent intentLlamada = new Intent(Intent.ACTION_CALL);
        intentLlamada.setData(Uri.parse("tel:" + phoneNumber)); // Cambia "2664553747" por el número de teléfono deseado
        intentLlamada.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentLlamada);
    }

    public void mostrarError(String mensajeError) {
        msjError.setValue(mensajeError);
    }

}