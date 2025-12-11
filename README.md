# 🏠 Hostel Booking System

A comprehensive web-based hostel booking management system built with Java, JSP, and MySQL. This system allows students to browse and book hostel rooms while providing administrators with tools to manage bookings, rooms, and student information.

## ✨ Features

### For Students
- **User Authentication**: Secure login system with role-based access
- **Room Browsing**: View available rooms with details (type, capacity, price, amenities)
- **Room Booking**: Book available rooms with check-in and check-out dates
- **Booking Management**: View and manage personal bookings
- **Booking Cancellation**: Cancel pending bookings
- **Student Dashboard**: Personalized dashboard with booking history

### For Administrators
- **Admin Dashboard**: Overview of all bookings, rooms, and students
- **Booking Approval**: Approve or reject student booking requests
- **Room Management**: View and manage room availability
- **Student Management**: View student information and booking history
- **Booking Analytics**: Track booking statuses and statistics

## 🛠️ Technology Stack

- **Backend**: Java 8, Java Servlets, JSP (JavaServer Pages)
- **Frontend**: HTML, CSS, JavaScript, Bootstrap Icons
- **Database**: MySQL 8.0
- **Build Tool**: Maven 3.9+
- **Application Server**: Apache Tomcat 9.0
- **Containerization**: Docker & Docker Compose

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 8 or higher
- **Maven**: Version 3.6 or higher
- **MySQL**: Version 8.0 or higher (if running without Docker)
- **Docker & Docker Compose**: (Optional, for containerized deployment)
- **Apache Tomcat**: Version 9.0 or higher (if running without Docker)

## 🚀 Getting Started

### Option 1: Using Docker (Recommended)

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/HostelBookingSystem.git
   cd HostelBookingSystem
   ```

2. **Update database credentials** (if needed)
   
   Edit `docker-compose.yml` and update the MySQL root password:
   ```yaml
   MYSQL_ROOT_PASSWORD: your_secure_password
   ```

3. **Build and start the application**
   ```bash
   docker-compose up --build
   ```

4. **Access the application**
   - Application: http://localhost:8080
   - MySQL: localhost:3306

### Option 2: Manual Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/HostelBookingSystem.git
   cd HostelBookingSystem
   ```

2. **Set up MySQL database**
   ```sql
   CREATE DATABASE hostel_booking_system;
   ```

3. **Configure database connection**
   
   Update the database connection details in:
   - `src/main/java/com/hostel/utils/DBConnection.java`
   - Or configure via environment variables if supported

4. **Build the project**
   ```bash
   mvn clean package
   ```

5. **Deploy to Tomcat**
   - Copy `target/HostelBookingSystem.war` to Tomcat's `webapps` directory
   - Start Tomcat server
   - Access the application at http://localhost:8080/HostelBookingSystem

### Option 3: Using Maven Jetty Plugin (Development)

1. **Run the application**
   ```bash
   mvn jetty:run
   ```

2. **Access the application**
   - Application: http://localhost:8080

## 📁 Project Structure

```
HostelBookingSystem/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/hostel/
│   │   │       ├── dao/              # Data Access Objects
│   │   │       │   ├── AdminDAO.java
│   │   │       │   ├── BookingDAO.java
│   │   │       │   ├── RoomDAO.java
│   │   │       │   └── StudentDAO.java
│   │   │       ├── model/            # Entity Models
│   │   │       │   ├── Admin.java
│   │   │       │   ├── Booking.java
│   │   │       │   ├── Room.java
│   │   │       │   └── Student.java
│   │   │       ├── service/           # Business Logic Layer
│   │   │       │   ├── AdminService.java
│   │   │       │   ├── BookingService.java
│   │   │       │   └── StudentService.java
│   │   │       ├── servlets/         # HTTP Request Handlers
│   │   │       │   ├── AdminServlet.java
│   │   │       │   ├── BookingServlet.java
│   │   │       │   ├── LoginServlet.java
│   │   │       │   └── ...
│   │   │       └── utils/            # Utility Classes
│   │   │           └── DBConnection.java
│   │   └── webapp/                   # Web Resources
│   │       ├── css/
│   │       │   └── style.css
│   │       ├── images/
│   │       ├── jsp/                  # JSP Pages
│   │       │   ├── index.jsp
│   │       │   ├── login.jsp
│   │       │   ├── student-dashboard.jsp
│   │       │   ├── admin-dashboard.jsp
│   │       │   └── ...
│   │       └── WEB-INF/
│   │           └── web.xml
├── pom.xml                           # Maven Configuration
├── Dockerfile                        # Docker Image Definition
├── docker-compose.yml               # Docker Compose Configuration
└── README.md
```

## 🗄️ Database Schema

The system uses the following main tables:

- **students**: Student information (registration number, name, email, course, etc.)
- **admins**: Administrator accounts
- **rooms**: Room details (room number, type, capacity, price, amenities)
- **bookings**: Booking records (student, room, dates, status, payment)

## 🔐 Default Credentials

⚠️ **Important**: Change default credentials before deploying to production!

- **Admin**: Configure in database
- **Student**: Register through the application

## 🎨 Screenshots

The application features a modern, responsive UI with:
- Clean and intuitive design
- Role-based dashboards
- Interactive room browsing
- Booking management interface
  ![WhatsApp Image 2025-11-03 at 00 54 08_bb3ded69](https://github.com/user-attachments/assets/c334b53d-dde4-4cdd-a31e-2c138abef2d9)
![WhatsApp Image 2025-11-03 at 00 54 40_e679a4c9](https://github.com/user-attachments/assets/e8c61f6a-b99d-465d-b10b-02249c222439)
![WhatsApp Image 2025-11-03 at 00 56 18_019d1612](https://github.com/user-attachments/assets/e77854fa-77bf-4f25-9c53-b90376f33cba)
![Uploading WhatsApp Image 2025-11-01 at 18.41.04_4ca1cb2c.jpg…]()


## 🔧 Configuration

### Environment Variables

When using Docker, you can configure the following environment variables:

- `DB_HOST`: Database host (default: `db`)
- `DB_PORT`: Database port (default: `3306`)
- `DB_NAME`: Database name (default: `hostel_booking_system`)
- `DB_USER`: Database user (default: `root`)
- `DB_PASS`: Database password (default: `change_me`)

## 📝 API Endpoints

### Student Endpoints
- `GET /` - Home/Login page
- `POST /login` - User authentication
- `GET /student` - Student dashboard
- `GET /rooms` - Browse available rooms
- `POST /book` - Create booking
- `GET /my-bookings` - View personal bookings
- `POST /cancel-booking` - Cancel a booking

### Admin Endpoints
- `GET /admin` - Admin dashboard
- `GET /bookings` - View all bookings
- `POST /approve-booking` - Approve a booking
- `POST /reject-booking` - Reject a booking

## 🧪 Testing

Run tests using Maven:

```bash
mvn test
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👤 Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)

## 🙏 Acknowledgments

- Bootstrap Icons for iconography
- Inter font family for typography
- Apache Tomcat community
- MySQL development team

## 📞 Support

If you have any questions or issues, please open an issue on the GitHub repository.

---

⭐ If you find this project helpful, please consider giving it a star!


