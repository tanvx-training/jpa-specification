## JPA Specification

### Project Setup and Dependency

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <optional>true</optional>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-test</artifactId>
  <scope>test</scope>
</dependency>
```

Các phần này trong tệp `pom.xml` của bạn là các phụ thuộc Maven, được sử dụng để quản lý các thư viện và công cụ trong dự án Spring Boot của bạn. Dưới đây là mô tả ngắn về mỗi phần:

1. **spring-boot-starter-data-jpa**: Đây là bộ khởi động Spring Boot cho Spring Data JPA, giúp bạn dễ dàng tích hợp và sử dụng JPA trong ứng dụng Spring Boot của mình.

2. **spring-boot-starter-web**: Đây là bộ khởi động Spring Boot cho phát triển ứng dụng web, bao gồm Spring MVC và các phần mở rộng khác để xử lý HTTP requests.

3. **h2**: Đây là một cơ sở dữ liệu nhỏ, nhúng, dùng để phát triển và kiểm thử ứng dụng. Trong trường hợp này, nó được sử dụng như một cơ sở dữ liệu runtime.

4. **lombok**: Lombok là một thư viện Java giúp giảm bớt code boilerplate thông qua các annotations. Nó giúp tạo ra các getter, setter, constructor và các phương thức khác tự động từ các annotations, giúp mã của bạn trở nên ngắn gọn hơn.

5. **spring-boot-starter-test**: Đây là bộ khởi động Spring Boot cho việc kiểm thử. Nó cung cấp các công cụ và thư viện để viết và chạy các bài kiểm thử cho ứng dụng Spring Boot của bạn.

Các phần này là cơ bản để bắt đầu một dự án Spring Boot với tích hợp JPA và phát triển ứng dụng web.

```properties
server.port=8080
spring.application.name=search-request
server.servlet.context-path=/api

spring.datasource.url=jdbc:h2:mem:db;
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

Đoạn mã trên là cấu hình cho ứng dụng Spring Boot của bạn. Dưới đây là mô tả của từng phần trong cấu hình:

1. **server.port=8080**: Định cấu hình cổng mà ứng dụng Spring Boot sẽ chạy trên.

2. **spring.application.name=search-request**: Đặt tên cho ứng dụng của bạn. Điều này có thể hữu ích trong việc xác định ứng dụng trong môi trường phân tán hoặc trong việc cấu hình các tính năng như gửi nhật ký.

3. **server.servlet.context-path=/api**: Định cấu hình context path cho ứng dụng của bạn. Mọi request đến ứng dụng sẽ phải bắt đầu với `/api`.

4. **spring.datasource.url=jdbc:h2:mem:db;**: Định cấu hình URL của cơ sở dữ liệu. Trong trường hợp này, bạn đang sử dụng H2 Database và cấu hình nó để lưu trữ trong bộ nhớ (`mem:db`).

5. **spring.datasource.driverClassName=org.h2.Driver**: Định cấu hình class của driver cho cơ sở dữ liệu. Trong trường hợp này, bạn đang sử dụng H2 Database, vì vậy bạn cần sử dụng `org.h2.Driver`.

6. **spring.datasource.username=sa** và **spring.datasource.password=password**: Định cấu hình tên người dùng và mật khẩu để truy cập vào cơ sở dữ liệu. Trong trường hợp này, tên người dùng được đặt là `sa` và mật khẩu là `password`.

7. **spring.jpa.database-platform=org.hibernate.dialect.H2Dialect**: Định cấu hình dialect của Hibernate cho cơ sở dữ liệu. Trong trường hợp này, bạn đang sử dụng H2 Database, vì vậy bạn cần sử dụng `org.hibernate.dialect.H2Dialect`.

8. **spring.h2.console.enabled=true**: Định cấu hình cho phép Spring H2 Console. Điều này làm cho bạn có thể truy cập vào giao diện web của H2 Database Console để quản lý cơ sở dữ liệu của mình.

9. **spring.jpa.show-sql=true**: Định cấu hình để hiển thị các truy vấn SQL được tạo ra bởi Spring JPA trong nhật ký. Điều này hữu ích để theo dõi các truy vấn SQL được tạo ra bởi ứng dụng của bạn.

10. **spring.jpa.hibernate.ddl-auto=update**: Định cấu hình cho Hibernate về cách xử lý việc tạo, cập nhật hoặc thay đổi cấu trúc của cơ sở dữ liệu. Trong trường hợp này, Hibernate sẽ tự động tạo và cập nhật cấu trúc cơ sở dữ liệu khi cần thiết.

### Implementation

##### Domain Data Access Object (DAO)

```java
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
```

Đoạn mã bạn đã cung cấp là một lớp Java đại diện cho một đối tượng "OperatingSystem". Dưới đây là mô tả của từng phần trong lớp này:

