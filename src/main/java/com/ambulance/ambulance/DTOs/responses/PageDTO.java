package com.ambulance.ambulance.DTOs.responses;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@SuppressWarnings("rawtypes")
public class PageDTO {
    private int size;
    private int number;
    private int numberOfElements;
    private boolean first;
    private boolean last;

    public PageDTO(Page page) {
        this.size = page.getSize();
        this.number = page.getNumber();
        this.numberOfElements = page.getNumberOfElements();
        this.first = page.isFirst();
        this.last = page.isLast();
    }
}
