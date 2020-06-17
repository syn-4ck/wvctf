package com.julianfm.wvctf.model.entity.mng;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.apache.catalina.User;
import org.springframework.data.relational.core.mapping.Table;

import com.julianfm.wvctf.model.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(value = "user_flag")
public class UserFlag {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(optional = false)
	private ManageUser users;
	
	@NotBlank(message = "Flag is mandatory")
	private String flag;
	
	private Date date;
	
}