1. **@Data**: Đây là một annotation từ thư viện Lombok. Nó tự động tạo các getter, setter, phương thức equals, hashCode và toString cho các trường dữ liệu của lớp. Sử dụng `@Data` giúp giảm bớt code boilerplate.

2. **@Builder**: Đây cũng là một annotation từ thư viện Lombok. Nó tạo ra một builder pattern cho lớp, cho phép bạn tạo các đối tượng OperatingSystem một cách dễ dàng và rõ ràng hơn.

3. **@NoArgsConstructor** và **@AllArgsConstructor**: Đây là các annotations từ Lombok giúp tự động tạo các constructor không có tham số và constructor có tất cả các tham số cho lớp.

4. **@Entity**: Đây là một annotation của JPA (Java Persistence API) để đánh dấu rằng lớp này là một Entity, nghĩa là nó ánh xạ với một bảng trong cơ sở dữ liệu.

5. **@Table(name = "operating_system")**: Annotation này được sử dụng để chỉ định tên của bảng trong cơ sở dữ liệu mà lớp này sẽ ánh xạ đến. Trong trường hợp này, tên bảng là "operating_system".

6. **@Id**: Đánh dấu trường id là khóa chính (Primary Key) của bảng.

7. **@GeneratedValue(strategy = GenerationType.IDENTITY)**: Đây là một annotation của JPA để chỉ định cách sinh giá trị cho trường id. Trong trường hợp này, giá trị id sẽ được sinh tự động và tăng dần.

8. Các trường dữ liệu khác như `name`, `version`, `kernel`, `releaseDate` và `usages` là các thuộc tính của đối tượng OperatingSystem, mỗi thuộc tính được ánh xạ tới một cột trong bảng cơ sở dữ liệu tương ứng. Các trường này đều được đánh dấu với annotation `@Column`, chỉ định tên cột và các thuộc tính khác như khả năng nullable và các ràng buộc.

##### Filter Using Specification

###### Enumeration of Field Type

```java
@Slf4j
public enum FieldType {

    BOOLEAN {
        public Object parse(String value) {
            return Boolean.valueOf(value);
        }
    },

    CHAR {
        public Object parse(String value) {
            return value.charAt(0);
        }
    },

    DATE {
        public Object parse(String value) {
            Object date = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                date = LocalDateTime.parse(value, formatter);
            } catch (Exception e) {
                log.info("Failed parse field type DATE {}", e.getMessage());
            }

            return date;
        }
    },

    DOUBLE {
        public Object parse(String value) {
            return Double.valueOf(value);
        }
    },

    INTEGER {
        public Object parse(String value) {
            return Integer.valueOf(value);
        }
    },

    LONG {
        public Object parse(String value) {
            return Long.valueOf(value);
        }
    },

    STRING {
        public Object parse(String value) {
            return value;
        }
    };

    public abstract Object parse(String value);

}
```

Đoạn mã trên định nghĩa một enum có tên là `FieldType`, đại diện cho các loại dữ liệu có thể có trong một trường dữ liệu. Dưới đây là mô tả của từng phần trong enum này:

1. **@Slf4j**: Đây là một annotation từ thư viện Lombok, tự động tạo một logger private static final `log` cho enum này.

2. **BOOLEAN, CHAR, DATE, DOUBLE, INTEGER, LONG, STRING**: Đây là các giá trị enum đại diện cho các loại dữ liệu khác nhau có thể được sử dụng trong một trường dữ liệu.

3. Mỗi giá trị enum này có một phương thức `parse(String value)` được ghi đè. Phương thức này được sử dụng để chuyển đổi một chuỗi đầu vào thành kiểu dữ liệu tương ứng. Ví dụ:
    - `BOOLEAN` chuyển đổi chuỗi thành kiểu `Boolean`.
    - `CHAR` chuyển đổi chuỗi thành ký tự đầu tiên của nó.
    - `DATE` chuyển đổi chuỗi thành đối tượng `LocalDateTime` sử dụng định dạng `"dd-MM-yyyy HH:mm:ss"`.
    - `DOUBLE` chuyển đổi chuỗi thành kiểu `Double`.
    - `INTEGER` chuyển đổi chuỗi thành kiểu `Integer`.
    - `LONG` chuyển đổi chuỗi thành kiểu `Long`.
    - `STRING` trả về chính chuỗi đầu vào.

4. Nếu việc chuyển đổi không thành công (ví dụ: khi parse ngày tháng không hợp lệ), một thông báo lỗi sẽ được ghi vào log thông qua biến `log`, đã được tạo ra từ annotation `@Slf4j`.

Enum này cung cấp một cách tiện lợi để chuyển đổi các chuỗi đầu vào thành các kiểu dữ liệu phổ biến khác nhau, mà không cần phải viết lại mã chuyển đổi cho mỗi kiểu dữ liệu.

