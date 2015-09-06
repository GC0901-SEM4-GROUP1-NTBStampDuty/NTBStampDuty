create database StampDuty
go
use StampDuty
go
create table tblUser
(
username nvarchar(50) primary key,
[password] nvarchar(50),
[role] int
)

insert into tblUser values('admin','admin',0)
insert into tblUser values('employee','employee',1)
insert into tblUser values('mainghia','123456', 2)
insert into tblUser values('sonhuy','123456', 2)
insert into tblUser values('phucngo','123456', 2)
insert into tblUser values('sondt','123456', 2)
insert into tblUser values('minhpham','123456', 2)
insert into tblUser values('tienxu','123456', 2)
insert into tblUser values('tuannguyen','123456', 2)

select * from tblUser

create table tblUserDetail
(
	username nvarchar(50) primary key references tblUser(username),
	fullname nvarchar(50),
	gender nvarchar(10),
	phone nvarchar(15),
	day_of_birth datetime,
	[address] nvarchar(50),
	email nvarchar(50)
)

insert into tblUserDetail values('admin','Admin','Male',0696969,null,'19/23 Giang Van Minh','abc@xyz')
insert into tblUserDetail values('employee','Employee','Female',01234567,null,'123 Giang Vo','asd@123')
insert into tblUserDetail values('mainghia','Mai Trung Nghia','Male',0869968,null,'321 Mam non Hoa Sen','awe@asd')
insert into tblUserDetail values('sonhuy','Nguyen Huy Son','Male',0811111,null,'321 Mam non Hoa Sen','awe@hotmail.com')
insert into tblUserDetail values('phucngo','Phuc And Go','Male',0860000,null,'312 Jump Street','awe@asd.net')
insert into tblUserDetail values('sondt','Dao Tuan Son','Male',0863333,null,'14 Le Duan','awe@gmail.com')
insert into tblUserDetail values('minhpham','Pham Thuc Minh','Female',021263333,null,'200 Yet Kieu','awe@fpt.edu.vn')
insert into tblUserDetail values('tienxu','Phan Tien Tung','Male',3223123,null,'101 Ly Thuong kiet','awesome@gmail.com')
insert into tblUserDetail values('tuannguyen','Nguyen Tuan','Male',2133212,null,'123 Cat Linh','awe@asdsda.com')


create table tblBuildingType
(
buildingType_id int primary key,
buildingType_name nvarchar(50)
)

insert into tblBuildingType values(1, 'Official')
insert into tblBuildingType values(2, 'Residental')
insert into tblBuildingType values(3, 'Shopping')
insert into tblBuildingType values(4, 'Cafe')
insert into tblBuildingType values(5, 'Cinema')

select * from tblBuildingType
create table tblLand

(
land_id int IDENTITY(1,1) primary key,
size int,
[address] nvarchar(100),
price int,
building_types int references tblBuildingType(buildingType_id),
img varchar(max),
available_status int
)

insert into tblLand values(200,'101 Quat Lam', 9900000, 1, '',1)
insert into tblLand values(500,'99 Giang Van Minh', 5000000, 4, '',1)
insert into tblLand values(600,'105A Giang Vo', 4000000, 1, '',0)
insert into tblLand values(800,'5 Ngo 69 Yen Phu', 3000000, 2, '',0)
insert into tblLand values(900,'6 Ngo 69 Yen Phu', 9000000, 5, '',0)
insert into tblLand values(500,'101 Gia Lam Re Phai', 1000000, 2, '',1)
insert into tblLand values(400,'5 Dao Tan', 2000000, 4, '',1)
insert into tblLand values(700,'201 Cau Giay', 7000000, 3, '',0)
insert into tblLand values(300,'7 Pham Hung', 5000000, 5, '',0)
insert into tblLand values(100,'15 Cat Linh', 8000000, 3, '',0)


select * from tblLand

create table tblBuildingPermit
(
land_id int primary key references tblLand(land_id),
applied_status int,
applied_date nvarchar(50),
received_status int,
received_date nvarchar(50),
fee int
)

create table tblBuildingDetails
(
building_id int primary key identity,
land_id int references tblLand(land_id),
buildingType_id int references tblBuildingType(buildingType_id),
building_name nvarchar(50),
floors int,
rooms int,
houses int,
shops int,
img nvarchar(max),
chosen_status int
)

