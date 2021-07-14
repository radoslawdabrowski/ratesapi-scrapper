package com.hsbc.ratesapiscrapper.mocks

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.util.StreamUtils

import java.nio.charset.Charset

class TimeSeriesMocks {

    static void setupMockBooksResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlMatching("/timeseries"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(StreamUtils.copyToString(
                                        TimeSeriesMocks.class.getClassLoader()
                                                .getResourceAsStream("fixtures/historical.json"),
                                        Charset.defaultCharset())
                        )));
    }

}
