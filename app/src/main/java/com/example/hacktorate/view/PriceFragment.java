package com.example.hacktorate.view;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.example.hacktorate.R;
import com.example.hacktorate.adapter.GoiAdapter;
import com.example.hacktorate.databinding.FragmentPriceBinding;
import com.example.hacktorate.models.GoiDetail;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PriceFragment extends Fragment {

    private FragmentPriceBinding binding;
    private GoiAdapter goiAdapter;
    private RecyclerView recyclerView;
    private List<GoiDetail> goiDetails;
    private LinearLayoutManager linearLayoutManager;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private Boolean isScrolling=false;
    int currentItems,totalItems,scrolledItems;
    int offset=20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPriceBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        recyclerView=findViewById(R.id.recyclerView);
        recyclerView = binding.recyclerView;
        goiDetails =new ArrayList<>();
        linearLayoutManager=new LinearLayoutManager(requireContext());
//        progressBar=findViewById(R.id.progressBar);
        progressBar = binding.progressBar;
        progressBar.setVisibility(View.GONE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(goiAdapter);

//        toolbar=findViewById((R.id.BarLayout));
        toolbar = binding.BarLayout.getRoot();

//        ActionBar actionBar= this.getSupportActionBar();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Kissan GOI");
        try{
//            MainActivity2.DownloadTask task = new MainActivity2.DownloadTask();
            PriceFragment.DownloadTask task = new DownloadTask();
            task.execute("https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=json&offset=0&limit=20") ;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        loadData();

    }


    public class DownloadTask extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... urls) {
            URL url;
            HttpURLConnection urlConnection = null;
            String result = "";
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader((inputStream));
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();

                }

                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String records = "";
                    records = jsonObject.getString("records");
                    JSONArray arr = new JSONArray(records);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject part = arr.getJSONObject(i);
//                        if(part.getString("district").equals("Kurnool") || part.getString("state").equals("Chattisgarh")||part.getString("commodity").equals("Tamarind Seed")) {
                        GoiDetail details = new GoiDetail();
                        details.setGroceryName(part.getString("commodity"));
                        details.setGroceryPlace(part.getString("district") + ", " + part.getString("state"));
                        details.setGroceryPrice(part.getString("modal_price"));
                        details.setGroceryTime(part.getString("arrival_date"));


                        goiDetails.add(details);


                    }
                    goiAdapter = new GoiAdapter(requireContext(), goiDetails);
                    goiAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(goiAdapter);
                    recyclerView.setAdapter(goiAdapter);
                    recyclerView.scrollToPosition(totalItems-currentItems+1);
                    progressBar.setVisibility(View.GONE);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{
                try{
//                    MainActivity2.DownloadTask task = new MainActivity2.DownloadTask();
                    PriceFragment.DownloadTask task = new DownloadTask();
                    task.execute("https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=json&offset=0&limit=20") ;
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }


        }
    }
    private void loadData() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling=true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems=linearLayoutManager.getChildCount();
                totalItems=linearLayoutManager.getItemCount();
                scrolledItems=linearLayoutManager.findFirstVisibleItemPosition();
                if(isScrolling && (currentItems+scrolledItems)==totalItems){
                    isScrolling=false;
                    progressBar.setVisibility(View.VISIBLE);
                    fetchData();

                }


            }
        });



    }

    private void fetchData(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
//                    MainActivity2.DownloadTask task = new MainActivity2.DownloadTask();
                    PriceFragment.DownloadTask task = new DownloadTask();
                    task.execute("https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=json&offset=0&limit=20") ;
                    offset+=20;

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

        },5000);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        requireActivity().getMenuInflater().inflate(R.menu.mainmenu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.searchByDistrict){
//            startActivity(filterIntent);

        }
        return true;

    }
}