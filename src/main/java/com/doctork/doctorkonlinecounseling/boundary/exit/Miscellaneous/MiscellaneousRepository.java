package com.doctork.doctorkonlinecounseling.boundary.exit.Miscellaneous;

import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Article;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Price;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Services;

import java.util.List;

public interface MiscellaneousRepository {

    List<Article> getAllArticles();

    Services addServices(Services services);

    Price addPrice(Price price, Long physicianId, Long ServicesId);
    Price editPrice(Long priceId , Price price);
    List<Price> readPrices(Long physicianId);

    Long deletePrice (Long priceId);


}
