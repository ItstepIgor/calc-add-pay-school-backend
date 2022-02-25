package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.ResultDTO;
import com.calcaddpayschoolbackend.entity.Result;
import com.calcaddpayschoolbackend.service.AddPayService;
import com.calcaddpayschoolbackend.service.BasicNormsService;
import com.calcaddpayschoolbackend.service.StaffListService;
import com.calcaddpayschoolbackend.service.TimeSheetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ResultDTOMapper implements EntityToDTOMapper<Result, ResultDTO> {

    private final ModelMapper modelMapper = new ModelMapper();

    private final AddPayService addPayService;

    private final StaffListService staffListService;

    private final TimeSheetService timeSheetService;

    private final BasicNormsService basicNormsService;

    public ResultDTOMapper(AddPayService addPayService, StaffListService staffListService, TimeSheetService timeSheetService, BasicNormsService basicNormsService) {
        super();
        this.addPayService = addPayService;
        this.staffListService = staffListService;
        this.timeSheetService = timeSheetService;
        this.basicNormsService = basicNormsService;
    }

    @Override
    public ResultDTO toDTO(Result entity, Object... args) {
        ResultDTO resultDTO = modelMapper.map(entity, ResultDTO.class);
        if (entity.getAddPays() != null) {
            resultDTO.setAddPayId(entity.getAddPays().getId());
        } else if (entity.getStaffList() != null) {
            resultDTO.setStaffListId(entity.getStaffList().getId());
        } else if (entity.getTimeSheets() != null) {
            resultDTO.setTimeSheetId(entity.getTimeSheets().getId());
        } else if (entity.getBasicNorms() != null) {
            resultDTO.setBasicNormsId(entity.getBasicNorms().getId());
        }
        return resultDTO;
    }

    @Override
    public Result toEntity(ResultDTO dto, Object... args) {
        Result result = modelMapper.map(dto, Result.class);
        result.setAddPays(addPayService.findAll(dto.getAddPayId()).get());
        result.setStaffList(staffListService.findAll(dto.getStaffListId()).get());
        result.setTimeSheets(timeSheetService.findAll(dto.getTimeSheetId()).get());
        result.setBasicNorms(basicNormsService.findAll(dto.getBasicNormsId()).get());
        return result;
    }
}
