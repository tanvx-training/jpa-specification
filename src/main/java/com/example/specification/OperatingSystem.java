package com.example.specification;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operating_system")
public class OperatingSystem implements Serializable {

  private static final long serialVersionUID = -1730538653948604611L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "version", nullable = false)
  private String version;

  @Column(name = "kernel", nullable = false)
  private String kernel;

  @Column(name = "release_date", nullable = false)
  private LocalDateTime releaseDate;

  @Column(name = "usages", nullable = false)
  private Integer usages;

}

