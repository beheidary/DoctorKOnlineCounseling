package com.doctork.doctorkonlinecounseling.database.mappers;


import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.SicknessEntity;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhysicianDetailsEntityMapper {

    Sickness sicknessEntityToModel (SicknessEntity sicknessEntity);
    SicknessEntity sicknessModelToEntity (Sickness sickness);
    Set<Sickness> sicknessEntityToModel (Set<SicknessEntity> sicknessEntities);
    Set<Sickness> listSicknessEntityToSetModel(List<SicknessEntity> sicknessEntities);
    Set <SicknessEntity> sicknessModelToEntity (Set<Sickness> sicknesses);



}
