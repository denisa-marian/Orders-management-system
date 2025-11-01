# Orders-management-system

# Orders Management System – Java, MySQL, Swing

A layered **warehouse orders management** application built in **Java** with **Swing** UI and
a **MySQL** relational database. The project demonstrates clean OOP design, reflection-based
data access, and a dynamic JTable renderer.

## Features
- **Layered Architecture**: `presentation`, `business`, `dataaccess`, `model`
- **CRUD** for **Client**, **Product**, **Order**
- **Swing GUI**: windows to add/edit/delete and tables (JTable) to view entities
- **Reflection**:
  - `GenericDAO<T>` that builds SQL queries dynamically from model classes
  - Table header + rows generated at runtime from object properties
- **Bill** as an **immutable Java `record`** stored in a `Log` table (insert/read only)
- **Streams & Lambdas** for filtering/sorting/aggregations
- **Stock check & under-stock message** when creating orders

## Tech
- Java 17 (works with 11+)
- MySQL 8.x
- Swing
- (Maven or Gradle) – choose one and update this section accordingly

## Database
Run `sql/dump.sql` to create and populate:
- `Client(id, name, email, address, ...)`
- `Product(id, name, price, stock, ...)`
- `Order(id, clientId, productId, quantity, createdAt, ...)`
- `Log(id, billText, createdAt)` – append-only (for `Bill` records)

Configure DB in `src/main/resources/db.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/oms?useSSL=false&serverTimezone=UTC
db.user=root
db.password=your_password
