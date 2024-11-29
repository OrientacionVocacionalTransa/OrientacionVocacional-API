package com.example.orientacionvocacionalapi.Mapper;

import com.example.orientacionvocacionalapi.dto.PurchaseCreateDTO;
import com.example.orientacionvocacionalapi.dto.PurchaseDTO;
import com.example.orientacionvocacionalapi.dto.PurchaseItemCreateDTO;
import com.example.orientacionvocacionalapi.dto.PurchaseItemDTO;
import com.example.orientacionvocacionalapi.model.entity.Plan;
import com.example.orientacionvocacionalapi.model.entity.Purchase;
import com.example.orientacionvocacionalapi.model.entity.PurchaseItem;
import com.example.orientacionvocacionalapi.model.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {
    private final ModelMapper modelMapper;

    public PurchaseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private PurchaseItem toPurchaseItemEntity(PurchaseItemCreateDTO purchaseItemDTO) {
        PurchaseItem item = modelMapper.map(purchaseItemDTO, PurchaseItem.class);
        Plan plan = new Plan();
        plan.setId(purchaseItemDTO.getPlanId());
        item.setPlan(plan);
        return item;
    }

    private PurchaseItemDTO toPurchaseItemDTO(PurchaseItem purchaseItem) {
        PurchaseItemDTO purchaseItemDTO = modelMapper.map(purchaseItem, PurchaseItemDTO.class);
        purchaseItemDTO.setPlanName(purchaseItem.getPlan().getName());
        return purchaseItemDTO;
    }


    public Purchase toPurchaseCreateDTO(PurchaseCreateDTO purchaseCreateDTO){
        Purchase purchase = modelMapper.map(purchaseCreateDTO, Purchase.class);
        User user = new User();
        user.setId(purchaseCreateDTO.getUserId());
        purchase.setUser(user);

        purchase.setItems(purchaseCreateDTO.getItems().stream()
                .map(this::toPurchaseItemEntity)
                .toList());

        return purchase;
    }

    public PurchaseDTO toPurchaseDTO(Purchase purchase){
        PurchaseDTO purchaseDTO = modelMapper.map(purchase, PurchaseDTO.class);
        purchaseDTO.setUser(purchase.getUser().getFirstName()+" "+purchase.getUser().getLastName());
        purchaseDTO.setItems(purchase.getItems().stream()
                .map(this::toPurchaseItemDTO)
                .toList());
        return purchaseDTO;
    }
}
