--جدول المساقات
INSERT INTO courses (course_code, name, subject, book, number_lectures, teacher, place)
VALUES 
    ('C001', 'Introduction to Programming', 'Computer Science', 'Programming Textbook', 30, 'Prof. Johnson', 'Room 101'),
    ('C002', 'Data Structures and Algorithms', 'Computer Science', 'DS&A Textbook', 45, 'Prof. Smith', 'Room 202'),
    ('C003', 'Database Management Systems', 'Computer Science', 'DBMS Textbook', 40, 'Prof. Davis', 'Room 303'),
    ('C004', 'Software Engineering', 'Computer Science', 'Software Engineering Textbook', 35, 'Prof. Thompson', 'Room 404'),
    ('C005', 'Operating Systems', 'Computer Science', 'OS Textbook', 30, 'Prof. Anderson', 'Room 505'),
    ('C006', 'Computer Networks', 'Computer Science', 'Networking Textbook', 45, 'Prof. Wilson', 'Room 606'),
    ('C007', 'Digital Logic Design', 'Computer Engineering', 'Digital Logic Textbook', 30, 'Prof. Hernandez', 'Room 707'),
    ('C008', 'Microprocessors and Assembly Language', 'Computer Engineering', 'Microprocessors Textbook', 45, 'Prof. Taylor', 'Room 808'),
    ('C009', 'Computer Architecture', 'Computer Engineering', 'Computer Architecture Textbook', 30, 'Prof. Martinez', 'Room 909'),
    ('C010', 'Embedded Systems', 'Computer Engineering', 'Embedded Systems Textbook', 40, 'Prof. Brown', 'Room 1010'),
	('C011', 'Introduction to Engineering', 'Engineering', 'Engineering Textbook', 30, 'Prof. Johnson', 'Room 101'),
    ('C012', 'Mechanical Engineering Principles', 'Mechanical Engineering', 'Mechanical Engineering Textbook', 45, 'Prof. Smith', 'Room 202'),
    ('C013', 'Civil Engineering Fundamentals', 'Civil Engineering', 'Civil Engineering Textbook', 40, 'Prof. Davis', 'Room 303'),
    ('C014', 'Electrical Engineering Basics', 'Electrical Engineering', 'Electrical Engineering Textbook', 35, 'Prof. Thompson', 'Room 404');

