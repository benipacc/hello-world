package com.ipacc.prodprice.bootexample.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.ipacc.prodprice.bootexample.data.entity.MessageEntity;

//NOTES:
//Automatically uses the H2 database because it's in the dependencies

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {

}
