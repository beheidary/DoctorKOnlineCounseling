package com.doctork.doctorkonlinecounseling.UseCase.miscellaneous;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianMongoEntity;
import com.doctork.doctorkonlinecounseling.database.mongoRepositories.PhysicianMongoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class PhysicianMiscellaneousServiceImpl {

    private final ObjectMapper objectMapper;

    private final PhysicianMongoRepository physicianMongoRepository;


    public PhysicianMiscellaneousServiceImpl(ObjectMapper objectMapper, PhysicianMongoRepository physicianMongoRepository) {
        this.physicianMongoRepository = physicianMongoRepository;
        this.objectMapper = objectMapper;

    }


    public List<PhysicianMongoEntity> extractDoctors (){

        ObjectReader reader = objectMapper.reader(JsonNode.class);

        String connectionString = "mongodb://admin:admin@localhost:27017/admin?authMechanism=SCRAM-SHA-256";
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> collectionsnappdoctor = database.getCollection("SnapDoctorFullDetail");
        MongoCollection<Document> collectiondoctoreto = database.getCollection("DoctoreTo");
        MongoCollection<Document> collectiondrnext = database.getCollection("DoctoreNext");
        MongoCollection<Document> collectiondrdr = database.getCollection("doctorsByExpertise");
        MongoCollection<Document> collectiondoctorsByExpertise = database.getCollection("boghratDoctors");


        List<PhysicianMongoEntity> doctors = physicianMongoRepository.findAll();


            for (PhysicianMongoEntity doctor : doctors) {

                String speciality = "unknown";



                try {

                    if (doctor.getSites().contains("snappdoctor")) {
                        speciality = getsnappdoctor2(doctor, collectionsnappdoctor, reader, collectiondoctorsByExpertise, collectiondrdr, collectiondrnext, collectiondoctoreto);
                    }else if (doctor.getSites().contains("boghrat")) {
                        speciality = getboghrat2(doctor, collectiondoctorsByExpertise, reader, collectiondrdr, collectiondrnext, collectiondoctoreto);
                    }else if (doctor.getSites().contains("drdr")) {
                        speciality = getdrdr2(doctor, collectiondrdr, reader, collectiondrnext, collectiondoctoreto);
                    }else if (doctor.getSites().contains("drnext")) {
                        speciality = getdrnext2(doctor, collectiondrnext, reader, collectiondoctoreto);
                    }else if (doctor.getSites().contains("doctoreto")) {
                        speciality = getdoctoreto2(doctor, collectiondoctoreto, reader);
                    }

                    doctor.setSpeciality(speciality);

                }catch (Exception e ){
                    System.out.println("in main");
                    System.out.println(e);
                }



            }

        return doctors;

    }




    private String getsnappdoctor2(PhysicianMongoEntity doctor, MongoCollection<Document> collectionsnappdoctor, ObjectReader reader, MongoCollection<Document> collectiondoctorsByExpertise, MongoCollection<Document> collectiondrdr, MongoCollection<Document> collectiondrnext, MongoCollection<Document> collectiondoctoreto) throws JsonProcessingException {


        String regex = ".*" + Pattern.quote(doctor.getNezam()) + ".*";
        Document query = new Document("full_description", Pattern.compile(regex));


        MongoCursor<Document> cursor = collectionsnappdoctor.find(query).iterator();

        if (cursor.hasNext()) {
            Document document = cursor.next();
            JsonNode node = reader.readValue(document.toJson());

            try {
                return node.get("short_description").asText();
            }catch (Exception e ){
                if (doctor.getSites().contains("boghrat")) {
                   return getboghrat2(doctor, collectiondoctorsByExpertise, reader, collectiondrdr, collectiondrnext, collectiondoctoreto);
                }else if (doctor.getSites().contains("drdr")) {
                    return getdrdr2(doctor, collectiondrdr, reader, collectiondrnext, collectiondoctoreto);
                }else if (doctor.getSites().contains("drnext")) {
                    return getdrnext2(doctor, collectiondrnext, reader, collectiondoctoreto);
                }else if (doctor.getSites().contains("doctoreto")) {
                    return getdoctoreto2(doctor, collectiondoctoreto, reader);
                }

            }



        }
        return null;


    }

    private String getboghrat2(PhysicianMongoEntity doctor, MongoCollection<Document> collectiondoctorsByExpertise, ObjectReader reader, MongoCollection<Document> collectiondrdr, MongoCollection<Document> collectiondrnext, MongoCollection<Document> collectiondoctoreto) throws JsonProcessingException {

        Document query = new Document("doctorCode", doctor.getNezam());



        MongoCursor<Document> cursor = collectiondoctorsByExpertise.find(query).iterator();

        if (cursor.hasNext()) {
            Document document = cursor.next();
            JsonNode node = reader.readValue(document.toJson());

            try {

                String temp1 = node.get("expertises").get(0).get("key").asText();
                String temp2 = node.get("expertises").get(0).get("value").asText();

                return temp1 + " " + temp2;

            }catch (Exception e ){
                if (doctor.getSites().contains("drdr")) {
                    return getdrdr2(doctor, collectiondrdr, reader, collectiondrnext, collectiondoctoreto);
                }else if (doctor.getSites().contains("drnext")) {
                    return getdrnext2(doctor, collectiondrnext, reader, collectiondoctoreto);
                }else if (doctor.getSites().contains("doctoreto")) {
                    return getdoctoreto2(doctor, collectiondoctoreto, reader);
                }

            }



        }
        return null;

    }
    private String getdrdr2(PhysicianMongoEntity doctor, MongoCollection<Document> collectiondrdr, ObjectReader reader, MongoCollection<Document> collectiondrnext, MongoCollection<Document> collectiondoctoreto) throws JsonProcessingException {

        Document query = new Document("irimc_code", doctor.getNezam());



        MongoCursor<Document> cursor = collectiondrdr.find(query).iterator();

        if (cursor.hasNext()) {
            Document document = cursor.next();
            JsonNode node = reader.readValue(document.toJson());
            try {
                String temp1 = null;
                if (doctor.getSites().contains("drnext")) {
                    temp1 =  getdrnext2(doctor, collectiondrnext, reader, collectiondoctoreto);
                }else if (doctor.getSites().contains("doctoreto")) {
                    temp1 = getdoctoreto2(doctor, collectiondoctoreto, reader);
                }else if (temp1 == null){
                    temp1 =  node.get("specialities").get(0).asText();
                }
                return temp1;

            }catch (Exception e ){
                if (doctor.getSites().contains("drnext")) {
                    return getdrnext2(doctor, collectiondrnext, reader, collectiondoctoreto);
                }else if (doctor.getSites().contains("doctoreto")) {
                    return getdoctoreto2(doctor, collectiondoctoreto, reader);
                }

            }



        }
        return null;

    }

    private String getdrnext2(PhysicianMongoEntity doctor, MongoCollection<Document> collectiondrnext, ObjectReader reader, MongoCollection<Document> collectiondoctoreto) throws JsonProcessingException {

        Document query = new Document("nezamCode", doctor.getNezam());



        MongoCursor<Document> cursor = collectiondrnext.find(query).iterator();

        if (cursor.hasNext()) {
            Document document = cursor.next();
            JsonNode node = reader.readValue(document.toJson());
            try {

               return node.get("speciality").get("name").asText();

            }catch (Exception e ){
                if (doctor.getSites().contains("doctoreto")) {
                    return getdoctoreto2(doctor, collectiondoctoreto, reader);
                }

            }



        }
        return null;


    }

    private String getdoctoreto2(PhysicianMongoEntity doctor, MongoCollection<Document> collectiondoctoreto, ObjectReader reader) throws JsonProcessingException {


        Document query = new Document("medicalNumber", doctor.getNezam());

        MongoCursor<Document> cursor = collectiondoctoreto.find(query).iterator();

        if (cursor.hasNext()) {
            Document document = cursor.next();
            JsonNode node = reader.readValue(document.toJson());
            try {

                String temp1 = node.get("specialities").get(0).get("name").asText();
                String temp2 = node.get("specialities").get(0).get("typeName").asText();

                return temp2 + " " + temp1;

            }catch (Exception e ){
                System.out.println(e);
                System.out.println("in subFunctions");
                return null;

            }


        }
        return null;


    }




}


