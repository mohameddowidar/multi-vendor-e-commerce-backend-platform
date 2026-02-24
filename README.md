# Multi‑Vendor E‑Commerce Backend Platform (v1)

**Tech Stack:** Spring Boot • Hibernate / Spring Data JPA • Oracle • REST APIs  
**Date:** 2026-02-19  

---

## 1. Purpose and Audience

This architecture document consolidates system requirements, database model, workflows, and API specifications for a **multi‑vendor e‑commerce backend**.  
It is designed as an **end-to-end guide for a junior Java developer**.

---

## 2. Architecture Overview

### 2.1 High-level Components

| Layer | Responsibilities |
|-------|----------------|
| **API Layer (Controllers)** | REST endpoints, request validation, authentication entry points |
| **Application Layer (Services)** | Business rules, transaction boundaries, orchestration (checkout, approval) |
| **Persistence Layer (Repositories)** | Spring Data JPA repositories, custom queries, pessimistic locking |
| **Domain Model** | Entities, enums, value objects, status machines |
| **Security** | JWT authentication, RBAC, tenant scoping by `vendor_id` / `customer_id` |
| **Observability** | Structured logs, correlation ID, exception handling |

---

### 2.2 Key Design Decisions

- **Stock consistency:** Uses database transactions + pessimistic inventory locks during checkout.  
- **Idempotent checkout:** `Idempotency-Key` prevents duplicate orders on retries.  
- **Multi-vendor support:** Each `OrderItem` stores `vendor_id` for vendor-level fulfillment.  
- **Product visibility:** Only `ACTIVE` products are visible to customers.

---

## 3. Database Design

### 3.1 Entity Relationship Diagram (ERD)

*(Include a diagram image here, e.g., `erd.png` in your repo)*  

**Core tables:**

| Table | Description |
|-------|------------|
| `users` | Customer and vendor accounts, roles, credentials |
| `roles` | Defines role-based access control |
| `products` | Product catalog with status, pricing, and vendor_id |
| `orders` | Stores orders with order-level info |
| `order_items` | Line items for each order, linked to `vendor_id` |
| `inventory` | Tracks stock per product with pessimistic locking |
| `transactions` | Checkout and payment transaction logs |

---

## 4. API Layer Overview

| Endpoint | Method | Description |
|----------|--------|------------|
| `/auth/login` | POST | JWT-based authentication |
| `/products` | GET | Fetch active products |
| `/orders` | POST | Create new order (idempotent) |
| `/orders/{id}` | GET | Fetch order details |
| `/vendors/{id}/products` | GET | Vendor-specific product listing |

---

## 5. Security and Multi-Tenancy

- JWT-based authentication for API requests  
- Role-based access control (RBAC)  
- Tenant scoping using `vendor_id` for vendor operations and `customer_id` for customer actions  

---

## 6. Observability

- Structured logs for requests, responses, and errors  
- Correlation ID to trace requests across services  
- Global exception handling for standardized error responses  

---

## 7. Notes

- All business-critical operations (checkout, inventory update) use **database transactions** to ensure consistency.  
- REST APIs are designed for **idempotency and reliability**.  
- Database design supports **multi-vendor e-commerce** with clear separation of vendor and product ownership.  

---

## Author

**Mohamed Dowidar**