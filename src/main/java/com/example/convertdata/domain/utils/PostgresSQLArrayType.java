package com.example.convertdata.domain.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;

public class PostgresSQLArrayType implements UserType {

  private final int[] arrayTypes = new int[] {Types.ARRAY};

  @Override
  public int[] sqlTypes() {
    return arrayTypes;
  }

  @Override
  public Class<String[]> returnedClass() {
    return String[].class;
  }

  @Override
  public boolean equals(Object x, Object y) throws HibernateException {
    return x == null ? y == null : x.equals(y);
  }

  @Override
  public int hashCode(Object x) throws HibernateException {
    return x == null ? 0 : x.hashCode();
  }

  @Override
  public Object nullSafeGet(
          ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
      throws HibernateException, SQLException {
    if (names != null && names.length > 0 && rs != null && rs.getArray(names[0]) != null) {
      String[] results = (String[]) rs.getArray(names[0]).getArray();
      return results;
    }
    return null;
  }

  @Override
  public void nullSafeSet(
      PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
      throws HibernateException, SQLException {
    if (value != null && st != null) {
      String[] castObject = (String[]) value;
      Array array = session.connection().createArrayOf("text", castObject);
      st.setArray(index, array);
    } else {
      st.setNull(index, arrayTypes[0]);
    }
  }

  @Override
  public Object deepCopy(Object value) throws HibernateException {
    return value == null ? null : ((String[]) value).clone();
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(Object value) throws HibernateException {
    return (Serializable) value;
  }

  @Override
  public Object assemble(Serializable cached, Object owner) throws HibernateException {
    return cached;
  }

  @Override
  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    return original;
  }
}
