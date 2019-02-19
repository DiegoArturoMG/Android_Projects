package com.example.r2d2.agenda_rest_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.r2d2.agenda_rest_example.Adapters.ContactListAdapter;
import com.example.r2d2.agenda_rest_example.model.ContactModel;
import com.example.r2d2.agenda_rest_example.util.URLConstants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton ibRefresh = findViewById(R.id.ibRefresh);
        ibRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumeWS();
            }
        });

        this.consumeWS();

    }

    private void consumeWS() {

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = URLConstants.ALL_CONTACTS_URL;

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG,"Json"+response.toString());

                        /*List<ContactModel> contacts = parseJson(response);*/
                        Gson gson = new Gson();
                        ContactModel[] arrContacts = gson.fromJson(response.toString(),ContactModel[].class);
                        List<ContactModel> contacts = Arrays.asList(arrContacts);
                        updateList(contacts);

                        //updateList(contacts);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,"Error: " + error.getMessage());
                        error.printStackTrace();
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();

                headers.put("Authorization",URLConstants.AUTH_HEADER);

                return headers;
            }
        };

        queue.add(request);

    }

    private void updateList(List<ContactModel> contacts) {

        ListView lvContacts = findViewById(R.id.lvContacts);

        ContactListAdapter adapter = new ContactListAdapter(contacts);

        lvContacts.setAdapter(adapter);

    }

    private List<ContactModel> parseJson(JSONArray response) {

        List<ContactModel> contacts = new ArrayList<>();

        for(int i=0;i<response.length();i++){
            JSONObject item = null;
            try {
                item = response.getJSONObject(i);

                ContactModel contact = new ContactModel();

                contact.setId(item.getString("_id"));
                contact.setName(item.getString("name"));
                contact.setLastName(item.getString("last_name"));
                contact.setPhone(item.getString("phone"));
                contact.setMail(item.getString("mail"));

                contacts.add(contact);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        Log.d(TAG,String.format("Se obtuvieron: %d registros",contacts.size()));
        Log.d(TAG,contacts.toString());

        return contacts;

    }

}
