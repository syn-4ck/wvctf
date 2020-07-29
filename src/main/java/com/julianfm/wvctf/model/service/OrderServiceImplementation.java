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
import com.julianfm.wvctf.model.entity.Commentary;
import com.julianfm.wvctf.model.entity.Orders;
import com.julianfm.wvctf.model.entity.Product;
import com.julianfm.wvctf.model.entity.Users;
import com.julianfm.wvctf.model.exception.OrderNotFoundException;
import com.julianfm.wvctf.model.repository.CommentaryRepository;
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
	
	@Autowired
	CommentaryRepository commentaryRepository;

	@Override
	public Orders createOrUpdateOrder(String browser, String username, Long productId) {
		
		ModelMapper orderMapper =  new ModelMapper();
		
		Orders insertedOrder = new Orders();
		Orders order = new Orders();
		OrderDTO insertedOrderDTO = new OrderDTO();
		
		if (
				((!username.equals("user1"))&&(browser.equals("no-cors")))
				||
				(username.equals("user1"))
			) {
			if(username!="") {
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
		}
		
		return insertedOrder;
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
			Users user = userRepository.findByUsername(username);
			UserDTO userDTO = orderMapper.map(user, UserDTO.class);
			String key = userDTO.getUsername() + "-" + productDTO.getId();
			Orders order = orderRepository.findByKey(key);
			if (order!=null) {
				if (order.getUsers().getUsername().equals(username)) {
					orderDTOList.add(new OrderDTO(order.getId(),orderMapper.map(p.getVendor(), UserDTO.class), 
							productDTO, order.getOrderDate(),order.getCount()));
				}
			}
		});
		
		return orderDTOList;
	}

	@Override
	public List<OrderDTO> findOrderByVendor(String username) {
		
		List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>();
		ModelMapper orderMapper =  new ModelMapper();
		
		
		orderRepository.findAll().forEach(o ->{
			if (o.getProduct().getVendor().getUsername().equals(username)) {
				orderDTOList.add(orderMapper.map(o,OrderDTO.class));
			}
		});

		
		return orderDTOList;
	}

	@Override
	public List<OrderDTO> orderInUser2() {
		
		ModelMapper orderMapper =  new ModelMapper();
		List<OrderDTO> orders = new ArrayList<OrderDTO>();

		List<Commentary> comms = new ArrayList<Commentary>();
		List<Commentary> comms_with_url = new ArrayList<Commentary>();
		
		orderRepository.findAll().forEach(o -> {
			if(!o.getUsers().getUsername().equals("user1")) {
				
				commentaryRepository.findAll().forEach(c -> {
					comms.add(c);
				});
				
				if (!comms.isEmpty()) {
					comms.forEach(cp -> {
						if ((cp.getText().contains("/orders?"))
								&&
							(cp.getText().contains("user="+o.getUsers().getUsername()))
								&&
							(cp.getText().contains("product="+o.getProduct().getId()))
						){
							comms_with_url.add(cp);
						}
					});
				}
				
				System.out.println("----> COMMENTS with URL: "+comms_with_url.toString());
				if (!comms_with_url.isEmpty()) {
					orders.add(orderMapper.map(o,OrderDTO.class));
				}
			}
		});
		return orders;
	}
	
}
