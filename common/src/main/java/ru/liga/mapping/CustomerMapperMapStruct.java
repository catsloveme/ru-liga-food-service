package ru.liga.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.liga.dto.response.CustomerResponse;
import ru.liga.entity.Customer;
import java.util.List;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomerMapperMapStruct {

    CustomerMapperMapStruct INSTANCE = Mappers.getMapper( CustomerMapperMapStruct.class );
    @Mapping(source = "customer.id", target = "id")
    @Mapping(source = "customer.phone", target = "phone")
    @Mapping(source = "customer.email", target = "email")
    @Mapping(source = "customer.address", target = "address")
    CustomerResponse customerToCustomerResponse(Customer customer);

    @Mapping(source = "customer.id", target = "id")
    @Mapping(source = "customer.phone", target = "phone")
    @Mapping(source = "customer.email", target = "email")
    @Mapping(source = "customer.address", target = "address")
    List<CustomerResponse> customersToCustomersResponse(List<Customer> customer);
}
