package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.PhysicianOutputDto;

import java.util.List;

public class TopExpertisesDto{
        private String name;
           private String latinName;

        private String imageName;
        private List<PhysicianOutputDto> physicians;



        public TopExpertisesDto(String imageName, String name, String latinName, List<PhysicianOutputDto> physicians) {
            this.name = name;
            this.imageName = imageName;
            this.latinName = latinName;
            this.physicians = physicians;
        }




        public String getName() {
            return name;
        }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setName(String name) {
            this.name = name;
        }

        public String getLatinName() {
            return latinName;
        }

        public void setLatinName(String latinName) {
            this.latinName = latinName;
        }

        public List<PhysicianOutputDto> getPhysicians() {
            return physicians;
        }

        public void setPhysicians(List<PhysicianOutputDto> physicians) {
            this.physicians = physicians;
        }
}
