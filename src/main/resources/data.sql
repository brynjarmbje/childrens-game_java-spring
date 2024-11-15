-- this file is run when application is started but after hibernate init
INSERT INTO admin (username, password,issupervisor)
VALUES ('admin', 'admin',true);

select * from admin;