package com.example.tin.moneybox;

import android.view.View;

/**
 * Created by Tin on 18/06/2018.
 */

/** Listens to the clicked position of an item in the RecyclerView */
public interface ProductPositionListener {

    void btnProductClick(View v, int position);

}
