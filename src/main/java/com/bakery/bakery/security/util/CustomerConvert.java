package com.bakery.bakery.security.util;

import java.util.List;
import java.util.stream.Collectors;

import com.bakery.bakery.security.dto.CustomerRequest;
import com.bakery.bakery.security.dto.CustomerResponse;
import com.bakery.bakery.security.model.Customer;
import com.bakery.bakery.util.ConvertImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConvert implements ConvertImpl<Customer, CustomerRequest, CustomerResponse>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public Customer convertToEntity(CustomerRequest request) {
        return mapper.map(request, Customer.class);
    }

    @Override
    public CustomerResponse convertToResponse(Customer entity) {
        return mapper.map(entity, CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> convertToListResponse(List<Customer> entities) {
        return entities.stream().map(entity -> mapper.map(entity, CustomerResponse.class)).collect(Collectors.toList());
    }
    
}
