package com.example.listview_more_loader.listviewmoreloader;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mradul on 4/8/2015.
 */
public class LoaderTask  extends AsyncTask<Void,Void,List<Product>> {
    private final int mFrom;
    private final int mTo;
    private final Context mContext;
    private final ProdAdapter mAdapter;

    private boolean mReachedLastPage;

    public LoaderTask(int from, int to, Context context, ProdAdapter adapter) {
        mFrom = from;
        mTo = to;
        mContext = context;
        mAdapter = adapter;
        mReachedLastPage = false;
    }

    @Override
    protected List<Product> doInBackground(Void... voids) {
        List<Product> list = new ArrayList<Product>();

        try{

            //--just an example, fetch this from server--
            JSONObject response = new JSONObject(fetch());

            JSONArray products = response.getJSONArray("products");

            for (int i = 0; i < products.length(); i++) {
                JSONObject p = products.getJSONObject(i);

                String name = p.getString("name");
                String company = p.getString("company");
                String address = p.getString("address");
                String city = p.getString("city");

                list.add(new Product(name, company, address, city));
            }

            if(response.getBoolean("last_page")){
                mReachedLastPage = true;
            }

        }catch (Exception e){
            Log.e("EA_DEMO", "Error fetching product list", e);
        }

        return list;
    }

    @Override
    protected void onPostExecute(List<Product> products) {
        super.onPostExecute(products);

        for (Product p : products){
            mAdapter.add(p);
        }

        mAdapter.notifyDataSetChanged();

        if (mReachedLastPage){
            mAdapter.notifyNoMoreItems();
        }

    }

    private String fetch() throws InterruptedException{

        //--this is a fake method to generate json and take some time--
        //--to simulate network loading--

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("\"products\": [\n");

        for (int i = mFrom; i <= mTo; i++){

            sb.append("{\n");
            sb.append("\"name\": \"item_"+i+"\",\n");
            sb.append("\"company\": \"company_"+i+"\",\n");
            sb.append("\"address\": \"street_"+i+"\",\n");
            sb.append("\"city\": \"city_"+i+"\"\n");

            if(i == mTo){
                sb.append("}\n");
            }else {
                sb.append("},\n");
            }
        }

        sb.append("],\n");
        if(mTo >= 40){
            sb.append("\"last_page\": true\n");
        }else {
            sb.append("\"last_page\": false\n");
        }
        sb.append("}\n");

        synchronized (this){
            wait(4000);
        }

        Log.i("EA_DEMO",sb.toString());
        return sb.toString();
    }
}

