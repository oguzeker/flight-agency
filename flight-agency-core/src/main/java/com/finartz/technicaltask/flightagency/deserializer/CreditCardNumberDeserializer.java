package com.finartz.technicaltask.flightagency.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class CreditCardNumberDeserializer extends JsonDeserializer<String> {

	public static final String MASK_REGEX = "\\b(\\d{6})(\\d{6})(\\d{4})";
	public static final String MASK_REPLACEMENT = "$1******$3";
	public static final String SANITIZATION_REGEX = "[^0-9]";

	public String deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
		String creditCardNumber = parser.readValueAs(String.class).replaceAll(SANITIZATION_REGEX, StringUtils.EMPTY);
		return creditCardNumber.replaceAll(MASK_REGEX, MASK_REPLACEMENT);
	}

}
