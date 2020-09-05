package com.example.dbservice.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;

public interface PurchaseDetails {
    String getName();
    BigDecimal getTotal();
 }
