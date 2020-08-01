package com.ifsaid.shark.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchParmas {
    private String name;
    private String id;
    private String type;
    private Object value;
}
