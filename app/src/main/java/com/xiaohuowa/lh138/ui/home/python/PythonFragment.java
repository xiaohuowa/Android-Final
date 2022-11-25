package com.xiaohuowa.lh138.ui.home.python;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaohuowa.lh138.R;
import com.xiaohuowa.lh138.adapter.PythonAdapter;

import java.nio.file.DirectoryIteratorException;

public class PythonFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // 绑定适配器
        View root = inflater.inflate(R.layout.fragment_python,container,false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL));
        PythonAdapter pythonAdapter = new PythonAdapter(null);
        recyclerView.setAdapter(pythonAdapter);

        PythonViewModel pythonViewModel = new ViewModelProvider(this).get(PythonViewModel.class);
        pythonViewModel.getPythonList().observe(getViewLifecycleOwner(), pythonAdapter::setList);

        return root;
    }

}