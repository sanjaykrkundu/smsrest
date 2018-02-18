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

public class IntegerSequenceIdentifier implements IdentifierGenerator, Configurable {

	private String intLen;
	private String query;

	public IntegerSequenceIdentifier() {
	}

	public IntegerSequenceIdentifier(String intLen) {
		this.intLen = intLen;
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
				String generatedId = String.format("%0" + intLen + "d", id);
				return generatedId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		this.setIntLen(params.getProperty("length", "1"));
		this.query = "SELECT max(SUBSTRING(" + params.getProperty("target_column") + ", -" + intLen + ", " + intLen
				+ ")) FROM " + params.getProperty("target_table");
	}

}
