package com.example.ecommerce_application.service;

import com.example.ecommerce_application.model.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private static final String CART_SESSION_KEY = "cart";

    // 🛒 Get cart items from session (or initialize if null)
    public List<CartItem> getCartItems(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    // ➕ Add an item to the cart
    public void addToCart(HttpSession session, CartItem item) {
        List<CartItem> cart = getCartItems(session);

        // Check if product already exists in cart, if so, update quantity
        boolean found = false;
        for (CartItem existingItem : cart) {
            if (existingItem.getProductId().equals(item.getProductId())) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(item);
        }

        session.setAttribute(CART_SESSION_KEY, cart);
    }

    // 🗑️ Clear the cart
    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }

    // ❌ Remove a single item from the cart by productId
    public void removeItem(HttpSession session, Long productId) {
        List<CartItem> cart = getCartItems(session);
        cart.removeIf(item -> item.getProductId().equals(productId));
        session.setAttribute(CART_SESSION_KEY, cart);
    }
}
