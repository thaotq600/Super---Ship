package com.example.quocthao.supership.shop;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.quocthao.supership.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Quoc Thao on 3/31/2017.
 */

public class CreateOrdersFragment extends Fragment{

    private EditText etAddressSend, etAddressReceive, etTimeOut, etCategory, etWeight;
    private EditText etMoneyItem, etMoneyShip, etPhoneSender, etPhoneReceiver, etNote;
    private Button btnOk;
    private FirebaseUser userShop = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference drShop = FirebaseDatabase.getInstance().getReference();
    private ICreateSuccessListener iCreateSuccessListener;

    public CreateOrdersFragment(ICreateSuccessListener iCreateSuccessListener) {
        this.iCreateSuccessListener = iCreateSuccessListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders_create, container, false);
        initValue(view);

        String idOrder = "124";
        drShop = drShop.child("shop").child(userShop.getUid()).child("bills").child(idOrder);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOrders();

                if(iCreateSuccessListener != null)
                    iCreateSuccessListener.onSuccess();
            }
        });

        return view;
    }

    private void initValue(View view) {
        etAddressSend = (EditText) view.findViewById(R.id.order_create_et_address_send);
        etAddressReceive = (EditText) view.findViewById(R.id.order_create_et_address_receive);
        etTimeOut = (EditText) view.findViewById(R.id.order_create_et_time_out);
        etCategory = (EditText) view.findViewById(R.id.order_create_et_category);
        etWeight = (EditText) view.findViewById(R.id.order_create_et_weight);
        etMoneyItem = (EditText) view.findViewById(R.id.order_create_et_money_item);
        etMoneyShip = (EditText) view.findViewById(R.id.order_create_et_money_ship);
        etPhoneSender = (EditText) view.findViewById(R.id.order_create_et_phone_sender);
        etPhoneReceiver = (EditText) view.findViewById(R.id.order_create_et_phone_receiver);
        etNote = (EditText) view.findViewById(R.id.order_create_et_note);
        btnOk = (Button) view.findViewById(R.id.order_create_btn_ok);
    }

    private void saveOrders(){
        drShop.child("address_send").setValue(etAddressSend.getText().toString());
        drShop.child("address_receive").setValue(etAddressReceive.getText().toString());
        drShop.child("time_out").setValue(etTimeOut.getText().toString());
        drShop.child("category").setValue(etCategory.getText().toString());
        drShop.child("weight").setValue(etWeight.getText().toString());
        drShop.child("money_item").setValue(etMoneyItem.getText().toString());
        drShop.child("money_ship").setValue(etMoneyShip.getText().toString());
        drShop.child("phone_sender").setValue(etPhoneSender.getText().toString());
        drShop.child("phone_receiver").setValue(etPhoneReceiver.getText().toString());
        drShop.child("note").setValue(etNote.getText().toString());
    }

    public interface ICreateSuccessListener{
        void onSuccess();
    }
}