insert into tblBuildingDetails values (1, 1, 'Apartment Buidling', 5, 0, 60, 0,'', 0)
insert into tblBuildingDetails values (2, 2, 'Super Buidling', 10, 60, 0, 0,'', 0)
insert into tblBuildingDetails values (3, 1, 'Batman Buidling', 5, 0, 0, 60,'', 0)
insert into tblBuildingDetails values (4, 2, 'Flash Buidling', 10, 60, 0, 0,'', 1)
insert into tblBuildingDetails values (5, 2, 'Office Buidling', 10, 0, 60, 0,'', 1)
insert into tblBuildingDetails values (3, 1, 'Weed Buidling', 5, 0, 60, 0,'', 0)
insert into tblBuildingDetails values (3, 2, 'McDonalds Buidling', 10, 60, 0, 0,'', 0)
insert into tblBuildingDetails values (3, 1, 'Supermarket Buidling', 10, 0, 0, 60,'', 0)
insert into tblBuildingDetails values (2, 2, 'Playhouse Buidling', 5, 60, 0, 0,'', 1)
insert into tblBuildingDetails values (2, 2, 'Office Buidling', 10, 0, 60, 0,'', 1)
insert into tblBuildingDetails values (1, 1, 'Theatre Buidling', 5, 0, 60, 0,'', 0)
insert into tblBuildingDetails values (4, 2, 'Cafe Buidling', 10, 60, 0, 0,'', 0)
insert into tblBuildingDetails values (5, 1, 'Ajax Buidling', 5, 0, 0, 60,'', 0)
insert into tblBuildingDetails values (4, 2, 'iOS Buidling', 10, 60, 0, 0,'', 1)
insert into tblBuildingDetails values (5, 2, 'Android Buidling', 5, 0, 60, 0,'', 1)

select * from tblBuildingDetails

--available status column: fail = 0, done = 1, process = 2, warning  = 3

Create table tblProjects(
proj_id int primary key identity(1,1),
proj_name nvarchar(50),
building_id int references tblBuildingDetails(building_id),
created_date Datetime,
finish_date Datetime,
period int,
available_status int,
)
insert into tblProjects values('Project1', 1, '2014-11-19', '2016-11-19', 1, 1)
insert into tblProjects values('Project2', 2, '2014-09-30', '2016-09-30', 1, 1)
insert into tblProjects values('Project3', 3, '2014-05-15', '2017-05-15', 1, 1)
insert into tblProjects values('Project4', 2, '2014-11-20', '2017-01-15', 1, 1)
insert into tblProjects values('Project5', 2, '2005-10-30', '2015-10-30', 1, 1)
insert into tblProjects values('Project6', 3, '2014-09-30', '2020-03-30', 1, 1)
insert into tblProjects values('Project7', 1, '2014-12-25', '2017-12-25', 1, 1)
insert into tblProjects values('Project8', 2, '2015-07-30', '2020-10-30', 1, 1)
insert into tblProjects values('Project9', 3, '2014-09-30', '2018-11-30', 1, 1)

Select * from tblProjects

Create table tblPeriod(
proj_id int references tblProjects(proj_id),
period_1 datetime,
period_2 datetime,
period_3 datetime,
complete_percent int
)
Update tblPeriod
Set complete_percent = 31
where proj_id = 1

Insert into tblPeriod values (1,'2015-05-30','2015-11-19','2016-11-19',60)
Insert into tblPeriod values (2,'2015-03-30','2015-09-30','2016-09-30',40)
Insert into tblPeriod values (3,'2015-05-15','2015-11-15','2017-05-15',90)
Insert into tblPeriod values (4,'2015-05-20','2016-05-20','2017-01-15',60)
Insert into tblPeriod values (5,'2007-10-30','2010-10-30','2015-10-30',20)
Insert into tblPeriod values (6,'2016-03-30','2018-03-30','2020-03-30',50)
Insert into tblPeriod values (7,'2015-12-25','2016-12-25','2017-12-25',70)
Insert into tblPeriod values (8,'2016-07-30','2018-10-30','2020-10-30',30)
Insert into tblPeriod values (9,'2015-09-30','2016-11-30','2018-11-30',0)

Select * from tblPeriod
select * from tblBuildingType
select * from tblLand

create table tblOccupancyPermit
(
building_id int primary key references tblBuildingDetails(building_id),
applied_status int,
applied_date nvarchar(50),
received_status int,
received_date nvarchar(50),
fee int
)

