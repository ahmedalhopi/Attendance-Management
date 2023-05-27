--جدول المساقات
CREATE TABLE courses (
  course_code VARCHAR(10) PRIMARY KEY,
  name VARCHAR(100) NOT null UNIQUE,
  subject VARCHAR(50),
  book VARCHAR(100),
  number_lectures INTEGER,
  teacher VARCHAR(100),
  place VARCHAR(50)
);
/*-----------------------------------------------------------*/
--جدول الطلاب
CREATE TABLE students (
  student_number VARCHAR(20) PRIMARY KEY,
  full_name VARCHAR(100) NOT null UNIQUE,
  gender VARCHAR(10),
  living VARCHAR(50),
  department VARCHAR(50),
  majoring VARCHAR(50)
);
/*-----------------------------------------------------------*/
-- جدول المعيدين
create table assistants (
  assistant_number VARCHAR(20) primary key,
  full_name VARCHAR(100) not null unique,
  gender VARCHAR(10),
  living VARCHAR(50),
  department VARCHAR(50),
  mobile VARCHAR(20) unique,
  password VARCHAR(100)
);

/*-----------------------------------------------------------*/
--جدول المحاضرات
CREATE TABLE lectures (
  lecture_id SERIAL PRIMARY KEY,
  course_code VARCHAR(20) NOT NULL,
  title VARCHAR(100) NOT NULL,
  place VARCHAR(50),
  day VARCHAR(10),
  date timestamp,
  hour_from TIME,
  hour_to TIME,
  FOREIGN KEY (course_code) REFERENCES courses (course_code)
);

/*-----------------------------------------------------------*/
--جدول الحضور و الغياب
CREATE TABLE attendance (
  id SERIAL PRIMARY KEY,
  lecture_id INTEGER,
  student_number VARCHAR(20),
  student_name VARCHAR(100),
  student_mobile VARCHAR(20),
  status VARCHAR(20),
  FOREIGN KEY (lecture_id) REFERENCES lectures (lecture_id),
  FOREIGN KEY (student_number) REFERENCES students (student_number)
);

/*-----------------------------------------------------------*/
--جدول مساقات الطلاب
CREATE TABLE students_courses (
  id SERIAL PRIMARY KEY,
  student_number VARCHAR(20),
  course_code VARCHAR(20),
  status VARCHAR(20),
  FOREIGN KEY (student_number) REFERENCES students (student_number),
  FOREIGN KEY (course_code) REFERENCES courses (course_code)
);
/*-----------------------------------------------------------*/
--حدول مساقات المعيدين
create table assistants_courses (
  id SERIAL primary key,
  assistant_number VARCHAR(20),
  code VARCHAR(20),
  foreign key (assistant_number) references assistants (assistant_number),
  foreign key (code) references courses (course_code)
);

/*-----------------------------------------------------------*/
--جدول الطلاب و ارقام الجوالات
CREATE TABLE students_mobiles (
  id SERIAL PRIMARY KEY,
  student_number VARCHAR(20),
  mobile VARCHAR(20) UNIQUE,
  notes TEXT,
  FOREIGN KEY (student_number) REFERENCES students (student_number)
);
/*-----------------------------------------------------------*/
-- جدول المدراء
CREATE TABLE manager (
  manager_number VARCHAR(20) PRIMARY KEY,
  full_name VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100)
);

/*-----------------------------------------------------------*/
ALTER TABLE mang.assistants_courses ADD CONSTRAINT assistants_courses_un UNIQUE (assistant_number,code);
ALTER TABLE mang.students_mobiles ADD CONSTRAINT students_mobiles_un UNIQUE (student_number,mobile);
ALTER TABLE mang.lectures ALTER COLUMN hour_from TYPE varchar USING hour_from::varchar;
ALTER TABLE mang.lectures ALTER COLUMN hour_to TYPE varchar USING hour_to::varchar;
ALTER TABLE mang.lectures ALTER COLUMN "date" TYPE date USING "date"::date;
ALTER TABLE mang.attendance ADD CONSTRAINT attendance_un UNIQUE (lecture_id,student_number);
ALTER TABLE mang.students_courses ADD CONSTRAINT students_courses_un UNIQUE (student_number,course_code);







