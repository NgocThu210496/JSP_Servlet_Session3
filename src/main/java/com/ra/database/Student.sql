Insert into Student(student_name,age,birth_day, student_status)
values ('Nguyen A',20,'2000-12-12',1);
Insert into Student(student_name,age,birth_day, student_status)
values ('Nguyen A',20,'2000/12/12',1),
       ('Nguyen B',20,'2000/2/2',0),
       ('Nguyen H',20,'2000/3/12',1),
       ('Nguyen M',20,'2000/12/28',0);

use StudentManagement;
select * from Student;

delimiter &&
drop procedure if exists proc_create_student;
create procedure proc_create_student(
    student_name varchar(100),
    age int,
    birth_day date,
    student_status bit
)
begin
    insert into Student (student_name, age, birth_day, student_status)
        VALUES (student_name,age,birth_day, student_status);

end &&;
delimiter &&

/*get student by id*/
Delimiter &&
drop procedure if exists proc_get_student_by_id;
create procedure proc_get_student_by_id(
    studentId int
)
begin
    select *from Student where student_id=studentId;
end &&
delimiter &&

/*update student*/
DELIMITER &&
DROP PROCEDURE IF EXISTS proc_update_student;
CREATE PROCEDURE proc_update_student(
    student_id_in int,
    student_name_in varchar(100),
    age_in int,
    birth_day_in date,
    student_status_in bit
)
BEGIN
    UPDATE  Student
        SET student_name = student_name_in,
            age = age_in,
            birth_day = birth_day_in,
            student_status = student_status_in
    WHERE student_id = student_id_in;
end &&
DELIMITER &&

/*delete student*/
DELIMITER &&
DROP PROCEDURE IF EXISTS proc_delete_student;
CREATE PROCEDURE proc_delete_student(
    student_id_in int
)
BEGIN
    DELETE FROM Student
    WHERE student_id = student_id_in;
end &&
DELIMITER &&

