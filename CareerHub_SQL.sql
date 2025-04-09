create database CareerHub;
use CareerHub;


create table companies (
    companyid int primary key,
    companyname varchar(100) not null,
    location varchar(100)
);


create table applicants (
    applicantid int primary key auto_increment,
    firstname varchar(50) not null,
    lastname varchar(50) not null,
    email varchar(100) unique not null,
    phone varchar(20),
    resume varchar(255)
);


create table jobs (
    jobid int not null auto_increment,
    companyid int not null,
    jobtitle varchar(100) not null,
    jobdescription text,
    joblocation varchar(100),
    salary decimal(10, 2),
    jobtype varchar(50),
    posteddate date not null,
    applicationdeadline date not null,
    primary key (jobid)
);


create table applications (
    applicationid int primary key auto_increment,
    jobid int not null,
    applicantid int not null,
    applicationdate date not null,
    coverletter text
);


alter table jobs
    add foreign key (companyid) references companies(companyid);

alter table applications
    add foreign key (jobid) references jobs(jobid),
    add foreign key (applicantid) references applicants(applicantid);


insert into companies values 
(1, 'AlphaTech', 'Mumbai'),
(2, 'BetaSoft', 'Pune'),
(3, 'GammaCorp', 'Delhi'),
(4, 'DeltaWorks', 'Bangalore'),
(5, 'EpsilonSys', 'Hyderabad');

-- Insert applicants
insert into applicants (firstname, lastname, email, phone, resume) values 
('Aarav', 'Sharma', 'aarav.sharma@mail.com', '9876543200', 'resume1.pdf'),
('Diya', 'Patel', 'diya.patel@mail.com', '9876543201', 'resume2.pdf'),
('Vivaan', 'Reddy', 'vivaan.reddy@mail.com', '9876543202', 'resume3.pdf'),
('Ananya', 'Singh', 'ananya.singh@mail.com', '9876543203', 'resume4.pdf'),
('Kabir', 'Mishra', 'kabir.mishra@mail.com', '9876543204', 'resume5.pdf');


insert into jobs (companyid, jobtitle, jobdescription, joblocation, salary, jobtype, posteddate, applicationdeadline) values 
(1, 'Software Engineer', 'Develop scalable applications.', 'Mumbai', 85000.00, 'Full-time', '2025-04-09', '2025-04-20'),
(2, 'Data Scientist', 'Analyze data patterns.', 'Pune', 95000.00, 'Full-time', '2025-04-08', '2025-04-22'),
(3, 'Product Manager', 'Manage product lifecycle.', 'Delhi', 100000.00, 'Part-time', '2025-04-07', '2025-04-25'),
(4, 'UX Designer', 'Design intuitive interfaces.', 'Bangalore', 78000.00, 'Contract', '2025-04-06', '2025-04-20'),
(5, 'DevOps Engineer', 'CI/CD pipelines.', 'Hyderabad', 89000.00, 'Full-time', '2025-04-04', '2025-04-18');


insert into applications (jobid, applicantid, applicationdate, coverletter) values 
(1, 2, '2025-04-08', 'Cover letter content by Diya Patel'),
(2, 4, '2025-04-08', 'Cover letter content by Ananya Singh'),
(3, 1, '2025-04-07', 'Cover letter content by Aarav Sharma'),
(4, 3, '2025-04-06', 'Cover letter content by Vivaan Reddy'),
(5, 5, '2025-04-05', 'Cover letter content by Kabir Mishra');
