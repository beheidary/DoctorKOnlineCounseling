package com.doctork.doctorkonlinecounseling.database.jpaRepositories;


import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public interface UserMySqlRepository extends CrudRepository<UserEntity, Integer> {
        Optional<UserEntity> findByEmail(String email);

    }


