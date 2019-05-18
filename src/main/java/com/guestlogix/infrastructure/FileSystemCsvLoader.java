package com.guestlogix.infrastructure;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class FileSystemCsvLoader implements CsvLoader {

    public <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();

            MappingIterator<T> readValues =
                    mapper.readerWithTypedSchemaFor(type)
                    .with(bootstrapSchema)
                    .readValues(file);

            return readValues.readAll();
        } catch (Exception e) {
            log.error("Error occurred loading file " + fileName, e);
            return Collections.emptyList();
        }
    }
}