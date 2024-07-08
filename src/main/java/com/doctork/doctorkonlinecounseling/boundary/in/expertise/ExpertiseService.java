package com.doctork.doctorkonlinecounseling.boundary.in.expertise;

import com.doctork.doctorkonlinecounseling.domain.Expertise.RequestedExpertise;
import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;

import java.util.List;
import java.util.Set;

public interface ExpertiseService {

    Expertise getExpertise(String latinName);



    List<Expertise> getExpertises();
    Expertise editExpertise(Long expertiseId , Expertise expertise);

    List<TopExpertises> findBestExpertisePhysicians();

    Set<Expertise> uploadExpertise (String physicianId, Set<Expertise> expertise);

    Set<Expertise> allPhysicianExpertises (String physicianId);

    void requestToAddExpertise (RequestedExpertise requestedExpertise);

    List<RequestedExpertise> waitingExpertises ();

    RequestedExpertise expertiseChangeState (RequestedExpertise requestedExpertise);






}
