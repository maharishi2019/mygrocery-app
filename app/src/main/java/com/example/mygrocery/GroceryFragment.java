package com.example.mygrocery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mygrocery.data.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroceryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroceryFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    NavController navController;
    ListView groceryList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GroceryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroceryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GroceryFragment newInstance(String param1, String param2) {
        GroceryFragment fragment = new GroceryFragment();
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
        return inflater.inflate(R.layout.fragment_grocery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        groceryList = view.findViewById(R.id.grocery_list);
        GroceryDatabase db = GroceryDatabase.getDBInstance(this.getActivity().getApplicationContext());
        ArrayList<String> myGroceryList = new ArrayList<>();
        for(int i = 0; i < db.groceryDao().getGroceries().size(); i++){
            myGroceryList.add(db.groceryDao().getGroceries().get(i).groceryItemName);
        }
        GroceryAdapter groceryAdapter = new GroceryAdapter(myGroceryList, this.getActivity().getApplicationContext());
        groceryList.setAdapter(groceryAdapter);
        view.findViewById(R.id.add_grocery_button).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        navController.navigate(R.id.action_groceryFragment_to_addGrocery);
    }
}