package de.fh.mae.md2.app;

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

/**
 * Created by Belal on 10/18/2017.
 */


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
            holder.textViewValue.setText(String.format("+ %.2f €", transaction.getValue()));
        }
        else{
            holder.textViewValue.setText(String.format("- %.2f €", Math.abs(transaction.getValue())));
            holder.textViewValue.setTextColor(Color.RED);
        }

        holder.textViewCategory.setText(transaction.getCategory().getName());
        holder.textViewDate.setText(new SimpleDateFormat("MM/dd/yyyy").format(transaction.getDate()));
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(transaction.getImage()));
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCategory, textViewValue, textViewDate;
        ImageView imageView;

        public TransactionViewHolder(View itemView) {
            super(itemView);

            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewValue = itemView.findViewById(R.id.textViewValue);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}