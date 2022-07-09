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

You can use [swagger-ui](https://swagger.io/tools/swagger-ui/)

```shell
GET /api/passengers - get list of all passengers
GET /api/passengers/{passportNumber} - get passenger by passport number
POST /api/passengers/create - create new passenger
PUT /api/passengers/update/{passportNumber} - update passenger by passport number
PUT /api/passengers/addToTrip/{passportNumber} - add passenger with given passport number to trip
DELETE /api/passengers/delete/{passportNumber} - delete passenger by passport number

GET /api/companies - get list of all companies
GET /api/companies/{name} - get company by name
POST /api/companies/create - create new company
POST /api/companies/{name}/addTrip - add new trip to company with given name
DELETE /api/companies/delete/{name} - delete company by name
```