/*-----------------------------------------------------------*/
--جدول الطلاب
INSERT INTO students (student_number, full_name, gender, living, department, majoring)
VALUES
  ('S001', 'John Doe', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S002', 'Jane Smith', 'Female', 'Off-Campus', 'Computer', 'Computer Science'),
  ('S003', 'David Johnson', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S004', 'Emily Davis', 'Female', 'Dormitory', 'Engineering', 'Computer Engineering'),
  ('S005', 'Michael Wilson', 'Male', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S006', 'Sarah Brown', 'Female', 'Dormitory', 'Engineering', 'Computer Engineering'),
  ('S007', 'Robert Taylor', 'Male', 'Off-Campus', 'Computer', 'Computer Science'),
  ('S008', 'Jessica Clark', 'Female', 'Off-Campus', 'Computer', 'Computer Science'),
  ('S009', 'Daniel Martinez', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S010', 'Olivia Anderson', 'Female', 'Off-Campus', 'Computer', 'Computer Science'),
  ('S011', 'Liam Hernandez', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S012', 'Amany Hernandez', 'Female', 'Off-Campus', 'Computer', 'Computer Science'),
  ('S013', 'Ola Martinez', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S014', 'Nour Thompson', 'Female', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S015', 'Jackson Smith', 'Male', 'Dormitory', 'Computer', 'Process Computer Science'),
  ('S016', 'Amany Johnson', 'Female', 'Off-Campus', 'Computer', 'Computer Science'),
  ('S017', 'Lucas Thompson', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S018', 'Lina Taylor', 'Female', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S019', 'Jackson Brown', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S020', 'Ella Wilson', 'Female', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S021', 'Liam Anderson', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S022', 'Emma Hernandez', 'Female', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S023', 'Noah Martinez', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S024', 'Isabella Thompson', 'Female', 'Off-Campus', 'Computer', 'Computer Science'),
  ('S025', 'Mason Smith', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S026', 'Sophia Johnson', 'Female', 'Off-Campus', 'Computer', 'Computer Science'),
  ('S027', 'Lucas Davis', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S028', 'Ava Taylor', 'Female', 'Off-Campus', 'Computer', 'Computer Science'),
  ('S029', 'Jackson Taylor', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S030', 'Ella Davis', 'Female', 'Off-Campus', 'Computer', 'Computer Science'),
  ('S031', 'Liam Johnson', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S032', 'Ella Smith', 'Female', 'Off-Campus', 'Computer', 'Computer Science'),
  ('S033', 'Mason Davis', 'Male', 'Dormitory', 'Engineering', 'Mechanical Engineering'),
  ('S034', 'Sophia Bahlol', 'Female', 'Off-Campus', 'Engineering', 'Mechanical Engineering'),
  ('S035', 'Sophia Ali', 'Male', 'Dormitory', 'Engineering', 'Mechanical Engineering'),
  ('S036', 'Olivia Smith', 'Female', 'Off-Campus', 'Computer', 'Computer Science'),
  ('S037', 'Noah Davis', 'Male', 'Dormitory', 'Engineering', 'Mechanical Engineering'),
  ('S038', 'Emma Wilson', 'Female', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S039', 'Liam Martinez', 'Male', 'Dormitory', 'Engineering', 'Computer Engineering'),
  ('S040', 'Sophia Anderson', 'Female', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S041', 'Lucas Johnson', 'Male', 'Dormitory', 'Engineering', 'Computer Engineering'),
  ('S042', 'Ava Davis', 'Female', 'Off-Campus', 'Engineering', 'Mechanical Engineering'),
  ('S043', 'Jackson Wilson', 'Male', 'Dormitory', 'Engineering', 'Computer Engineering'),
  ('S044', 'Oliver Smith', 'Male', 'Dormitory', 'Engineering', 'Computer Engineering'),
  ('S045', 'Sophia Taylor', 'Female', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S046', 'Lucas Anderson', 'Male', 'Dormitory', 'Engineering', 'Mechanical Engineering'),
  ('S047', 'Isabella Davis', 'Female', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S048', 'Elijah Thompson', 'Male', 'Dormitory', 'Engineering', 'Computer Engineering'),
  ('S049', 'Mia Hernandez', 'Female', 'Off-Campus', 'Engineering', 'Mechanical Engineering'),
  ('S050', 'Aiden Smith', 'Male', 'Dormitory', 'Engineering', 'Computer Engineering'),
  ('S068', 'Ella Johnson', 'Female', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S069', 'Henry Wilson', 'Male', 'Dormitory', 'Computer', 'Computer Science'),
  ('S070', 'Scarlett Davis', 'Female', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S071', 'Gabriel Smith', 'Male', 'Dormitory', 'Engineering', 'Mechanical Engineering'),
  ('S072', 'Victoria Anderson', 'Female', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S073', 'Samuel Thompson', 'Male', 'Dormitory', 'Engineering', 'Computer Engineering'),
  ('S074', 'Madison Johnson', 'Female', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S075', 'Daniel Davis', 'Male', 'Dormitory', 'Engineering', 'Mechanical Engineering'),
  ('S076', 'Elizabeth Smith', 'Female', 'Off-Campus', 'Engineering', 'Computer Engineering'),
  ('S077', 'Joseph Wilson', 'Male', 'Dormitory', 'Engineering', 'Computer Engineering'),
  ('S078', 'Penelope Anderson', 'Female', 'Off-Campus', 'Engineering', 'Mechanical Engineering');
/*-----------------------------------------------------------*/
-- جدول المعيدين
INSERT INTO assistants (assistant_number, full_name, gender, living, department, mobile, password)
VALUES 
    ('A005', 'Osama R. Al Zayan', 'Male', 'Dormitory', 'Engineering', '1111111111', 'password5'),
    ('A006', 'Rasha E. Kahil', 'Female', 'Off-Campus', 'Computer', '2222222222', 'password6'),
    ('A007', 'Ahmed I. Al Hopi', 'Male', 'Dormitory', 'Engineering', '3333333333', 'password7'),
    ('A008', 'Ali M. Harb', 'Male', 'Off-Campus', 'Engineering', '4444444444', 'password8');


/*-----------------------------------------------------------*/
--جدول المحاضرات


/*-----------------------------------------------------------*/
--جدول الحضور و الغياب


/*-----------------------------------------------------------*/
--جدول مساقات الطلاب


/*-----------------------------------------------------------*/
--جدول مساقات المعيدين


/*-----------------------------------------------------------*/
--جدول الطلاب و ارقام الجوالات


/*-----------------------------------------------------------*/
-- جدول المدراء


/*-----------------------------------------------------------*/

