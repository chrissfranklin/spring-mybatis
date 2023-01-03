package org.example.rest.preference.entity;

//import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

//@Entity
//@Data
//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Table(name="t_preference_item")
public class PreferenceItemEntity
///  implements java.io.Serializable
{

  private int id;
  private int preferenceId;
  private String reportName;
  private String itemName;
  private String itemValue;

  //PreferenceEntity preferencyEntity;

/*
  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "preference_id")
  @JsonBackReference
  private PreferenceEntity preferenceEntity;

  @Column(name="report_name")
  private String reportName;

  @Column(name="item_name")
  private String itemName;

  @Column(name="item_value")
  private String itemValue;

  //public Long getId() { return this.id; }
  public int getId() { return this.id; }

  //public void setId(Long id) { this.id = id; }
  public void setId(int id) { this.id = id; }

  @Override
  public boolean equals(Object o)
  {
    if(this == o) return true;
    if(! (o instanceof PreferenceItemEntity)) return false;
    //return id != null && id.equals(((PreferenceItemEntity) o).getId()); // Long
    return id == ((PreferenceItemEntity) o).getId();                      // int
  }

  @Override
  public int hashCode()
  {
    return getClass().hashCode();
  }

  @Override
  public String toString()
  {
    return "PreferenceItemEntity [ id=" + this.id + ", reportName=" + this.reportName + ", itemName=" + this.itemName + ", itemValue=" + this.itemValue;
  }
*/

}
