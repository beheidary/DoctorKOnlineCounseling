package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Counseling.OnlineCounselingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnlineCounselingMySqlRepository extends JpaRepository<OnlineCounselingEntity , Long> {
}
