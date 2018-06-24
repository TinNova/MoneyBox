package com.example.tin.moneybox;

import android.view.View;


/** Listens to the clicked position of an item in the RecyclerView */
public interface ProductPositionListener {

    void btnProductClick(View v, int position);

}
