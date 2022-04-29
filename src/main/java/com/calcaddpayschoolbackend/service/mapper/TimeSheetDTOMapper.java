package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.TimeSheetDTO;
import com.calcaddpayschoolbackend.entity.TimeSheet;
import com.calcaddpayschoolbackend.service.CalcSettingsService;
import com.calcaddpayschoolbackend.service.PeopleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TimeSheetDTOMapper implements EntityToDTOMapper<TimeSheet, TimeSheetDTO> {

    private final ModelMapper modelMapper = new ModelMapper();

    private final PeopleService peopleService;

    private final CalcSettingsService calcSettingsService;

    public TimeSheetDTOMapper(PeopleService peopleService, CalcSettingsService calcSettingsService) {
        super();
        this.peopleService = peopleService;
        this.calcSettingsService = calcSettingsService;
    }

    @Override
    public TimeSheetDTO toDTO(TimeSheet entity, Object... args) {
        TimeSheetDTO timeSheetDTO = modelMapper.map(entity, TimeSheetDTO.class);
        if (entity.getStaffList().getPeople() != null) {
            timeSheetDTO.setPeopleId(entity.getStaffList().getPeople().getId());
            timeSheetDTO.setPeopleSurAndFirstName(peopleService.findFIOPeopleById(entity.getStaffList().getPeople().getId()));
/*                    entity.getPeople().getSurName() + " "
                    + entity.getPeople().getFirstName() + " " + entity.getPeople().getPatronymic());*/
        }
        if (entity.getCalcSettings() != null) {
            timeSheetDTO.setCalcSettingsId(entity.getCalcSettings().getId());
            timeSheetDTO.setCalcDate(entity.getCalcSettings().getCalcDate());
        }
        return timeSheetDTO;
    }

    @Override
    public TimeSheet toEntity(TimeSheetDTO dto, Object... args) {
        TimeSheet timeSheet = modelMapper.map(dto, TimeSheet.class);
        timeSheet.setCalcSettings(calcSettingsService.getMaxDateCalcSettings());
        return timeSheet;
    }
}
