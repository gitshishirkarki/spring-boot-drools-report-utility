package com.shishir.reports.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CsvGenerator {
    public void generateCsv(List<Map<String, String>> dataList, String delimiter, String filePath) {
        if (!dataList.isEmpty()) {
            // Extract headers from the first map in the list
            String headers = dataList.get(0).keySet().stream()
                    .map(CsvGenerator::escapeSpecialCharacters)
                    .collect(Collectors.joining(delimiter));

            // Extract values for each map in the list
            String values = dataList.stream()
                    .map(map -> map.values().stream()
                            .map(CsvGenerator::escapeSpecialCharacters)
                            .collect(Collectors.joining(delimiter)))
                    .collect(Collectors.joining("\n"));

            // Combine headers and values into a CSV string
            String csvString = headers + "\n" + values;

            // Write the CSV string to a file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(csvString);
                log.info("CSV file successfully created at: {}", filePath);
            } catch (IOException e) {
                log.error("Error writing CSV file: ", e);
            }
        } else {
            log.warn("No data to convert to csv");
        }
    }

    private static String escapeSpecialCharacters(String s) {
        // Escape double quotes by doubling them
        return s.replace("\"", "\"\"");
    }
}

