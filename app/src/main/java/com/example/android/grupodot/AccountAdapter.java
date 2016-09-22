package com.example.android.grupodot;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.grupodot.Model.Account;

import java.util.List;

/**
 * Created by Ingmicha on 22/09/2016.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private List<Account> items;

    public AccountAdapter(List<Account> items) {
        this.items = items;
    }

    public static class AccountViewHolder extends RecyclerView.ViewHolder {

        public ImageView mActiveImageView;
        public TextView mAccountIdTextView;
        public TextView mActiveFromTextView;
        public TextView mCategoryTextView;
        public TextView mExpiryDateTextView;
        public TextView mNameTextView;
        public TextView mBalanceTextView;
        public TextView mInitialBalanceTextView;
        public TextView mReservedBalanceTextView;
        public TextView mTypeTextView;
        public TextView mCurrencyIdTextView;
        public TextView mUnitIdTextView;
        public TextView mUnitMantissaTextView;
        public TextView mUnitNameTextView;
        public TextView mUnitRelationTextView;

        public AccountViewHolder(View itemView) {

            super(itemView);
            mActiveImageView = (ImageView) itemView.findViewById(R.id.active_image);
            mAccountIdTextView = (TextView) itemView.findViewById(R.id.account_id_text);
            mActiveFromTextView = (TextView) itemView.findViewById(R.id.active_from_text);
            mCategoryTextView = (TextView) itemView.findViewById(R.id.category__text);
            mExpiryDateTextView = (TextView) itemView.findViewById(R.id.expiry_date_text);
            mNameTextView = (TextView) itemView.findViewById(R.id.name_text);
            mBalanceTextView = (TextView) itemView.findViewById(R.id.balance_text);
            mInitialBalanceTextView = (TextView) itemView.findViewById(R.id.initial_balance_text);
            mReservedBalanceTextView = (TextView) itemView.findViewById(R.id.reserved_balance_text);
            mTypeTextView = (TextView) itemView.findViewById(R.id.type_text);
            mCurrencyIdTextView = (TextView) itemView.findViewById(R.id.currency_id_text);
            mUnitIdTextView = (TextView) itemView.findViewById(R.id.id_text);
            mUnitMantissaTextView = (TextView) itemView.findViewById(R.id.mantissa_text);
            mUnitNameTextView = (TextView) itemView.findViewById(R.id.name_unit_text);
            mUnitRelationTextView = (TextView) itemView.findViewById(R.id.relation_unit_text);
        }
    }


    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_row, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {

        if (!items.get(position).getActive()) {
            holder.mActiveImageView.setImageResource(R.mipmap.ic_no_active);
        }
        String accountId = "Account Id: " + items.get(position).getAccountId();
        holder.mAccountIdTextView.setText(accountId);

        String activeFrom = "Active From: " + items.get(position).getActiveFrom();
        holder.mActiveFromTextView.setText(activeFrom);

        holder.mCategoryTextView.setText(items.get(position).getCategory());

        String expiryDate = ""+items.get(position).getExpiryDate();
        holder.mExpiryDateTextView.setText(expiryDate);

        holder.mNameTextView.setText(items.get(position).getName());

        String balance = ""+items.get(position).getBalance();
        holder.mBalanceTextView.setText(balance);

        String initialBalance =""+items.get(position).getInitialBalance();
        holder.mInitialBalanceTextView.setText(initialBalance);

        String reservedBalance = ""+items.get(position).getReservedBalance();
        holder.mReservedBalanceTextView.setText(reservedBalance);

        holder.mTypeTextView.setText(items.get(position).getType());

        String currencyId = "Currency Id: "+items.get(position).getUnit().getCurrencyId();
        holder.mCurrencyIdTextView.setText(currencyId);

        String id = "Id: "+items.get(position).getUnit().getId();
        holder.mUnitIdTextView.setText(id);

        String mantissa = ""+items.get(position).getUnit().getMantissa();
        holder.mUnitMantissaTextView.setText(mantissa);

        holder.mUnitNameTextView.setText(items.get(position).getUnit().getName());

        String relation = "Relation: "+items.get(position).getUnit().getRelation();
        holder.mUnitRelationTextView.setText(relation);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}