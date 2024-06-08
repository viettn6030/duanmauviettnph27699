package com.example.duanmau_ph27699;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.duanmau_ph27699.DAO.SachDAO;
import com.example.duanmau_ph27699.DAO.ThuThuDAO;
import com.example.duanmau_ph27699.Fragment.LoaiSachFragment;
import com.example.duanmau_ph27699.Fragment.PhieuMuonFragment;
import com.example.duanmau_ph27699.Fragment.SachFragment;
import com.example.duanmau_ph27699.Fragment.ThanhVienFragment;
import com.example.duanmau_ph27699.Fragment.ThongKetp10Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SachDAO dao = new SachDAO(this);
        dao.getDSSach();

        Toolbar toolbar = findViewById(R.id.toolBar);
        frameLayout = findViewById(R.id.frameLayout);
        NavigationView navigationView = findViewById(R.id.Navig);
        drawerLayout = findViewById(R.id.drawlayout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.bars_solid);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                Fragment fragment = null;
                switch (itemId){
                    case (R.id.mQLphieu):

                        fragment = new PhieuMuonFragment();

                        break;
                    case R.id.mQLLoaiSach:

                        fragment = new LoaiSachFragment();

                        break;
                    case R.id.mQLSach:

                        fragment = new SachFragment();

                        break;
                    case R.id.mQLThanhVien:

                        fragment = new ThanhVienFragment();

                        break;
                    case R.id.mDangXuat:
                        Intent intent   = new Intent(MainActivity.this, Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                    case R.id.mDoiMK:
                        doiMK();
                        break;
                    case R.id.mTop10:
                        fragment = new ThongKetp10Fragment();
                        break;
                    default:
                        fragment = new PhieuMuonFragment();
                        break;
                }
                if (fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, fragment)
                            .commit();
                    toolbar.setTitle(item.getTitle());
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    private void doiMK(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setNegativeButton("Cập nhật",null)
                .setPositiveButton("Huỷ",null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimk,null);
        EditText edtOldpass = view.findViewById(R.id.oldPass);
        EditText edtnewpass1 = view.findViewById(R.id.newPass1);
        EditText edtnewpass2 = view.findViewById(R.id.newPass2);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtOldpass.getText().toString();
                String newPass1 = edtnewpass1.getText().toString();
                String newPass2 = edtnewpass2.getText().toString();
                if (newPass1.equals(newPass2)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
                    String matt = sharedPreferences.getString("matt","");
                    ThuThuDAO thuThuDAO = new ThuThuDAO(MainActivity.this);

                    //cap nhat
                    boolean check = thuThuDAO.doimatkhau(matt,oldPass,newPass1);
                    if (check){
                        Toast.makeText(MainActivity.this, " Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(MainActivity.this,Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Mật khẩu không trùng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}