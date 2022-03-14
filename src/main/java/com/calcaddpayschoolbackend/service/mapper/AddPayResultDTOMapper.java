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

    private final StaffListService staffListService;

    private final TimeSheetService timeSheetService;

    private final BasicNormsService basicNormsService;

    private final AddPayResultService addPayResultService;

    public AddPayResultDTOMapper(AddPayService addPayService, StaffListService staffListService, TimeSheetService timeSheetService, BasicNormsService basicNormsService, AddPayResultService addPayResultService) {
        super();
        this.addPayService = addPayService;
        this.staffListService = staffListService;
        this.timeSheetService = timeSheetService;
        this.basicNormsService = basicNormsService;
        this.addPayResultService = addPayResultService;
    }

    @Override
    public AddPayResultDTO toDTO(AddPayResult entity, Object... args) {
        AddPayResultDTO addPayResultDTO = modelMapper.map(entity, AddPayResultDTO.class);
        if (entity.getAddPay() != null) {
            addPayResultDTO.setAddPayId(entity.getAddPay().getId());
            addPayResultDTO.setAddPayCode(entity.getAddPay().getAddPayCode());

        }
        if (entity.getStaffList() != null) {
            addPayResultDTO.setStaffListId(entity.getStaffList().getId());
            addPayResultDTO.setPeopleSurAndFirstName(entity.getStaffList().getPeople().getSurName() + " "
                    + entity.getStaffList().getPeople().getFirstName());
            addPayResultDTO.setPositionName(entity.getStaffList().getPosition().getPositionName());
        }
        if (entity.getTimeSheets() != null) {
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
        addPayResult.setStaffList(staffListService.findStaffListById(dto.getStaffListId()));
        addPayResult.setTimeSheets(timeSheetService.getMaxTimeSheetForStaffList(dto.getStaffListId()));
        addPayResult.setSum(addPayResultService.calcSumAddPay(dto));
        addPayResult.setBasicNorms(basicNormsService.getMaxDateBasicNorms());
        addPayResult.setAddPay(addPayService.findAddPayById(dto.getAddPayId()));
        return addPayResult;
    }
}
