package com.ma.test.testXMLParse;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class TestSaxParse {

	public static void main(String[] args) {
		try {
			SAXParser sp=SAXParserFactory.newInstance().newSAXParser();
			//sp.parse(arg0, arg1);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

}
