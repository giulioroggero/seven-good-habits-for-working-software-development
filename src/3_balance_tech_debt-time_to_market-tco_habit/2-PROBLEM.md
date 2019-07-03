# Un problema tipico in ambito Java

Esempio: voglio gestire i dati sul DB, esporli come REST e visualizzarli in una UI

Quello che viene da fare è approcciare i problemi sempre allo stesso modo. In questo caso JPA/DTO/Service/Controller/ecc
(da https://howtodoinjava.com/hibernate/hibernate-3-introduction-and-writing-hello-world-application/)

```java
...
  
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
