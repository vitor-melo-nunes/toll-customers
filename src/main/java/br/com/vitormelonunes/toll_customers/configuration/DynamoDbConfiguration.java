package br.com.vitormelonunes.toll_customers.configuration;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

import java.net.URI;


@Configuration
public class DynamoDbConfiguration {

    @Bean
    public DynamoDbClient dynamoDbClient() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create("fakeAccessKey", "fakeSecretKey");

        return DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:4566"))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.SA_EAST_1)
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient())
                .build();
    }

    @Bean
    public DynamoDbTemplate dynamoDbTemplate() {
        return new DynamoDbTemplate(getDynamoDbEnhancedClient());
    }

}