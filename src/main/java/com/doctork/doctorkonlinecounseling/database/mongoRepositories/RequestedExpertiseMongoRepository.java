package com.doctork.doctorkonlinecounseling.database.mongoRepositories;


import com.doctork.doctorkonlinecounseling.database.entities.Expertise.RequestedExpertiseMongoEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableMongoRepositories
public interface RequestedExpertiseMongoRepository extends MongoRepository<RequestedExpertiseMongoEntity, ObjectId> {

    List<RequestedExpertiseMongoEntity> findByState (State state);

    RequestedExpertiseMongoEntity findByName (String name);

}
