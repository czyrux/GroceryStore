package de.czyrux.store.ui.catalog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.czyrux.store.R;
import de.czyrux.store.core.domain.product.Product;

class CatalogItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.catalog_item_imageview)
    ImageView image;

    @BindView(R.id.catalog_item_name)
    TextView name;

    @BindView(R.id.catalog_item_price)
    TextView price;

    private final CatalogListener listListener;

    CatalogItemViewHolder(View view, CatalogListener listListener) {
        super(view);
        this.listListener = listListener;
        ButterKnife.bind(this, view);
    }

    public void bind(Product product) {
        name.setText(product.title);
        String formattedPrice = NumberFormat.getNumberInstance(Locale.getDefault()).format(product.price);
        price.setText(String.format("%s €", formattedPrice));
        itemView.setOnClickListener(v -> listListener.onProductClicked(product));

        Picasso.with(itemView.getContext())
                .load(product.imageUrl)
                .centerInside()
                .fit()
                .into(image);
    }
}
