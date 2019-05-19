package com.guestlogix.infrastructure;

import com.guestlogix.model.Airline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class FileSystemCsvLoaderTest {

    @Autowired
    private CsvLoader csvLoader;

    @Test
    public void loadingAndParsingInListAirlinesCsvFile() {

        Airline airline = new Airline();
        airline.setName("Air China");
        airline.setTwoDigitCode("AC");
        airline.setThreeDigitCode("CCA");
        airline.setCountry("China");

        List<Airline> airlineListFromCsv = csvLoader.loadObjectList(Airline.class, "data/airlines.csv");

        assertThat(airlineListFromCsv).hasSize(6);

        assertThat(airline).isEqualToComparingFieldByField(airlineListFromCsv.get(0));
    }


    @Configuration
    @EnableConfigurationProperties
    @Import({FileSystemCsvLoader.class})
    public static class Config {

    }
}
