package de.czyrux.store.ui.cart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.czyrux.store.R;
import de.czyrux.store.core.domain.cart.CartProduct;

class CartAdapter extends RecyclerView.Adapter<CartItemViewHolder> {

    private final List<CartProduct> productList;
    private final CartListener listListener;

    public CartAdapter(CartListener listListener) {
        this.listListener = listListener;
        this.productList = new ArrayList<>(20);
    }

    public void setProductList(List<CartProduct> productList) {
        this.productList.clear();
        this.productList.addAll(productList);
        this.notifyDataSetChanged();
    }

    @Override
    public CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        return new CartItemViewHolder(view, listListener);
    }

    @Override
    public void onBindViewHolder(CartItemViewHolder holder, int position) {
        CartProduct product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


}
