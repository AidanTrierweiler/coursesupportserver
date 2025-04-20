-- Create the courses table
CREATE TABLE IF NOT EXISTS courses (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    course_id TEXT NOT NULL,
    course_name TEXT NOT NULL
);

-- Create the students table
CREATE TABLE IF NOT EXISTS students (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    netpass TEXT NOT NULL,
    preferred_name TEXT NOT NULL
);

-- Create the attendance_marks table
CREATE TABLE IF NOT EXISTS attendance_marks (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    student_id TEXT NOT NULL,
    course_id TEXT NOT NULL,
    day_number INTEGER NOT NULL,
    status TEXT NOT NULL
);

-- Insert initial data into courses
INSERT INTO courses (course_id, course_name) VALUES ('COMP220', 'Data Structures');
INSERT INTO courses (course_id, course_name) VALUES ('COMP172', 'Introduction to Programming');
INSERT INTO courses (course_id, course_name) VALUES ('COMP310', 'Algorithms');
INSERT INTO courses (course_id, course_name) VALUES ('COMP360', 'Operating Systems');

-- Insert initial data into students
INSERT INTO students (netpass, preferred_name) VALUES ('john123', 'John Doe');
INSERT INTO students (netpass, preferred_name) VALUES ('jane456', 'Jane Smith');
INSERT INTO students (netpass, preferred_name) VALUES ('alice789', 'Alice Johnson');
INSERT INTO students (netpass, preferred_name) VALUES ('bob321', 'Bob Brown');
INSERT INTO students (netpass, preferred_name) VALUES ('charlie654', 'Charlie White');

-- Insert initial data into attendance_marks
INSERT INTO attendance_marks (student_id, course_id, day_number, status) VALUES ('john123', 'COMP220', 1, 'Present');
INSERT INTO attendance_marks (student_id, course_id, day_number, status) VALUES ('jane456', 'COMP220', 1, 'Absent');
INSERT INTO attendance_marks (student_id, course_id, day_number, status) VALUES ('alice789', 'COMP220', 1, 'Present');
INSERT INTO attendance_marks (student_id, course_id, day_number, status) VALUES ('bob321', 'COMP220', 1, 'Present');
INSERT INTO attendance_marks (student_id, course_id, day_number, status) VALUES ('charlie654', 'COMP220', 1, 'Absent');
INSERT INTO attendance_marks (student_id, course_id, day_number, status) VALUES ('john123', 'COMP310', 2, 'Present');
INSERT INTO attendance_marks (student_id, course_id, day_number, status) VALUES ('jane456', 'COMP310', 2, 'Present');
INSERT INTO attendance_marks (student_id, course_id, day_number, status) VALUES ('alice789', 'COMP310', 2, 'Absent');
INSERT INTO attendance_marks (student_id, course_id, day_number, status) VALUES ('bob321', 'COMP310', 2, 'Present');
INSERT INTO attendance_marks (student_id, course_id, day_number, status) VALUES ('charlie654', 'COMP310', 2, 'Absent');