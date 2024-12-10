package app.Api.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Api.Datasource.DynamicRoutingDataSource;
import app.Api.Datasource.TenantContext;

@Service
public class SchemaService {
	@Autowired
	private DynamicRoutingDataSource dynamicDataSource;
	
	public void createSchemaForUser(String schemaName) {
        try (Connection connection = dynamicDataSource.getResolvedDefaultDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            
            // Step 1: Create the new schema
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS " + schemaName);
            
         // Create SectionTable
            statement.executeUpdate("CREATE TABLE " + schemaName + ".SectionTable (" +
                    "SectionId INT AUTO_INCREMENT PRIMARY KEY, " +
                    "SectionName VARCHAR(255) NOT NULL)");

            // Create ClassTable
            statement.executeUpdate("CREATE TABLE " + schemaName + ".ClassTable (" +
                    "ClassId INT AUTO_INCREMENT PRIMARY KEY, " +
                    "ClassIdentifier VARCHAR(255) NOT NULL, " +
                    "GradeLevel VARCHAR(50) NOT NULL, " +
                    "SectionId INT, " +
                    "FOREIGN KEY (SectionId) REFERENCES " + schemaName + ".SectionTable(SectionId))");

            // Create Student table
            statement.executeUpdate("CREATE TABLE " + schemaName + ".Student (" +
                    "StudentId INT AUTO_INCREMENT PRIMARY KEY, " +
                    "firstname VARCHAR(255) NOT NULL, " +
                    "middlename VARCHAR(255), " +
                    "surname VARCHAR(255) NOT NULL, " +
                    "dateofbirth DATE NOT NULL, " +
                    "ClassId INT, " +
                    "SectionId INT, " +
                    "FOREIGN KEY (ClassId) REFERENCES " + schemaName + ".ClassTable(ClassId), " +
                    "FOREIGN KEY (SectionId) REFERENCES " + schemaName + ".SectionTable(SectionId))");

            // Create SubjectTable
            statement.executeUpdate("CREATE TABLE " + schemaName + ".SubjectTable (" +
                    "SubjectId INT AUTO_INCREMENT PRIMARY KEY, " +
                    "subjectName VARCHAR(255) NOT NULL, " +
                    "SectionID INT, " +
                    "FOREIGN KEY (SectionID) REFERENCES " + schemaName + ".SectionTable(SectionId))");

            // Create Teacher table
            statement.executeUpdate("CREATE TABLE " + schemaName + ".Teacher (" +
                    "teacherId BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "firstname VARCHAR(255) NOT NULL, " +
                    "middlename VARCHAR(255), " +
                    "surname VARCHAR(255) NOT NULL, " +
                    "subjectId INT, " +
                    "SectionId INT, " +
                    "FOREIGN KEY (subjectId) REFERENCES " + schemaName + ".SubjectTable(SubjectId), " +
                    "FOREIGN KEY (SectionId) REFERENCES " + schemaName + ".SectionTable(SectionId))");

            // Create ExamList table
            statement.executeUpdate("CREATE TABLE " + schemaName + ".ExamList (" +
                    "ExamID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "SectionID INT, " +
                    "date DATE NOT NULL, " +
                    "status VARCHAR(255), " +
                    "examname VARCHAR(255) NOT NULL, " +
                    "FOREIGN KEY (SectionID) REFERENCES " + schemaName + ".SectionTable(SectionId))");

            // Create ExamResults table
            statement.executeUpdate("CREATE TABLE " + schemaName + ".ExamResults (" +
                    "ExamId INT, " +
                    "StudentId INT, " +
                    "SubjectId INT, " +
                    "Score INT, " +
                    "PRIMARY KEY (ExamId, StudentId, SubjectId), " +
                    "FOREIGN KEY (ExamId) REFERENCES " + schemaName + ".ExamList(ExamID), " +
                    "FOREIGN KEY (StudentId) REFERENCES " + schemaName + ".Student(StudentId), " +
                    "FOREIGN KEY (SubjectId) REFERENCES " + schemaName + ".SubjectTable(SubjectId))");

            // Create StdAttendanceTable
            statement.executeUpdate("CREATE TABLE " + schemaName + ".StdAttendanceTable (" +
                    "AttendanceID BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "studentId INT, " +
                    "date DATE NOT NULL, " +
                    "Status VARCHAR(50) NOT NULL, " +
                    "FOREIGN KEY (studentId) REFERENCES " + schemaName + ".Student(StudentId))");

            // Create TchAttendanceTable
            statement.executeUpdate("CREATE TABLE " + schemaName + ".TchAttendanceTable (" +
                    "AttendanceID BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "TeacherID BIGINT, " +
                    "date DATE NOT NULL, " +
                    "Status VARCHAR(50) NOT NULL, " +
                    "FOREIGN KEY (TeacherID) REFERENCES " + schemaName + ".Teacher(teacherId))");

            System.out.println("Tables created successfully in schema: " + schemaName);
            
           // TenantContext.setCurrentTenant(schemaName); 

        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
	
}
