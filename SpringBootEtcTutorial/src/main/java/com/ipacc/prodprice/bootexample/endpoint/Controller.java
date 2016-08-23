package com.ipacc.prodprice.bootexample.endpoint;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipacc.prodprice.bootexample.data.entity.MessageEntity;
import com.ipacc.prodprice.bootexample.data.repository.MessageRepository;
import com.ipacc.prodprice.bootexample.domain.Message;
import com.ipacc.prodprice.bootexample.resource.MessageResource;

@RestController
public class Controller {
	
	@Autowired
	MessageRepository msgRepository;
	
	@GetMapping(value="/ping")
	public String ping() {
		return "MyBoot is alive";
	}
	
	@GetMapping(value="/hello")
	public String sayHello(@RequestParam String name) {
		return "Hello, " + name + "!";
	}
	
	//NOTES:
	//Method param name different from JSON name; default value provided, so value isn't required to be passed in
	@GetMapping(value="/doMessage")
	public Message doMessageJson(@RequestParam int id, 
			@RequestParam(value="message", defaultValue="No Message") String msg) {
		Message aMessage = Message.builder().id(id).message(msg).build();
		return aMessage;
	}
	
	//NOTES:
	//Using RequestMapping here because the Spring HATEOAS methods don't yet recognize the newer annotations 
	@RequestMapping(value="/getMessage", method=RequestMethod.GET)
	public ResponseEntity<MessageResource> getMessage(@RequestParam long id) {
		MessageEntity messageEntity = msgRepository.findOne(id);
		
		MessageResource messageRsc = MessageResource.builder()
				.messageId(messageEntity.getId())
				.messageText(messageEntity.getMessage())
				.build();
		messageRsc.add(linkTo(methodOn(Controller.class).getMessage(id)).withSelfRel());
		
		return new ResponseEntity<MessageResource>(messageRsc, HttpStatus.OK);
	}
	
	@RequestMapping(value="/createMessage", method=RequestMethod.POST)
	public ResponseEntity<MessageResource> postMessage(@RequestBody Message reqMessage) {
		MessageEntity messageEntity = MessageEntity.builder()
				.id(reqMessage.getId())
				.message(reqMessage.getMessage())
				.build();
		msgRepository.save(messageEntity);
		
		MessageResource messageRsc = MessageResource.builder()
				.messageId(messageEntity.getId())
				.messageText(messageEntity.getMessage())
				.build();
		messageRsc.add(linkTo(methodOn(Controller.class).updateMessage(reqMessage)).withRel("update"));
		messageRsc.add(linkTo(methodOn(Controller.class).postMessage(reqMessage)).withSelfRel());
		
		return new ResponseEntity<MessageResource>(messageRsc, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/updateMessage", method=RequestMethod.PUT)
	public ResponseEntity<MessageResource> updateMessage(@RequestBody Message reqMessage) {
		ResponseEntity<MessageResource> response = null;
		
		if(msgRepository.exists(reqMessage.getId())) {
			MessageEntity messageEntity = MessageEntity.builder()
					.id(reqMessage.getId())
					.message(reqMessage.getMessage())
					.build();
			msgRepository.save(messageEntity);
			
			MessageResource messageRsc = MessageResource.builder()
					.messageId(messageEntity.getId())
					.messageText(messageEntity.getMessage())
					.build();
			messageRsc.add(linkTo(methodOn(Controller.class).updateMessage(reqMessage)).withSelfRel());
			
			response = ResponseEntity.ok(messageRsc); //shortcut for HTTP status
		} else {
			response = new ResponseEntity<MessageResource>(HttpStatus.CONFLICT);
		}
		
		return response;
	}

}
