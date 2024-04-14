package com.leotoloza.tp4.ui.llamada;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.leotoloza.tp4.databinding.FragmentLlamadaBinding;

public class LlamadaFragment extends Fragment {

    private LlamadaViewModel llamadaViewModel;
    private FragmentLlamadaBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        llamadaViewModel = new ViewModelProvider(this).get(LlamadaViewModel.class);
        binding = FragmentLlamadaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        llamadaViewModel.getMsjError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.msjError.setText(s);
            }
        });

        binding.btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = binding.editTextPhone.getText().toString();

                if (!phoneNumber.isEmpty()) {
                    llamadaViewModel.iniciarLlamada(getContext(),phoneNumber);
                } else {
                    llamadaViewModel.mostrarError("Ingrese un número de teléfono");
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
