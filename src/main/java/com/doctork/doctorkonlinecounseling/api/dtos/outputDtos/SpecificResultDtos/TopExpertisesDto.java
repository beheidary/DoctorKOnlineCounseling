package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.PhysicianOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;

import java.util.List;

public class TopExpertisesDto{
        private String name;

        private ExpertiseLatinNames latinName;
        private List<PhysicianOutputDto> physicians;

        public TopExpertisesDto(String name, ExpertiseLatinNames latinName, List<PhysicianOutputDto> physicians) {
            this.name = name;
            this.latinName = latinName;
            this.physicians = physicians;
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

        public List<PhysicianOutputDto> getPhysicians() {
            return physicians;
        }

        public void setPhysicians(List<PhysicianOutputDto> physicians) {
            this.physicians = physicians;
        }
}
