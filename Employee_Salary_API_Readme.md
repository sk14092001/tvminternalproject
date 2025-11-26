# Employee & Salary Management API

This project provides REST APIs to manage **Employee details** and **Salary records**. Users can create, update, view, and delete employee records, as well as add or view salary information.

---

## **Base URL**

```
http://localhost:8080/api
```
## **Swagger**

```
http://localhost:8080/swagger-ui/index.html

```


---

## **1️⃣ Create Employee**

**Endpoint**

```
POST /employees
```

**Request Body**

```json
{
  "employeeCode": "EMP001",
  "name": "John Doe",
  "designation": "Developer",
  "location": "Chennai",
  "joiningDate": "2020-06-01",
  "panNo": "ABCDE1234F",
  "dob": "1990-05-10",
  "bankAccount": "1234567890"
}
```

**cURL**

```bash
curl -X POST "http://localhost:8080/api/employees"      -H "Content-Type: application/json"      -d '{
           "employeeCode": "EMP001",
           "name": "John Doe",
           "designation": "Developer",
           "location": "Chennai",
           "joiningDate": "2020-06-01",
           "panNo": "ABCDE1234F",
           "dob": "1990-05-10",
           "bankAccount": "1234567890"
         }'
```

---

## **2️⃣ Get Employee Details by Employee Code**

**Endpoint**

```
GET /employees/{employeeCode}
```

**cURL**

```bash
curl -X GET "http://localhost:8080/api/employees/EMP001"
```

---

## **3️⃣ Update Employee Details**

**Endpoint**

```
PUT /employees/{employeeCode}
```

**Request Body** (update only necessary fields)

```json
{
  "name": "John Doe",
  "designation": "Senior Developer",
  "location": "Chennai",
  "panNo": "ABCDE1234F",
  "bankAccount": "1234567890",
  "dob": "1990-05-10",
  "joiningDate": "2020-06-01"
}
```

**cURL**

```bash
curl -X PUT "http://localhost:8080/api/employees/EMP001"      -H "Content-Type: application/json"      -d '{
           "name": "John Doe",
           "designation": "Senior Developer",
           "location": "Chennai",
           "panNo": "ABCDE1234F",
           "bankAccount": "1234567890",
           "dob": "1990-05-10",
           "joiningDate": "2020-06-01"
         }'
```

---

## **4️⃣ Delete Employee**

**Endpoint**

```
DELETE /employees/{employeeCode}
```

**cURL**

```bash
curl -X DELETE "http://localhost:8080/api/employees/EMP001"
```

**Response Example**

```json
{
  "message": "Employee deleted successfully: EMP001"
}
```

---

## **5️⃣ Add or Update Employee Salary**

**Endpoint**

```
POST /employees/{employeeCode}/salaries
```

**Request Body**

```json
{
  "year": 2025,
  "salaryMonth": "October",
  "nwd": 22,
  "nol": 2,
  "basicSalary": 50000,
  "houseRentAllowance": 10000,
  "medicalAllowance": 2000,
  "conveyanceAllowance": 1500,
  "flexiBenefitPlan": 2500,
  "leaveTravelAllowance": 3000,
  "specialAllowance": 2000,
  "professionalTax": 200,
  "incomeTax": 5000,
  "leaveDeductions": 0,
  "otherDeductions": 0
}
```

**cURL**

```bash
curl -X POST "http://localhost:8080/api/employees/EMP001/salaries"      -H "Content-Type: application/json"      -d '{
           "year": 2025,
           "salaryMonth": "October",
           "nwd": 22,
           "nol": 2,
           "basicSalary": 50000,
           "houseRentAllowance": 10000,
           "medicalAllowance": 2000,
           "conveyanceAllowance": 1500,
           "flexiBenefitPlan": 2500,
           "leaveTravelAllowance": 3000,
           "specialAllowance": 2000,
           "professionalTax": 200,
           "incomeTax": 5000,
           "leaveDeductions": 0,
           "otherDeductions": 0
         }'
```

---

## **6️⃣ Get All Salary Records for an Employee**

**Endpoint**

```
GET /employees/{employeeCode}/salaries
```

**cURL**

```bash
curl -X GET "http://localhost:8080/api/employees/EMP001/salaries"
```

---

## **7️⃣ Get Employee Salary for Specific Month**

**Endpoint**

```
GET /employees/{employeeCode}/salaries/{year}/{month}
```

**Example**

```
GET /employees/EMP001/salaries/2025/October
```

**cURL**

```bash
curl -X GET "http://localhost:8080/api/employees/EMP001/salaries/2025/October"
```

---

## **8️⃣ Get All Employees with Salary Details**

**Endpoint**

```
GET /employees-with-salaries
```

**cURL**

```bash
curl -X GET "http://localhost:8080/api/employees-with-salaries"
```

### **9️⃣ Dashboard Summary**
curl --location 'http://localhost:8080/api/dashboard' \
--header 'Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJzYW5qaXYiLCJpYXQiOjE3NjQxNTAwMTcsImV4cCI6MTc2NDE1MzYxN30.-674uE5LZA5cSkbkxALf64LK7fJZKIZh_Cb_lEx0ChGKQJyENnVAeVCRHGW6zvFB'