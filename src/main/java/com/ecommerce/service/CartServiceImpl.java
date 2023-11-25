package com.ecommerce.service;

//import com.ecommerce.dao.CartRepository;
import com.ecommerce.dao.CartItemRepository;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

//    private CartRepository cartRepository;
//make the field final [abdelkarim]
    private CartItemRepository cartItemRepository;

    @Autowired
    public CartServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }


    @Override
    public CartItem findByCartIdAndProductId(int cartId, int productId) {
        return cartItemRepository.findByCartIdAndProductId(cartId, productId);
    }

    @Override
    public void saveItem(CartItem item) {
        cartItemRepository.save(item);
    }

    @Override
    public void deleteItemById(int cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
