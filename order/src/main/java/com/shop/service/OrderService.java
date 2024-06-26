package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.controllers.OrderDTO;
import com.shop.controllers.ProductDTO;
import com.shop.model.OrderEntity;
import com.shop.model.ProductEntity;
import com.shop.repositories.OrderRepository;
import com.shop.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public List<OrderDTO> getOrdersByUserId(int userId) {
		List<OrderEntity> orderEntities = orderRepository.findByuserId(userId);
		return orderEntities.stream().map(OrderDTO::orderEntityToOrderDTO).toList();
	}
	
	public OrderDTO createOrder(int userId, OrderDTO orderDTO) {
		orderDTO.setUserId(userId);
		OrderEntity orderEntity = OrderDTO.orderDTOToOrderEntity(orderDTO);
	
		OrderDTO.orderEntityToOrderDTO(orderRepository.save(orderEntity));
		
		for (ProductDTO p : orderDTO.getProducts()) {
			ProductEntity pE = ProductDTO.ProductDTOToProductEntity(p);
			pE.setOrderEntity(orderEntity);
			productRepository.save(pE);
		}
		
		return orderDTO;
	}
	
	public OrderDTO getOrderDetails(int orderId) {
		return OrderDTO.orderEntityToOrderDTO(orderRepository.findById(orderId).orElse(new OrderEntity()));
	}
	
	

}
