package com.app.processing.dictionary;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class OperationTypeTest {

	@Test
	public final void testGetType() {
		assertEquals(OperationType.ADD, OperationType.getType("A"));
		assertEquals(OperationType.MULTIPLY, OperationType.getType("M"));
		assertEquals(OperationType.SUBTRACT, OperationType.getType("S"));
		assertEquals(OperationType.NO_OPERATION, OperationType.getType("N"));
	}

	@Test
	public final void testApplyOperation() {
		BigDecimal expected = new BigDecimal("1.25");
		assertEquals(expected,OperationType.applyOperation(OperationType.ADD, new BigDecimal("1.00"), new BigDecimal("0.25")));
		assertEquals(expected,OperationType.applyOperation(OperationType.MULTIPLY, new BigDecimal("5.00"), new BigDecimal("0.25")));
		assertEquals(expected,OperationType.applyOperation(OperationType.SUBTRACT, new BigDecimal("0.75"), new BigDecimal("2.00")));
		assertEquals(expected,OperationType.applyOperation(OperationType.NO_OPERATION, new BigDecimal("0.75"), new BigDecimal("1.25")));
	}

}
