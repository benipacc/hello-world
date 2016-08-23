package com.ipacc.prodprice.bootexample.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//NOTES:
//Automatically goes a table with the same name as the class

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MessageEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String message;

}
