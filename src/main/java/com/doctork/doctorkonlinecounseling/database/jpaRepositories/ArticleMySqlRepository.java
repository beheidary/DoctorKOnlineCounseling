package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.ArticleEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMySqlRepository extends JpaRepository<ArticleEntity, Long> {

    ArticleEntity findByIdAndPhysician (long articleId , PhysicianEntity physicianEntity);
    List<ArticleEntity> findAllByPhysicianAndState (PhysicianEntity physician , State state);
    List<ArticleEntity> findAllByState (State state);

}
