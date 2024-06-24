# Toll Customers
[This repository is part of the Toll Free Flow project](https://github.com/vitor-melo-nunes/toll-freeflow)

The Toll Customers Service handles all customer-related data. This includes managing customer information and retrieving customer details for notifications.

## Features
- Add and manage customer information
- Retrieve customer details

### Prerequisites
- Docker
- Brownser

### Build and Run
1. Run ```docker-compose up```

2. Run ```docker exec -it localstack aws --endpoint="http://localhost:4566" dynamodb create-table --region "sa-east-1" --table-name "customers" --attribute-definitions "AttributeName=licensePlateNumber,AttributeType=S" "AttributeName=identificationDocument,AttributeType=S" --key-schema "AttributeName=licensePlateNumber,KeyType=HASH" "AttributeName=identificationDocument,KeyType=RANGE" --provisioned-throughput "ReadCapacityUnits=5,WriteCapacityUnits=5"```
