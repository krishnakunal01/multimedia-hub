package com.example.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;

import com.example.adapter.PdfAdapter;
import com.example.multimedia.Pdfitem;
import com.example.multimedia.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentPdf extends Fragment {


    RecyclerView pdfrecyclerview;
    PdfAdapter pdfAdapter;

    public FragmentPdf() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pdf, container, false);



        pdfrecyclerview = view.findViewById(R.id.recyclerViewPdf);
        pdfrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        pdfAdapter = new PdfAdapter(getActivity(),pdfFiles());
        pdfrecyclerview.setAdapter(pdfAdapter);




        return view;
    }

    private  ArrayList<String> pdfFiles(){
        ContentResolver contentResolver = getActivity().getContentResolver();

        String mime = MediaStore.Files.FileColumns.MIME_TYPE +"=?";
        String memeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf");
        String[] args = new String[]{memeType};
        String[] proj = {MediaStore.Files.FileColumns.DATA,MediaStore.Files.FileColumns.DISPLAY_NAME};
        String sortingOrder = MediaStore.Files.FileColumns.DATE_ADDED +" DESC";
        Cursor cursor = contentResolver.query(MediaStore.Files.getContentUri("external")
                ,proj, mime,args,sortingOrder);
        ArrayList<String> pdfFiles = new ArrayList<>();
        if (cursor != null){
            while (cursor.moveToNext()){
                int index = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);

                String path = cursor.getString(index);
                pdfFiles.add(path);
            }
            cursor.close();
        }
        return pdfFiles;
    }

}