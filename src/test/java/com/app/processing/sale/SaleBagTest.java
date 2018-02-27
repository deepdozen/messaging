package com.app.processing.sale;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.app.processing.dictionary.MessageType;
import com.app.processing.dictionary.OperationType;
import com.app.processing.product.Product;

public class SaleBagTest {
	
	SaleBag bag;
	Product apple;
	Product banana;

	@Before
	public void setUp() throws Exception {
		bag = new SaleBag();
		apple = new Product("apple", new BigDecimal("1.05"));
		banana = new Product("banana", new BigDecimal("2.10"));
	}

	@Test
	public final void testAddSale() {
		Sale sale = new Sale(apple, 1, OperationType.NO_OPERATION, MessageType.TYPE1);
		bag.add(sale, sale.getSalesCount());
		Sale sale1 = new Sale(apple, 2, OperationType.NO_OPERATION, MessageType.TYPE2);
		bag.add(sale1, sale1.getSalesCount());
		Sale sale2 = new Sale(banana, 4, OperationType.NO_OPERATION, MessageType.TYPE2);
		bag.add(sale2, sale2.getSalesCount());
		assertEquals(3, bag.getCount(sale));
	}

}
