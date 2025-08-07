# Spring Boot Todo Application

A modern, responsive Todo application built with Spring Boot, MySQL, and Thymeleaf.

## Features

- ✅ Create, read, update, and delete todos
- ✅ Mark todos as completed or pending
- ✅ Beautiful, responsive UI with Bootstrap 5
- ✅ Real-time statistics (total, pending, completed tasks)
- ✅ Filter todos by status (all, pending, completed)
- ✅ Form validation
- ✅ Flash messages for user feedback
- ✅ Modern gradient design with animations

## Technologies Used

- **Backend**: Spring Boot 3.1.0
- **Database**: MySQL 8.0
- **Frontend**: Thymeleaf, Bootstrap 5, Font Awesome
- **Build Tool**: Maven
- **Java Version**: 17

## Prerequisites

Before running the application, make sure you have:

1. **Java 17** or higher installed
2. **Maven 3.6+** installed
3. **MySQL 8.0+** running on your system

## Database Setup

1. Start your MySQL server
2. Create a database named `todoapp` (optional - the app will create it automatically)
3. Update the database credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

Default configuration assumes:
- Username: `root`
- Password: `password`
- Database: `todoapp` (created automatically)
- Port: `3306`

## Running the Application

1. **Clone or navigate to the project directory**

2. **Install dependencies and run the application**:
   ```bash
   mvn spring-boot:run
   ```

3. **Access the application**:
   Open your web browser and go to: `http://localhost:8080`

## Application Structure

```
src/
├── main/
│   ├── java/com/example/todoapp/
│   │   ├── TodoAppApplication.java          # Main application class
│   │   ├── entity/Todo.java                 # Todo entity/model
│   │   ├── repository/TodoRepository.java   # Data access layer
│   │   ├── service/TodoService.java         # Business logic layer
│   │   └── controller/TodoController.java   # Web controller
│   └── resources/
│       ├── application.properties           # Configuration
│       └── templates/                       # Thymeleaf templates
│           ├── index.html                   # Home page with add form
│           ├── todos.html                   # Filtered todos page
│           └── edit.html                    # Edit todo page
```

## API Endpoints

- `GET /` - Home page (all todos + add form)
- `GET /pending` - View pending todos
- `GET /completed` - View completed todos
- `POST /todos` - Create a new todo
- `GET /todos/{id}/edit` - Edit todo form
- `POST /todos/{id}` - Update todo
- `POST /todos/{id}/toggle` - Toggle todo completion status
- `POST /todos/{id}/delete` - Delete todo

## Features Overview

### Home Page (`/`)
- Displays all todos with statistics
- Add new todo form
- Action buttons for each todo (complete/pending, edit, delete)

### Filtered Views
- `/pending` - Shows only pending todos
- `/completed` - Shows only completed todos

### Todo Management
- **Create**: Add new todos with title and optional description
- **Read**: View all todos with creation/update timestamps
- **Update**: Edit todo title, description, and completion status
- **Delete**: Remove todos with confirmation

### UI Features
- Responsive design that works on desktop and mobile
- Modern gradient background and card-based layout
- Smooth animations and hover effects
- Bootstrap icons and styling
- Flash messages for user feedback
- Form validation with error messages

## Customization

### Database Configuration
Modify `src/main/resources/application.properties` to change database settings.

### Styling
The application uses Bootstrap 5 with custom CSS. You can modify the styles in the `<style>` sections of the HTML templates.

### Business Logic
Add new features by extending the `TodoService` class and adding corresponding controller methods.

## Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Ensure MySQL is running
   - Check database credentials in `application.properties`
   - Verify the database exists or enable auto-creation

2. **Port Already in Use**
   - Change the port in `application.properties`: `server.port=8081`

3. **Maven Build Issues**
   - Ensure Java 17+ is installed and configured
   - Run `mvn clean install` to refresh dependencies

## License

This project is open source and available under the MIT License.
