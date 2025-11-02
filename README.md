# Orders-management-system

# Orders Management System – Java, MySQL

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
- Java 
- MySQL 
- Swing

## Database
- `Client(id, name, email, address, ...)`
- `Product(id, name, price, stock, ...)`
- `Order(id, clientId, productId, quantity, createdAt, ...)`
- `Log(id, billText, createdAt)` – append-only (for `Bill` records)
