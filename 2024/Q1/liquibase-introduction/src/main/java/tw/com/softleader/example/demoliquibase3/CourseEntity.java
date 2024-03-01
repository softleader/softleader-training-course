package tw.com.softleader.example.demoliquibase3;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "course")
@Entity
public class CourseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = false)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id")
  private StudentEntity student;
}
