package com.example.tin.moneybox;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tin.moneybox.models.Product;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Tin on 17/06/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<Product> mProducts;
    private final Context context;

    /* Constructor:
     * Pass in the StationPositionListener Interface into the Adapter on construction */
    public ProductAdapter(ArrayList<Product> products, Context context) {
        this.mProducts = products;
        this.context = context;
    }

    // We are passing the station data via a method, not when the Adapter is created
    public void setProducts(ArrayList<Product> products) {
        this.mProducts = products;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Product product = mProducts.get(position);

        viewHolder.btnProduct.setText(product.getFriendlyName());

    }

    @Override
    public int getItemCount() {
        if (mProducts == null) {
            return 0;
        } else {
            return mProducts.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final Button btnProduct;

        public ViewHolder(View itemView) {
            super(itemView);

            btnProduct = itemView.findViewById(R.id.btn_Product);
        }
    }
}
