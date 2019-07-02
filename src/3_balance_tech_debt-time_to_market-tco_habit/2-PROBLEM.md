# Un problema tipico in ambito Java

Esempio (da https://howtodoinjava.com/hibernate/hibernate-3-introduction-and-writing-hello-world-application/)

```java
  package hibernate.test.dto;
  
  import java.io.Serializable;
  
  import javax.persistence.Column;
  import javax.persistence.Entity;
  import javax.persistence.GeneratedValue;
  import javax.persistence.GenerationType;
  import javax.persistence.Id;
  import javax.persistence.Table;
  import javax.persistence.UniqueConstraint;
  
  import org.hibernate.annotations.OptimisticLockType;
  
  @Entity
  @org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL)
  @Table(name = "Employee", uniqueConstraints = {
          @UniqueConstraint(columnNames = "ID"),
          @UniqueConstraint(columnNames = "EMAIL") })
  public class EmployeeEntity implements Serializable {
  
      private static final long serialVersionUID = -1798070786993154676L;
  
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "ID", unique = true, nullable = false)
      private Integer employeeId;
  
      @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
      private String email;
  
      @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
      private String firstName;
  
      @Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
      private String lastName;
  }
```

per esporlo su REST è necessario serializzarlo.

## Il costo di ownership è...

- aggiungo un campo sul DB
- lo mappo sulla Entity
- lo espongo via REST
- lo consumo lato frontend Javascript rimappandolo su una entity

un continuo copia e incolla che non finisce piu
