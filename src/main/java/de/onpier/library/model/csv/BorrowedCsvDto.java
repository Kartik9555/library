package de.onpier.library.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowedCsvDto {

    @CsvBindByName
    private String borrower;

    @CsvBindByName
    private String book;

    @CsvDate(value = "MM/dd/yyyy")
    @CsvBindByName(column = "Borrowed from")
    private LocalDate borrowedFrom;

    @CsvDate(value = "MM/dd/yyyy")
    @CsvBindByName(column = "Borrowed to")
    private LocalDate borrowedTo;
}
