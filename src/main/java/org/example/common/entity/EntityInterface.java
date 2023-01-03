package org.example.common.entity;

import java.util.Map;

public interface EntityInterface
{
  public Map<String, Object> toMap();

  public static Map<String, String> getColumnMap() { return null; } // TODO: remove return null on this line

  //public boolean containsText(Map<String,String> filterColumnMap, String filterText);

  //public List<String> getFilterColumnList(); // deprecated
  public static Map<String,String> getFilterColumnMap() { return null; }

}
