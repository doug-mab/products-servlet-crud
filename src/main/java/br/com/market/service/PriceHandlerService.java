package br.com.market.service;

import java.math.BigDecimal;

public class PriceHandlerService {
	private String price;

	public boolean validatePrice(String rawPrice) {
		String processedPrice = null;
		if (rawPrice == null || rawPrice.isBlank())
			return false;
		if (!rawPrice.matches("^[0-9,\\.]+$"))
			return false;
		String noCollonPrice = rawPrice.replace(",", "").trim();
		if (noCollonPrice.contains(".")) {
			String[] splitPrice = noCollonPrice.split("\\.");
			if (splitPrice.length != 2)
				return false;
			System.out.println(splitPrice[1].substring(0, 2));
			splitPrice[1] = splitPrice[1].substring(0, 2);
			processedPrice = String.join(".", splitPrice);
		} else
			processedPrice = noCollonPrice + ".00";

		if (Double.parseDouble(processedPrice) < 0)
			return false;
		this.price = processedPrice;

		return true;
	}

	public BigDecimal parsePriceToBigDecimal(String strPrice) {
		if (!this.validatePrice(strPrice))
			throw new IllegalArgumentException("The given price is not a valid format.");
		return new BigDecimal(this.price);
	}
}
