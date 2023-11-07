ALTER TABLE project
DROP COLUMN name_instructor,
DROP COLUMN email_instructor,
DROP COLUMN name_leadership,
DROP COLUMN email_leadership;



ALTER TABLE project
ADD COLUMN fk_leadership INTEGER,
ADD CONSTRAINT fk_leadership_fk
FOREIGN KEY (fk_leadership)
REFERENCES leadership(id_leadership);
