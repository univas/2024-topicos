package br.edu.univas.si7.topicos.delivery;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"FAST", "WITHDRAW", "BASIC"})
public enum DeliveriesType {
    FAST,
    WITHDRAW,
    BASIC
}


