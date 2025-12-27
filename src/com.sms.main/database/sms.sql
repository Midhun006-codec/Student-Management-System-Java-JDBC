-- =====================================================
-- STUDENT MANAGEMENT SYSTEM - DATABASE SCHEMA
-- =====================================================

-- Drop existing database if it exists
DROP DATABASE IF EXISTS sms;

-- Create Database
CREATE DATABASE sms;
USE sms;

-- =====================================================
-- ADMINS TABLE
-- =====================================================
CREATE TABLE admins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- =====================================================
-- STUDENTS TABLE
-- =====================================================
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(10) NOT NULL,
    course VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- =====================================================
-- CREATE INDEXES FOR BETTER PERFORMANCE
-- =====================================================
CREATE INDEX idx_student_name ON students(name);
CREATE INDEX idx_student_course ON students(course);
CREATE INDEX idx_student_email ON students(email);
CREATE INDEX idx_admin_username ON admins(username);
CREATE INDEX idx_admin_email ON admins(email);

-- =====================================================
-- INSERT DEMO ADMIN DATA
-- =====================================================
INSERT INTO admins (username, password, email) VALUES 
('admin', 'admin123', 'admin@sms.com'),
('manager', 'manager123', 'manager@sms.com'),
('supervisor', 'supervisor123', 'supervisor@sms.com');

-- =====================================================
-- INSERT DEMO STUDENT DATA
-- =====================================================
INSERT INTO students (name, email, phone, course) VALUES 
('Rajesh Kumar', 'rajesh.kumar@email.com', '9876543210', 'B.Tech - CSE'),
('Priya Singh', 'priya.singh@email.com', '9876543211', 'B.Tech - ECE'),
('Amit Patel', 'amit.patel@email.com', '9876543212', 'B.Tech - Mechanical'),
('Neha Sharma', 'neha.sharma@email.com', '9876543213', 'B.Tech - CSE'),
('Vikram Reddy', 'vikram.reddy@email.com', '9876543214', 'B.Tech - Civil'),
('Anjali Verma', 'anjali.verma@email.com', '9876543215', 'B.Tech - ECE'),
('Rohan Gupta', 'rohan.gupta@email.com', '9876543216', 'B.Tech - CSE'),
('Divya Nair', 'divya.nair@email.com', '9876543217', 'B.Tech - Mechanical'),
('Sanjay Rao', 'sanjay.rao@email.com', '9876543218', 'B.Tech - Civil'),
('Pooja Desai', 'pooja.desai@email.com', '9876543219', 'B.Tech - ECE'),
('Arjun Singh', 'arjun.singh@email.com', '9876543220', 'B.Tech - CSE'),
('Kavya Menon', 'kavya.menon@email.com', '9876543221', 'B.Tech - Mechanical'),
('Nikhil Joshi', 'nikhil.joshi@email.com', '9876543222', 'B.Tech - Civil'),
('Shreya Iyer', 'shreya.iyer@email.com', '9876543223', 'B.Tech - ECE'),
('Varun Kapoor', 'varun.kapoor@email.com', '9876543224', 'B.Tech - CSE');

-- =====================================================
-- VERIFY DATA
-- =====================================================
SELECT 'Admin Records:' AS Info;
SELECT * FROM admins;

SELECT 'Student Records:' AS Info;
SELECT * FROM students;

SELECT CONCAT('Total Students: ', COUNT(*)) AS Statistics FROM students;
SELECT CONCAT('Total Admins: ', COUNT(*)) AS Statistics FROM admins;
