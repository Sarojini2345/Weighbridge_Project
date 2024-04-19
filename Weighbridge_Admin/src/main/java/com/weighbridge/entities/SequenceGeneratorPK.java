package com.weighbridge.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class SequenceGeneratorPK implements Serializable {
    private String companyId;
    private String siteId;
}