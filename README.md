# Insurance Management System

## Project Context

In a fictional insurance company, a development team where you serve as a developer has been tasked with creating an application aimed at simplifying contract management for insurance clients. The goal is to allow users to easily access various services related to their insurance policies. Clients can manage their car, home, and health insurance online, enhancing the user experience and reducing administrative costs.

## Application Features

1. **Registration and Login:**
    - Users must create an account with personal information (name, address, email, phone number).

2. **Obtain Insurance Quotes:**
    - Users can request quotes by filling out a form with details about the desired type of insurance.
    - Quotes are calculated based on various criteria (age, property value, claims history, etc.).

3. **Contract Subscription:**
    - Once the quote is accepted, users can subscribe to the insurance contract by providing necessary documents (driving license, proof of residence, etc.).

4. **Contract Management:**
    - Users can modify their contract information (address, vehicle, etc.) or terminate their contract directly through the application.

## Quote Calculation Rules

### 1. Car Insurance
- **Parameters:**
    - Driver's age
    - Vehicle type (model, brand, type (luxury, utility, etc.))
    - Vehicle usage (personal, professional)
    - Driving history (accidents, infractions)

- **Example Calculation (per month):**
    - Base: 500 MAD
    - +10% if the driver is under 25 years old.
    - +15% for a luxury vehicle.
    - +10% for professional use.
    - -20% for a driver with no claims in the last 3 years; +10% otherwise.

### 2. Home Insurance
- **Parameters:**
    - Property value
    - Type of residence (apartment, house, etc.)
    - Location (risk zone)
    - Security system (alarm, cameras)

- **Example Calculation:**
    - Base: 300 MAD
    - +2% if the property is a house.
    - +5% if the residence is located in a risk zone.
    - +10% if the property's value exceeds 200,000 MAD.
    - -15% if the insured has a security system; +15% otherwise.

### 3. Health Insurance
- **Parameters:**
    - Age
    - Health status (medical history)
    - Coverage type (basic, premium)

- **Example Calculation:**
    - Base: 150 MAD
    - +20% for individuals over 60 years old.
    - +30% for a history of chronic diseases.
    - -10% for basic coverage; +5% for premium.

## Technologies and Concepts Used
- Spring Core
- Spring Web
- JSP
- JPA
- JUnit / Mockito

## Project Links
- **Project Presentation:** [View on Canva](https://www.canva.com/design/DAGUtQ0EG2k/7aYGUhbIiHx-hbga4lNFGA/edit?utm_content=DAGUtQ0EG2k&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
- **Jira Project Planning:** [Access Jira](https://youssefhihi.atlassian.net/jira/software/projects/IM/boards/12?atlOrigin=eyJpIjoiOTk5NjNjMzQ4YzkxNGJhNzlkYTlkZmIxZTEyZDBjOTYiLCJwIjoiaiJ9)

---

### Use Case Diagram
![Use Case Diagram](UML/insurancecases.png)

### Class Diagram
![Class Diagram](UML/insuranceclasses.png)

---

### Developer
[Youssef Hihi](https://github.com/youssefhihi)

