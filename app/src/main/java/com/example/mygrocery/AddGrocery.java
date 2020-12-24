package com.example.mygrocery;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.mygrocery.data.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddGrocery#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddGrocery extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    NavController navController;
    EditText groceryName;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddGrocery() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddGrocery.
     */
    // TODO: Rename and change types and number of parameters
    public static AddGrocery newInstance(String param1, String param2) {
        AddGrocery fragment = new AddGrocery();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_grocery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        Button saveGroceryButton = (Button) view.findViewById(R.id.save_grocery_button);
        groceryName = view.findViewById(R.id.grocery_name_input);
        saveGroceryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        GroceryDatabase db = GroceryDatabase.getDBInstance(this.getActivity().getApplicationContext());
        Grocery newItem = new Grocery();
        newItem.groceryItemName = groceryName.getText().toString();
        db.groceryDao().insertGrocery(newItem);
        navController.navigate(R.id.action_addGrocery_to_groceryFragment);
    }
}