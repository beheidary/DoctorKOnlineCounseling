package com.doctork.doctorkonlinecounseling;

import com.doctork.doctorkonlinecounseling.boundary.in.doctor.DoctorService;
import com.doctork.doctorkonlinecounseling.boundary.in.searchEngine.ElasticService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DoctorKOnlineCounselingApplication {

    public static void main(String[] args) {

//          SpringApplication.run(DoctorKOnlineCounselingApplication.class, args);

        ApplicationContext Apctx = SpringApplication.run(DoctorKOnlineCounselingApplication.class, args);

        DoctorService doctorService =  Apctx.getBean(DoctorService.class);

        doctorService.getDoctorsForSync();


    }

}
