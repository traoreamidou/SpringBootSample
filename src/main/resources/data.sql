INSERT INTO Employee(id, name, age) VALUES ('1', 'TOM', 30);

INSERT INTO m_user(user_id, password, user_name, birthday, age, gender, department_id, role) VALUES ('system@co.jp', 'password', 'System Administrator', '2000-01-01', 21, 2, 2, 'ROLE_GENERAL');

INSERT INTO m_department(department_id, department_name) VALUES (1, 'System Management'), (2, 'Sales');

INSERT INTO t_salary(user_id, year_month, salary) VALUES ('user@co.jp', '11/2020', 2800), ('user@co.jp', '12/2020', 2900), ('user@co.jp', '01/2020', 3000);