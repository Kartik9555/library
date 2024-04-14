package de.onpier.library.service;

import de.onpier.library.model.csv.BookCsvDto;
import de.onpier.library.model.csv.BorrowedCsvDto;
import de.onpier.library.model.csv.UserCsvDto;
import java.io.File;
import java.util.List;

public interface CsvService {
    List<UserCsvDto> convertUserCSV(File csvFile);

    List<BorrowedCsvDto> convertBorrowedCSV(File csvFile);

    List<BookCsvDto> convertBookCSV(File csvFile);
}