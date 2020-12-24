package com.example.mygrocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mygrocery.data.GroceryDatabase;

import java.util.ArrayList;

public class GroceryAdapter extends BaseAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public GroceryAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.content_groceryview, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.textView);
        listItemText.setText(list.get(position));

        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                GroceryDatabase db = GroceryDatabase.getDBInstance(context.getApplicationContext());
                db.groceryDao().deleteGrocery(listItemText.getText().toString());
                notifyDataSetChanged();
                navController.navigate(R.id.action_groceryFragment_self);
            }
        });

        return view;
    }
}