package com.ambulance.ambulance.DTOs.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

@Getter
@SuppressWarnings("rawtypes")
public class SortDTO {
    private List<OrderDTO> orders;

    public SortDTO(Page page) {
        Iterator<Sort.Order> iterator = page.getSort().iterator();
        this.orders = new ArrayList<>();
        while (iterator.hasNext()) {
            Sort.Order order = iterator.next();
            String property = order.getProperty();
            String direction = order.getDirection().name();
            this.orders.add(new OrderDTO(property, direction));
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public class OrderDTO {
        private String property;
        private String direction;
    }
}
