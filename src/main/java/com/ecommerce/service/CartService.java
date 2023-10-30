package com.ecommerce.service;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;

public interface CartService {
//    Cart findByOwnerId(int ownerId);

    CartItem findByCartIdAndProductId(int cartId, int productId);

    void saveItem(CartItem item);

    void deleteItemById(int cartItemId);
}
