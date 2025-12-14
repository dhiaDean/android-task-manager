# 📱 Android Task Manager

![Android](https://img.shields.io/badge/Android-Mobile-green.svg)
![Java](https://img.shields.io/badge/Java-11-blue.svg)
![SQLite](https://img.shields.io/badge/SQLite-Database-blue.svg)


A native Android application for personal task management with user authentication, task categorization, and status tracking. Built with Java and SQLite for local data persistence.

---

## 🎯 Project Purpose

This Android application provides a complete task management solution, enabling users to organize their daily activities efficiently. The app features secure user authentication, allowing each user to create, view, edit, and delete tasks with custom categories and status indicators.

---

## ✨ Features

- **🔐 User Authentication**
  - Secure registration with email and password
  - Login system with credential validation
  - User data persistence in SQLite database

- **📋 Task Management**
  - Create new tasks with title, description, category, and status
  - View all tasks in a card-based layout
  - Edit existing tasks (name, description, category, status)
  - Delete tasks with confirmation
  - Task categorization: Personal or Public
  - Status tracking: Completed or Pending
  - Color-coded task status indicators

- **🧭 Navigation**
  - Navigation drawer for easy app navigation
  - Intuitive user interface with Material Design components
  - Floating action button for quick task creation

- **💾 Data Persistence**
  - Local SQLite database storage
  - Automatic data persistence across app sessions
  - Efficient database operations for CRUD functionality

---

## 🛠️ Tech Stack

- **Platform**: Android SDK (API 28+)
- **Language**: Java 8
- **Database**: SQLite
- **UI Framework**: Android Material Design Components
- **Architecture**: Standard Android Activity-based architecture
- **Build System**: Gradle (Kotlin DSL)

### Key Libraries & Dependencies

- `androidx.appcompat` - App compatibility library
- `com.google.android.material` - Material Design components
- `androidx.recyclerview` - RecyclerView for efficient list rendering
- `androidx.cardview` - CardView for task card UI

---

## 📦 Installation / Setup

### Prerequisites

- **Android Studio** (latest version recommended)
- **Android SDK** (API 28 or higher)
- **Java Development Kit (JDK)** 8 or higher
- **Android device** or **Android Virtual Device (AVD)** emulator

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/dhiaDean/android-task-manager.git
   cd android-task-manager
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select `Open an existing Android Studio project`
   - Navigate to the cloned repository directory and select it

3. **Sync Gradle**
   - Android Studio will automatically sync Gradle dependencies
   - If prompted, update any outdated plugins or SDK components
   - Wait for the sync to complete

4. **Run the application**
   - Connect an Android device via USB (with USB debugging enabled) or start an AVD emulator
   - Click the `Run` button (green triangle) in Android Studio toolbar
   - Select your target device/emulator
   - The app will build and install automatically

---

## 🚀 Usage

1. **Registration**
   - Launch the app
   - Tap "Sign Up" on the main screen
   - Fill in your details: First Name, Last Name, Phone, Email, and Password
   - Confirm your password and agree to terms and conditions
   - Tap "Register" to create your account

2. **Login**
   - Enter your registered email and password
   - Tap "Login" to access the home screen

3. **View Tasks**
   - After logging in, view all your tasks on the home screen
   - Tasks are displayed as cards with color-coded status indicators
   - Green indicates "Completed", Red indicates "Pending"

4. **Add Task**
   - Tap the floating action button (FAB) at the bottom right
   - A new sample task will be created (in a production version, this would open a form)

5. **View Task Details**
   - Tap on any task card to view its full details
   - See task name, description, category, and status

6. **Edit Task**
   - Open a task's detail view
   - Tap "Edit Task" button
   - Modify the task name, description, category, or status
   - Tap "Save" to update the task

7. **Delete Task**
   - Open a task's detail view
   - Tap "Delete Task" button
   - The task will be removed from the database

8. **Navigation**
   - Use the navigation drawer (hamburger menu) to navigate between sections
   - Access Home, Tasks, and Settings from the drawer menu

---

## 📸 Screenshots

_Placeholder for app screenshots_

![App Icon](app/src/main/res/drawable/app_image.png)

---

## 📄 License & Credits

### License

This project is distributed under the MIT License. See `LICENSE` file for more information.



### Project Credits

- **Developer**: Dean
- **Contact**: [jouablidean@gmail.com](mailto:jouablidean@gmail.com)
- **Project Link**: [https://github.com/dhiaDean/android-task-manager](https://github.com/dhiaDean/android-task-manager)

---

## 📝 Notes

- This application uses local SQLite database storage. All data is stored on the device.
- User passwords are stored in plain text (not recommended for production - should use hashing/encryption).
- The app requires Android API level 28 (Android 9.0) or higher.

---

