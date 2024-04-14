package de.onpier.library.model.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCsvDto {
    @CsvBindByName
    private String title;

    @CsvBindByName
    private String author;

    @CsvBindByName
    private String genre;

    @CsvBindByName
    private String publisher;
}
