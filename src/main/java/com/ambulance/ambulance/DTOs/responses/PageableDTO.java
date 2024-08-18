package com.ambulance.ambulance.DTOs.responses;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageableDTO {
    private PageDTO page;
    private int totalPages;
    private long totalElements;

    public PageableDTO(Page page){
        this.page = new PageDTO(page);
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}
