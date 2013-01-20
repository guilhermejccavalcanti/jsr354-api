package net.java.javamoney.ri.samples;

import java.io.IOException;
import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatter;
import javax.money.format.AmountParser;
import javax.money.format.StylableAmountFormatter;
import javax.money.format.StylableAmountParser;
import javax.money.format.common.ParseException;

public class Samples {

	public static void main(String[] args) {
		// Creating one
		CurrencyUnit currency = Monetary.getCurrencyUnitProvider().get(
				"ISO4217", "CHF");
		MonetaryAmount amount = Monetary.getMonetaryAmountFactory().get(
				currency, 1.0d);

		// Using parsers
		try {
			AmountParser parser = Monetary.getAmountParserFactory()
					.getAmountParser(Locale.GERMANY);
			MonetaryAmount amount1 = parser.parse("CFH 123.45");

			StylableAmountParser locParser = Monetary
					.getAmountParserFactory().getLocalizableAmountParser();

			MonetaryAmount amount2 = locParser
					.parse("CFH 123.45", Locale.CHINA);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Using formatters
		try {
			AmountFormatter formatter = Monetary.getAmountFormatterFactory()
					.getAmountFormatter(Locale.GERMANY);
			String formatted = formatter.print(amount);
			StylableAmountFormatter locFormatter = Monetary
					.getAmountFormatterFactory()
					.getLocalizableAmountFormatter();

			String formatted2 = locFormatter.print(amount, Locale.CHINA);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
