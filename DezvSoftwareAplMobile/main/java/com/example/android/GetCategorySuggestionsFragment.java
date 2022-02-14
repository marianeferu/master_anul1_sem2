package com.example.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.adapter.CategorySuggestionAdapter;
import com.example.android.model.CategorySuggestion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetCategorySuggestionsFragment extends Fragment implements CategorySuggestionAdapter.OnItemClickListener  {

    private RecyclerView mRecyclerView;
    private CategorySuggestionAdapter mCategoryAdapter;
    private ArrayList<CategorySuggestion> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_categorysuggestion,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();

        mRecyclerView = getView().findViewById(R.id.recycler_view2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //getContext()
        //mRecyclerView.setAdapter(mItemAdapter);

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getActivity()); //getContext()
        parseJSON();
    }

    private void parseJSON() {
        String url = "https://my-json-server.typicode.com/ghadamiyan/JsonServer/db";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("categorysuggestion");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String Id = hit.getString("id");
                                String Name = hit.getString("name");

                                mExampleList.add(new CategorySuggestion(Id,Name));

                            }

                            mCategoryAdapter = new CategorySuggestionAdapter(getActivity(), mExampleList);
                            mRecyclerView.setAdapter(mCategoryAdapter);
                            mCategoryAdapter.setOnItemClickListener(GetCategorySuggestionsFragment.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

    }

    @Override
    public void onItemClick(int position) {

        //Log.e("TAG", mExampleList.get(position).getName() + "-" + mExampleList.get(position).getId());
        Fragment fragment = new GetItemSuggestionFragment(
                mExampleList.get(position).getId());
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.second_ll, fragment);
        transaction.commit();



        // Intent detailIntent = new Intent(getActivity(), GetItemSuggestionFragment.class);
        // CategorySuggestion clickedItem = mExampleList.get(position);

        // detailIntent.putExtra(EXTRA_NAME, clickedItem.getName());
        // detailIntent.putExtra(EXTRA_ID, clickedItem.getId());

        // startActivity(detailIntent);
    }

}