###### Filter Request

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilterRequest implements Serializable {

    private static final long serialVersionUID = 6293344849078612450L;

    private String key;

    private Operator operator;

    private FieldType fieldType;

    private transient Object value;

    private transient Object valueTo;

    private transient List<Object> values;

}
```

Đoạn mã trên định nghĩa một lớp Java có tên là `FilterRequest`, đại diện cho một yêu cầu lọc trong ứng dụng của bạn. Dưới đây là mô tả của từng phần trong lớp này:

1. **@Data, @Builder, @NoArgsConstructor, @AllArgsConstructor**: Đây là các annotations từ thư viện Lombok.
    - `@Data` tự động tạo các getter, setter, phương thức equals, hashCode và toString cho các trường dữ liệu của lớp.
    - `@Builder` tạo ra một builder pattern cho lớp, cho phép bạn tạo các đối tượng `FilterRequest` một cách dễ dàng và rõ ràng hơn.
    - `@NoArgsConstructor` tạo ra một constructor không có tham số.
    - `@AllArgsConstructor` tạo ra một constructor có tất cả các tham số.

2. **@JsonIgnoreProperties(ignoreUnknown = true)**: Đây là một annotation của thư viện Jackson JSON, được sử dụng để bỏ qua các thuộc tính không được khai báo trong lớp khi chuyển đổi từ JSON thành đối tượng Java. Trong trường hợp này, nó được sử dụng để bỏ qua các thuộc tính không được định nghĩa trong lớp khi deserialization.

3. **@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)**: Đây là một annotation của thư viện Jackson JSON, được sử dụng để chỉ định chiến lược đặt tên các thuộc tính khi chuyển đổi giữa JSON và Java. Trong trường hợp này, `SnakeCaseStrategy` được sử dụng để chuyển đổi tên thuộc tính từ kiểu camelCase sang snake_case.

4. **private transient Object value;**, **private transient Object valueTo;**, **private transient List<Object> values;**: Các trường dữ liệu này được đánh dấu là `transient`, điều này có nghĩa là chúng sẽ không được serializable. Trong trường hợp này, có vẻ như chúng không cần thiết khi serialize/deserialize. Có thể đây là các giá trị tạm thời được sử dụng trong quá trình xử lý lọc và không cần được lưu trữ khi truyền dữ liệu giữa các hệ thống.

5. **private String key;**, **private Operator operator;**, **private FieldType fieldType;**: Các trường dữ liệu này đại diện cho thông tin cần thiết để thực hiện việc lọc.
    - `key` là tên của trường dữ liệu.
    - `operator` là một enum đại diện cho toán tử lọc (không được định nghĩa trong đoạn mã bạn đã cung cấp).
    - `fieldType` là một enum đại diện cho loại dữ liệu của trường dữ liệu.

Lớp `FilterRequest` này có thể được sử dụng để đại diện cho một yêu cầu lọc, trong đó một trường dữ liệu cần được lọc dựa trên tên, toán tử và loại dữ liệu của nó, cùng với giá trị cần lọc.

###### Enumeration of Operator

```java
@Slf4j
public enum Operator {

