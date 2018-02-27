package com.app.processing.sale;

import java.math.BigDecimal;

import com.app.processing.dictionary.MessageType;
import com.app.processing.dictionary.OperationType;
import com.app.processing.product.Product;

public class SaleFactory {

	public Sale getSale(String line) {
		String[] a = line.split("\\|+");
		
		MessageType messageType = MessageType.getType(new Integer(a[0]));
		OperationType operationType = OperationType.getType(a[4]);
		Product product = new Product(a[1], new BigDecimal(a[2]));
		
		return new Sale(product, new Integer(a[3]), operationType, messageType);
	}
	
	public SaleBag getBag() {
		return new SaleBag();
	}
	
}
