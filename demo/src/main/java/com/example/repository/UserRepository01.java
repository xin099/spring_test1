package com.example.repository;

import com.example.dataobject.UserDO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository01 extends CrudRepository<UserDO,Integer> {
}
