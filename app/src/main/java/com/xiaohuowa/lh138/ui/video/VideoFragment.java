package com.xiaohuowa.lh138.ui.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.xiaohuowa.lh138.R;
import com.xiaohuowa.lh138.databinding.FragmentVideoBinding;

public class VideoFragment extends Fragment {

    private FragmentVideoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VideoViewModel videoViewModel =
                new ViewModelProvider(this).get(VideoViewModel.class);

        binding = FragmentVideoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = root.findViewById(R.id.text_video);
        videoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}