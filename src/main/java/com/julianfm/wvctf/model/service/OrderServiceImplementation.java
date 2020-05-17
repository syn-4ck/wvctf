package com.julianfm.wvctf.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.OrderDTO;
import com.julianfm.wvctf.api.dto.ProductDTO;
import com.julianfm.wvctf.api.dto.UserDTO;
import com.julianfm.wvctf.api.service.OrderService;
import com.julianfm.wvctf.model.entity.Orders;
import com.julianfm.wvctf.model.entity.Product;
import com.julianfm.wvctf.model.entity.Users;
import com.julianfm.wvctf.model.exception.OrderNotFoundException;
import com.julianfm.wvctf.model.repository.OrderRepository;
import com.julianfm.wvctf.model.repository.ProductRepository;
import com.julianfm.wvctf.model.repository.UserRepository;

@Service
public class OrderServiceImplementation implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public OrderDTO createOrUpdateOrder(String username, Long productId) {
		
		ModelMapper orderMapper =  new ModelMapper();
		
		Orders insertedOrder = new Orders();
		Orders order = new Orders();
		OrderDTO insertedOrderDTO = new OrderDTO();
		
		if(username!=null && username!="") {
			Users user = userRepository.findByUsername(username);
			if(productId!=null) {
				Product product = productRepository.findById(productId).get();
				if (product!=null) {
					String key = user.getUsername() + "-" + product.getId();
					Orders orderKey = orderRepository.findByKey(key);
					if(orderKey==null) {
						order.setKey(key);
						order.setUsers(user);
						order.setProduct(product);
						order.setOrderDate(new Date());
						order.setCount(1);
						insertedOrder = orderRepository.save(order);
						insertedOrder.setUsers(user);
						insertedOrder.setProduct(product);
						insertedOrderDTO = orderMapper.map(insertedOrder, OrderDTO.class);
					} else {
						orderKey.setCount(orderKey.getCount()+1);
						orderKey.setOrderDate(new Date());
						insertedOrder = orderRepository.save(orderKey);
					}
					
				}
			} else {
				throw new OrderNotFoundException();
			}
		}
		
		return insertedOrderDTO;
	}

	@Override
	public void deleteOrder(Long id) {
		
		Orders order = new Orders();
		order = orderRepository.findById(id).get();
		
		if (order!=null) {
			orderRepository.delete(order);
		} else {
			throw new OrderNotFoundException();
		}
		
	}

	@Override
	public List<OrderDTO> findOrderByUser(String username) {
		
		List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>();
		ModelMapper orderMapper =  new ModelMapper();
		
		List<Product> products = new ArrayList<Product>();
		
		productRepository.findAll().forEach(p -> {
			products.add(p);
		});
		
		products.forEach(p -> {
			ProductDTO productDTO =  orderMapper.map(p, ProductDTO.class);
			UserDTO userDTO = orderMapper.map(p.getVendor(), UserDTO.class);
			String key = userDTO.getUsername() + "-" + productDTO.getId();
			Orders order = orderRepository.findByKey(key);
			if (order!=null) {
				if (order.getUsers().getUsername().equals(username)) {
					orderDTOList.add(new OrderDTO(order.getId(),userDTO, productDTO, order.getOrderDate(),order.getCount()));
				}
			}
		});
		
		return orderDTOList;
	}

	
	
}
