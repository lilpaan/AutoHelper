package com.mydiploma.autohelper.ui.card;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.adapter.BusinessCardAdapter;
import com.mydiploma.autohelper.adapter.DiscountCardAdapter;
import com.mydiploma.autohelper.dao.BusinessCardDao;
import com.mydiploma.autohelper.dao.DiscountCardDao;
import com.mydiploma.autohelper.database.BusinessCardDatabase;
import com.mydiploma.autohelper.database.DiscountCardDatabase;
import com.mydiploma.autohelper.databinding.FragmentHomeBinding;
import com.mydiploma.autohelper.entity.BusinessCard;
import com.mydiploma.autohelper.entity.DiscountCard;


public class CardFragment extends Fragment {

    private FragmentHomeBinding binding;
    Dialog chooseCardType;
    Button closeDialog;
    Button chooseDiscount;
    Button chooseBusiness;
    BusinessCardDatabase businessCardDatabase;
    DiscountCardDatabase discountCardDatabase;
    BusinessCardDao businessCardDao;
    DiscountCardDao discountCardDao;
    BusinessCard[] businessCards;
    DiscountCard[] discountCards;
    TextView discountCardCountView;
    TextView businessCardCountView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView businessListView = root.findViewById(R.id.added_business_card_list);
        Thread thread = new Thread(){
            @Override
            public void run() {
                businessCardDatabase = Room.databaseBuilder(requireContext(), BusinessCardDatabase.class,
                        Constants.BUSINESS_CARD).build();
                businessCardDao = businessCardDatabase.businessCardDao();
                businessCards = businessCardDao.getBusinessCardTitle().toArray(new BusinessCard[0]);
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BusinessCardAdapter businessAdapter= new BusinessCardAdapter(requireContext(), businessCards);
        businessListView.setAdapter(businessAdapter);
        businessListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent businessIntent = new Intent(root.getContext(), BusinessCardInfo.class);
            businessIntent.putExtra(Constants.ID, businessAdapter.getItem(position).getId());
            startActivity(businessIntent);
        });
        ListView discountListView = root.findViewById(R.id.added_discount_card_list);
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                discountCardDatabase = Room.databaseBuilder(requireContext(), DiscountCardDatabase.class,
                        Constants.DISCOUNT_CARD).build();
                discountCardDao = discountCardDatabase.discountCardDao();
                discountCards = discountCardDao.getDiscountCardTitle().toArray(new DiscountCard[0]);
            }
        };
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(){
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                discountCardDatabase = Room.databaseBuilder(requireContext(), DiscountCardDatabase.class,
                        Constants.DISCOUNT_CARD).build();
                discountCardDao = discountCardDatabase.discountCardDao();
                discountCardCountView = root.findViewById(R.id.discount_cards_count);
                discountCardCountView.setText(discountCardDao.getDiscountCount().toString());
            }
        };
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread3 = new Thread(){
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                businessCardDatabase = Room.databaseBuilder(requireContext(), BusinessCardDatabase.class,
                        Constants.BUSINESS_CARD).build();
                businessCardDao = businessCardDatabase.businessCardDao();
                businessCardCountView = root.findViewById(R.id.business_cards_count);
                businessCardCountView.setText(businessCardDao.getBusinessCount().toString());
            }
        };
        thread3.start();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DiscountCardAdapter discountCardAdapter = new DiscountCardAdapter(requireActivity(), discountCards);
        discountListView.setAdapter(discountCardAdapter);
        discountListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent discountIntent = new Intent(root.getContext(), DiscountCardInfo.class);
            discountIntent.putExtra(Constants.ID, discountCardAdapter.getItem(position).getId());
            startActivity(discountIntent);
        });
        LinearLayout discountCardList = root.findViewById(R.id.discount_card_list);
        LinearLayout businessCardList = root.findViewById(R.id.business_card_list);
        Button businessCardButton = root.findViewById(R.id.business_card_button);

        businessCardButton.setOnClickListener(v -> {
            businessCardList.setVisibility(View.VISIBLE);
            discountCardList.setVisibility(View.GONE);
        });

        Button discountCardButton = root.findViewById(R.id.discount_card_button);

        discountCardButton.setOnClickListener(v -> {
            discountCardList.setVisibility(View.VISIBLE);
            businessCardList.setVisibility(View.GONE);
        });

        Button addCard = root.findViewById(R.id.add_card_button);

        addCard.setOnClickListener(v -> {
            chooseCardType = new Dialog(getActivity());
            chooseCardType.requestWindowFeature(Window.FEATURE_NO_TITLE);
            chooseCardType.setContentView(R.layout.choose_card_type);
            closeDialog = chooseCardType.findViewById(R.id.close_dialog_button);
            closeDialog.setOnClickListener(v1 -> {
                chooseCardType.cancel();
            });
            chooseDiscount = chooseCardType.findViewById(R.id.choose_discount_card_button);
            chooseDiscount.setOnClickListener(v1 -> {
                Intent intent = new Intent(root.getContext(), AddDiscountCardActivity.class);
                startActivity(intent);
                chooseCardType.cancel();
            });
            chooseBusiness = chooseCardType.findViewById(R.id.choose_business_card_button);
            chooseBusiness.setOnClickListener(v1 -> {
                Intent intent = new Intent(root.getContext(), AddBusinessCardActivity.class);
                startActivity(intent);
                chooseCardType.cancel();
            });
            chooseCardType.show();
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}