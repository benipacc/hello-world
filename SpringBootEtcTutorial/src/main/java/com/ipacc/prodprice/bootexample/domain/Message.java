package com.ipacc.prodprice.bootexample.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Message {
	
	private long id;
	private String message;

}
