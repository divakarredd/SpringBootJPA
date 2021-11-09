package com.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import com.entity.FurnitureOrder;
import com.exception.UserNotFoundException;
import com.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements OrderServiceInterface {

	
	@Autowired
	public OrderRepository od;

	
	@Transactional
	@Override
	public List<FurnitureOrder> getAllOrders() {
		
		List<FurnitureOrder> getFurniture = od.findAll();
		
		return getFurniture;
	}


	
	@Transactional
	@Override
	public FurnitureOrder updateOrder(FurnitureOrder order) throws UserNotFoundException {
		if ((order != null)) {

			
			FurnitureOrder updateUser = od.save(order);
			
			return updateUser;

		} else {
			throw new UserNotFoundException("Order no exist");

		}
	}

	
	@Transactional
	@Override
	public FurnitureOrder updateOrderById(String orderId, FurnitureOrder order) throws UserNotFoundException {
		FurnitureOrder resultUser;
		try {
			
			order = od.findById(orderId).orElse(null);
			if (orderId.equals(order.getOrderId())) {
				resultUser = od.save(order);
				
				return resultUser;
			}

			else {
				throw new UserNotFoundException("No order found");
			}
		} catch (Exception e) {
			throw new UserNotFoundException("no orderFound");
		}
	}

}
