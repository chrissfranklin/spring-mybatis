package org.example.rest.preference.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import org.example.rest.preference.entity.PreferenceItemEntity;

@Mapper
public interface PreferenceItemEntityMapper
{
  //
  // SELECT
  //
  @Select
  (
    "SELECT * FROM t_preference_item WHERE preference_id = #{preferenceId}"
  )
  @Results(id = "preferenceItemEntityResultMap", value = {
    @Result(property = "id",           column = "id"),
    @Result(property = "preferenceId", column = "preference_id"),
    @Result(property = "reportName",   column = "report_name"),
    @Result(property = "itemName",     column = "item_name"),
    @Result(property = "itemValue",    column = "item_value")
  })
  List<PreferenceItemEntity> selectPreferenceItemEntityListByPreferenceId
  (
    @Param("preferenceId") int preferenceId
  );

  @Select("SELECT * FROM t_preference_item WHERE preference_id = #{preferenceId} AND report_name = #{reportName}")
  @ResultMap("preferenceItemEntityResultMap")
  List<PreferenceItemEntity> selectPreferenceItemEntityListByPreferenceIdAndReportName
  (
    @Param("preferenceId") int preferenceId,
    @Param("reportName")   String reportName
  );

  @Select
  (
    "SELECT * FROM t_preference_item " +
    "WHERE preference_id = #{preferenceId} " +
    "AND report_name = #{reportName}" +
    "AND item_name = #{itemName}"
  )
  @ResultMap("preferenceItemEntityResultMap")
  PreferenceItemEntity selectPreferenceItemEntityByPreferenceIdAndReportNameAndItemName
  (
    @Param("preferenceId") int preferenceId,
    @Param("reportName")   String reportName,
    @Param("itemName")     String itemName
  );

  //
  // INSERT
  //
  @Insert
  (
    "INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) " +
    "VALUES( #{preferenceId}, #{reportName}, #{itemName}, #{itemValue} )"
  )
  void insertPreferenceItem
  (
    int preferenceId,
    String reportName,
    String itemName,
    String itemValue
  );


  //
  // UPDATE
  //
  @Update
  (
    "UPDATE t_preference_item " +
    "SET item_value = #{itemValue} " +
    "WHERE preference_id = #{preferenceId} " +
    "AND report_name = #{reportName} " +
    "AND item_name = #{itemName}"
  )
  void updatePreferenceItem
  (
    int preferenceId,
    String reportName,
    String itemName,
    String itemValue
  );

}