  EQUAL {
    public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
      Object value = request.getFieldType().parse(request.getValue().toString());
      Expression<?> key = root.get(request.getKey());
      return cb.and(cb.equal(key, value), predicate);
    }
  },

  NOT_EQUAL {
    public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
      Object value = request.getFieldType().parse(request.getValue().toString());
      Expression<?> key = root.get(request.getKey());
      return cb.and(cb.notEqual(key, value), predicate);
    }
  },

  LIKE {
    public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
      Expression<String> key = root.get(request.getKey());
      return cb.and(cb.like(cb.upper(key), "%" + request.getValue().toString().toUpperCase() + "%"), predicate);
    }
  },

  IN {
    public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
      List<Object> values = request.getValues();
      CriteriaBuilder.In<Object> inClause = cb.in(root.get(request.getKey()));
      for (Object value : values) {
        inClause.value(request.getFieldType().parse(value.toString()));
      }
      return cb.and(inClause, predicate);
    }
  },

  BETWEEN {
    public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
      Object value = request.getFieldType().parse(request.getValue().toString());
      Object valueTo = request.getFieldType().parse(request.getValueTo().toString());
      if (request.getFieldType() == FieldType.DATE) {
        LocalDateTime startDate = (LocalDateTime) value;
        LocalDateTime endDate = (LocalDateTime) valueTo;
        Expression<LocalDateTime> key = root.get(request.getKey());
        return cb.and(cb.and(cb.greaterThanOrEqualTo(key, startDate), cb.lessThanOrEqualTo(key, endDate)), predicate);
      }

      if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
        Number start = (Number) value;
        Number end = (Number) valueTo;
        Expression<Number> key = root.get(request.getKey());
        return cb.and(cb.and(cb.ge(key, start), cb.le(key, end)), predicate);
      }

      log.info("Can not use between for {} field type.", request.getFieldType());
      return predicate;
    }
  };

  public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate);

}
```
Đoạn mã trên định nghĩa một enum có tên là `Operator`, đại diện cho các toán tử lọc có thể được sử dụng trong việc tạo ra các điều kiện (predicate) cho truy vấn JPA. Dưới đây là mô tả của từng phần trong enum này:

1. **@Slf4j**: Đây là một annotation từ thư viện Lombok, tự động tạo một logger private static final `log` cho enum này.

2. **EQUAL, NOT_EQUAL, LIKE, IN, BETWEEN**: Đây là các giá trị enum đại diện cho các toán tử lọc khác nhau. Mỗi toán tử được định nghĩa bởi một phương thức abstract `build`, nhận các tham số là `Root<T> root`, `CriteriaBuilder cb`, `FilterRequest request`, và `Predicate predicate`.

3. Mỗi phương thức `build` trong các giá trị enum này xây dựng và trả về một `Predicate`, được sử dụng để thêm điều kiện cho truy vấn JPA.
    - `EQUAL`: Xây dựng điều kiện so sánh bằng.
    - `NOT_EQUAL`: Xây dựng điều kiện so sánh không bằng.
    - `LIKE`: Xây dựng điều kiện tìm kiếm theo mẫu.
    - `IN`: Xây dựng điều kiện tìm kiếm trong một danh sách các giá trị.
    - `BETWEEN`: Xây dựng điều kiện so sánh nằm trong một khoảng giá trị.

4. Trong một số trường hợp, các giá trị của trường dữ liệu cần được chuyển đổi sang kiểu dữ liệu phù hợp trước khi sử dụng. Điều này được thực hiện thông qua phương thức `parse` của enum `FieldType`, mà bạn đã định nghĩa trước đó.

5. Trong trường hợp toán tử `BETWEEN`, kiểm tra loại dữ liệu của trường và xây dựng điều kiện tương ứng. Đối với các trường dữ liệu là ngày tháng (`DATE`), sử dụng phương thức `greaterThanOrEqualTo` và `lessThanOrEqualTo`. Đối với các trường dữ liệu khác, sử dụng phương thức `ge` và `le`.

6. Nếu không thể sử dụng toán tử `BETWEEN` cho loại dữ liệu nhất định (ví dụ: `CHAR`, `BOOLEAN`), một thông báo lỗi sẽ được ghi vào log thông qua biến `log`.

Enum này cung cấp các phương thức để xây dựng các điều kiện lọc phức tạp dựa trên các loại toán tử khác nhau trong truy vấn JPA.

###### Enumeration of Sorting Direction

```java
public enum SortDirection {

    ASC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request) {
            return cb.asc(root.get(request.getKey()));
        }
    },
    DESC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request) {
            return cb.desc(root.get(request.getKey()));
        }
    };

    public abstract <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request);

}
```
Đoạn mã trên định nghĩa một enum có tên là `SortDirection`, đại diện cho hướng sắp xếp (ASC - tăng dần, DESC - giảm dần) trong truy vấn JPA. Dưới đây là mô tả của từng phần trong enum này:

1. **ASC, DESC**: Đây là các giá trị enum đại diện cho hướng sắp xếp. Mỗi giá trị được định nghĩa bởi một phương thức abstract `build`, nhận các tham số là `Root<T> root`, `CriteriaBuilder cb`, và `SortRequest request`.

2. Mỗi phương thức `build` trong các giá trị enum này xây dựng và trả về một `Order`, được sử dụng để chỉ định hướng sắp xếp của truy vấn JPA.
    - `ASC`: Xây dựng một đối tượng `Order` để chỉ định sắp xếp tăng dần dựa trên trường được chỉ định trong `SortRequest`.
    - `DESC`: Xây dựng một đối tượng `Order` để chỉ định sắp xếp giảm dần dựa trên trường được chỉ định trong `SortRequest`.

3. Trong mỗi phương thức `build`, `root.get(request.getKey())` được sử dụng để truy cập đến trường được chỉ định trong `SortRequest`, và sau đó sử dụng `cb.asc()` hoặc `cb.desc()` để xác định hướng sắp xếp tương ứng.

Enum này cung cấp các phương thức để xây dựng các đối tượng `Order` dựa trên hướng sắp xếp được chỉ định trong `SortRequest` trong truy vấn JPA.

###### Sort Request

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SortRequest implements Serializable {

    private static final long serialVersionUID = 3194362295851723069L;

    private String key;

    private SortDirection direction;

}
```

Đoạn mã trên định nghĩa một lớp Java có tên là `SortRequest`, đại diện cho yêu cầu sắp xếp trong truy vấn của bạn. Dưới đây là mô tả của từng phần trong lớp này:

