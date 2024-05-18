package com.doctork.doctorkonlinecounseling.UseCase.Physician;

import com.doctork.doctorkonlinecounseling.UseCase.miscellaneous.PhysicianMiscellaneousServiceImpl;
import com.doctork.doctorkonlinecounseling.boundary.exit.Physician.PhysicianRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.Physician.PhysicianService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.configuration.RabbitMQConfig.RabbitMQConfig;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.database.mongoRepositories.PhysicianMongoRepository;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.RabbitMqMessages.PhysicianStatusChangeMessage;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhysicianServiceImpl implements PhysicianService {

    private final PhysicianRepository physicianRepository;

    private RabbitTemplate rabbitTemplate;
    private final PhysicianMiscellaneousServiceImpl doctorMiscellaneousService;
    private final PhysicianMongoRepository physicianMongoRepository;


    public PhysicianServiceImpl(RabbitTemplate rabbitTemplate, PhysicianMiscellaneousServiceImpl doctorMiscellaneousService, PhysicianRepository physicianRepository, PhysicianMongoRepository physicianMongoRepository) {
        this.physicianRepository = physicianRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.doctorMiscellaneousService = doctorMiscellaneousService;
        this.physicianMongoRepository = physicianMongoRepository;
    }

    @Override
    public Physician PhysicianCompleteProfile(Physician physician , Long nationalCode) {

        if(nationalCode == null)
            throw new IdInputException();

        physician = physicianRepository.PhysicianCompleteProfile(physician , nationalCode);


        return physician;

    }

    @Override
    public Physician PhysicianEditProfile(Physician physician , Long nationalCode) {

        if(nationalCode == null)
            throw new IdInputException();
        Long tokenNationalCode =((UserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNationalCode();
        if (!nationalCode.equals(tokenNationalCode))
            throw new AccessDeniedException("You do not have the required access");
        physician = physicianRepository.PhysicianEditProfile(physician , nationalCode);
        return physician;




    }

    @Override
    public List<Physician> topPhysician() {
        return physicianRepository.topPhysician();
    }

    @Override
    public Physician fetchPhysician(Long nationalCode) {
        if(nationalCode == null)
            throw new IdInputException();

        return physicianRepository.fetchPhysician(nationalCode);

    }


    @Override
    public Physician changeStatus(Long nationalCode, PhysicianStatus status) {

        if(nationalCode == null || status == null)
            throw new IdInputException();

        Long tokenNationalCode =((UserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNationalCode();
        if (!nationalCode.equals(tokenNationalCode))
            throw new AccessDeniedException("You do not have the required access");
        Physician physician = physicianRepository.changeStatus(nationalCode,status);

        PhysicianStatusChangeMessage message = new PhysicianStatusChangeMessage();
        message.setNationalCode(nationalCode);
        message.setStatus(status);
        message.setTimestamp(System.currentTimeMillis());

        rabbitTemplate.convertAndSend(RabbitMQConfig.MAIN_EXCHANGE, RabbitMQConfig.MAIN_QUEUE, message);
        return physician;


    }

    @Override
    public Physician changeState(Long nationalCode, State state) {

        if(nationalCode == null || state == null)
            throw new IdInputException();

        return physicianRepository.changeState(nationalCode,state);
    }

//    @Override
//    public List<Physician> getPhysiciansForSync() {
//
//
//        List<PhysicianMongoEntity> physicians = doctorMiscellaneousService.extractDoctors();
//
//        List<Physician> domainphysicians = new ArrayList<Physician>();
//
//
//        for (PhysicianMongoEntity doctor : physicians) {
//
//
//            try {
//                domainphysicians.add(elasticAdapter.setDoctorForSync(doctor));
//                physicianMongoRepository.save(doctor);
//            }catch (Exception e){
//                System.out.println(e);
//            }
//
//        }
//        return domainphysicians;

//    }


}
