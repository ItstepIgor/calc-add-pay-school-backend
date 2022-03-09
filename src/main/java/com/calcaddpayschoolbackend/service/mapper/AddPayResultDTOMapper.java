package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.AddPayResultDTO;
import com.calcaddpayschoolbackend.entity.AddPayResult;
import com.calcaddpayschoolbackend.service.AddPayService;
import com.calcaddpayschoolbackend.service.BasicNormsService;
import com.calcaddpayschoolbackend.service.StaffListService;
import com.calcaddpayschoolbackend.service.TimeSheetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddPayResultDTOMapper implements EntityToDTOMapper<AddPayResult, AddPayResultDTO> {

    private final ModelMapper modelMapper = new ModelMapper();

    private final AddPayService addPayService;

    private final StaffListService staffListService;

    private final TimeSheetService timeSheetService;

    private final BasicNormsService basicNormsService;

    public AddPayResultDTOMapper(AddPayService addPayService, StaffListService staffListService, TimeSheetService timeSheetService, BasicNormsService basicNormsService) {
        super();
        this.addPayService = addPayService;
        this.staffListService = staffListService;
        this.timeSheetService = timeSheetService;
        this.basicNormsService = basicNormsService;
    }

    @Override
    public AddPayResultDTO toDTO(AddPayResult entity, Object... args) {
        AddPayResultDTO addPayResultDTO = modelMapper.map(entity, AddPayResultDTO.class);
        if (entity.getAddPays() != null) {
            addPayResultDTO.setAddPayId(entity.getAddPays().getId());
        }
        if (entity.getStaffList() != null) {
            addPayResultDTO.setStaffListId(entity.getStaffList().getId());
            addPayResultDTO.setPeopleSurAndFirstName(entity.getStaffList().getPeople().getSurName() + " "
                    + entity.getStaffList().getPeople().getFirstName());
        }
        if (entity.getTimeSheets() != null) {
            addPayResultDTO.setTimeSheetId(entity.getTimeSheets().getId());
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
        addPayResult.setAddPays(addPayService.findAddPayById(dto.getAddPayId()));
        addPayResult.setStaffList(staffListService.findStaffListById(dto.getStaffListId()));
        addPayResult.setTimeSheets(timeSheetService.findTimeSheetById(dto.getTimeSheetId()));
        addPayResult.setBasicNorms(basicNormsService.findBasicNormsById(dto.getBasicNormsId()));
        return addPayResult;
    }
}
