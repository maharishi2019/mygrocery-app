package com.example.mygrocery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mygrocery.data.Grocery;
import com.example.mygrocery.data.GroceryDatabase;
import com.example.mygrocery.data.Home;
import com.example.mygrocery.data.HomeDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addHome extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    NavController navController;
    EditText homeName;
    EditText expireDate;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addHome.
     */
    // TODO: Rename and change types and number of parameters
    public static addHome newInstance(String param1, String param2) {
        addHome fragment = new addHome();
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
        return inflater.inflate(R.layout.fragment_add_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        Button saveHomeItemButton = (Button) view.findViewById(R.id.save_homeitem_button);
        homeName = view.findViewById(R.id.homeitem_name_input);
        expireDate = view.findViewById(R.id.expire_date_input);
        saveHomeItemButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        HomeDatabase db = HomeDatabase.getDBInstance(this.getActivity().getApplicationContext());
        Home newItem = new Home();
        newItem.homeItemName = homeName.getText().toString();
        newItem.expireDate = expireDate.getText().toString();
        db.homeDao().insertHomeItem(newItem);
        navController.navigate(R.id.action_addHome_to_homeFragment);
    }
}