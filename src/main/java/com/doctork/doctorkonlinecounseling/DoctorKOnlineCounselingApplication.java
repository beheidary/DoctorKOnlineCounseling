package com.doctork.doctorkonlinecounseling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DoctorKOnlineCounselingApplication {

    public static void main(String[] args) {

          SpringApplication.run(DoctorKOnlineCounselingApplication.class, args);

//        ApplicationContext Apctx = SpringApplication.run(DoctorKOnlineCounselingApplication.class, args);

//        PhysicianService doctorService =  Apctx.getBean(PhysicianService.class);

//        doctorService.getPhysiciansForSync();


    }

}
