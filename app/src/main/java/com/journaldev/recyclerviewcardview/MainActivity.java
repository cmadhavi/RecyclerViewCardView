package com.journaldev.recyclerviewcardview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WebServiceCallBack {


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Item> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    ArrayList<String> post_id, post_img, post_name;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        new WebserviceHelper(this).getData(getString(R.string.base_url) + "banner/Service/getCategory", "getCategory", this);




    }

    @Override
    public int getLayoutResource() {
        return 0;
    }

    @Override
    public void onJSONResponse(String jsonResponse, String type) throws IOException {
        switch (type) {
            case "getCategory":
                Log.e("getCategory", jsonResponse);
                try {
                    JSONObject jsonObjectcat = new JSONObject(jsonResponse);
                    String status = jsonObjectcat.getString("status");
                    JSONArray jsonArraylist = jsonObjectcat.getJSONArray("list");

                    post_id = new ArrayList<>();
                    post_id.clear();
                    post_img = new ArrayList<>();
                    post_img.clear();
                    post_name = new ArrayList<>();
                    post_name.clear();


                    for (int i = 0; i < jsonArraylist.length(); i++) {
                        JSONObject jsonobjectsingledata = jsonArraylist.getJSONObject(i);
                        String id = jsonobjectsingledata.getString("id");
                        String name = jsonobjectsingledata.getString("name");
                        String img = jsonobjectsingledata.getString("img");
                        post_id.add(id);
                        post_name.add(name);
                        post_img.add(img);

                    };

                    mAdapter = new CustomAdapter(MainActivity.this,  post_name, post_img);
                    recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
                    GridLayoutManager gridLayoutManager= new GridLayoutManager(this, 2);
                    recyclerView.setHasFixedSize(true);

                    recyclerView.setLayoutManager(gridLayoutManager);
                   // recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    @Override
    public void onFailure() {

    }

   public static class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

        ArrayList<String> post_titles;
        ArrayList<String> post_img;
        private Context mContext;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            ImageView imageView;

            public MyViewHolder(View rowView) {
                super(rowView);
                tv = (TextView) rowView.findViewById(R.id.textViewName);
                imageView = (ImageView) rowView.findViewById(R.id.imageView);
            }
        }

        public CustomAdapter(Context context, ArrayList<String> titles, ArrayList<String> images) {

            mContext = context;
            this.post_img = images;
            this.post_titles = titles;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cards_layout, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            try {
                holder.tv.setText(post_titles.get(position));

                Picasso.with(mContext)
                        .load(post_img.get(position))
                        .into(holder.imageView);
            } catch (Exception ignored) {
            }

        }

        @Override
        public int getItemCount() {
            return post_titles.size();
        }
    }

    }