create table tblRoomType
(
[type_id] int primary key identity,
[type_name] nvarchar(10) 
)
select*from tblRoomType

insert into tblRoomType values('House')
insert into tblRoomType values('Shop')
insert into tblRoomType values('Office')
insert into tblRoomType values('Cinema')
insert into tblRoomType values('Cafe')

select*from tblRoomType

create table tblRoomDetails
(
room_id int primary key identity,
building_id int references tblBuildingDetails(building_id),
[type_id] int references tblRoomType([type_id]),
room_size int,
[floor] int,
room_price int,
sellStatus int
)
Go
insert into tblRoomDetails values(1, 1, '50', '1', '1500',0)
insert into tblRoomDetails values(1, 2, '50', '1', '4000',1)
insert into tblRoomDetails values(1, 1, '50', '1', '1500',0)
insert into tblRoomDetails values(1, 2, '50', '1', '10000',1)
insert into tblRoomDetails values(1, 1, '50', '2', '1500',1)
insert into tblRoomDetails values(1, 3, '50', '1', '7500',1)
insert into tblRoomDetails values(1, 2, '50', '1', '4000',0)
insert into tblRoomDetails values(1, 1, '50', '1', '1500',0)
insert into tblRoomDetails values(2, 2, '50', '1', '4000',0)
insert into tblRoomDetails values(2, 3, '50', '1', '1500',0)
insert into tblRoomDetails values(1, 1, '50', '1', '1500',0)
insert into tblRoomDetails values(1, 2, '50', '1', '4000',0)
insert into tblRoomDetails values(1, 1, '50', '1', '1500',0)
insert into tblRoomDetails values(1, 2, '50', '1', '10000',0)
insert into tblRoomDetails values(1, 1, '50', '2', '1500',0)
insert into tblRoomDetails values(1, 3, '50', '1', '7500',0)
insert into tblRoomDetails values(1, 2, '50', '1', '4000',0)
insert into tblRoomDetails values(1, 1, '50', '1', '1500',0)
insert into tblRoomDetails values(2, 2, '50', '1', '4000',0)
insert into tblRoomDetails values(2, 3, '50', '1', '1500',0)
insert into tblRoomDetails values(1, 1, '50', '1', '1500',0)
insert into tblRoomDetails values(1, 2, '50', '1', '4000',0)
insert into tblRoomDetails values(1, 1, '50', '1', '1500',0)
insert into tblRoomDetails values(1, 2, '50', '1', '10000',0)
insert into tblRoomDetails values(1, 1, '50', '2', '1500',0)
insert into tblRoomDetails values(1, 3, '50', '1', '7500',0)
insert into tblRoomDetails values(1, 2, '50', '1', '4000',0)
insert into tblRoomDetails values(1, 1, '50', '1', '1500',0)
insert into tblRoomDetails values(2, 2, '50', '1', '4000',0)
insert into tblRoomDetails values(2, 3, '50', '1', '1500',0)

GO
select*from tblRoomDetails



Go
create table tblContract
(
con_id int primary key identity,
username nvarchar(50) references tblUser(username),
room_id int references tblRoomDetails(room_id),
created_date Datetime,
payment_period int,
deposit int,
total_payment int,
total_paid int,
total_due int,
invoice_status int
)


select*from tblContract

insert into tblContract values('mainghia', 2, '2014-12-30',1,50000, 200000, 66666, 133334,0)
insert into tblContract values('sondt', 4, '2014-11-20',1,100000, 500000, 166666, 333334,0)
insert into tblContract values('minhpham', 5, '2015-01-30',1,150000, 750000, 250000, 500000,0)
insert into tblContract values('tienxu', 6, '2015-03-30',1,75000, 375000, 125000, 250000,1)

create table tblPayment
(
contract_id int references tblContract(con_id),
payment_time Datetime,
paid int
)

select*from tblPayment

insert into tblPayment values(1,'2014-12-30',66666)
insert into tblPayment values(2,'2014-11-20',166666)
insert into tblPayment values(3,'2015-01-30',250000)
insert into tblPayment values(4,'2015-03-30',125000)

create table tblStampDuty
(
stamp_price int primary key
)

select*from tblPayment
inner join tblContract
on tblPayment.contract_id = tblContract.con_id
where tblContract.username = 'customer'

