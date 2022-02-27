package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.StaffListDTO;
import com.calcaddpayschoolbackend.entity.StaffList;
import com.calcaddpayschoolbackend.service.PeopleService;
import com.calcaddpayschoolbackend.service.PositionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StaffListDTOMapper implements EntityToDTOMapper<StaffList, StaffListDTO> {


    private final ModelMapper modelMapper = new ModelMapper();

    private final PeopleService peopleService;
    private final PositionService positionService;

    public StaffListDTOMapper(PeopleService peopleService, PositionService positionService) {
        super();
        this.peopleService = peopleService;
        this.positionService = positionService;
    }

    @Override
    public StaffListDTO toDTO(StaffList entity, Object... args) {
        StaffListDTO staffListDTO = modelMapper.map(entity, StaffListDTO.class);
        if (entity.getPeople() != null) {
            staffListDTO.setPeopleId(entity.getPeople().getId());
        } else if (entity.getPosition() != null) {
            staffListDTO.setPositionId(entity.getPosition().getId());
        }
//        staffListDTO.setPeopleName(entity.getPeople().getFirstName() /*+ ' ' + entity.getPeople().getSurName() + ' '
//                + entity.getPeople().getPatronymic()*/);
//        staffListDTO.setPositionName(entity.getPosition().getPositionName());
        return staffListDTO;
    }

    @Override
    public StaffList toEntity(StaffListDTO dto, Object... args) {
        StaffList staffList = modelMapper.map(dto, StaffList.class);
        staffList.setPeople(peopleService.findPeopleById(dto.getPeopleId()));
        staffList.setPosition(positionService.findPositionById(dto.getPositionId()));
        return staffList;
    }
}
