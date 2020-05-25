package com.julianfm.wvctf.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.julianfm.wvctf.model.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	
	public default List<Product> findByName (String name) throws SQLException {
		
		String nameQuery = name.toLowerCase();
		
		String url = "jdbc:postgresql://localhost:5432/wvctf";
        Connection conn = DriverManager.getConnection(url,"postgres","root");
        
        String sql;
        
        if (name == "") {
        	sql = "SELECT * FROM product";
        } else {
        	sql = "SELECT *"
			      + "FROM product "
			      + "WHERE LOWER( name ) LIKE '%"
			      + nameQuery
			      + "%'";
        }
        
		ResultSet rs = conn.createStatement().executeQuery(sql);
		
		List<Product> products = new ArrayList<Product>();
		
		while(rs.next()) {
			Product p = new Product(); 
			p.setId(rs.getLong("id"));
			p.setName(rs.getString("name"));
			p.setDescription(rs.getString("description"));
			p.setColor(rs.getString("color"));
			p.setSize(rs.getString("size"));
			p.setPrice(rs.getDouble("price"));
			
			products.add(p);
		}
		
		return products;
	}
	
}
