package com.example.filterlistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.filterlistapp.adapters.ChocolateListAdapter;
import com.example.filterlistapp.repository.ChocolateRepository;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private TextView txtEmptyList;

    private ChocolateListAdapter adapter;
    private ChocolateRepository chocolateRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chocolateRepository = new ChocolateRepository();
        list = findViewById(R.id.list);
        txtEmptyList = findViewById(R.id.txt_empty);

        adapter = new ChocolateListAdapter();
        adapter.setOnListEmptyListener(isEmpty -> {
            if (isEmpty){
                list.setVisibility(View.GONE);
                txtEmptyList.setVisibility(View.VISIBLE);
            }else {
                list.setVisibility(View.VISIBLE);
                txtEmptyList.setVisibility(View.GONE);
            }
        });

        list.setLayoutManager(new LinearLayoutManager(this));
        list.setHasFixedSize(true);
        list.setAdapter(adapter);

        adapter.submitList(chocolateRepository.getChocolates());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }
}