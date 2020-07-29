package com.julianfm.wvctf.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.julianfm.wvctf.api.dto.OrderDTO;
import com.julianfm.wvctf.model.entity.Orders;

@Service
public interface OrderService {

	public Orders createOrUpdateOrder (String browser, String user, Long product);
	
	public void deleteOrder (Long id);
	
	public List<OrderDTO> findOrderByUser(String username);

	List<OrderDTO> findOrderByVendor(String username);

	public List<OrderDTO> orderInUser2();
	
}
