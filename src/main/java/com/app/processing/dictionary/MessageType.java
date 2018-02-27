package com.app.processing.dictionary;

/*
 * Message Type 1 – contains the details of 1 sale E.g apple at 10p
 * Message Type 2 – contains the details of a sale and the number of occurrences of
 *	that sale. E.g 20 sales of apples at 10p each.
 * Message Type 3 – contains the details of a sale and an adjustment operation to be
 *	applied to all stored sales of this product type. Operations can be add, subtract, 
 *	or multiply e.g Add 20p apples would instruct your application to add 20p to each sale 
 *	of apples you have recorded.
 */

public enum MessageType {
	
	TYPE1(1), TYPE2(2), TYPE3(3);
	
	private final int typeId;
	
	MessageType(int typeId){
		this.typeId = typeId;
	}
	
	public static MessageType getType(int typeId) {
		for (MessageType type: MessageType.values()) {
			if(type.typeId == typeId) {
				return type;
			}
		}
		return null;
	}
	
}
