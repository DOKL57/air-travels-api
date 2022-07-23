# air-travels-api

[![License: WTFPL](https://img.shields.io/badge/License-WTFPL-brightgreen.svg)](http://www.wtfpl.net/about/)  
API for air travels service with Spring boot, Spring data, postgresql and flyway.

## Running the application locally

```shell
git clone https://github.com/DOKL57/air-travels-api.git
cd ./air-travels-api
docker-compose up -d
```



## Test API requests

![ERD](/erd.png?raw=true)

You can use [swagger-ui](https://swagger.io/tools/swagger-ui/) or [postman](https://www.postman.com/) to test the API.

```shell
GET /api/passengers - get list of all passengers
GET /api/passengers/{passportNumber} - get passenger by passport number
POST /api/passengers/create - create new passenger
PUT /api/passengers/update/{passportNumber} - update passenger by passport number
DELETE /api/passengers/delete/{passportNumber} - delete passenger by passport number

GET /api/companies - get list of all companies
GET /api/companies/{name} - get company by name
POST /api/companies/create - create new company
DELETE /api/companies/delete/{name} - delete company by name

GET /api/trips - get list of all trips
GET /api/trips/{id} - get trip by id
POST /api/trips/create - create new trip
PUT /api/trips/{id}/update - update trip by id

POST /api/registration/new - register a passenger to trip
```

