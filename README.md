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


## Usage

After successfully installing the project, you can use the following commands:

- `npm start`: Starts the application in development mode.
- `npm test`: Runs the project's test suite.
- `npm run build`: Builds the project for production deployment.

Make sure to update the configuration file `config.js` with your specific settings before running the project.

## Contributing

Contributions are welcome! If you want to contribute to this project, please follow these guidelines:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/my-feature`
3. Make your changes and commit them: `git commit -m 'Add new feature'`
4. Push the changes to your forked repository: `git push origin feature/my-feature`
5. Submit a pull request describing your changes.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

For any questions or suggestions, please contact the project maintainer at project@example.com.
