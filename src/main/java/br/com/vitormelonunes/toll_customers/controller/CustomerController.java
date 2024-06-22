package br.com.vitormelonunes.toll_customers.controller;

import br.com.vitormelonunes.toll_customers.entity.Customers;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.List;

@RestController
@RequestMapping("v1/customers")
public class CustomerController {

    private final DynamoDbTemplate dynamoDbTemplate;

    public CustomerController(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody CustomerRequest customerRequest) {
        var customer = Customers.generateRegister(customerRequest);
        dynamoDbTemplate.save(customer);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{licensePlateNumber}")
    public ResponseEntity<List<Customers>> listCustomersBylicensePlateNumber(@PathVariable("licensePlateNumber") String licensePlateNumber) {

        var key = Key.builder().partitionValue(licensePlateNumber).build();

        var conditional = QueryConditional.keyEqualTo(key);

        var customersFound = dynamoDbTemplate.query(QueryEnhancedRequest.builder()
                        .queryConditional(conditional).build(),
                Customers.class);

        return ResponseEntity.ok(customersFound.items().stream().toList());
    }
}
