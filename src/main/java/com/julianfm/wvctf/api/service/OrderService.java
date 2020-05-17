package com.julianfm.wvctf.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.OrderDTO;

@Service
public interface OrderService {

	public OrderDTO createOrUpdateOrder (String user, Long product);
	
	public void deleteOrder (Long id);
	
	public List<OrderDTO> findOrderByUser(String username);
	
}
