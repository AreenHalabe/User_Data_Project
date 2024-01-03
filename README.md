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
