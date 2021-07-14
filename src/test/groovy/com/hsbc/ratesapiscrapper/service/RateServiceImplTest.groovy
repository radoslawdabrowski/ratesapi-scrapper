package com.hsbc.ratesapiscrapper.service

import com.hsbc.ratesapiscrapper.model.Symbol
import com.hsbc.ratesapiscrapper.repository.RateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.time.LocalDate

@SpringBootTest
@ContextConfiguration(classes = [RateRepository])
class RateServiceImplTest extends Specification {

    @Autowired
    RateRepository rateRepository = Mock()
    RateService rateService = new RateServiceImpl(rateRepository)

    def "should save collection of rates"() {
        given:
        Map<LocalDate, Map<Symbol, Double>> map = new LinkedHashMap<>()
        Map<Symbol, Double> rates = new LinkedHashMap<Symbol, Double>() {{
            put(Symbol.USD, 0.86532d)
            put(Symbol.GBP, 0.85932d)
            put(Symbol.HKD, 0.43232d)
        }}

        when:
        map.put(LocalDate.now(), rates)

        then:
        rateService.save(map)
    }

    def "should retrieve rates for date and symbol"() {
    }

    def "should retrieve rates between dates"() {
    }

}
