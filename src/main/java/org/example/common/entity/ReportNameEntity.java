package org.example.common.entity;

public enum ReportNameEntity
{
  INFREQUENTLY_USED_ITEMS("Infrequently Used Items"),
  UTILIZATION_BY_PROVIDER_LEVEL_1("Utilization by Provider - Level 1"),
  UTILIZATION_BY_PROVIDER_LEVEL_2("Utilization by Provider - Level 2"),
  UTILIZATION_BY_PROVIDER_LEVEL_3("Utilization by Provider - Level 3"),
  DAYS_INVENTORY_ON_HAND_LEVEL_1("Days Inventory On Hand - Level 1"),
  DAYS_INVENTORY_ON_HAND_LEVEL_2("Days Inventory On Hand - Level 2"),
  DAYS_INVENTORY_ON_HAND_LEVEL_3("Days Inventory On Hand - Level 3"),
  PATIENT_TRANSACTION("Patient Transaction"),
  BILLING_ACCURACY("Billing Accuracy");

  private final String description;

  private ReportNameEntity(String s) 
  {
    description = s;
  }

  public String getDescription()
  {
    return description;
  }

}
