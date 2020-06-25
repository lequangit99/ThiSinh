package com.kiemtra.thisinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SuaActivity extends AppCompatActivity {
    private EditText edtSbd,edtHoTen,edtToan,edtLy,edtHoa;
    private Button btnDongY, btnQuayVe;
    private DBContext dbContext;
    private int code;
    private List<ThiSinh> thiSinhList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);

        initViews();
        dbContext = new DBContext(this);
        thiSinhList = dbContext.getAllThiSinh();

        final Intent intent = getIntent();
        code = intent.getIntExtra("code",0);

        if (code == 2){
           edtSbd.setText(intent.getStringExtra("sbd"));
           edtSbd.setEnabled(false);
           edtHoTen.setText(intent.getStringExtra("hoten"));
           edtToan.setText(String.valueOf(intent.getFloatExtra("toan",0)));
           edtLy.setText(String.valueOf(intent.getFloatExtra("ly",0)));
           edtHoa.setText(String.valueOf(intent.getFloatExtra("hoa",0)));
        }

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = true;
                if (code == 1){
                    ThiSinh thiSinh = createThiSinh();
                    for (ThiSinh thiSinh1: thiSinhList
                         ) {
                        if (thiSinh.getmSBD().equals(thiSinh1.getmSBD())){
                            Toast.makeText(SuaActivity.this, "Không được trùng SBD", Toast.LENGTH_SHORT).show();
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        dbContext.addThiSinh(thiSinh);
                        Intent intent = new Intent(SuaActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                }
                if (code == 2){
                    ThiSinh thiSinh = new ThiSinh();
                    thiSinh.setmSBD(intent.getStringExtra("sbd"));
                    thiSinh.setmHoTen(edtHoTen.getText().toString());
                    thiSinh.setmToan(Float.parseFloat(edtToan.getText().toString()));
                    thiSinh.setmLy(Float.parseFloat(edtLy.getText().toString()));
                    thiSinh.setmHoa(Float.parseFloat(edtHoa.getText().toString()));

                    dbContext.updateThiSinh(thiSinh);
                    Intent intent = new Intent(SuaActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initViews(){
        edtSbd = (EditText) findViewById(R.id.edt_sbd);
        edtHoTen = (EditText) findViewById(R.id.edt_hoTen);
        edtToan = (EditText) findViewById(R.id.edt_toan);
        edtLy = (EditText) findViewById(R.id.edt_ly);
        edtHoa = (EditText) findViewById(R.id.edt_hoa);
        btnDongY = (Button) findViewById(R.id.btn_dong_y);
        btnQuayVe = (Button) findViewById(R.id.btn_quay_ve);

    }

    private ThiSinh createThiSinh() {
        String SBD = edtSbd.getText().toString();
        String HoTen = edtHoTen.getText().toString();
        float toan = Float.parseFloat(edtToan.getText().toString());
        float ly = Float.parseFloat(edtLy.getText().toString());
        float hoa = Float.parseFloat(edtHoa.getText().toString());

        ThiSinh thiSinh = new ThiSinh(SBD,HoTen,toan,ly,hoa);
        return thiSinh;
    }
}