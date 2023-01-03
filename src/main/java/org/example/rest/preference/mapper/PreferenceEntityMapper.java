package org.example.rest.preference.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import org.example.rest.preference.entity.PreferenceEntity;

@Mapper
public interface PreferenceEntityMapper
{

  //
  // SELECT
  //
  @Select("SELECT * FROM t_preference WHERE user_id = #{userId} AND practice_id = #{practiceId}")
  @Results(id = "preferenceEntityResultMap", value = {
    @Result(property = "id",             column = "id"),
    @Result(property = "userId",         column = "user_id"),
    @Result(property = "practiceId",     column = "practice_id"),
    @Result(property = "preferenceName", column = "preference_name")
  })
  List<PreferenceEntity> selectPreferenceEntityListByUserIdAndPracticeId
  (
    @Param("userId")     int userId,
    @Param("practiceId") int practiceId
  );

  @Select
  (
    "SELECT * " +
    "FROM t_preference WHERE user_id = #{userId} " +
    "AND practice_id = #{practiceId} " +
    "AND preference_name = #{preferenceName}"
  )
  @ResultMap("preferenceEntityResultMap")
  PreferenceEntity selectPreferenceEntityByUserIdAndPracticeIdAndPreferenceName
  (
    @Param("userId")         int userId,
    @Param("practiceId")     int practiceId,
    @Param("preferenceName") String preferenceName
  );


  //
  // INSERT
  //
  @Insert
  (
    "INSERT INTO t_preference (user_id, practice_id, preference_name) " +
    "VALUES( #{userId}, #{practiceId}, #{preferenceName} )"
  )
  void insertPreference
  (
    int userId,
    int practiceId,
    String preferenceName
  );

}
