# Auto Service Management System

Auto Service Management System is a Java application designed to manage auto repair and service records. It is built to be used by different types of users, including administrators, clients, staff, and technicians. The application provides features for managing users, vehicles, repairs, parts, and more.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [User Roles](#user-roles)
- [File Structure](#file-structure)



## Features

- **User Management**: Administrators can add, update, and delete users. Users have different roles, including admin, staff, technician, and client.

- **Vehicle Management**: Staff can take in vehicles and assign them to technicians. Clients can view their vehicles.

- **Repair Management**: Technicians can mark repairs as completed, and staff can create repair orders. Clients can view their repairs and payments.

- **Part Management**: Technicians can order parts for repairs.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed.
- A Java Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse.
- A basic understanding of Java programming.

## Installation

1. Open the project in your preferred Java IDE.

## Usage

1. Run the `Main` class to start the application.
2. You will be prompted to log in. Use the provided username and password based on your role (admin, staff, technician, or client).

## User Roles

- **Admin**: Administrators can manage users and oversee the entire system.
- **Staff**: Staff can handle vehicle management, including taking in vehicles and assigning them to technicians.
- **Technician**: Technicians can manage repairs, mark them as completed, and order parts.
- **Client**: Clients can view their vehicles and repair history.

## File Structure

The project is organized into the following main packages:

- `models`: Contains classes for defining the data model, such as users, vehicles, repairs, and parts.
- `services`: Includes services for managing various aspects of the system, like users, repairs, and parts.
- `menus`: Provides user interfaces and menus for different user roles.
- `data`: Handles data storage and file operations.
- `resources`: Contains any additional resources, such as data files.

Author: Angelov

