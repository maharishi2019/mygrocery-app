package com.example.mygrocery;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mygrocery.data.Grocery;
import com.example.mygrocery.data.GroceryDatabase;
import com.example.mygrocery.data.HomeDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    public static final String CHANNEL_ID = "notification channel";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    NavController navController;
    ListView homeList;
    NotificationManagerCompat notificationManager;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "main channel", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("this is main channel");
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        homeList = view.findViewById(R.id.home_list);
        notificationManager = NotificationManagerCompat.from(this.getContext());
        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date dateObject = new Date();
        String currentDate = df.format(dateObject);
        HomeDatabase db = HomeDatabase.getDBInstance(this.getActivity().getApplicationContext());
        GroceryDatabase db_grocery = GroceryDatabase.getDBInstance(this.getActivity().getApplicationContext());
        ArrayList<String> myHomeList = new ArrayList<>();
        for(int i = 0; i < db.homeDao().getHomeItems().size(); i++){
            String entryName = db.homeDao().getHomeItems().get(i).homeItemName;
            String entryDate = db.homeDao().getHomeItems().get(i).expireDate;
            if(entryDate.equals(currentDate)){
                Notification notification = new NotificationCompat.Builder(this.getContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_grocery)
                        .setContentTitle("A Grocery At Home Has Expired!")
                        .setContentText("Today's Date is "+currentDate+" and is unfortunately "+entryName+"'s expire date.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .build();
                Log.d("notify", "notifying");
                notificationManager.notify(1, notification);
                db.homeDao().deleteHomeItem(entryName);
                Grocery new_grocery = new Grocery();
                new_grocery.groceryItemName = entryName;
                db_grocery.groceryDao().insertGrocery(new_grocery);

            }else{
                myHomeList.add(entryName+" "+entryDate);
            }
        }
        HomeAdapter homeAdapter = new HomeAdapter(myHomeList, this.getActivity().getApplicationContext());
        homeList.setAdapter(homeAdapter);
        view.findViewById(R.id.add_homeitem_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        navController.navigate(R.id.action_homeFragment_to_addHome);
    }
}