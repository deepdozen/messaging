package com.app.processing.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.app.processing.dictionary.MessageType;
import com.app.processing.dictionary.OperationType;
import com.app.processing.sale.Sale;
import com.app.processing.sale.SaleBag;
import com.app.processing.sale.SaleFactory;

/**
 * 
 * @author Alex
 * 
 * Main class with processing logic;
 *
 */


public class MessageProcessor {

	private String file;
	private List<SaleBag> bagList;
	private List<Sale> saleList;
	private List<String> adjustmentList;
	private SaleFactory factory;

	public MessageProcessor(String file) {
		this.file = file;
		this.bagList = new ArrayList<>();
		this.saleList = new ArrayList<>();
		this.adjustmentList = new ArrayList<>();
		this.factory = new SaleFactory();
	}

	//populates raw data into sale list
	public void process() {
		String line = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while ((line = reader.readLine()) != null) {
				if (!line.contains("#")) {
					Sale sale = factory.getSale(line);
					if (sale.getMessageType() == MessageType.TYPE3) {
						adjustment(sale);
					}
					saleList.add(sale);
				}
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		loadData();
		showReports();
	}
	
	//applies adjustment to raw data stored in sale list
	private void adjustment(Sale sale) {
		int cnt = 0;
		Set<String> changes = new TreeSet<>();
		for (Sale s: saleList) {
			if(s.equals(sale)&&s.getMessageType()!=MessageType.TYPE3) {
				BigDecimal oldPrice = s.getProduct().getProductPrice();
				BigDecimal adjustment = sale.getProduct().getProductPrice();
				BigDecimal newPrice = OperationType.applyOperation(sale.getOperationType(), adjustment, oldPrice);
				s.getProduct().setProductPrice(newPrice);
				changes.add(String.format("%s -> %s", oldPrice, newPrice));
				cnt+=s.getSalesCount();
			}
		}
		adjustmentList.add(String.format("%s %s apllied to %d %s [Price changes: %10s]",sale.getOperationType(),sale.getProduct().getProductPrice(), cnt, sale.getProduct().getProductType(), String.join(", ", changes)));
	}
	
	//iterates over sale list
	//after every 10 messages new Bag created
	//Bag stores count of sales for each product type appears in 10 messages
	//all bags stored in BagList 
	private void loadData() {
		SaleBag bag = factory.getBag();
		int cnt = 0;
		for (Sale sale : saleList) {
			cnt++;
			if (cnt % 10 != 0) {
				if (sale.getMessageType() != MessageType.TYPE3) {
					bag.add(sale, sale.getSalesCount());
				}
			}else {
				bagList.add(bag);
				bag = factory.getBag();
			}
		}
		bagList.add(bag);
	}

	private void showReports() {
		int cnt = 0;
		for (SaleBag bag : bagList) {
			cnt++;
			if (cnt % 6 != 0) {
				System.out.println("-----------Report after 10 messages processed---------------");
				BigDecimal total = new BigDecimal(0);
				for (Sale sale : bag.keySet()) {
					total = total.add(new BigDecimal(bag.getCount(sale)).multiply(sale.getProduct().getProductPrice()));
					System.out.printf("%s x%d%n", sale, bag.getCount(sale));
				}
				System.out.println("-------------------------------------------------------------");
				System.out.printf("      Total: %s%n", total);
				System.out.println();
				System.out.println();
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println(
						"---------------Adjustment report after 50 messages processed------------------------");
				adjustmentList.forEach(System.out::println);
				break;
			}
		}
	}

	public static void main(String[] args) {
		String file = (args.length > 0) ? args[0] : "./resources/data.txt";
		MessageProcessor messageProcessor = new MessageProcessor(file);
		messageProcessor.process();
	}
}
