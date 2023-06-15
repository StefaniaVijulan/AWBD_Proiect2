package com.awbd.discount.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Discount {
    private int value;
    private int period;
    private String versionId;
}
