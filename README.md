# InvestWise - Portfolio Management System

A comprehensive investment portfolio management system built with Java, demonstrating advanced software engineering principles and design patterns.


> **📋 Documentation Links:**
> - **Software Requirements Specification (SRS)**: [View Document](https://docs.google.com/document/d/1nqc9T-SO8n3cvxU3MlIWucNkhHrnu4r7kPHkeLconnc/edit?tab=t.0#heading=h.gjdgxs)
> - **Software Design Specification (SDS)**: [View Document](https://docs.google.com/document/d/1zqokjS6AvUUxYiVnFRhJ1YVYdtlt0UfN/edit?usp=drivesdk&ouid=105722686532724388161&rtpof=true&sd=true)

## 🚀 Features

### Core Functionality
- **User Authentication**: Secure registration and login system with password validation
- **Portfolio Management**: Create, view, edit, and delete investment portfolios
- **Asset Management**: Support for multiple asset types (Stocks, Crypto, Real Estate, Gold)
- **Data Persistence**: Serialized file-based database system
- **Real-time Asset Operations**: Add, edit, remove, and view assets within portfolios

### Asset Types Supported
- **Stocks**: Track stock symbols, exchanges, and market data
- **Cryptocurrency**: Monitor crypto symbols and trading exchanges
- **Real Estate**: Manage property locations and types
- **Gold**: Track karat purity and weight measurements
- **Zakat Calculation**: Islamic finance compliance for applicable assets

## 🏗️ SOLID Principles Implementation

### Single Responsibility Principle (SRP)
- **AuthService**: Handles only authentication logic
- **PortfolioManager**: Manages portfolio operations exclusively
- **AssetFactory**: Responsible solely for asset creation
- **Database classes**: Each handles persistence for one entity type

### Open/Closed Principle (OCP)
- **Asset hierarchy**: New asset types can be added without modifying existing code
- **Database abstraction**: New database implementations can be added easily
- **Authentication system**: Extensible for new authentication methods

### Liskov Substitution Principle (LSP)
- **Asset subclasses**: All concrete assets (Stocks, Crypto, etc.) can substitute the base Asset class
- **User hierarchy**: Investor can substitute User in all contexts
- **Database implementations**: All database types are interchangeable

### Interface Segregation Principle (ISP)
- **Serializable interface**: Classes implement only required serialization methods
- **Scanner usage**: Minimal interface exposure for user input operations

### Dependency Inversion Principle (DIP)
- **Database abstraction**: High-level modules depend on Database<T> abstraction
- **AuthUI dependency**: Depends on AuthService abstraction, not concrete implementation
- **Portfolio operations**: Depend on abstract Asset class, not concrete implementations

## 🎨 Design Patterns

### 1. Factory Pattern
```java
// AssetFactory creates different asset types based on user input
Asset asset = AssetFactory.createAsset();
```
**Benefits**: Centralizes object creation, easy to extend with new asset types

### 2. Template Method Pattern
```java
// Asset.editAsset() defines the algorithm structure
public final void editAsset() {
    // Common editing steps
    editSpecificDetails(scanner); // Subclass-specific implementation
}
```
**Benefits**: Code reuse, consistent behavior across asset types

### 3. Singleton Pattern (Database)
```java
// Each database type maintains single instance per application
private static final UserDatabase instance = new UserDatabase();
```
**Benefits**: Ensures data consistency, prevents resource conflicts

### 4. Strategy Pattern (Asset-specific operations)
- Different assets implement their own editing strategies
- Polymorphic behavior for asset-specific operations

## 🏛️ Architecture

### Layered Architecture
```
┌─────────────────────────────────────┐
│           Presentation Layer        │
│        (AuthUI, PortfolioManager)   │
├─────────────────────────────────────┤
│            Business Layer           │
│     (AuthService, Portfolio, Asset) │
├─────────────────────────────────────┤
│           Persistence Layer         │
│        (Database, FileSystem)       │
└─────────────────────────────────────┘
```

### Package Structure
```
src/
├── Authentication/
│   ├── AuthService.java
│   ├── AuthUI.java
│   └── User.java
├── Portfolio/
│   ├── Portfolio.java
│   ├── PortfolioManager.java
│   └── PortfolioDatabase.java
├── Assets/
│   ├── Asset.java (Abstract)
│   ├── AssetFactory.java
│   ├── Stocks.java
│   ├── Crypto.java
│   ├── RealState.java
│   └── Gold.java
└── Database/
    ├── Database.java (Abstract)
    └── UserDatabase.java
```

## 🔧 Technical Implementation

### Object-Oriented Programming
- **Inheritance**: Asset hierarchy with specialized implementations
- **Polymorphism**: Runtime type determination for assets
- **Encapsulation**: Private fields with controlled access methods
- **Abstraction**: Abstract classes defining contracts

### Error Handling & Validation
- **Input Validation**: Comprehensive user input validation
- **Exception Handling**: Graceful error recovery
- **Password Security**: Regex-based password strength validation
- **Business Logic Validation**: Email format, username uniqueness

### Data Persistence
- **Serialization**: Object serialization for data persistence
- **File-based Storage**: Efficient file I/O operations
- **Database Abstraction**: Generic database operations


## 🚦 Getting Started

### Prerequisites
- Java JDK 8 or higher
- VS Code with Java Extension Pack

### Installation
1. Clone the repository
2. Open in VS Code
3. Compile: `javac -d bin src/*.java`
4. Run: `java -cp bin App`

### Usage
1. Register a new account or login
2. Create a new portfolio
3. Add assets to your portfolio
4. Manage and track your investments

## 📁 Folder Structure

- `src/`: Source code files
- `bin/`: Compiled output files
- `lib/`: External dependencies
- `database/`: Serialized data files

## 🎯 Software Engineering Best Practices

- **Code Documentation**: Comprehensive Javadoc comments
- **Naming Conventions**: Clear, descriptive variable and method names
- **Code Organization**: Logical package structure
- **Version Control**: Git-based development workflow
- **Testing Strategy**: Unit testable design
- **Security**: Password encryption and validation

## 🔮 Future Enhancements

- Database integration (MySQL/PostgreSQL)
- REST API implementation
- Web-based user interface
- Real-time market data integration
- Advanced portfolio analytics
- Mobile application support

---

**Note**: This project demonstrates mastery of software engineering principles including SOLID design, design patterns, and clean architecture practices.