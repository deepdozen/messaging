package com.app.processing.dictionary;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public enum OperationType {
	ADD("A"), SUBTRACT("S"), MULTIPLY("M"), NO_OPERATION("N");
	
	private final String typeId;
	
	OperationType(String typeId){
		this.typeId = typeId;
	}
	
	public static OperationType getType(String typeId) {
		for (OperationType type: OperationType.values()) {
			if(type.typeId.equals(typeId)) {
				return type;
			}
		}
		return null;
	}
	
	public static BigDecimal applyOperation(OperationType type, BigDecimal value, BigDecimal amount) {
		
        switch (type) {
        case ADD:
            return amount.add(value);
        case SUBTRACT:
            return amount.subtract(value);
        case MULTIPLY:
            return amount.multiply(value).round(new MathContext(3, RoundingMode.HALF_UP));
        case NO_OPERATION:
        		return amount;
        }

        return amount;
	}
	
}
