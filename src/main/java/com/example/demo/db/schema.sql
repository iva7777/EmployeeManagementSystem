CREATE TABLE positions (
    position_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    hierarchy_level INT NOT NULL
);

CREATE TABLE teams (
    team_id INT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(50) NOT NULL,
    manager_id INT
);

CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    gender VARCHAR(10),
    experience_years INT,
    position_id INT,
    team_id INT
);


-- Add foreign key for the manager in teams
ALTER TABLE teams 
ADD CONSTRAINT fk_manager 
FOREIGN KEY (manager_id) 
REFERENCES employees(employee_id) ON DELETE SET NULL;

-- Add foreign keys for employees
ALTER TABLE employees 
ADD CONSTRAINT fk_position 
FOREIGN KEY (position_id) 
REFERENCES positions(position_id);

ALTER TABLE employees 
ADD CONSTRAINT fk_team 
FOREIGN KEY (team_id) 
REFERENCES teams(team_id);

