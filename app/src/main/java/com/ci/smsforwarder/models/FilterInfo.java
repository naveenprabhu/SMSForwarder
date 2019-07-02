package com.ci.smsforwarder.models;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FilterInfo implements Serializable {

    private String name;

    private String number;

    private boolean isfilterStatusOn;
}
