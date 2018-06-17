package com.dovi.alarm;

import android.app.Activity;
import android.app.AlarmManager;
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

/**
 * Created by dsm2016 on 2018-05-31.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>{
    private Context context;
    private ArrayList<RecyclerItem> recyclerItemArrayList;
    private AlarmManager alarmManager;

    public RecyclerViewAdapter(ArrayList<RecyclerItem> recyclerItemArrayList, Context context) {
        this.recyclerItemArrayList = recyclerItemArrayList;
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
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
        holder.textViewDate.setText(recyclerItemArrayList.get(position).getTextDate());
    }

    @Override
    public int getItemCount() {
        return recyclerItemArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CheckBox checkBox;
        private TextView textViewTime;
        private TextView textViewDate;
        private Button buttonDelete;

        public ItemViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            textViewTime = (TextView) itemView.findViewById(R.id.textViewTime);
            textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
            buttonDelete = (Button) itemView.findViewById(R.id.buttonDelete);

            checkBox.setOnClickListener(this);
            textViewTime.setOnClickListener(this);
            textViewDate.setOnClickListener(this);
            buttonDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            if(view.getId() == buttonDelete.getId()) {
                Log.d("Delete Button", "onClick");

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:

                                // Delete RecyclerViewItem of RecyclerView

                                Log.d("Delete Dialog index", Integer.toString(getAdapterPosition()));
                                Log.d("Dialog Clicked", "Positive");
                                removeAt(getLayoutPosition());
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                Log.d("Dialog Clicked : ", "Negative");
                                break;
                        }
                    }
                };

                // Show Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("삭제하시겠습니까?");
                builder.setPositiveButton("삭제", dialogClickListener);
                builder.setNegativeButton("취소", dialogClickListener);
                builder.show();
            } else if (view.getId() == checkBox.getId()) {
                // Activate / Deactivate Alarm

                if(checkBox.isChecked() == true) {
                    // Deactivate Alarm
                    Log.d("checkBox", "Checked -> Unchecked");

                } else {
                    // Activate Alarm
                    Log.d("checkBox", "Unchecked -> Checked");
                }

            } else {
                // Edit Alarm
                String strTime = (String) textViewTime.getText();
                String strHour = strTime.split(":")[0];
                String strMin = strTime.split(":")[1];

                Intent intent = new Intent(view.getContext(), SetAlarmActivity.class);

                if(Integer.parseInt(strHour) == -1 || Integer.parseInt(strMin) == -1 || getAdapterPosition() == -1) {
                    Log.e("EDIT ALARM", "WRONG INTENT");
                    Log.e("Wrong Intent", strHour);
                    Log.e("Wrong Intent", strMin);
                    return;
                }

                intent.putExtra(MainActivity.INTENT_ISCHECKED, checkBox.isChecked());
                intent.putExtra(MainActivity.INTENT_HOUR, Integer.parseInt(strHour));
                intent.putExtra(MainActivity.INTENT_MIN, Integer.parseInt(strMin));
                intent.putExtra(MainActivity.INTENT_INDEX, getAdapterPosition());
                intent.putExtra("requestCode", MainActivity.ALARM_EDIT);

                Log.d(MainActivity.INTENT_ISCHECKED , Boolean.toString(intent.getBooleanExtra(MainActivity.INTENT_ISCHECKED, false)));
                Log.d(MainActivity.INTENT_HOUR , Integer.toString(intent.getIntExtra(MainActivity.INTENT_HOUR, -1)));
                Log.d(MainActivity.INTENT_MIN , Integer.toString(intent.getIntExtra(MainActivity.INTENT_MIN, -1)));
                Log.d(MainActivity.INTENT_INDEX, Integer.toString(intent.getIntExtra(MainActivity.INTENT_INDEX, -1)));

                Log.d("RecyclerViewHolder", "startActivityForResult");
                ((Activity) view.getContext()).startActivityForResult(intent, MainActivity.ALARM_EDIT);
            }
        }

        /*
        * Remove ReclcyerView Item
        * */
        public void removeAt(int position) {
            recyclerItemArrayList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, recyclerItemArrayList.size());
        }
    }
}
