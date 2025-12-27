# Student Management System (SMS)

A Java-based Student Management System with admin authentication and complete CRUD operations.

## Project Structure

```
src/com.sms.main/
├── Main.java                 # Main application entry point
├── README.md                 # Project documentation
├── database/
│   └── sms.sql              # Database schema and setup
├── model/
│   ├── Student.java         # Student model class
│   └── Admin.java           # Admin model class
├── dao/
│   ├── StudentDAO.java      # Student DAO interface
│   ├── StudentDAOImpl.java   # Student DAO implementation
│   ├── AdminDAO.java        # Admin DAO interface
│   └── AdminDAOImpl.java     # Admin DAO implementation
└── util/
    └── DBConnection.java    # Database connection utility
```

## Features

✅ **Admin Authentication** - Secure login system
✅ **Student Management** - Add, view, search, update, delete students
✅ **Advanced Search** - Search by ID, name, or course
✅ **Admin Settings** - View profile and change password
✅ **Database Integration** - MySQL with JDBC
✅ **Error Handling** - Comprehensive error messages

## Setup Instructions

### 1. Database Setup
```sql
mysql -u root -p < src/com.sms.main/database/sms.sql
```

### 2. Compile Project
```bash
cd src/com.sms.main
javac -cp ".:mysql-connector-java.jar" *.java dao/*.java model/*.java util/*.java
```

### 3. Run Application
```bash
java -cp ".:mysql-connector-java.jar" Main
```

## Default Credentials
- **Username:** admin
- **Password:** admin123

## Database Configuration
Edit `util/DBConnection.java` to change:
- Database URL
- Username
- Password

## Requirements
- Java 16+
- MySQL 5.7+
- MySQL JDBC Driver (mysql-connector-java.jar)

## Usage

1. Login with admin credentials
2. Choose from menu options:
   - Add new student
   - View all students
   - Search student by ID
   - Update student information
   - Delete student
   - Search by name or course
   - Manage admin settings

## Error Handling
All database operations include error handling with descriptive messages printed to console.
