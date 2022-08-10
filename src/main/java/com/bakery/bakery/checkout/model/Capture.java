package com.bakery.bakery.checkout.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.bakery.bakery.checkout.dto.CaptureStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "checkout")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Capture {
    @Id
    @JoinColumn(name = "payment_id")
    private String paymentId;

    @JoinColumn(name = "payer_id")
    private String payerId;

    @JoinColumn(name = "create_at")
    private Date createAt;

    private String token;

    private CaptureStatus status;
}
