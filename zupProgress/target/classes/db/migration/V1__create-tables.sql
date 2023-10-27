CREATE TABLE mentor(
    id_mentor serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    training BOOLEAN NOT NULL,
    bio VARCHAR(400),
    start_mentoring DATE NOT NULL,
    end_mentoring DATE NOT NULL
);
CREATE TABLE instructor(
    id_instructor serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE
);
CREATE TABLE leadership(
    id_leadership serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE
);
CREATE TABLE project(
    id_project serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(400) NOT NULL,
    training_institution VARCHAR(255),
    fk_instructor INTEGER,
    FOREIGN KEY (fk_instructor) REFERENCES instructor(id_instructor)
);
CREATE TABLE student(
    id_student serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age int NOT NULL,
    city VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    image VARCHAR(255),
    fk_mentor INTEGER,
    bio VARCHAR(400),
    pcd BOOLEAN,
    type_of_disability VARCHAR(255),
    fk_project INTEGER,
    contract_end DATE,
    FOREIGN KEY (fk_mentor) REFERENCES mentor(id_mentor),
    FOREIGN KEY (fk_project) REFERENCES project(id_project)
);
CREATE TABLE challenge(
    id_challenge serial PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    fk_student INTEGER,
    FOREIGN KEY(fk_student) REFERENCES student(id_student)
);
CREATE TABLE feedback(
    id_feedback serial PRIMARY KEY,
    type VARCHAR(255),
    atributes VARCHAR(255),
    description VARCHAR(255),
    status VARCHAR(255),
    fk_challenge INTEGER,
    FOREIGN KEY(fk_challenge) REFERENCES challenge(id_challenge)
);