1. **@Data, @Builder, @NoArgsConstructor, @AllArgsConstructor**: Đây là các annotations từ thư viện Lombok.
    - `@Data` tự động tạo các getter, setter, phương thức equals, hashCode và toString cho các trường dữ liệu của lớp.
    - `@Builder` tạo ra một builder pattern cho lớp, cho phép bạn tạo các đối tượng `SortRequest` một cách dễ dàng và rõ ràng hơn.
    - `@NoArgsConstructor` tạo ra một constructor không có tham số.
    - `@AllArgsConstructor` tạo ra một constructor có tất cả các tham số.

2. **@JsonIgnoreProperties(ignoreUnknown = true)**: Đây là một annotation của thư viện Jackson JSON, được sử dụng để bỏ qua các thuộc tính không được khai báo trong lớp khi chuyển đổi từ JSON thành đối tượng Java. Trong trường hợp này, nó được sử dụng để bỏ qua các thuộc tính không được định nghĩa trong lớp khi deserialization.

3. **@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)**: Đây là một annotation của thư viện Jackson JSON, được sử dụng để chỉ định chiến lược đặt tên các thuộc tính khi chuyển đổi giữa JSON và Java. Trong trường hợp này, `SnakeCaseStrategy` được sử dụng để chuyển đổi tên thuộc tính từ kiểu camelCase sang snake_case.

4. **private String key;**: Trường dữ liệu này đại diện cho tên của trường mà bạn muốn sắp xếp theo.

5. **private SortDirection direction;**: Trường dữ liệu này đại diện cho hướng sắp xếp (tăng dần hoặc giảm dần). Đối tượng `SortDirection` được sử dụng để chỉ định hướng sắp xếp.

Lớp `SortRequest` này có thể được sử dụng để đại diện cho một yêu cầu sắp xếp, trong đó bạn chỉ định trường và hướng sắp xếp cho truy vấn của mình.

