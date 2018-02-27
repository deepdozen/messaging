package com.app.processing.sale;

import com.app.processing.dictionary.MessageType;
import com.app.processing.dictionary.OperationType;
import com.app.processing.product.Product;

public class Sale {

	private Product product;
	private int salesCount;
	private OperationType operationType;
	private MessageType messageType;
	
	public Sale(Product product, int salesCount, OperationType operationType, MessageType messageType) {
		this.product = product;
		this.salesCount = salesCount;
		this.operationType = operationType;
		this.messageType = messageType;
	}

	public Product getProduct() {
		return product;
	}

	public int getSalesCount() {
		return salesCount;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	@Override
	public String toString() {
		return String.format("%s ", product.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

}
