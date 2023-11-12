package ru.mirea.sukhovav.mireaproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;


public class AnyDesk extends Fragment {

    ListView listView;
    TextView text;
    Button button;
    private static final String TAG = "myLogs";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int flags = PackageManager.GET_META_DATA |
                PackageManager.GET_SHARED_LIBRARY_FILES |
                PackageManager.GET_UNINSTALLED_PACKAGES;


        @SuppressLint("QueryPermissionsNeeded") List<PackageInfo> ril = getActivity().getPackageManager().getInstalledPackages(flags);
        List<String> componentList = new ArrayList<String>();
        int i = 0;


        int flag = 0;
        // get size of ril and create a list
        String[] apps = new String[ril.size()];
        for (PackageInfo ri : ril) {

            apps[i] = ri.applicationInfo.loadLabel(getActivity().getPackageManager()).toString();

            if(ri.applicationInfo.loadLabel(getActivity().getPackageManager()).toString().equals("AnyDesk")){
                flag = 1;
            }
            i++;

        }

        Log.d(TAG, "Installed package FLAG :" + flag);


        if(flag == 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Важное сообщение!")
                    .setMessage("Найдена программа удалённого доступа!")
                    .setPositiveButton("Закрыть приложение", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Закрываем диалоговое окно
                            System.exit(0);
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }




    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_any_desk, container, false);

        listView = view.findViewById(R.id.listview);
        text =  view.findViewById(R.id.totalapp);
        button = view.findViewById(R.id.check);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

                // get list of all the apps installed
                @SuppressLint("QueryPermissionsNeeded") List<PackageInfo> ril = getActivity().getPackageManager().getInstalledPackages(2048);
                List<String> componentList = new ArrayList<String>();
                String name = null;
                int i = 0;

                // get size of ril and create a list
                String[] apps = new String[ril.size()];
                for (PackageInfo ri : ril) {

                    apps[i] = ri.applicationInfo.loadLabel(getActivity().getPackageManager()).toString();
                    i++;

                }
                // set all the apps name in list view
                listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, apps));

                for (String packageInfo : apps) {
                    Log.d(TAG, "Installed package :" + packageInfo);
                    Log.d(TAG, "Installed package :" + ril.size());
                }
            }
        });




        return view;
    }



}