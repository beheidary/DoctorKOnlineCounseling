package com.doctork.doctorkonlinecounseling.UseCase.doctor;

import com.doctork.doctorkonlinecounseling.boundary.exit.doctor.DoctorRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.doctor.DoctorService;
import com.doctork.doctorkonlinecounseling.boundary.in.searchEngine.ElasticService;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.mongoRepositories.DoctorMongoRepository;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ObjectMapper objectMapper;

    private final DoctorMongoRepository doctorMongoRepository;

    private final ElasticService elasticService;


    public DoctorServiceImpl(ElasticService elasticService, DoctorRepository doctorRepository,ObjectMapper objectMapper,DoctorMongoRepository doctorMongoRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorMongoRepository = doctorMongoRepository;
        this.objectMapper = objectMapper;
        this.elasticService = elasticService;

    }

    @Override
    public Doctor addDoctor(UUID userId, Doctor doctor) {

        doctor.setSaveDateTime (LocalDateTime.now());
        doctor = doctorRepository.addDoctor(doctor);

        return doctor;

    }

    @Override
    public List<Doctor> getDoctorsForSync() {

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

        FindIterable<Document> extractDatasnappdoctor = collectionsnappdoctor.find();
        FindIterable<Document> extractDatadoctoreto = collectiondoctoreto.find();
        FindIterable<Document> extractDatadrnext = collectiondrnext.find();
        FindIterable<Document> extractDatadrdr = collectiondrdr.find();
        FindIterable<Document> extractDataboghrat = collectiondoctorsByExpertise.find();

        List<DoctorMongoEntity> doctors = doctorMongoRepository.findAll();

        List<Doctor> domaindoctors = new ArrayList<Doctor>();

        //doctoreto_boghrat_drnext_drdr_snappdoctor

        for (DoctorMongoEntity doctor : doctors) {

            String speciality;

            if (doctor.getSites().contains("doctoreto")){
                speciality = getdoctoreto(doctors,extractDatadoctoreto);
            } else if (doctor.getSites().contains("drnext")) {
                speciality = getdoctoreto(doctors,extractDatadrnext);
            }else if (doctor.getSites().contains("drdr")) {
                speciality = getdoctoreto(doctors,extractDatadrdr);
            }else if (doctor.getSites().contains("snappdoctor")) {
                speciality = getdoctoreto(doctors,extractDatasnappdoctor);
            }else if (doctor.getSites().contains("boghrat")) {
                speciality = getdoctoreto(doctors,extractDataboghrat);
            }




        }




        try (MongoCursor<Document> cursor = extractData.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();

                JsonNode node = reader.readValue(document.toJson());

                 int index = IntStream.range(0, doctors.size())
                        .filter(i -> doctors.get(i).getNezam().equals(node.get("doctorCode").asText().trim()))
                        .findFirst()
                         .orElse(-1);

                if (index >0){

                    try {

                        doctors.get(index).setSpeciality(node.get("expertises").get(0).get("value").asText());
                        domaindoctors.add(elasticService.setDoctorForSync(doctors.get(index)));
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }






            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return domaindoctors;

    }


    public String getsnappdoctor (List<DoctorMongoEntity> doctors ,FindIterable<Document> extractData ){
        return null;
    }
    public String getdoctoreto (List<DoctorMongoEntity> doctors ,FindIterable<Document> extractData ){
        return null;
    }
    public String getdrnext (List<DoctorMongoEntity> doctors ,FindIterable<Document> extractData ){
        return null;
    }
    public String getdrdr (List<DoctorMongoEntity> doctors ,FindIterable<Document> extractData ){
        return null;
    }
    public String getboghrat (List<DoctorMongoEntity> doctors ,FindIterable<Document> extractData ){
        return null;
    }


}
