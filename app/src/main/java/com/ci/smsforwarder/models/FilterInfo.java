package com.ci.smsforwarder.models;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FilterInfo implements Serializable {

    private String name;

    private String number;

    private boolean isfilterStatusOn;
}
