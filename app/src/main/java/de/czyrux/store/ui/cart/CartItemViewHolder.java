package de.czyrux.store.ui.cart;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.czyrux.store.R;
import de.czyrux.store.core.domain.cart.CartProduct;
import de.czyrux.store.ui.util.PriceFormatter;

class CartItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.cart_item_imageview)
    ImageView image;

    @BindView(R.id.cart_item_name)
    TextView name;

    @BindView(R.id.cart_item_price)
    TextView price;

    @BindView(R.id.cart_item_quantity)
    TextView quantity;

    private final CartListener listListener;

    CartItemViewHolder(View view, CartListener listListener) {
        super(view);
        this.listListener = listListener;
        ButterKnife.bind(this, view);
    }

    public void bind(CartProduct product) {
        name.setText(product.title);
        price.setText(PriceFormatter.format(product.price));
        itemView.setOnClickListener(v -> listListener.onCartProductClicked(product));

        String quantityFormatted = String.format(itemView.getResources().getString(R.string.cart_item_quantity), product.quantity);
        quantity.setText(quantityFormatted);

        Picasso.with(itemView.getContext())
                .load(product.imageUrl)
                .centerInside()
                .fit()
                .into(image);
    }
}
