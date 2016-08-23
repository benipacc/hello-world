package com.ipacc.prodprice.bootexample.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipacc.prodprice.bootexample.data.entity.MessageEntity;

//NOTES:
//You can GET and POST to the collection of messages at the path provided
//Then PUT/PATCH/DELETE each resource at it's individual URI

@RepositoryRestResource(collectionResourceRel = "messages", path = "/messagesSDR")
public interface MessageSDRRepository extends PagingAndSortingRepository<MessageEntity, Long> {

}