###### Search Request
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SearchRequest implements Serializable {

    private static final long serialVersionUID = 8514625832019794838L;

    private List<FilterRequest> filters;

    private List<SortRequest> sorts;

    private Integer page;

    private Integer size;

    public List<FilterRequest> getFilters() {
        if (Objects.isNull(this.filters)) return new ArrayList<>();
        return this.filters;
    }

    public List<SortRequest> getSorts() {
        if (Objects.isNull(this.sorts)) return new ArrayList<>();
        return this.sorts;
    }

}
```

Đoạn mã trên định nghĩa một lớp Java có tên là `SearchRequest`, đại diện cho yêu cầu tìm kiếm trong truy vấn của bạn. Dưới đây là mô tả của từng phần trong lớp này:

1. **@Data, @Builder, @NoArgsConstructor, @AllArgsConstructor**: Các annotations này từ thư viện Lombok:
    - `@Data` tự động tạo các getter, setter, phương thức equals, hashCode và toString cho các trường dữ liệu của lớp.
    - `@Builder` tạo ra một builder pattern cho lớp, cho phép bạn tạo các đối tượng `SearchRequest` một cách dễ dàng và rõ ràng hơn.
    - `@NoArgsConstructor` tạo ra một constructor không có tham số.
    - `@AllArgsConstructor` tạo ra một constructor có tất cả các tham số.

2. **@JsonIgnoreProperties(ignoreUnknown = true)**: Annotation này của thư viện Jackson JSON, được sử dụng để bỏ qua các thuộc tính không được khai báo trong lớp khi chuyển đổi từ JSON thành đối tượng Java.

3. **@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)**: Annotation này của thư viện Jackson JSON, được sử dụng để chỉ định chiến lược đặt tên các thuộc tính khi chuyển đổi giữa JSON và Java. Trong trường hợp này, `SnakeCaseStrategy` được sử dụng để chuyển đổi tên thuộc tính từ kiểu camelCase sang snake_case.

4. **private List<FilterRequest> filters;**: Trường này đại diện cho danh sách các yêu cầu lọc trong truy vấn.

5. **private List<SortRequest> sorts;**: Trường này đại diện cho danh sách các yêu cầu sắp xếp trong truy vấn.

6. **private Integer page;**: Trường này đại diện cho trang hiện tại của kết quả truy vấn.

7. **private Integer size;**: Trường này đại diện cho kích thước của mỗi trang kết quả.

8. Phương thức `getFilters()` và `getSorts()` được định nghĩa để trả về một danh sách rỗng nếu trường tương ứng là null. Điều này giúp tránh được lỗi NullPointerException khi truy cập vào danh sách nếu trường không được khởi tạo.

Lớp `SearchRequest` này có thể được sử dụng để đại diện cho một yêu cầu tìm kiếm trong truy vấn của bạn, bao gồm các yêu cầu lọc, sắp xếp, trang và kích thước của kết quả truy vấn.

###### Generic Class Search Specification

```java
@Slf4j
@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = -9153865343320750644L;

    private final transient SearchRequest request;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);

        for (FilterRequest filter : this.request.getFilters()) {
            log.info("Filter: {} {} {}", filter.getKey(), filter.getOperator().toString(), filter.getValue());
            predicate = filter.getOperator().build(root, cb, filter, predicate);
        }

        List<Order> orders = new ArrayList<>();
        for (SortRequest sort : this.request.getSorts()) {
            orders.add(sort.getDirection().build(root, cb, sort));
        }

        query.orderBy(orders);
        return predicate;
    }

    public static Pageable getPageable(Integer page, Integer size) {
        return PageRequest.of(Objects.requireNonNullElse(page, 0), Objects.requireNonNullElse(size, 100));
    }

}
```

Đoạn mã trên định nghĩa một lớp Java có tên là `SearchSpecification`, là một implementation của `Specification` trong Spring Data JPA, được sử dụng để tạo ra các điều kiện tìm kiếm dựa trên yêu cầu tìm kiếm (SearchRequest). Dưới đây là mô tả của từng phần trong lớp này:

1. **@Slf4j**: Đây là một annotation từ thư viện Lombok, tự động tạo một logger private static final `log` cho lớp này.

2. **@AllArgsConstructor**: Đây là một annotation từ thư viện Lombok, tự động tạo một constructor có tất cả các tham số.

3. **private final transient SearchRequest request;**: Trường này lưu trữ yêu cầu tìm kiếm được truyền vào lớp `SearchSpecification`.

4. **@Override public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb)**: Phương thức này được ghi đè từ `Specification` interface. Nó tạo ra và trả về một Predicate, được sử dụng để tạo các điều kiện tìm kiếm trong truy vấn JPA.

5. Trong phương thức `toPredicate`, một Predicate được khởi tạo ban đầu với điều kiện luôn đúng (`cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE)`). Sau đó, vòng lặp duyệt qua danh sách các yêu cầu lọc trong `SearchRequest`, và mỗi yêu cầu lọc được chuyển cho operator tương ứng để xây dựng điều kiện tương ứng.

6. Danh sách các yêu cầu sắp xếp trong `SearchRequest` cũng được duyệt qua, và mỗi yêu cầu sắp xếp được chuyển cho hướng sắp xếp tương ứng để xây dựng các đối tượng `Order`.

7. Cuối cùng, danh sách các đối tượng `Order` được sử dụng để sắp xếp kết quả truy vấn thông qua phương thức `query.orderBy(orders)`.

8. Phương thức `getPageable(Integer page, Integer size)` là một phương thức tĩnh được sử dụng để tạo ra một đối tượng `Pageable` dựa trên trang và kích thước được chỉ định. Nếu giá trị `page` hoặc `size` là null, mặc định sẽ sử dụng giá trị 0 và 100.

Lớp `SearchSpecification` này là một phần quan trọng trong việc tạo ra các truy vấn linh hoạt và có thể mở rộng cho Spring Data JPA. Nó cho phép bạn xây dựng các điều kiện tìm kiếm và sắp xếp dựa trên các yêu cầu từ người dùng hoặc từ các phần tử khác của ứng dụng.

### Using Search Specification

##### Repository

```java
@Repository
public interface OperatingSystemRepository extends JpaRepository<OperatingSystem, Long>,
        JpaSpecificationExecutor<OperatingSystem> {
}
``` 

Đoạn mã trên định nghĩa một interface `OperatingSystemRepository`, là một repository Spring Data JPA được sử dụng để tương tác với bảng `OperatingSystem` trong cơ sở dữ liệu. Dưới đây là mô tả của từng phần trong interface này:

1. **@Repository**: Đây là một annotation của Spring, được sử dụng để đánh dấu interface này là một Spring bean và nó thực hiện vai trò của một repository.

2. **JpaRepository<OperatingSystem, Long>**: Interface `JpaRepository` là một phần của Spring Data JPA, cung cấp các phương thức để thực hiện các thao tác cơ bản như lưu, cập nhật, xóa và truy vấn dữ liệu trong cơ sở dữ liệu.
    - `OperatingSystem`: Đối tượng entity mà repository này sẽ làm việc với.
    - `Long`: Kiểu dữ liệu của trường ID của entity.

3. **JpaSpecificationExecutor<OperatingSystem>**: Interface `JpaSpecificationExecutor` cũng là một phần của Spring Data JPA, cho phép thực hiện truy vấn linh hoạt và động bằng cách sử dụng các đối tượng Specification.
    - `OperatingSystem`: Đối tượng entity mà repository này sẽ thực hiện truy vấn.

Interface `OperatingSystemRepository` này sẽ được Spring tự động tạo ra một implementation cụ thể tại runtime. Bằng cách này, bạn có thể sử dụng các phương thức được cung cấp sẵn để thực hiện các thao tác cơ bản và truy vấn phức tạp đối với đối tượng `OperatingSystem`.

##### Service 

```java
@Slf4j
@Service
public class OperatingSystemService {

    @Autowired
    private OperatingSystemRepository operatingSystemRepository;

