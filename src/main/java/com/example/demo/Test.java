package com.example.demo;

import java.io.StringWriter;
import java.util.Date;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;

public class Test {
	
	public static void main(String[] args) throws JAXBException {
		Person p = new Person();
		p.nome = "andrea";
		p.cognome = "braghese";
		p.data = new Date();
		
//		JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
		
//		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		jaxbMarshaller.marshal(p, System.out);
		StringWriter s = new StringWriter();
		JAXB.marshal(p, System.out);
		JAXB.marshal(p, s);
	}
	
	// order of the fields in XML
	// @XmlType(propOrder = {"price", "name"})
	static class Person {
		public String nome;
		public String cognome;
		public Date data;
	}

}
