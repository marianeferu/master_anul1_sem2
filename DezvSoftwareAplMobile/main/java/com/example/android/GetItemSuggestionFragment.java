package com.example.android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.adapter.ItemSuggestionAdapter;
import com.example.android.model.ItemSuggestion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetItemSuggestionFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ItemSuggestionAdapter mItemAdapter;
    private ArrayList<ItemSuggestion> mExampleList;
    private RequestQueue mRequestQueue;
    private String id;

    GetItemSuggestionFragment( String id){
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_itemsuggestion,container,false);
    }



    @Override
    public void onStart() {
        super.onStart();


        mRecyclerView = getView().findViewById(R.id.recycler_view_itemsuggestion);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getActivity());
        parseJSON();
    }

    private void parseJSON() {

        final String category_id = id;

        String url = "https://my-json-server.typicode.com/marianeferu/JsonServer/itemsuggestion?categoryId="+ category_id;

        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject hit = response.getJSONObject(i);

                                String categoryId = hit.getString("categoryId");
                                String id = hit.getString("id");
                                String name = hit.getString("name");


                                mExampleList.add(new ItemSuggestion(categoryId,id,name));
                            }

                            mItemAdapter = new ItemSuggestionAdapter(getActivity(), mExampleList);
                            mRecyclerView.setAdapter(mItemAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage());
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

    }

}
