package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.AddPayDTO;
import com.calcaddpayschoolbackend.entity.AddPay;
import com.calcaddpayschoolbackend.service.AddPayTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddPayDTOMapper implements EntityToDTOMapper<AddPay, AddPayDTO> {

    private final ModelMapper modelMapper = new ModelMapper();

    private final AddPayTypeService addPayTypeService;

    public AddPayDTOMapper(AddPayTypeService addPayTypeService) {
        super();
        this.addPayTypeService = addPayTypeService;
    }

    @Override
    public AddPayDTO toDTO(AddPay entity, Object... args) {
        AddPayDTO addPayDTO = modelMapper.map(entity, AddPayDTO.class);
        if (entity.getAddPayTypes() != null) {
            addPayDTO.setAddPayTypeId(entity.getAddPayTypes().getId());
        }
        return addPayDTO;
    }

    @Override
    public AddPay toEntity(AddPayDTO dto, Object... args) {
        AddPay addPay = modelMapper.map(dto, AddPay.class);
        addPay.setAddPayTypes(addPayTypeService.findAddPayTypeById(dto.getAddPayTypeId()));
        return addPay;
    }
}
