package com.pranay.bloggerapi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Myholder>
{

    List<Item> list;
    Context context;

    public MyAdapter(List<Item> list,Context context)
    {
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.my_card,parent,false);
        return new Myholder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

        Item item=list.get(position);
        holder.title.setText(item.getTitle());
        Document document=Jsoup.parse(item.getContent());
        holder.decs.setText(document.text());

        Elements element=document.select("img");
        Log.d("CODE","Image -"+element.get(0).attr("src"));
        Log.d("TEXT",document.text());

        Glide.with(context).load(element.get(0).attr("src")).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title,decs;
        public Myholder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            decs=itemView.findViewById(R.id.description);
            imageView=itemView.findViewById(R.id.imageView);

        }
    }
}
