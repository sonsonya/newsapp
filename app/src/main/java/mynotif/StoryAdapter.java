package mynotif.topstory.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mynotif.topstory.R;
import mynotif.topstory.model.Item;
import mynotif.topstory.view.DetailStory;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder>{

    private ArrayList<Item> list;
    private Context context;

    public StoryAdapter(ArrayList<Item> list) {
        this.list=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list,parent,false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText(list.get(position).Title);
        holder.descendants.setText("Comment : "+list.get(position).Descendants);
        holder.score.setText("Score : "+list.get(position).Score);
        Log.d("descendants adapter",list.get(position).Descendants);
        Log.d("score adapter",list.get(position).Score);
        holder.clickLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailStory.class);
                intent.putExtra("id", list.get(position).Id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView descendants;
        TextView score;
        LinearLayout clickLayout;


        public MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            descendants=(TextView)itemView.findViewById(R.id.descendants);
            score=(TextView)itemView.findViewById(R.id.score);
            clickLayout=(LinearLayout)itemView.findViewById(R.id.layoutClick);
        }
    }
}
