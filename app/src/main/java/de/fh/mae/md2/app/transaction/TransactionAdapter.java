package de.fh.mae.md2.app.transaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

import de.fh.mae.md2.app.MyPayments;
import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.enums.ICategroryType;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
   //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Transaction> transactionList;

    private int TRANSACTION_ID = 1;

    //getting the context and product list with constructor
    public TransactionAdapter(Context mCtx, List<Transaction> productList) {
        this.mCtx = mCtx;
        this.transactionList = productList;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.transaction_card, null);
        // TODO: View Klickbar machen - setOnClickListener! Bei Klick AddTransactionActivity per Intent aufrufen und vorher dem Intent die Id anhängen per: intent.putExtra("TRANSACTION_ID", id);
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(mCtx, AddTransactionActivity.class);
                intent.putExtra("TRANSACTION_ID", parent.getId());
                //mCtx.startActivity(intent);
                if(mCtx instanceof Activity){
                    Activity a = ((Activity) mCtx);
                    a.startActivityForResult(intent, TRANSACTION_ID);
                }
            }
        } );*/
        return new TransactionViewHolder(view);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        //getting the product of the specified position
        Transaction transaction = transactionList.get(position);

        //binding the data with the viewholder views
        // TODO: Notiz mit anzeigen? siehe TODO in transaction_card.xml
        holder.textAmount.setText(transaction.getAmount()+ " " + MyPayments.getCurrencySymbol());
        if(transaction.getCategory().getType() == ICategroryType.INCOME){
            holder.textAmount.setTextColor(mCtx.getResources().getColor(R.color.colorIncome));
        } else{
            holder.textAmount.setTextColor(mCtx.getResources().getColor(R.color.colorOutcome));
        }

        holder.textCategory.setText(transaction.getCategory().getName());
        holder.imageCategory.setImageDrawable(mCtx.getResources().getDrawable(transaction.getCategory().getImage()));

        holder.textDate.setText(DateFormat.getDateInstance(DateFormat.FULL).format(transaction.getDate()));
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