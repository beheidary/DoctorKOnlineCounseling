package com.doctork.doctorkonlinecounseling.database.entities.Expertise;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@Document(collection = "RequestedExpertise")
public class RequestedExpertiseMongoEntity {
        @Override
        public String toString() {
            return "DoctorMongoEntity{" +
                    "_id=" + _id +
                    ", RequestedFromPid=" + pid +
                    ", name='" + name + '\'' +
                    ", state='" + state + '\'' +
                    ", description='" + description + '\'' +
                    ", requestedAt='" + createdAt + '\'' +
                    ", reviewedAt='" + updatedAt + '\'' +
                    ", latinName='" + latinName + '\'' +
                    '}';
        }

        @Id
        private org.bson.types.ObjectId _id;

        @Field(name = "RequestedFromPid")
        private String pid;
        @Field(name = "name")
        private String name;
        @Field(name = "state")
        private State state;
        @Field(name = "created_at")
        private LocalDateTime createdAt;

        @Field(name = "updated_at")
        private LocalDateTime updatedAt;
        @Field(name = "description")
        private String description;

        @Field(name = "latinName")
        private String latinName;





        public RequestedExpertiseMongoEntity(String pid, String name) {
            this.pid = pid;
            this.name = name;
            this.createdAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
            this.state = State.Waiting;
            this.description = "";
            this.latinName = "";
        }
        public RequestedExpertiseMongoEntity() {
        }


        public ObjectId get_id() {
            return _id;
        }

        public void set_id(ObjectId _id) {
            this._id = _id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public String getLatinName() {
            return latinName;
        }

        public void setLatinName(String latinName) {
            this.latinName = latinName;
        }

    public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getDescription() {
                return description;
            }

        public void setDescription(String description) {
            this.description = description;
        }
}
