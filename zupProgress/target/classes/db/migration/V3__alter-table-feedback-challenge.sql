ALTER TABLE challenge
DROP COLUMN fk_student;


ALTER TABLE feedback
ADD COLUMN fk_student INTEGER,
ADD CONSTRAINT fk_feedback_student
FOREIGN KEY (fk_student) REFERENCES student(id_student);