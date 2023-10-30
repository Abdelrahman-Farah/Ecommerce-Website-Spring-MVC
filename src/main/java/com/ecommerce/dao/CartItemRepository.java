package com.ecommerce.dao;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    CartItem findByCartIdAndProductId(int cartId, int productId);
}
