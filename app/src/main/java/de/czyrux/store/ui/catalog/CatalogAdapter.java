package de.czyrux.store.ui.catalog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.czyrux.store.R;
import de.czyrux.store.core.domain.product.Product;

class CatalogAdapter extends RecyclerView.Adapter<CatalogItemViewHolder> {

    private final List<Product> productList;
    private final CatalogListener listListener;

    public CatalogAdapter(CatalogListener listListener) {
        this.listListener = listListener;
        this.productList = new ArrayList<>(20);
    }

    public void setProductList(List<Product> productList) {
        this.productList.clear();
        this.productList.addAll(productList);
        this.notifyDataSetChanged();
    }

    @Override
    public CatalogItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_list_item, parent, false);
        return new CatalogItemViewHolder(view, listListener);
    }

    @Override
    public void onBindViewHolder(CatalogItemViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


}
