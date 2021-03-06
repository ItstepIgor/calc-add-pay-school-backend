package com.calcaddpayschoolbackend.service.mapper;

import com.calcaddpayschoolbackend.dto.AddPayFundDTO;
import com.calcaddpayschoolbackend.entity.AddPayFund;
import com.calcaddpayschoolbackend.service.AddPayTypeService;
import com.calcaddpayschoolbackend.service.CalcSettingsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddPayFundDTOMapper implements EntityToDTOMapper<AddPayFund, AddPayFundDTO> {

    private final ModelMapper modelMapper = new ModelMapper();

    private final AddPayTypeService addPayTypeService;

    private final CalcSettingsService calcSettingsService;

    public AddPayFundDTOMapper(AddPayTypeService addPayTypeService, CalcSettingsService calcSettingsService) {
        super();
        this.addPayTypeService = addPayTypeService;
        this.calcSettingsService = calcSettingsService;
    }

    @Override
    public AddPayFundDTO toDTO(AddPayFund entity, Object... args) {
        AddPayFundDTO addPayFundDTO = modelMapper.map(entity, AddPayFundDTO.class);
//        if (entity.getAddPayTypes() != null) {
//            addPayFundDTO.setAddPayTypeId(entity.getAddPayTypes().getId());
//            addPayFundDTO.setAddPayTypeName(entity.getAddPayTypes().getAddPayTypeName());
//        }
//        if (entity.getCalcSettings() != null) {
//            addPayFundDTO.setCalcSettingId(entity.getCalcSettings().getId());
//            addPayFundDTO.setCalcDate(entity.getCalcSettings().getCalcDate());
//        }
        return addPayFundDTO;
    }

    @Override
    public AddPayFund toEntity(AddPayFundDTO dto, Object... args) {
        AddPayFund addPayFund = modelMapper.map(dto, AddPayFund.class);
//        addPayFund.setAddPayTypes(addPayTypeService.findAddPayTypeById(dto.getAddPayType().getId()));
        addPayFund.setCalcSettings(calcSettingsService.getMaxDateCalcSettings());
        System.out.println(addPayFund);
        return addPayFund;
    }
}
