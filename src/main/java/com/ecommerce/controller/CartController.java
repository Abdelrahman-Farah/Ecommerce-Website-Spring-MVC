package com.ecommerce.controller;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.service.CartService;
import com.ecommerce.service.ProductService;
import com.ecommerce.utils.UserContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    private final CartService cartService;

    @Autowired
    public CartController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/")
    private String showCart(Model theModel){
        User user = UserContextUtil.getCurrentUser();

        Cart cart = user.getCart();

        theModel.addAttribute("cart", cart);
        theModel.addAttribute("itemsCount", cart.getCartItems().size());

        return "cart/show-cart";
    }

    @PostMapping("/add-item")
    private String addCartItem (
            @RequestParam("productId") int productId,
            @RequestParam("quantity") int quantity
    ){
        // TODO: handle exception if the cartItemId is not found
        // TODO: enter string in PathVariable("cartItemId")
        User user = UserContextUtil.getCurrentUser();

        Product theProduct = productService.findById(productId);
        Cart theCart = user.getCart();


        CartItem cartItem = cartService.findByCartIdAndProductId(theCart.getId(), theProduct.getId());

        if (cartItem == null){
            cartItem = new CartItem(theProduct, theCart, 0);
        }

        cartItem.setQuantity(cartItem.getQuantity() + quantity);

        cartService.saveItem(cartItem);
        return "redirect:/cart/";
    }

    @PostMapping("/delete-item")
    private String deleteCartItem (@RequestParam("cartItemId") int cartItemId){
        // TODO: handle exception if the cartItemId is not found
        // TODO: enter string in PathVariable("cartItemId")

        System.out.println("cartItemId"+ cartItemId);
        cartService.deleteItemById(cartItemId);
        return "redirect:/cart/";
    }
}
