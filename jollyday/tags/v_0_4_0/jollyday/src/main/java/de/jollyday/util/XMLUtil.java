/**
 * Copyright 2010 Sven Diedrichsen 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language 
 * governing permissions and limitations under the License. 
 */
package de.jollyday.util;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import org.joda.time.DateTimeConstants;

import de.jollyday.HolidayType;
import de.jollyday.config.Configuration;
import de.jollyday.config.Month;
import de.jollyday.config.Weekday;
import de.jollyday.holidaytype.LocalizedHolidayType;

public class XMLUtil {

	/**
	 * the package name to search for the generated java classes.
	 */
	public static final String PACKAGE = "de.jollyday.config";

	/**
	 * Unmarshalls the configuration from the stream. Uses <code>JAXB</code> for
	 * this.
	 * 
	 * @param stream
	 * @return The unmarshalled configuration.
	 * @throws JAXBException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Configuration unmarshallConfiguration(InputStream stream)
			throws JAXBException, IOException {
		if (stream == null) {
			throw new IllegalArgumentException(
					"Stream is NULL. Cannot read XML.");
		}
		try {
			JAXBContext ctx = JAXBContext.newInstance(XMLUtil.PACKAGE);
			Unmarshaller um = ctx.createUnmarshaller();
			JAXBElement<Configuration> el = (JAXBElement<Configuration>) um
					.unmarshal(stream);
			return el.getValue();
		} catch (UnmarshalException ue) {
			throw new IllegalStateException("Cannot parse holidays XML file.",
					ue);
		} finally {
			stream.close();
		}
	}

	/**
	 * Returns the <code>DateTimeConstants</code> value for the given weekday.
	 * 
	 * @param weekday
	 * @return DateTimeConstants value.
	 */
	public static final int getWeekday(Weekday weekday) {
		switch (weekday) {
		case MONDAY:
			return DateTimeConstants.MONDAY;
		case TUESDAY:
			return DateTimeConstants.TUESDAY;
		case WEDNESDAY:
			return DateTimeConstants.WEDNESDAY;
		case THURSDAY:
			return DateTimeConstants.THURSDAY;
		case FRIDAY:
			return DateTimeConstants.FRIDAY;
		case SATURDAY:
			return DateTimeConstants.SATURDAY;
		case SUNDAY:
			return DateTimeConstants.SUNDAY;
		default:
			throw new IllegalArgumentException("Unknown weekday " + weekday);
		}
	}

	/**
	 * Returns the <code>DateTimeConstants</code> value for the given month.
	 * 
	 * @param month
	 * @return DateTimeConstants value.
	 */
	public static int getMonth(Month month) {
		switch (month) {
		case JANUARY:
			return DateTimeConstants.JANUARY;
		case FEBRUARY:
			return DateTimeConstants.FEBRUARY;
		case MARCH:
			return DateTimeConstants.MARCH;
		case APRIL:
			return DateTimeConstants.APRIL;
		case MAY:
			return DateTimeConstants.MAY;
		case JUNE:
			return DateTimeConstants.JUNE;
		case JULY:
			return DateTimeConstants.JULY;
		case AUGUST:
			return DateTimeConstants.AUGUST;
		case SEPTEMBER:
			return DateTimeConstants.SEPTEMBER;
		case OCTOBER:
			return DateTimeConstants.OCTOBER;
		case NOVEMBER:
			return DateTimeConstants.NOVEMBER;
		case DECEMBER:
			return DateTimeConstants.DECEMBER;
		default:
			throw new IllegalArgumentException("Unknown month " + month);
		}
	}

	/**
	 * Gets the type.
	 * 
	 * @param type
	 *            the type of holiday in the config
	 * 
	 * @return the type of holiday
	 */
	public static HolidayType getType(de.jollyday.config.HolidayType type) {
		switch (type) {
		case OFFICIAL_HOLIDAY:
			return LocalizedHolidayType.OFFICIAL_HOLIDAY;
		case UNOFFICIAL_HOLIDAY:
			return LocalizedHolidayType.UNOFFICIAL_HOLIDAY;
		default:
			throw new IllegalArgumentException("Unknown type " + type);
		}
	}

}