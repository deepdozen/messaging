package com.app.processing.sale;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.app.processing.dictionary.MessageType;
import com.app.processing.dictionary.OperationType;

public class SaleFactoryTest {

	SaleFactory factory;
	
	@Before
	public void setUp() throws Exception {
		factory = new SaleFactory();
	}

	@Test
	public final void testGetSale() {
		String line = "1|apple|1.25|1|N";
		Sale sale = factory.getSale(line);
		assertEquals("apple", sale.getProduct().getProductType());
		assertEquals(MessageType.TYPE1, sale.getMessageType());
		assertEquals(OperationType.NO_OPERATION, sale.getOperationType());
		assertEquals(new BigDecimal("1.25"), sale.getProduct().getProductPrice());
	}
	

}
