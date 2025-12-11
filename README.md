# Hostel-Booking-Website
Campus Hostel booking website
# 🏫 Campus Hostel Booking System

Welcome to **Campus Hostel** — a full-featured 💻 web app for managing hostel room bookings in universities!
Built with **Java Servlets/JSP**, **MySQL**, and **Apache Tomcat**, it provides secure logins for both **Students** and **Admins**, featuring a modern glassmorphic UI and real-time booking updates.

---

## 🌟 Features

### 👥 Student & Admin Login

* 🔐 Secure authentication with role-based access
* 🧑‍🎓 Student dashboard for tracking bookings
* 🧑‍💼 Admin dashboard for managing rooms, hostels, and students

### 🏠 Room Booking

* 🛏️ Browse and filter rooms by type, floor, and availability
* 📸 View amenities, prices (₹), and real room images
* ⚡ Book rooms instantly with live status updates

### 📊 Admin Dashboard

* 🧩 Manage hostels, rooms, and student data
* ✅ Approve/reject bookings
* 🔔 View and handle notifications

### 💡 Student Dashboard

* 📅 Check booking status in real time
* 🏡 Track available and personal rooms

### 💻 Responsive UI

* ✨ Sleek glassmorphic design
* 📱 Works on mobile, tablet, and desktop
* 🖌️ Easy customization via CSS

---

## 🧰 Tech Stack

| Technology                                        | Purpose               |
| ------------------------------------------------- | --------------------- |
| ☕ **Java 8+**                                     | Core backend          |
| 🧱 **Apache Tomcat 9+**                           | Web server            |
| 🪄 **JSP & Servlets**                             | Dynamic web pages     |
| 🔗 **JDBC (MySQL Connector)**                     | Database connectivity |
| 🗄️ **MySQL 5.7+/8+**                             | Data storage          |
| 🎨 **HTML5, CSS3, Bootstrap Icons, Google Fonts** | Frontend design       |

---

## ⚙️ Setup Instructions

### 🧭 Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/hostel-booking-system.git
```

### 🗃️ Database Setup

1. Ensure MySQL is running.
2. Execute `/sql/hostel_booking_schema.sql` to create tables and sample data.

### 🔐 Backend Configuration

Edit your MySQL credentials in `DBConnection.java`:

```java
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/hostel_booking_system",
    "root",
    "YOUR_PASSWORD"
);
```

💡 *Tip: Use a non-root user for production.*

### 🚀 Build & Deploy

If you use **Maven**:

```bash
mvn clean package
```

Then deploy the generated WAR file to Tomcat’s `/webapps` folder.

### 🌐 Run the App

Visit → [http://localhost:8080/HostelBookingSystem/](http://localhost:8080/HostelBookingSystem/)

---

## 📁 Folder Structure

```
/src/main/java       -> Servlets, Models, DAO  
/src/main/webapp     -> JSPs, HTML, CSS, images  
/sql                 -> Database schema & data  
```

---

## 🖼️ Screenshots


📸 Example:
<img width="1914" height="998" alt="Screenshot 2025-10-09 004256" src="https://github.com/user-attachments/assets/19de81b1-7e50-4d7d-9593-03ceff0f5d7c" />


---

## 🎨 Customization

* 🪪 Replace logos and gallery images in `/images`
* 🎨 Edit `/css/style.css` to tweak colors and fonts
* 🏢 Add new amenities, floor levels, or room types via the database

---

## 👩‍💻 Developer

**Developed by:** *Amrita Hariharan*
💡 *Design inspiration:* [Unsplash](https://unsplash.com) & [Bootstrap Icons](https://icons.getbootstrap.com)

---

## 📜 License

This project is for **educational purposes**.
For commercial use, please contact the author for permission.

---

## 🤝 Contribute

Fork it 🍴 | Star it ⭐ | Improve it 🚀
Pull requests are always welcome!

> “Great software isn’t just about code — it’s about creating something that simplifies life.” 🌍







