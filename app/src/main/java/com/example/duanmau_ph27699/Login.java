package com.example.duanmau_ph27699;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_ph27699.DAO.ThuThuDAO;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText editUser = findViewById(R.id.edtUser);
        EditText editPass = findViewById(R.id.edtPass);
        Button btn = findViewById(R.id.btnDangNhap);
        ThuThuDAO thuThuDAO = new ThuThuDAO(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String user = editUser.getText().toString();
                String pass = editPass.getText().toString();
                if (thuThuDAO.checkDangNhap(user,pass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("matt",user);
                    editor.commit();
                    startActivity(new Intent(Login.this,MainActivity.class));
                }
                else {
                    Toast.makeText(Login.this, "Khong dung mat khau hoac tai khoan", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}