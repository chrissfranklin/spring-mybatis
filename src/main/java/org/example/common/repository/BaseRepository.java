package org.example.common.repository;

import org.example.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.Set;

@Slf4j
public class BaseRepository
{
  public static final String SPLUNK_EVENT_SQL_REPORT_QUERY = "SPLUNK_EVENT_SQL_REPORT_QUERY";
  public static final String SPLUNK_EVENT_SQL_ADMIN_QUERY  = "SPLUNK_EVENT_SQL_ADMIN_QUERY";
  public static final String SPACE  = " ";
  public static final String EMPTY  = "";

  //
  // Helper to insert LIKE clause inside of the given sqlQuery.
  // The given sqlQuery must contain the a line starting with -- $LIKE_CLAUSE for token replacement to work properly
  public String insertLikeClause(String sqlQuery, Map<String,String> filterColumnMap, String filterText)
  {
    //Set<String> keys = filterColumnMap.keySet();
    String dataType = EMPTY;
    // only insert into existing SQL (replace btwn tokens) if the sortColumn is given (will be empty on first request)
    String replaceText  = EMPTY;
    String myColumnName = EMPTY;
    String myDataType   = EMPTY;
    String tmpString    = EMPTY;
    int cnt = 0;
    if(MyUtil.stringIsNotEmpty(filterText)) {
      // make filterText lowercase
      filterText = filterText.toLowerCase();
      for(Map.Entry<String,String> entry : filterColumnMap.entrySet()) {
        cnt++;
        myColumnName = entry.getKey();
        myDataType   = entry.getValue();
        // if myValue == Number then dont add single quotes or like syntax - use direct compare operator
        if(MyUtil.stringIsNotEmpty(myDataType)) {
          //log.trace("myColumnName: " + myColumnName);
          //log.trace("myDataType:   " + myDataType);
          // if first item used 'AND' clause otherwise use 'OR'
          if(cnt == 1)
            tmpString = "AND ";
          else
            tmpString = "OR ";
          if(myDataType.equals("String")) {
            // TODO: use StringBuilder for String cat here
            replaceText += tmpString + "LOWER(" + myColumnName + ") LIKE '%" + filterText + "%'\n";
          }
          else {
           replaceText += tmpString + "LOWER(TO_CHAR(" + myColumnName + ")) LIKE '%" + filterText + "%'\n";
          }
        } // end if not empty myDataType
      } // end for
      sqlQuery = this.replaceBetweenTokensInString(sqlQuery, replaceText, "LIKE_TOKEN_BEGIN", "LIKE_TOKEN_END");
    }
    return sqlQuery;
  }

  //
  // Helper to insert ORDER BY clause inside of the given sqlQuery.
  // The given sqlQuery must contain the a line starting with -- $ORDER_BY_CLAUSE for token replacement to work properly
  public String insertOrderByClause(String sqlQuery, Map<String,String> filterColumnMap, String sortColumn, String sortDirection)
  {
    Set<String> keys = filterColumnMap.keySet();
    String dataType = EMPTY;
    // only insert into existing SQL (replace btwn tokens) if the sortColumn is given (will be empty on first request)
    String replaceText = EMPTY;
    if(MyUtil.stringIsNotEmpty(sortColumn)) {
      if(keys.contains(sortColumn)) {
        dataType = filterColumnMap.get(sortColumn);
        // If dataType != number then wrap ORDER BY x with LOWER(x)
        // This logic is tied to datatype (String, Number) found in Entity.GetFilterColumnMap()
        if(MyUtil.stringIsNotEmpty(dataType)) {
          if(dataType.equals("String")) {
            replaceText = "ORDER BY LOWER(" + sortColumn + ")";
          }
          else {
            replaceText = "ORDER BY " + sortColumn;
          }
        }
        // Append the DESC and ASC directive
        if(sortDirection.equals("DESC") || sortDirection.equals("ASC")) {
          replaceText += SPACE + sortDirection;
        }
        sqlQuery = this.replaceBetweenTokensInString(sqlQuery, replaceText, "ORDER_BY_TOKEN_BEGIN", "ORDER_BY_TOKEN_END");
      }
      else {
        log.error("sortColumn '" + sortColumn + "' does not exist in InventoryLevel1Entity.getColumnMap().keySet(): " + keys.toString());
      }
    }
    return sqlQuery;
  }

  // Helper to replace text in the given inputString based on start and end tokens.
  public String replaceBetweenTokensInString(String sqlInputString, String replaceText, String startToken, String endToken)
  {
    String regex = "-- " + startToken + "[\\s\\S]*?" + endToken;
    String rezult = sqlInputString.replaceAll(regex, replaceText);
    //log.trace("replaceText: " + replaceText);
    //log.trace("inputString length: " + sqlInputString.length());
    //log.trace("rezult length:      " + rezult.length());
    //log.trace("rezult:             " + rezult);
    // Remove the tokens - TODO: make renmove tokens below work
    rezult = rezult.replaceFirst("-- " + startToken + "\n", "");
    rezult = rezult.replaceFirst("-- " + endToken, "");
    //log.trace("rezult2:             " + rezult);
    return rezult;
  }


  // This method used by all Repositories for page variable calculations
  // Given a pageNumber and pageSize, determine startRow, endRow
  // and return as StartEndRows object
  public StartEndRows calculateStartEndRows(Pageable pageable, Long rowCount)
  {
    int pageSize   = pageable.getPageSize();   // 10
    int pageNumber = pageable.getPageNumber(); // 2
    // Calculate the start/end row number based on pageSize and pageNumber
    // default to showing first page
    int startRow = 1;
    int endRow   = pageSize; // ex: 10
    if(this.isFetchAllData(pageable)) { // if pageNumber and pageSize are both == 1
      //endRow = 10000; // for now set to large number, later rewrite the sql with no upper constraint
      endRow = Integer.parseInt(rowCount.toString()); // set the end row the max size of the resultSet
    }
    else {
      if(pageNumber > 0) {
        startRow = (pageSize * pageNumber) + 1; // ex: (10*1)+1 = 10
        endRow   = (pageSize * pageNumber) + pageSize;
        // ex: 10*2     = 20
      }
    }
    log.trace("pageNumber: " + pageNumber);
    log.trace("pageSize:   " + pageSize);
    log.trace("startRow:   " + startRow);
    log.trace("endRow:     " + endRow);
    return new StartEndRows(startRow, endRow);
  }

  // Simple Inner class to store start and end rows
  // Used by calculateStartEndRows()
  public class StartEndRows
  {
    public StartEndRows(Integer startRow, Integer endRow)
    {
      this.startRow = startRow;
      this.endRow   = endRow;
    }
    public Integer startRow;
    public Integer endRow;
  }

  // Return true if both pageSize and pageNUmber == 1
  public boolean isFetchAllData(Pageable pageable)
  {
    return (pageable.getPageSize() == 1) && (pageable.getPageNumber() == 1);
  }

  // If filterText is not empty then add LIKE clause for each key in columnMap
  public boolean needsLikeClause(String filterText)
  {
    return ( MyUtil.stringIsNotEmpty(filterText) );
  }

  // If sortColumn is not empty and match Entity.columnMap.keys return true
  public boolean needsOrderByClause(String sortColumn, Map<String,String> columnMap)
  {
    return ( MyUtil.stringIsNotEmpty(sortColumn) && columnMap.containsKey(sortColumn) );
  }

}
