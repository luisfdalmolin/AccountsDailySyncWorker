package com.luisfdalmolin.accountsdailysyncworker.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class CSVUtils {

    public static final String CSV_COLUMN_SEPARATOR = ";";
    public static final String CSV_LINE_ENDING_CHAR = "\n";

    public static List<String> readCSV(File file) throws IOException {
        return Files.readAllLines(Paths.get(file.toURI()), StandardCharsets.UTF_8)
                    .stream()
                    .skip(1)
                    .collect(Collectors.toList());
    }

    public static void writeCSV(File file, StringBuilder stringBuilder) throws IOException {
        Files.writeString(Paths.get(file.toURI()), stringBuilder, StandardOpenOption.APPEND);
    }

    public static String[] getColumns(String row) {
        return row.split(CSV_COLUMN_SEPARATOR);
    }
}
