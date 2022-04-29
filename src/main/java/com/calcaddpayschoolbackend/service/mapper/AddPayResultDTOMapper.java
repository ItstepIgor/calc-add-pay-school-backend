package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.AddPayResultDTO;
import com.calcaddpayschoolbackend.entity.AddPayResult;
import com.calcaddpayschoolbackend.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddPayResultDTOMapper implements EntityToDTOMapper<AddPayResult, AddPayResultDTO> {

    private final ModelMapper modelMapper = new ModelMapper();

    private final AddPayService addPayService;


    private final TimeSheetService timeSheetService;

    private final PeopleService peopleService;

    private final BasicNormsService basicNormsService;

    public AddPayResultDTOMapper(AddPayService addPayService, TimeSheetService timeSheetService, PeopleService peopleService, BasicNormsService basicNormsService) {
        super();
        this.addPayService = addPayService;
        this.timeSheetService = timeSheetService;
        this.peopleService = peopleService;
        this.basicNormsService = basicNormsService;
    }

    @Override
    public AddPayResultDTO toDTO(AddPayResult entity, Object... args) {
        AddPayResultDTO addPayResultDTO = modelMapper.map(entity, AddPayResultDTO.class);
        if (entity.getAddPay() != null) {
            addPayResultDTO.setAddPayId(entity.getAddPay().getId());
            addPayResultDTO.setAddPayCode(entity.getAddPay().getAddPayCode());
        }
        if (entity.getTimeSheets() != null) {
            addPayResultDTO.setStaffListId(entity.getTimeSheets().getStaffList().getId());
            addPayResultDTO.setPeopleSurAndFirstName(peopleService.findFIOPeopleById(entity
                    .getTimeSheets()
                    .getStaffList()
                    .getPeople()
                    .getId()));
            addPayResultDTO.setPositionName(entity.getTimeSheets().getStaffList().getPosition().getPositionName());
            addPayResultDTO.setTimeSheetId(entity.getTimeSheets().getId());
            addPayResultDTO.setCalcDate(entity.getTimeSheets().getCalcSettings().getCalcDate());
        }
        if (entity.getBasicNorms() != null) {
            addPayResultDTO.setBasicNormsId(entity.getBasicNorms().getId());
            addPayResultDTO.setBasicNormName(entity.getBasicNorms().getBasicNormName());
        }
        return addPayResultDTO;
    }

    @Override
    public AddPayResult toEntity(AddPayResultDTO dto, Object... args) {
        AddPayResult addPayResult = modelMapper.map(dto, AddPayResult.class);
        addPayResult.setTimeSheets(timeSheetService.getMaxTimeSheetForStaffList(dto.getStaffListId()));
        addPayResult.setBasicNorms(basicNormsService.getMaxDateBasicNorms());
        addPayResult.setAddPay(addPayService.findAddPayById(dto.getAddPayId()));
        return addPayResult;
    }
}
