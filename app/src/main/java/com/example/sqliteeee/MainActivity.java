package com.example.sqliteeee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvMon;
    ListMonAdapter adapter;
    List<Mon> listMon = new ArrayList<>();
    DatabaseHelper dbHelper;

    Button btnThemMon, btnXoaMon, btnCapNhat;
    EditText txtTenMon, txtGia;
    int maMonChon = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ
        btnThemMon = findViewById(R.id.buttonThemMon);
        btnXoaMon = findViewById(R.id.buttonXoa);
        btnCapNhat = findViewById(R.id.buttonCapNhap);
        txtTenMon = findViewById(R.id.editTextTenMon);
        txtGia = findViewById(R.id.editTextGia);
        lvMon = findViewById(R.id.listViewMon);

        dbHelper = new DatabaseHelper(this);

        //đọc dữ liệu
        List<Mon> list = dbHelper.layDanhSachMon();
        listMon.addAll(list);

        //đổ dl lên listview
        adapter = new ListMonAdapter(this, R.layout.list_item_mon, listMon);
        lvMon.setAdapter(adapter);

        // xử lí cahwcs nút thêm cập nhâpk
        btnThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenMon = txtTenMon.getText().toString();
                double gia = Double.parseDouble(txtGia.getText().toString());

                dbHelper.ThemMon(tenMon, gia);

                capnhapListView();
                clearViews();
                Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
        lvMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mon mon = listMon.get(position);
                txtTenMon.setText(mon.getTenMon());
                txtGia.setText(String.valueOf(mon.getGia()));
                maMonChon = mon.getMaMon();
            }
        });
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maMonChon == -1) return;

                String tenMon = txtTenMon.getText().toString();
                double gia = Double.parseDouble(txtGia.getText().toString());
                dbHelper.capNhapMon(maMonChon, tenMon, gia);

                capnhapListView();
                clearViews();
                Toast.makeText(MainActivity.this, "Câp nhập thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btnXoaMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maMonChon == -1) return;
                dbHelper.xoaMon(maMonChon);

                capnhapListView();
                clearViews();
                Toast.makeText(MainActivity.this, "Xóa món thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
            private void capnhapListView() {
                listMon.clear();
                listMon.addAll(dbHelper.layDanhSachMon());
                adapter.notifyDataSetChanged();
            }
            private void clearViews(){
                txtTenMon.setText("");
                txtGia.setText("");
                maMonChon = -1;
            }
}