    public Page<OperatingSystem> searchOperatingSystem(SearchRequest request) {
        SearchSpecification<OperatingSystem> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return operatingSystemRepository.findAll(specification, pageable);
    }

}
```

Đoạn mã trên định nghĩa một class `OperatingSystemService`, là một service Spring được sử dụng để thực hiện các logic liên quan đến `OperatingSystem`. Dưới đây là mô tả của từng phần trong class này:

1. **@Slf4j**: Đây là một annotation từ thư viện Lombok, tự động tạo một logger private static final `log` cho class này.

2. **@Service**: Đây là một annotation của Spring, được sử dụng để đánh dấu class này là một Spring bean và nó thực hiện vai trò của một service.

3. **@Autowired private OperatingSystemRepository operatingSystemRepository;**: Đây là một trường được chú thích bằng @Autowired, để Spring tự động tiêm vào một instance của `OperatingSystemRepository`. Điều này cho phép bạn sử dụng repository này để thực hiện các thao tác trên cơ sở dữ liệu liên quan đến `OperatingSystem`.

4. **public Page<OperatingSystem> searchOperatingSystem(SearchRequest request)**: Đây là phương thức được sử dụng để tìm kiếm các bản ghi `OperatingSystem` dựa trên yêu cầu tìm kiếm được cung cấp.
    - **SearchSpecification<OperatingSystem> specification = new SearchSpecification<>(request);**: Tạo một instance của `SearchSpecification` dựa trên yêu cầu tìm kiếm được cung cấp.
    - **Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());**: Tạo một `Pageable` object dựa trên trang và kích thước được chỉ định trong yêu cầu tìm kiếm.
    - **return operatingSystemRepository.findAll(specification, pageable);**: Sử dụng `operatingSystemRepository` để tìm tất cả các bản ghi `OperatingSystem` phù hợp với yêu cầu tìm kiếm và phân trang bằng cách sử dụng `findAll()` method. Kết quả trả về là một đối tượng `Page<OperatingSystem>`, chứa các bản ghi `OperatingSystem` trong trang hiện tại.

Class `OperatingSystemService` này cung cấp một phương thức để tìm kiếm các bản ghi `OperatingSystem` dựa trên yêu cầu tìm kiếm và phân trang, sử dụng các tiêu chuẩn và thao tác cơ sở dữ liệu thông qua `OperatingSystemRepository`.

##### Controller

```java
@Slf4j
@RestController
@RequestMapping(value = "/operating-system", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OperatingSystemController {

    @Autowired
    private OperatingSystemService operatingSystemService;

    @PostMapping(value = "/search")
    public Page<OperatingSystem> search(@RequestBody SearchRequest request) {
        return operatingSystemService.searchOperatingSystem(request);
    }

}
```

Đoạn mã trên định nghĩa một class `OperatingSystemController`, là một REST Controller trong ứng dụng của bạn. Dưới đây là mô tả của từng phần trong class này:

1. **@Slf4j**: Đây là một annotation từ thư viện Lombok, tự động tạo một logger private static final `log` cho class này.

2. **@RestController**: Đây là một annotation của Spring, kết hợp `@Controller` và `@ResponseBody`, giúp định nghĩa một RESTful controller để xử lý các yêu cầu HTTP và trả về các đối tượng dưới dạng JSON.

3. **@RequestMapping**: Đây là một annotation của Spring, được sử dụng để ánh xạ các yêu cầu HTTP tới một phương thức xử lý cụ thể hoặc một class controller. Trong trường hợp này, nó ánh xạ tất cả các yêu cầu đến `/operating-system` và chỉ chấp nhận các yêu cầu có định dạng JSON và trả về dữ liệu dưới dạng JSON.

4. **@Autowired private OperatingSystemService operatingSystemService;**: Đây là một trường được chú thích bằng `@Autowired`, để Spring tự động tiêm vào một instance của `OperatingSystemService`. Điều này cho phép bạn sử dụng service này để xử lý các yêu cầu từ client.

5. **@PostMapping(value = "/search")**: Đây là một phương thức được chú thích bằng `@PostMapping`, chỉ xử lý các yêu cầu POST tới `/operating-system/search`.

6. **public Page<OperatingSystem> search(@RequestBody SearchRequest request)**: Đây là phương thức xử lý yêu cầu tìm kiếm các bản ghi `OperatingSystem` dựa trên yêu cầu tìm kiếm được gửi từ client.
    - **@RequestBody SearchRequest request**: Annotation `@RequestBody` đánh dấu đối số này là một phần của dữ liệu gửi từ client và nó sẽ được deserialized từ JSON thành một đối tượng `SearchRequest`.
    - **return operatingSystemService.searchOperatingSystem(request)**: Gọi phương thức `searchOperatingSystem()` từ service `OperatingSystemService` để thực hiện tìm kiếm các bản ghi `OperatingSystem` dựa trên yêu cầu tìm kiếm và trả về kết quả dưới dạng `Page<OperatingSystem>`.

Class `OperatingSystemController` này là một phần của REST API của bạn và cung cấp một endpoint để tìm kiếm các bản ghi `OperatingSystem` thông qua gửi một yêu cầu tìm kiếm dưới dạng JSON.

### Test Search Sepecification

- Open `http://localhost:8080/api/h2-console`
- Execute this query to h2-console.
```sql
INSERT INTO operating_system (id, name, version, kernel, release_date, usages) VALUES (1, 'Arch Linux', '2022.03.01', '5.16.11', {ts '2022-03-01 00:10:00.69'}, 80);
INSERT INTO operating_system (id, name, version, kernel, release_date, usages) VALUES (2, 'Ubuntu', '20.04.4 LTS', '5.8', {ts '2022-02-22 00:10:00.69'}, 128);
INSERT INTO operating_system (id, name, version, kernel, release_date, usages) VALUES (3, 'Ubuntu', '21.10', '5.13', {ts '2022-01-28 00:10:00.69'}, 110);
INSERT INTO operating_system (id, name, version, kernel, release_date, usages) VALUES (4, 'CentOS', '7', '5.8', {ts '2020-11-12 00:10:00.69'}, 200);
INSERT INTO operating_system (id, name, version, kernel, release_date, usages) VALUES (5, 'CentOS', '8', '5.13', {ts '2021-11-12 00:10:00.69'}, 176);
INSERT INTO operating_system (id, name, version, kernel, release_date, usages) VALUES (6, 'EndeavourOS', '21.5', '5.15.8', {ts '2022-03-03 00:10:00.69'}, 93);
INSERT INTO operating_system (id, name, version, kernel, release_date, usages) VALUES (7, 'Deepin', '20.2.4', '5.13', {ts '2022-03-11 00:10:00.69'}, 76);
INSERT INTO operating_system (id, name, version, kernel, release_date, usages) VALUES (8, 'Deepin', '20.2.2', '5.8', {ts '2022-01-11 00:10:00.69'}, 121);
INSERT INTO operating_system (id, name, version, kernel, release_date, usages) VALUES (9, 'Red Hat', '7.9', '5.13', {ts '2022-02-01 00:10:00.69'}, 329);
INSERT INTO operating_system (id, name, version, kernel, release_date, usages) VALUES (10, 'Red Hat', '8', '5.16.11', {ts '2022-03-20 00:10:00.69'}, 283);
```
- Samples:

###### Without Filter and Sorting

```json
{
    "filters": [],
    "sorts": [],
    "page": null,
    "size": null
}
```

###### Filter by Name and Sort by Release Date ASC

```json
{
    "filters": [
        {
            "key": "name",
            "operator": "EQUAL",
            "field_type": "STRING",
            "value": "CentOS"
        }
    ],
    "sorts": [
        {
            "key": "releaseDate",
            "direction": "ASC"
        }
    ],
    "page": null,
    "size": null
}
```

###### Filter name not equal to CentOS

```json
{
    "filters": [
        {
            "key": "name",
            "operator": "NOT_EQUAL",
            "field_type": "STRING",
            "value": "CentOS"
        }
    ],
    "sorts": [
        {
            "key": "releaseDate",
            "direction": "ASC"
        }
    ],
    "page": null,
    "size": null
}
``` 

###### Filter name not equal to CentOS and size 1 response
```json
{
    "filters": [
        {
            "key": "name",
            "operator": "NOT_EQUAL",
            "field_type": "STRING",
            "value": "CentOS"
        }
    ],
    "sorts": [
        {
            "key": "releaseDate",
            "direction": "ASC"
        }
    ],
    "page": null,
    "size": 1
}
```

###### Filter name like and sort by release data DESC

```json
{
    "filters": [
        {
            "key": "name",
            "operator": "LIKE",
            "field_type": "STRING",
            "value": "Red"
        }
    ],
    "sorts": [
        {
            "key": "releaseDate",
            "direction": "DESC"
        }
    ],
    "page": null,
    "size": null
}
```

###### Filter kernel in

```json
{
    "filters": [
        {
            "key": "kernel",
            "operator": "IN",
            "field_type": "STRING",
            "values": ["5.13", "5.8"]
        }
    ],
    "sorts": [],
    "page": null,
    "size": null
}
```

###### Filter using between

```json
{
    "filters": [
        {
            "key": "releaseDate",
            "operator": "BETWEEN",
            "field_type": "DATE",
            "value": "01-03-2022 00:00:00",
            "value_to": "11-03-2022 23:59:59"
        }
    ],
    "sorts": [],
    "page": null,
    "size": null
}
```

###### Filter usages

```json
{
    "filters": [
        {
            "key": "usages",
            "operator": "BETWEEN",
            "field_type": "INTEGER",
            "value": 100,
            "value_to": 250
        }
    ],
    "sorts": [],
    "page": null,
    "size": null
}
```