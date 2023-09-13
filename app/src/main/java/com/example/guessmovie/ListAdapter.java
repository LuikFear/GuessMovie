package com.example.guessmovie;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter  extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListItemM> mData;
    private LayoutInflater inflater;
    private Context context;
    final ListAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(ListItemM itemM);
    }

    public ListAdapter(List<ListItemM> itemList, Context context,ListAdapter.OnItemClickListener listener){

        this.inflater= LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener= listener;

    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = inflater.inflate(R.layout.item_list,null);
        return new ListAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));


    }

   public void setItems(List<ListItemM> items){mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name,des,status;

        ViewHolder(View itemView){
            super (itemView);
           iconImage = itemView.findViewById(R.id.icon1);
           name = itemView.findViewById(R.id.leveltextview);
           des = itemView.findViewById(R.id.destextview);
           status= itemView.findViewById(R.id.statustext);


        }

        void bindData(final ListItemM item){

            name.setText(item.getName());
            des.setText(item.getDes());
            status.setText(item.getStatus());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }




    }


}
