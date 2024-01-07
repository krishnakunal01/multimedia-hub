package com.example.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multimedia.Pdfitem;
import com.example.multimedia.R;

import java.io.File;
import java.util.List;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.PdfViewHolder> {
    Context context;
    List<String> pdffiles;

    public PdfAdapter(Context context,List<String> pdffiles) {
        this.context = context;
        this.pdffiles = pdffiles;
    }

    @NonNull
    @Override
    public PdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_pdf, parent,false);
        return new PdfViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PdfViewHolder holder, int position) {
        String path = pdffiles.get(position);
        File pdffile =new File(path);
        String filename = pdffile.getName();

        holder.Pdffilename.setText(filename);

    }

    @Override
    public int getItemCount() {
        return pdffiles.size();
    }

    static class PdfViewHolder extends RecyclerView.ViewHolder{
        TextView Pdffilename;

        public PdfViewHolder(@NonNull View itemView) {
            super(itemView);
            Pdffilename = itemView.findViewById(R.id.Pdffilename);
        }
    }
}
