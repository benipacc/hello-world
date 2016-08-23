package com.ipacc.prodprice.bootexample.resource;

import org.springframework.hateoas.ResourceSupport;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageResource extends ResourceSupport {
	
	private long messageId;
	private String messageText;
	
	/*@JsonCreator*/
	/*public MessageResource(@JsonProperty("message") String message) {
		this.message = message;
	}*/

}
