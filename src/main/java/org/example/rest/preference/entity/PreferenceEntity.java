package org.example.rest.preference.entity;

//import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PreferenceEntity
{
  private int id;
  private int userId;
  private int practiceId;
  private String preferenceName;

  List<PreferenceItemEntity> preferenceItemEntityList;

/*
// https://www.stackchief.com/blog/One%20To%20Many%20Example%20%7C%20Spring%20Data%20JPA
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="t_preference")
public class PreferenceEntity implements java.io.Serializable
{
  @javax.persistence.Id
  //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preference_item_generator")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="user_id")
  private int userId;

  @Column(name="practice_id")
  private int practiceId;

  @Column(name="preference_name")
  private String preferenceName;

  @OneToMany(mappedBy="preferenceEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private Set<PreferenceItemEntity> preferenceItemEntitySet = new HashSet<>();
  //private List<PreferenceItemEntity> preferenceItemEntityList = new ArrayList<>();

  public void addPreferenceItemEntity(PreferenceItemEntity preferenceItemEntity)
  {
    this.preferenceItemEntitySet.add(preferenceItemEntity);
    //this.preferenceItemEntityList.add(preferenceItemEntity);
    preferenceItemEntity.setPreferenceEntity(this);
  }

  public void removePreferenceItemEntity(PreferenceItemEntity preferenceItemEntity)
  {
    this.preferenceItemEntitySet.remove(preferenceItemEntity);
    //this.preferenceItemEntityList.remove(preferenceItemEntity);
    preferenceItemEntity.setPreferenceEntity(null);
  }
*/
}
