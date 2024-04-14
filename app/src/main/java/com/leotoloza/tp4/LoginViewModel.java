package com.leotoloza.tp4;

import android.app.Application;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leotoloza.tp4.data.model.Usuario;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuarioLiveData;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Usuario> getMutableUsuario() {
        if (usuarioLiveData == null) {
            usuarioLiveData = new MutableLiveData<>();
        }
        return usuarioLiveData;
    }

    public void login(String nombre, String pass) {
        Usuario user = new Usuario();
        user.setNombre("Leonel");
        user.setPassword("123456");
        if (nombre.equalsIgnoreCase(user.getNombre()) && pass.equalsIgnoreCase(user.getPassword())) {
            usuarioLiveData.setValue(user);
        }
    }
}
