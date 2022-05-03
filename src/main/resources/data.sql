INSERT INTO Employee(id, name, age) VALUES ('1', 'TOM', 30);

INSERT INTO m_user(user_id, password, user_name, birthday, age, gender, department_id, role) 
    VALUES ('user@co.jp', 'password', 'System Administrator', '1989-01-01', 33, 2, 1, 'ROLE_GENERAL'),
           ('system@xxx.co.jp', 'password', 'System Manger', '1990-01-01', 32, 1, 1, 'MANAGER'),
           ('system@xxx.co.nz', 'password', 'Editor', '1994-01-01', 28, 1, 3, 'Editor');

INSERT INTO m_department(department_id, department_name) VALUES (1, 'System Management'), (2, 'Sales'), (3, 'Editor');

INSERT INTO t_salary(user_id, year_month, salary) VALUES ('user@co.jp', '11/2020', 2800), ('user@co.jp', '12/2020', 2900), ('user@co.jp', '01/2020', 3000);