package com.example.quocthao.supership.login_register;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.quocthao.supership.customer.ContentCustomer;
import com.example.quocthao.supership.customer.InfoCustomer;
import com.example.quocthao.supership.saler.InfoSaler;
import com.example.quocthao.supership.shipper.InfoShipper;
import com.example.quocthao.supership.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.orhanobut.hawk.Hawk;

public class LoginActivity extends Activity {

    private ViewFlipper vfLogo;
    private EditText etUser;
    private EditText etPass;
    private Button btnLogin;
    private TextView tvForgetPass;
    private TextView tvRegister;

    private Intent intentInfo;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private AlertDialog dialogWho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initContent();

        Hawk.init(this).build();
        checkEmailExists();



    }

    public void checkEmailExists() {
        if (Hawk.get("email") == null) {
            // khởi tạo giá trị.

            vfLogo.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    vfLogo.setInAnimation(LoginActivity.this, R.anim.anim_around_in);
                    vfLogo.setOutAnimation(LoginActivity.this, R.anim.anim_around_out);
                    vfLogo.showNext();

                    return false;
                }


            });

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startLogin();
                }
            });

            tvForgetPass.setMovementMethod(LinkMovementMethod.getInstance()); //link web

            tvRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intentRegister);
                }
            });
        } else {
            chooseWho();
        }
    }

    private void initContent() {
        vfLogo = (ViewFlipper) findViewById(R.id.vf_logo);
        etUser = (EditText) findViewById(R.id.et_user);
        etPass = (EditText) findViewById(R.id.et_pass);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvForgetPass = (TextView) findViewById(R.id.tv_forget_pass);
        tvRegister = (TextView) findViewById(R.id.tv_register);
    }

    private void chooseWho() {

        LayoutInflater li = LayoutInflater.from(this);
        View convertView = li.inflate(R.layout.dialog_login_who, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout layoutSaler = (LinearLayout) convertView.findViewById(R.id.layout_saler);
        LinearLayout layoutShipper = (LinearLayout) convertView.findViewById(R.id.layout_shipper);
        LinearLayout layoutCustomer = (LinearLayout) convertView.findViewById(R.id.layout_customer);
        TextView tvAccount = (TextView) convertView.findViewById(R.id.dialog_login_who_tv_account);

        layoutSaler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intentInfo = new Intent(LoginActivity.this, InfoSaler.class);
                startActivity(intentInfo);
            }
        });

        layoutShipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intentInfo = new Intent(LoginActivity.this, InfoShipper.class);
                startActivity(intentInfo);
            }
        });

        layoutCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intentInfo = new Intent(LoginActivity.this, InfoCustomer.class);
                startActivity(intentInfo);
            }
        });

        tvAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hawk.delete("email");
                dialogWho.cancel();
                checkEmailExists();
            }
        });

        builder.setView(convertView);
        builder.setCancelable(false);

        dialogWho = builder.create();

        dialogWho.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.cancel();
                    LoginActivity.this.finish();
                    return true;
                }
                return false;
            }
        });

        dialogWho.show();
    }

    private void startLogin() {

        final String email = etUser.getText().toString();
        final String pass = etPass.getText().toString();
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Success",
                                    Toast.LENGTH_SHORT).show();

                            Hawk.put("email", email);
                            chooseWho();
                        } else {
                            Toast.makeText(LoginActivity.this, "Fail",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void forgotPassword() {

    }
}
