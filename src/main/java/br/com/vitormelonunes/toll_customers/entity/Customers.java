package br.com.vitormelonunes.toll_customers.entity;

import br.com.vitormelonunes.toll_customers.controller.CustomerRequest;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;


import java.time.Instant;
import java.util.UUID;

@DynamoDbBean
public class Customers {

    private String licensePlateNumber;
    private String identificationDocument;
    private String name;
    private String phone;
    private Instant createdAt;

    public static Customers generateRegister(CustomerRequest customerRequest) {

        var  entity = new Customers();

        entity.setIdentificationDocument(customerRequest.identificationDocument());
        entity.setName(customerRequest.name());
        entity.setLicensePlateNumber(customerRequest.licensePlateNumber());
        entity.setPhone(customerRequest.phone());
        entity.setCreatedAt(Instant.now());

        return entity;
    }

    @DynamoDbAttribute("createdAt")
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @DynamoDbAttribute("phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("licensePlateNumber")
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    @DynamoDbAttribute("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("identificationDocument")
    public String getIdentificationDocument() {
        return identificationDocument;
    }

    public void setIdentificationDocument(String customerId) {
        this.identificationDocument = customerId;
    }
}
