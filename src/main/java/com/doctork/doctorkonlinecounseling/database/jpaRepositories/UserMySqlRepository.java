package com.doctork.doctorkonlinecounseling.database.jpaRepositories;


import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
    public interface UserMySqlRepository extends CrudRepository<UserEntity, UUID> {
        Optional<UserEntity> findByEmail(String email);

        UserEntity findUserEntityById (UUID uuid);

    }


