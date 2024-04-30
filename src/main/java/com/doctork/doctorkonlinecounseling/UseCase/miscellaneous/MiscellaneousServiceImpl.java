package com.doctork.doctorkonlinecounseling.UseCase.miscellaneous;

import com.doctork.doctorkonlinecounseling.boundary.exit.Miscellaneous.MiscellaneousRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.miscellaneous.MiscellaneousService;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Article;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.FAQ;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class MiscellaneousServiceImpl implements MiscellaneousService {

    ServletContext context;
    ObjectMapper objectMapper;
    MiscellaneousRepository miscellaneousRepository;


    public MiscellaneousServiceImpl(MiscellaneousRepository miscellaneousRepository,ObjectMapper objectMapper, ServletContext context) {
        this.context = context;
        this.miscellaneousRepository = miscellaneousRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<FAQ> getFaq() {

        try {

        String jsonContent = new String(Files.readAllBytes(Paths.get("/home/behnam/DoctorKOnlineCounseling/faq.json")));
            JsonNode jsonNode = objectMapper.readTree(jsonContent);
            List<FAQ> faqs = new ArrayList<>();

            for (JsonNode item: jsonNode){
                faqs.add( objectMapper.convertValue(item,FAQ.class));
            }
            return faqs;

        }catch (IOException e ){
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public List<Article> getAllArticles() {
        return miscellaneousRepository.getAllArticles();
    }
}
