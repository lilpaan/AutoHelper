package com.mydiploma.autohelper.ui.card;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    DiscountCardAdapter discountCardAdapter;
    BusinessCardAdapter businessAdapter;
    ListView businessListView;
    ListView discountListView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        businessListView = root.findViewById(R.id.added_business_card_list);
        discountListView = root.findViewById(R.id.added_discount_card_list);
        LinearLayout discountCardList = root.findViewById(R.id.discount_card_list);
        LinearLayout businessCardList = root.findViewById(R.id.business_card_list);
        Button businessCardButton = root.findViewById(R.id.business_card_button);
        Button discountCardButton = root.findViewById(R.id.discount_card_button);
        Button addCard = root.findViewById(R.id.add_card_button);

        // thread to show business items
        Thread threadToShowBusinessCard = new Thread(){
            @Override
            public void run() {
                businessCardDatabase = Room.databaseBuilder(requireContext(), BusinessCardDatabase.class,
                        Constants.BUSINESS_CARD).build();
                businessCardDao = businessCardDatabase.businessCardDao();
                businessCards = businessCardDao.getBusinessCardTitle().toArray(new BusinessCard[0]);
            }
        };
        threadToShowBusinessCard.start();
        try {
            threadToShowBusinessCard.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // init and set business adapter
        businessAdapter = new BusinessCardAdapter(requireContext(), businessCards);
        businessListView.setAdapter(businessAdapter);
        // for open business info
        businessListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent businessIntent = new Intent(root.getContext(), BusinessCardInfo.class);
            businessIntent.putExtra(Constants.ID, businessAdapter.getItem(position).getId());
            startActivity(businessIntent);
        });
        // thread to show discount items
        Thread threadToShowDiscountCard = new Thread(){
            @Override
            public void run() {
                discountCardDatabase = Room.databaseBuilder(requireContext(), DiscountCardDatabase.class,
                        Constants.DISCOUNT_CARD).build();
                discountCardDao = discountCardDatabase.discountCardDao();
                discountCards = discountCardDao.getDiscountCardTitle().toArray(new DiscountCard[0]);
            }
        };
        threadToShowDiscountCard.start();
        try {
            threadToShowDiscountCard.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // for show discount cards count
        Thread threadToShowDiscountCount = new Thread(){
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
        threadToShowDiscountCount.start();
        try {
            threadToShowDiscountCount.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // for show business cards count
        Thread threadToShowBusinessCount = new Thread(){
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
        threadToShowBusinessCount.start();
        try {
            threadToShowBusinessCount.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // init and set discount adapter
        discountCardAdapter = new DiscountCardAdapter(requireActivity(), discountCards);
        discountListView.setAdapter(discountCardAdapter);

        // for open discount info
        discountListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent discountIntent = new Intent(root.getContext(), DiscountCardInfo.class);
            discountIntent.putExtra(Constants.ID, discountCardAdapter.getItem(position).getId());
            startActivity(discountIntent);
        });

        // for show business list
        businessCardButton.setOnClickListener(v -> {
            businessCardButton.setTextColor(WHITE);
            businessCardCountView.setTextColor(WHITE);
            discountCardButton.setBackgroundColor(View.INVISIBLE);
            discountCardButton.setTextColor(BLACK);
            discountCardCountView.setTextColor(BLACK);
            businessCardList.setVisibility(View.VISIBLE);
            discountCardList.setVisibility(View.GONE);
        });

        // for show discount list
        discountCardButton.setOnClickListener(v -> {
            discountCardButton.setTextColor(WHITE);
            discountCardCountView.setTextColor(WHITE);
            businessCardButton.setTextColor(BLACK);
            businessCardCountView.setTextColor(BLACK);
            discountCardList.setVisibility(View.VISIBLE);
            businessCardList.setVisibility(View.GONE);
        });

        // for add new card
        addCard.setOnClickListener(v -> {
            // dialog with choosing card type
            chooseCardType = new Dialog(getActivity());
            chooseCardType.requestWindowFeature(Window.FEATURE_NO_TITLE);
            chooseCardType.setContentView(R.layout.choose_card_type);
            // for dialog design
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(chooseCardType.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            chooseCardType.getWindow().setAttributes(lp);
            chooseCardType.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // for close dialog
            closeDialog = chooseCardType.findViewById(R.id.close_dialog_button);
            closeDialog.setOnClickListener(v1 -> {
                chooseCardType.cancel();
            });
            chooseDiscount = chooseCardType.findViewById(R.id.choose_discount_card_button);
            // if discount chose
            chooseDiscount.setOnClickListener(v1 -> {
                Intent intentToAddDiscount = new Intent(root.getContext(), AddDiscountCardActivity.class);
                startActivityForResult(intentToAddDiscount, 1);
                chooseCardType.cancel();
            });

            chooseBusiness = chooseCardType.findViewById(R.id.choose_business_card_button);
            //if business chose
            chooseBusiness.setOnClickListener(v1 -> {
                Intent intentToAddBusiness = new Intent(root.getContext(), AddBusinessCardActivity.class);
                startActivityForResult(intentToAddBusiness, 1);
                chooseCardType.cancel();
            });

            // show dialog
            chooseCardType.show();
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

/*    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // update business card info
        businessCards = businessCardDao.getBusinessCardTitle().toArray(new BusinessCard[0]);
        businessAdapter = new BusinessCardAdapter(requireContext(), businessCards);
        businessListView.setAdapter(businessAdapter);
        businessListView.invalidateViews();

        // update discount card info
        discountCards = discountCardDao.getDiscountCardTitle().toArray(new DiscountCard[0]);
    }*/

}