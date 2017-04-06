package com.example.quocthao.supership.shop.manage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quocthao.supership.R;
import com.example.quocthao.supership.objects.ObjBill;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

public class WaitShipFragment extends Fragment {

    private ListView lvBill;
    private QuickAdapter quickAdapter;
    private FirebaseUser userShop = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference drShop = FirebaseDatabase.getInstance().getReference();
    private ObjBill bill = new ObjBill();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manage_wait_ship, container, false);

        lvBill = (ListView) view.findViewById(R.id.manage_wait_ship_lv_bill);
        String idOrder = "123";
        drShop = drShop.child("shop").child(userShop.getUid()).child("bills").child(idOrder);

        quickAdapter = new QuickAdapter<ObjBill>(getContext(), R.layout.item_orders_ship) {
            @Override
            protected void convert(BaseAdapterHelper helper, ObjBill item) {
                helper.setText(R.id.item_bill_address_send, item.getAddressSend())
                        .setText(R.id.item_bill_address_receive, item.getAddressReceive());
            }

        };

        readBills();

        return view;
    }

    private void readBills(){
        drShop.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bill.setAddressSend(dataSnapshot.child("address_send").getValue().toString());
                bill.setAddressReceive(dataSnapshot.child("address_receive").getValue().toString());
                bill.setTimeOut(dataSnapshot.child("time_out").getValue().toString());
                bill.setCategory(dataSnapshot.child("category").getValue().toString());
                bill.setWeight(dataSnapshot.child("weight").getValue().toString());
                bill.setMoneyItem(dataSnapshot.child("money_item").getValue().toString());
                bill.setMoneyShip(dataSnapshot.child("money_ship").getValue().toString());
                bill.setPhoneSender(dataSnapshot.child("phone_sender").getValue().toString());
                bill.setPhoneReceiver(dataSnapshot.child("phone_receiver").getValue().toString());
                bill.setNote(dataSnapshot.child("note").getValue().toString());

                quickAdapter.add(bill);

                lvBill.setAdapter(quickAdapter);

                lvBill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        View viewDialog = LayoutInflater.from(getContext())
                                .inflate(R.layout.dialog_content_bill, null);

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setView(viewDialog);

                        final TextView etAddressSend = (TextView) viewDialog.findViewById(R.id.content_bill_tv_address_send);
                        final TextView etAddressReceive = (TextView) viewDialog.findViewById(R.id.content_bill_tv_address_receive);
                        final TextView etTimeOut = (TextView) viewDialog.findViewById(R.id.content_bill_tv_time_out);
                        final TextView etCategory = (TextView) viewDialog.findViewById(R.id.content_bill_tv_category);
                        final TextView etWeight = (TextView) viewDialog.findViewById(R.id.content_bill_tv_weight);
                        final TextView etMoneyItem = (TextView) viewDialog.findViewById(R.id.content_bill_tv_money_item);
                        final TextView etMoneyShip = (TextView) viewDialog.findViewById(R.id.content_bill_tv_money_ship);
                        final TextView etPhoneSender = (TextView) viewDialog.findViewById(R.id.content_bill_tv_phone_sender);
                        final TextView etPhoneReceiver = (TextView) viewDialog.findViewById(R.id.content_bill_tv_phone_receiver);
                        final TextView etNote = (TextView) viewDialog.findViewById(R.id.content_bill_tv_note);

                        etAddressSend.append(bill.getAddressSend());
                        etAddressReceive.append(bill.getAddressReceive());
                        etTimeOut.append(bill.getTimeOut());
                        etCategory.append(bill.getCategory());
                        etWeight.append(bill.getWeight());
                        etMoneyItem.append(bill.getMoneyItem());
                        etMoneyShip.append(bill.getMoneyShip());
                        etPhoneSender.append(bill.getPhoneSender());
                        etPhoneReceiver.append(bill.getPhoneReceiver());
                        etNote.append(bill.getNote());

                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
