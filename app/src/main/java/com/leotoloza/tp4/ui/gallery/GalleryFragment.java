package com.leotoloza.tp4.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.leotoloza.tp4.R;
import com.leotoloza.tp4.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

    binding = FragmentGalleryBinding.inflate(inflater, container, false);
    View root = binding.getRoot();


        ImageView img = binding.imgPerfil;
        img.setImageResource(R.drawable.designer);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}