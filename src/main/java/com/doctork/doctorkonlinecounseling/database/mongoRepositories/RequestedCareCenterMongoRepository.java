package com.doctork.doctorkonlinecounseling.database.mongoRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.CareCenter.RequestedCareCenterMongoEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RequestedCareCenterMongoRepository extends MongoRepository<RequestedCareCenterMongoEntity , ObjectId> {

    RequestedCareCenterMongoEntity findByCenterName (String centerName);

    List<RequestedCareCenterMongoEntity> findByState (State state);
}
