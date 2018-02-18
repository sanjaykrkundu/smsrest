package com.example.demo.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class StringSequenceIdentifier implements IdentifierGenerator, Configurable {

	private String prefix;
	private String query;
	private String intLen;

	public StringSequenceIdentifier() {
	}

	public StringSequenceIdentifier(String prefix, String query, String intLen) {
		this.prefix = prefix;
		this.query = query;
		this.intLen = intLen;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getIntLen() {
		return intLen;
	}

	public void setIntLen(String intLen) {
		this.intLen = intLen;
	}

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		Connection connection = session.connection();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				int id = rs.getInt(1) + 1;
				String generatedId = String.format("%s%0" + intLen + "d", prefix.toUpperCase(), id);
				return generatedId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		this.setPrefix(params.getProperty("prefix", ""));
		this.setIntLen(params.getProperty("length", "3"));
		String stratigyType = params.getProperty("type", "COUNT");

		if (stratigyType.equalsIgnoreCase("MAX"))
			this.setQuery("SELECT max(SUBSTRING(" + params.getProperty("target_column") + ", -" + intLen + ", " + intLen
					+ ")) FROM " + params.getProperty("target_table"));
		else
			this.setQuery("SELECT COUNT(" + params.getProperty("target_column") + ") FROM "
					+ params.getProperty("target_table"));
	}

}
