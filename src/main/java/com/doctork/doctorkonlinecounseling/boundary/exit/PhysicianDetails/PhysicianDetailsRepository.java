package com.doctork.doctorkonlinecounseling.boundary.exit.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;

import java.util.List;
import java.util.Set;

public interface PhysicianDetailsRepository {

    Sickness crateSickness (Sickness sickness);
    Set<Sickness> uploadSickness (Long physicianId, Set<Sickness> sickness);

    Set<Sickness> allSicknesses();
    Set<Sickness> allPhysicianSicknesses(Long physicianId);

}
