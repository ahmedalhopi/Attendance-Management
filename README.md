# Attendance-Management

A project in Java and PostgreSQL databases to manage attendance and absence at the university.

## Installation

To install and run the project, follow these steps:

1. You need netbeans ide to run it correctlly.
2. Install postgreSQL database.
3. Open the project from netbeans ide.
4. Create new connection with database from services.
5. Right click on DataBase->NewConnection.
6. In driver select last option New Driver->Add.
7. Select the drivar file .jar from folder project->Driver->postgresql-42.6.0.jar then click ok then Next.
8. Write the database that you want to connect it.
9. set user postgres and password that you write it when you install the database.
10. then select schema that you must create a new schema that name "mang" to run the program correctlly then finish.


## Edit The Project

1. Go to DatabaseConnect.java to edit sitting for connection.
2. The url update value from your connection ,click rhigt on connection Properties->DataBase URL and copy it.
3. username set postgres and password that you write it when you install the database.


## Create Database

1. create a new database you can choose any name but the name will be same the name when you create connection with it.
2. create new schema that name must be "mang".
3. create the tables that sql for these in project folder->SQL Commands->Tables Commands.sql.
4. then you can insert a data to test the program from project folder->SQL Commands->Data Insert.sql.
