package edu.oleks.security.Item;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
@author   oleksandra
@project   security
@class  ItemRepository
@version  1.0.0
@since 19.02.2025 - 22.53
*/
@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {
}
