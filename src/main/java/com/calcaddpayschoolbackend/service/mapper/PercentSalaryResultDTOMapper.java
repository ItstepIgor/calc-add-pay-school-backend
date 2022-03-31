package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.PercentSalaryResultDTO;
import com.calcaddpayschoolbackend.entity.PercentSalaryResult;
import com.calcaddpayschoolbackend.service.PeopleService;
import com.calcaddpayschoolbackend.service.PercentSalaryService;
import com.calcaddpayschoolbackend.service.StaffListService;
import com.calcaddpayschoolbackend.service.TimeSheetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class PercentSalaryResultDTOMapper implements EntityToDTOMapper<PercentSalaryResult, PercentSalaryResultDTO> {

    private final ModelMapper modelMapper = new ModelMapper();

    private final StaffListService staffListService;

    private final TimeSheetService timeSheetService;

    private final PeopleService peopleService;

    private final PercentSalaryService percentSalaryService;

    public PercentSalaryResultDTOMapper(StaffListService staffListService, TimeSheetService timeSheetService, PeopleService peopleService, PercentSalaryService percentSalaryService) {
        this.staffListService = staffListService;
        this.timeSheetService = timeSheetService;
        this.peopleService = peopleService;
        this.percentSalaryService = percentSalaryService;
    }


    @Override
    public PercentSalaryResultDTO toDTO(PercentSalaryResult entity, Object... args) {
        PercentSalaryResultDTO percentSalaryResultDTO = modelMapper.map(entity, PercentSalaryResultDTO.class);
        if (entity.getStaffList() != null) {
            percentSalaryResultDTO.setStaffListId(entity.getStaffList().getId());
            percentSalaryResultDTO.setPeopleSurAndFirstName(peopleService.findFIOPeopleById(entity
                    .getStaffList()
                    .getPeople()
                    .getId()));
            percentSalaryResultDTO.setPositionName(entity.getStaffList().getPosition().getPositionName());
        }
        if (entity.getTimeSheets() != null) {
            percentSalaryResultDTO.setTimeSheetId(entity.getTimeSheets().getId());
            percentSalaryResultDTO.setCalcDate(entity.getTimeSheets().getCalcSettings().getCalcDate());
        }
        if (entity.getPercentSalary() != null) {
            percentSalaryResultDTO.setPercentSalaryId(entity.getPercentSalary().getId());
        }
        return percentSalaryResultDTO;
    }

    @Override
    public PercentSalaryResult toEntity(PercentSalaryResultDTO dto, Object... args) {
        PercentSalaryResult percentSalaryResult = modelMapper.map(dto, PercentSalaryResult.class);
        percentSalaryResult.setStaffList(staffListService.findStaffListById(dto.getStaffListId()));
        percentSalaryResult.setTimeSheets(timeSheetService.findTimeSheetById(dto.getTimeSheetId()));
        percentSalaryResult.setPercentSalary(percentSalaryService.findPercentSalaryById(dto.getPercentSalaryId()));
        return percentSalaryResult;
    }
}
