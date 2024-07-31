package com.doctork.doctorkonlinecounseling.boundary.exit.Miscellaneous;

import com.doctork.doctorkonlinecounseling.database.entities.user.OtpDetailsEntity;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Article;

import java.util.List;
import java.util.Optional;

public interface MiscellaneousRepository {

    Optional<OtpDetailsEntity> findLatestByMobileNumber(String mobileNumber);



}
