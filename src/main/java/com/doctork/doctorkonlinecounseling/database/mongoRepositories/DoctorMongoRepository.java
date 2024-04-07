package com.doctork.doctorkonlinecounseling.database.mongoRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;



        import java.util.List;

@Repository
@EnableMongoRepositories
public interface DoctorMongoRepository extends MongoRepository<DoctorMongoEntity,Long> {


    List<DoctorMongoEntity> findAll();

}

