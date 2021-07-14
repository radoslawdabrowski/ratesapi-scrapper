package com.hsbc.ratesapiscrapper.client

import com.github.tomakehurst.wiremock.WireMockServer
import com.hsbc.ratesapiscrapper.CustomWireMockConfiguration
import com.hsbc.ratesapiscrapper.client.dto.RatesApiResponseDto
import com.hsbc.ratesapiscrapper.client.dto.TimeSeriesRequestDto
import com.hsbc.ratesapiscrapper.client.dto.TimeSeriesResponseDto
import com.hsbc.ratesapiscrapper.mocks.HistoricalDataMocks
import com.hsbc.ratesapiscrapper.mocks.TimeSeriesMocks
import com.hsbc.ratesapiscrapper.model.Symbol
import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import spock.lang.Specification

import java.time.LocalDate

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = [CustomWireMockConfiguration.class])
class RatesAPIClientTest extends Specification {

    private RatesAPIClient ratesAPIClient = Stub(RatesAPIClient)

    @Autowired
    private WireMockServer ratesApiClientService

    @BeforeEach
    void setup() throws IOException {
        ratesApiClientService = new WireMockServer(9561)
    }

    @ConditionalOnProperty(prefix = "external.rates.api", name = "plan", havingValue = "pro")
    def "should send request for timeseries API"() {
        given:
        TimeSeriesMocks.setupMockBooksResponse(ratesApiClientService)
        TimeSeriesRequestDto requestDto = new TimeSeriesRequestDto(
                LocalDate.now().minusYears(1),
                LocalDate.now()
        )
        when:
        TimeSeriesResponseDto responseDto = ratesAPIClient.fetchTimeSeriesData(requestDto)

        then:
        responseDto.getBase() == Symbol.EUR
    }

    def "should send request for historical data"() {
        given:
        HistoricalDataMocks.setupMockBooksResponse(ratesApiClientService)
        List<Symbol> symbols = List.of(Symbol.USD, Symbol.GBP, Symbol.HKD)

        when:
        RatesApiResponseDto responseDto = ratesAPIClient.fetchHistoricalData(LocalDate.of(2019, 5,1), symbols)

        then:
        responseDto.getBase() == Symbol.EUR
        responseDto.getRates().size() == 1
        responseDto.getRates().get(Symbol.USD) == 1.12004
        responseDto.getRates().get(Symbol.GBP) == 0.858177
        responseDto.getRates().get(Symbol.HKD) == 8.786772
    }

    def "should send request for latest data"() {
        HistoricalDataMocks.setupMockBooksResponse(ratesApiClientService)
        List<Symbol> symbols = List.of(Symbol.USD, Symbol.GBP, Symbol.HKD)

        when:
        RatesApiResponseDto responseDto = ratesAPIClient.fetchLatest(symbols)

        then:
        responseDto.getBase() == Symbol.EUR
        responseDto.getRates().size() == 1
        responseDto.getRates().get(Symbol.USD) == 1.12004
        responseDto.getRates().get(Symbol.GBP) == 0.858177
        responseDto.getRates().get(Symbol.HKD) == 8.786772
    }

}
