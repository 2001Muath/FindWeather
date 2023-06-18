package com.example.backgraunde_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.TV);

        registerForContextMenu(textView);

        textView.setOnClickListener(view ->
                showPopupMenu());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_search:
                showToast(getString(R.string.Search));
                return true;
            case R.id.item_favorites:
                showToast(getString(R.string.Favorites));
                return true;
            case R.id.item_settings:
                showToast(getString(R.string.Settings));
                return true;
            case R.id.item_info:
                showToast(getString(R.string.Info));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_search:
                showToast(getString(R.string.Search));
                return true;
            case R.id.item_favorites:
                showToast(getString(R.string.Favorites));
                return true;
            case R.id.item_settings:
                showToast(getString(R.string.Settings));
                return true;
            case R.id.item_info:
                showToast(getString(R.string.Info));
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    public void showToast(String text){
        Toast.makeText(getApplicationContext(),
                "You selected :" + text, Toast.LENGTH_LONG).show();

    }

    @SuppressLint("NonConstantResourceId")
    public void showPopupMenu(){
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, textView);
        popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.item_search:
                    showToast(getString(R.string.Search));
                    return true;
                case R.id.item_favorites:
                    showToast(getString(R.string.Favorites));
                    return true;
                case R.id.item_settings:
                    showToast(getString(R.string.Settings));
                    return true;
                case R.id.item_info:
                    showToast(getString(R.string.Info));
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
    }

}

