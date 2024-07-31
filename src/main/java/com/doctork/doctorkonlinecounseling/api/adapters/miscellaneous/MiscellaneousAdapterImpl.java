package com.doctork.doctorkonlinecounseling.api.adapters.miscellaneous;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.ArticleOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.FAQOutputDto;
import com.doctork.doctorkonlinecounseling.api.mappers.MiscellaneousMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.miscellaneous.MiscellaneousService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MiscellaneousAdapterImpl implements MiscellaneousAdapter {

    private final MiscellaneousService miscellaneousService;
    private final MiscellaneousMapper miscellaneousMapper;

    public MiscellaneousAdapterImpl(MiscellaneousService miscellaneousService,MiscellaneousMapper miscellaneousMapper) {
        this.miscellaneousService = miscellaneousService;
        this.miscellaneousMapper = miscellaneousMapper;
    }

    @Override
    public List<FAQOutputDto> getFaq() {

         return miscellaneousMapper.faqModelToDto(miscellaneousService.getFaq());
    }

}
