package com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.SpecificResultDtos;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.DoctorOutputDTO;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;

import java.util.List;

public class TopExpertisesDto{
        private String name;

        private ExpertiseLatinNames latinName;
        private List<DoctorOutputDTO> doctors;

        public TopExpertisesDto(String name, ExpertiseLatinNames latinName, List<DoctorOutputDTO> doctors) {
            this.name = name;
            this.latinName = latinName;
            this.doctors = doctors;
        }




        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ExpertiseLatinNames getLatinName() {
            return latinName;
        }

        public void setLatinName(ExpertiseLatinNames latinName) {
            this.latinName = latinName;
        }

        public List<DoctorOutputDTO> getDoctors() {
            return doctors;
        }

        public void setDoctors(List<DoctorOutputDTO> doctors) {
            this.doctors = doctors;
        }
}
