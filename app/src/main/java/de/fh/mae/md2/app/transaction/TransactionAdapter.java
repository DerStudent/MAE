package de.fh.mae.md2.app.transaction;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;


import java.util.List;

import de.fh.mae.md2.app.R;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Transaction> transactionList;

    //getting the context and product list with constructor
    public TransactionAdapter(Context mCtx, List<Transaction> productList) {
        this.mCtx = mCtx;
        this.transactionList = productList;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.transaction_card, null);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        //getting the product of the specified position
        Transaction transaction = transactionList.get(position);

        //binding the data with the viewholder views

        if(transaction.getValue() >= 0){
            holder.textAmount.setText(String.format("+ %.2f €", transaction.getValue()));
            int colorIncome = mCtx.getResources().getColor(R.color.colorIncome);
            holder.textAmount.setTextColor(colorIncome);
        }
        else{
            holder.textAmount.setText(String.format("- %.2f €", Math.abs(transaction.getValue())));
            int colorOutcome = mCtx.getResources().getColor(R.color.colorOutcome);
            holder.textAmount.setTextColor(colorOutcome);
        }

        holder.textCategory.setText(transaction.getCategory().getName());
        holder.textDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(transaction.getDate()));
        holder.imageCategory.setImageDrawable(mCtx.getResources().getDrawable(transaction.getImage()));
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView textCategory, textAmount, textDate;
        ImageView imageCategory;

        public TransactionViewHolder(View itemView) {
            super(itemView);

            textCategory = itemView.findViewById(R.id.text_transaction_card_category);
            textDate = itemView.findViewById(R.id.text_transaction_card_date);
            textAmount = itemView.findViewById(R.id.text_transaction_card_amount);
            imageCategory = itemView.findViewById(R.id.image_transaction_card_category);
        }
    }
}