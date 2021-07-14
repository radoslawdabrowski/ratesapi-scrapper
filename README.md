# Rate API Scrapper 

## Build & Deployment
### Requirements
- Rates API key
- jdk 11

### Setting environment
Available environment variables to set:

1. db_name
2. db_username
3. db_passowrd
4. access_key
5. log_level
6. rates_api_plan

Only access key is required 

### Build

```./gradlew clean build```

### Run
```./gradlew bootRun```

Application is available on port `8080`

## Endpoints

    GET /api/v1/rates

Fetching data from external API and saving records in local DB.
Optional parameters are dates in format `yyyy-mm-dd` e.g.

    GET /api/v1/rates?dateFrom=2020-07-01&dateTo=2021-07-01

Dates will be convert to first day of month if `rates_api_plan` is set to free

    GET /{date}

Get rates for specific date from local datastore. Optional parameter `symbol` with default value set to `GBP` is currency symbol. e.g.
    
    GET /2020-07-01?symbol=HKD
