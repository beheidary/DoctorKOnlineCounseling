package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DoctorSqlRepository extends CrudRepository<DoctorEntity, Long> {

//    List<DoctorEntity> queryFirst10BysavaDataTime (String birthdate);


}
