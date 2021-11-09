package com.service;

import java.util.List;

import com.entity.Cart;
import com.exception.ShoppingCartException;

public interface ShoppingCartServiceInterface {
	
	public List<Cart> getAllCarts() throws ShoppingCartException;
	
	public Cart getCartById(int cartId) throws ShoppingCartException;
	 
	public Cart updateCartById(int cartId,Cart cart) throws ShoppingCartException;
	
	public String deleteCart() throws ShoppingCartException;
	
	public Cart deleteCartById(int cartId)throws ShoppingCartException;

}