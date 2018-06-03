package com.dovi.alarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by dsm2016 on 2018-05-31.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>{
    private Context context;
    private ArrayList<RecyclerItem> recyclerItemArrayList;

    public RecyclerViewAdapter(ArrayList<RecyclerItem> recyclerItemArrayList, Context context) {
        this.recyclerItemArrayList = recyclerItemArrayList;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.checkBox.setChecked(recyclerItemArrayList.get(position).getIsChecked());
        holder.textViewTime.setText(recyclerItemArrayList.get(position).getTextTime());
        holder.textViewAMPM.setText(recyclerItemArrayList.get(position).getTextAMPM());
        holder.textViewDate.setText(recyclerItemArrayList.get(position).getTextDate());
    }

    @Override
    public int getItemCount() {
        return recyclerItemArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CheckBox checkBox;
        private TextView textViewTime;
        private TextView textViewAMPM;
        private TextView textViewDate;
        private Button buttonDelete;

        public ItemViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            textViewTime = (TextView) itemView.findViewById(R.id.textViewTime);
            textViewAMPM = (TextView) itemView.findViewById(R.id.textViewAMPM);
            textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
            buttonDelete = (Button) itemView.findViewById(R.id.buttonDelete);

            textViewTime.setOnClickListener(this);
            textViewAMPM.setOnClickListener(this);
            textViewDate.setOnClickListener(this);
            buttonDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            if(view.getId() == buttonDelete.getId()) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Log.d("Delete Dialog index : ", Integer.toString(getAdapterPosition()));
                                removeAt(getLayoutPosition());
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                Log.d("Delete Button : ", "onClick");
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("삭제하시겠습니까?");
                builder.setPositiveButton("삭제", dialogClickListener);
                builder.setNegativeButton("취소", dialogClickListener);
                builder.show();
            } else {
                // show activity
            }
        }

        public void removeAt(int position) {
            recyclerItemArrayList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, recyclerItemArrayList.size());
        }
    }
}
