package br.edu.univas.si7.topicos.customer.enumeration;

import java.util.stream.Stream;

import br.edu.univas.si7.topicos.support.InvalidDataException;

public enum CustomerType {
    PHISICPERSON(0),
    JURIDICPERSON(1);

    private Integer type;

    CustomerType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
    
    public static CustomerType getType(Integer type) {
    	return Stream.of(CustomerType.values())
    			.filter(t -> t.getType() == type)
    			.findFirst()
    			.orElseThrow(() -> new InvalidDataException("Invalid unit: " + type));
    }
}