# User_Data_Project
## Data Deletion Feature :
We've introduced a new feature that allow users to delete their data based on user classification:

- **Soft Delete:** Removes all information except basic data required for the account to function in the system.
- **Hard Delete:** Erases all user related data while preventing any other user from using the same username.

We've emphasized the importance of flexible and reusable data deletion logic. By employing **factory** and **strategy** design patterns, new delete controllers can be effortlessly added without impacting clients.

1. **IDeleteDataController Interface:**
   - Defines a common API for deleting user data from different services.
   - Concrete controller classes implement this interface to delete data from specific services.

2. **ControllerDeleteFactory:**
   - Uses the factory pattern to instantiate appropriate controller objects based on delete type and user type.
   - Controller objects encapsulate the logic to delete data from their respective services, isolating this from client code. This demonstrates the strategy pattern.

3. **DeleteServices Class:**
   - Coordinates the overall deletion process.
   - Uses the factory to obtain controllers, then iterates through them to delete data using a common interface.
   - Adheres to best practices such as interface-based programming, the single responsibility principle, and custom exceptions for error handling.

### Authentication and Logging

- **Authentication:** Not required; the logger helps verify account ownership within DeleteServices.
- **Audit Trail:** When the ```Delete()``` function is called, the user's name and deletion type are logged to create an audit trail.
- **Verification:** Ensures the logged-in user matches the deletion username if not, an error is logged, and deletion is stopped to prevent unauthorized access.

### Logging and Exception Handling

- **Deletion Steps:** Each deletion step is logged with the user's name to associate account activity with authenticated users.
- **Exception Handling:** Any exceptions are logged with the user's name for troubleshooting purposes.

### Scalability and Flexibility

The feature is designed to be scalable by leveraging interfaces and dependency injection, providing a flexible foundation to adapt to various requirements.

Here is a consolidated summary:

The code enables converting data to PDF and ZIP file formats through reusable components. Specifically, the IPDFExportConverter interface defines the ability to convert data to PDF format and write to a file. The ExportPDFConverter class implements this using the iText library to convert text data from a file into a PDF document. Similarly, the IZIPExportConverter interface defines the ability to convert a file to ZIP format. The ExportZIPConverter class implements this to compress PDF files into ZIP archives using Java's ZIP API.

By encapsulating third-party libraries, these components provide reusable PDF and ZIP conversion capabilities to client code. The use of interfaces abstracts away these capabilities from their implementations.

This overall design follows several good object-oriented principles and patterns:

- Interface segregation principle to split up behaviors
- Single responsibility principle for focused classes  
- Dependency inversion for decoupled interfaces
- Adapter pattern to wrap libraries and provide common APIs
- Exception handling best practices

The key benefits are loose coupling, high cohesion and modular code. Adding new formats is easy by just adding another adapter class. The components isolate complexity and facilitate reuse and maintenance of conversion logic across the application.

Certainly! Here's a formatted version of the explanation for your Java README:

###﻿ File Storage Interface

The File Storage interface is chargeable for uploading information to various destinations. In our mission, we've applied the functionality to add facts to a neighborhood text document. To add a new upload technique, virtually create a category and put in force the FileStorage services.

### FetchDataController Interface

The FetchDataController interface is designed to fetch statistics about users from IAM, Payment, Posts, and Profile Services. To utilize this interface, create a controller for each provider. Additionally, if you wish to add a new service for the consumer, you only want to create a controller for that carrier and enforce it.

### Loggers Interfaces

The Loggers interfaces are designed to document every movement that occurs in our utility, consolidating the statistics into a unmarried object to decorate overall performance and decrease RAM usage
