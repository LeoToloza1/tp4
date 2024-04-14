package com.leotoloza.tp4;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.leotoloza.tp4.data.model.Usuario;
import com.leotoloza.tp4.databinding.ActivityLoginBinding;
import com.leotoloza.tp4.ui.llamada.llamadaBroadcast;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    private llamadaBroadcast llamadaWIF;
private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pedirPermiso();
        registrarBrodcast();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Inicializar el ViewModel
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(LoginViewModel.class);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.username.getText().toString();
                String password = binding.password.getText().toString();
                viewModel.login(username, password);
            }
        });

        viewModel.getMutableUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                    intent = new Intent(LoginActivity.this, SegundaActivity.class);
                    startActivity(intent);
                    //cuando llama a la segunda activity se limpian los campos
                binding.username.setText("");
                binding.password.setText("");
            }
        });
    }
    public void pedirPermiso() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 2500);
        }
    }

    public void registrarBrodcast(){
        this.llamadaWIF = new llamadaBroadcast();
        Log.d("salida","Aca llega el metodo");
        registerReceiver(llamadaWIF,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(llamadaWIF);
    }
}
