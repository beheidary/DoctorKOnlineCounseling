package com.doctork.doctorkonlinecounseling.boundary.in.miscellaneous;

import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Article;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.FAQ;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Price;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Services;

import java.util.List;

public interface MiscellaneousService {

    List<FAQ> getFaq();

    List<Article> getAllArticles();

    Services addServices(Services services);


    Price addPrice(Price price, Long physicianId, Long servicesId);
    Price editPrice(Long priceId , Price price);
    List<Price> readPrices(Long physicianId);

    Long deletePrice (Long priceId);


}
