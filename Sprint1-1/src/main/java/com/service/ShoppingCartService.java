package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.entity.Cart;
import com.exception.ShoppingCartException;
import com.repository.IShoppingCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service

public class ShoppingCartService implements ShoppingCartServiceInterface {

	
	@Autowired
	IShoppingCartRepository shopingrepo;
	private JpaRepository<Cart, Integer> shoppingrepo;

	
	@Transactional
	@Override
	public List<Cart> getAllCarts() throws ShoppingCartException {
		List<Cart> cartTemp = shoppingrepo.findAll();
		
		if (cartTemp.isEmpty()) {
			throw new ShoppingCartException("Cart not found");
		} else {
			List<Cart> getAllCarts = shoppingrepo.findAll();
			
			return getAllCarts;
		}

	}

	
	@Transactional
	@Override
	public Cart getCartById(int cartId) throws ShoppingCartException {
		
		Optional<Cart> cartTemp = shoppingrepo.findById(cartId);
		if (cartTemp.isPresent() == false) {
			throw new ShoppingCartException("CartId does not exist");
		} else {
			Cart getCart = shoppingrepo.findById(cartId).orElse(null);
			
			return getCart;

		}
	}

	
	@Transactional
	@Override
	public Cart updateCartById(int cartId, Cart cart) throws ShoppingCartException {
		
		Optional<Cart> cartTemp = shoppingrepo.findById(cartId);
		if (cartTemp.isPresent() == false) {
			throw new ShoppingCartException("Cart does not exist");
		} else {
			Cart updcart = shoppingrepo.save(cart);
			
			return updcart;
		}
		
	}

	
	
	@Transactional
	@Override
	public String deleteCart() throws ShoppingCartException {
		
		List<Cart> cartTemp = shoppingrepo.findAll();
		if (cartTemp.isEmpty()) {
			throw new ShoppingCartException("Cart not found");
		} else {
			shoppingrepo.deleteAll();
			
			return "All carts deleted";
		}
	}

	
	
	@Transactional
	@Override
	public Cart deleteCartById(int cartId) throws ShoppingCartException {
		
		Optional<Cart> cart = shoppingrepo.findById(cartId);
		if (cart.isPresent()) {
			shoppingrepo.delete(cart.get());
			
			return cart.get();
		} else {
			throw new ShoppingCartException("Cart does not exist");
		}
	}
}