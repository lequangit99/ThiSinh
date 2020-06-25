package com.kiemtra.thisinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton floatingActionButton;
    private SearchView searchView;
    private CustomAdapter customAdapter;
    private DBContext dbContext;
    private List<ThiSinh> thiSinhList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setTitle("Quản lý thí sinh");
        dbContext = new DBContext(this);
        thiSinhList = dbContext.getAllThiSinh();

        setAdapter();

        registerForContextMenu(listView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                customAdapter.filter(s);
                return false;
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SuaActivity.class);
                intent.putExtra("code",1);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        if (item.getItemId()==R.id.menu_xoa){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Bạn có muốn xoá không");
            alertDialogBuilder.setTitle("Xác nhận?");

            alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    ThiSinh thiSinh = thiSinhList.get(menuInfo.position);
                    dbContext.deleteVeTau(thiSinh.getmSBD());
                    updateListStudent();
                }
            });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        if (item.getItemId()==R.id.menu_sua){
            ThiSinh thiSinh = thiSinhList.get(menuInfo.position);
            Intent intent = new Intent(MainActivity.this, SuaActivity.class);
            intent.putExtra("code", 2);
            intent.putExtra("sbd", thiSinh.getmSBD());
            intent.putExtra("hoten", thiSinh.getmHoTen());
            intent.putExtra("toan",thiSinh.getmToan());
            intent.putExtra("ly",thiSinh.getmLy());
            intent.putExtra("hoa",thiSinh.getmHoa());
            startActivity(intent);
        }

        return super.onContextItemSelected(item);
    }



    private void initViews(){
        listView = (ListView) findViewById(R.id.lst_thiSinh);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        searchView = (SearchView) findViewById(R.id.search_view);
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.items_thisinh, thiSinhList);
            listView.setAdapter(customAdapter);
        }else{
            customAdapter.notifyDataSetChanged();
            listView.setSelection(customAdapter.getCount()-1);
        }
    }

    public void updateListStudent(){
        thiSinhList.clear();
        thiSinhList.addAll(dbContext.getAllThiSinh());
        if(customAdapter!= null){
            customAdapter.notifyDataSetChanged();
        }
    }

}