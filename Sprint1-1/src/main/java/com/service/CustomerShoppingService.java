package com.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.entity.Cart;
import com.entity.Customer;
import com.entity.Furniture;
import com.entity.FurnitureOrder;
import com.exception.CustomerShoppingException;
import com.exception.FurnitureServiceException;
import com.repository.CustomerRepository;
import com.repository.IFurnitureRepository;
import com.repository.IShoppingCartRepository;
import com.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerShoppingService implements CustomerShoppingServiceInterface {

	
	@Autowired
	private IFurnitureRepository furnitureRepo;
	@Autowired
	IShoppingCartRepository cartRepo;
	@Autowired
	public OrderRepository orderRepo;
    @Autowired
    public CustomerRepository cup;
	
	@Transactional
	@Override
	public List<Furniture> getAllFurnitures() throws CustomerShoppingException{
		try {
		
		List<Furniture> resultFurniture = furnitureRepo.findAll();
		if(resultFurniture!=null) {
		
		return resultFurniture;
		}
		else {
			throw new CustomerShoppingException("The Furniture is empty");
		}
		}
		catch(Exception e) {
			throw new CustomerShoppingException("The Furniture is empty");
		}
	}

	
	@Transactional
	@Override
	public Furniture getFurnitureByName(String furnitureName) throws CustomerShoppingException {

		try {
			
			Furniture result = furnitureRepo.findbyName(furnitureName);
				
				return result; 
		}
		catch (Exception e) {
			throw new CustomerShoppingException("Furniture nor found");
		}
	}
	/*public Furniture getFurnitureByName(String furnitureName) throws CustomerShoppingException  {
		Furniture getFurniture = null;
		try {
		
			getFurniture =furnitureRepo.findbyName(furnitureName).orElse(null);
			if(getFurniture!=null) {
			if (getFurniture.getFurnitureName() == furnitureName) {
				getFurniture = furnitureRepo.findbyName(furnitureName).orElse(null);
				
				
			} else {
				throw new FurnitureServiceException("Name is not Present");
			}}
			else {
				throw new FurnitureServiceException("Name is not Present");
			}
		} catch (Exception e) {
			throw new CustomerShoppingException("Furniture nor found");
		}
		
		return getFurniture;
	}*/
	@Override
	public Customer addCustomerDetails(Customer cd) {
	return cup.save(cd);
	}
	@Transactional
 @Override
	public Cart addtoCart(Cart cart) {
	return cartRepo.save(cart);
	}
	@Transactional
	@Override
	public FurnitureOrder placeOrder(FurnitureOrder order) {
	return orderRepo.save(order);
	}
	
	
	
	/*@Override
	public Cart addtoCart(Cart furniture) throws CustomerShoppingException {

		if ((furniture.getCartId()!= 0)) {

			
			Cart updateUser = cartRepo.save(furniture);
			
			return updateUser;

		} else {
			throw new CustomerShoppingException("Furniture not added to cart");

		}
	}

	
	
	@Transactional(readOnly = true)
	@Override
	public FurnitureOrder placeOrder(FurnitureOrder order) throws CustomerShoppingException {
		if ((order.getOrderId()!=null)) {

			
			FurnitureOrder placeOrder = orderRepo.save(order);
			
			return placeOrder;

		} else {
			throw new CustomerShoppingException("Order cannot be placed");

		}
	}*/
}