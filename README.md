# Management of appointments at a dental office
A Java implementation based on layered architecture principles for appointment management at a dental office. The entities in the problem domain are Patient (ID, first name, last name, age) and Appointment (ID, patient : Patient, date, time, purpose of appointment). Each appointment has exactly one patient associated with it, but a patient can have multiple appointments. Each appointment lasts 60 minutes.
- A2
     -  The user interface is implemented in the form of a console application with an user menu.
     -  The user interface allows performing CRUD operations on entities in the domain.
     -  Basic validations (e.g. objects in the repository must have a unique ID, an object was not found in the repository, validations related to possible overlaps of data in appointments and rentals) using the exception mechanism in Java. Exceptions thrown will be caught in the user interface, where an appropriate message will be displayed.
- A3
    - The requirements defined in topic A2 are kept.
    - New Repository classes for storing problem domain entities. One of these classes will store the entities in a text file and the other in a binary file. Also, the program can be started using any of these Repository implementations.
   -  The decision regarding the type of Repository used, as well as the location on disk of the input files is made through a settings file (settings.properties) that will be read by the program through the Properties class.
   - Unit tests using JUnit.
- A4
  - The requirements defined in topic A3 are kept.
  - A new Repository which allows the storage of domain entities in a SQL database. The decision regarding which type of repository is used will be made by using the settings.properties file, implemented within the A3 theme.
  - The graphical user interface implemented for the problem using JavaFX technology. The application can be started both in the command line and using the graphical interface.
  - With the help of Java 8 streams:
- The number of appointments for each individual patient. The patient's data and the total number of appointments for him are displayed. The display is done in descending order of the number of appointments.
- Total number of appointments for each month of the year. Each month of the year is displayed, as well as the total number of existing appointments for that month. The display is done in descending order of the number of appointments.
- The number of days since each patient's last appointment. The patient's data, the date of the last appointment and the number of days since the last appointment are displayed. The display is done in descending order of the number of days that have passed since the last appointment.
- The busiest months of the year. The months of the year are displayed, sorted in descending order by the number of appointments. The number of appointments for each month is also displayed.
