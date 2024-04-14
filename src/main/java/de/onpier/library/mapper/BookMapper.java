package de.onpier.library.mapper;

import de.onpier.library.domain.Book;
import de.onpier.library.model.dto.BookDto;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    BookDto toBookDto(Book book);
}
