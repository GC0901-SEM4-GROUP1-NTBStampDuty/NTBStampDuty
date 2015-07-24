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
insert into tblUser values('customer','customer', 2)

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

insert into tblUserDetail values('admin','Tao La Admin','Male',0696969,null,'19/23 Giang Van Minh','abc@xyz')
insert into tblUserDetail values('employee','Tao La Employee','Female',01234567,null,'123 Giang Vo','asd@123')
insert into tblUserDetail values('customer','Tao La Customer','Male',0869968,null,'321 Mam non Hoa Sen','awe@asd')

create table tblBuildingType
(
buildingType_id int primary key,
buildingType_name nvarchar(50)
)

insert into tblBuildingType values(1, 'Official')
insert into tblBuildingType values(2, 'Residental')
insert into tblBuildingType values(3, 'Shopping')

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
insert into tblLand values(500,'99 Giang Van Minh', 5000000, 1, '',1)
insert into tblLand values(600,'105A Giang Vo', 4000000, 1, '',0)
insert into tblLand values(800,'5 Ngo 69 Yen Phu', 3000000, 1, '',0)
insert into tblLand values(900,'6 Ngo 69 Yen Phu', 9000000, 1, '',0)


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

insert into tblBuildingDetails values (1, 1, 'New Buidling', 10, 0, 60, 0,'', 0)
insert into tblBuildingDetails values (2, 2, 'Newer Buidling', 10, 60, 0, 0,'', 0)
insert into tblBuildingDetails values (3, 1, 'New Shop Buidling', 10, 0, 0, 60,'', 0)
insert into tblBuildingDetails values (4, 2, 'House Buidling', 10, 60, 0, 0,'', 1)
insert into tblBuildingDetails values (5, 2, 'Office Buidling', 10, 0, 60, 0,'', 1)

select * from tblBuildingDetails

Create table tblProjects(
proj_id int primary key identity(1,1),
proj_name nvarchar(50),
building_id int references tblBuildingDetails(building_id),
created_date Datetime,
finish_date Datetime,
period int,
available_status int,
)
insert into tblProjects values('Project1', 1, '2015-07-19', '2016-07-19', 1, 1)
insert into tblProjects values('Project2', 2, '2015-05-30', '2015-06-30', 1, 1)
insert into tblProjects values('Project3', 3, '2015-05-30', '2015-06-30', 1, 1)

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

Insert into tblPeriod values (1,'2015-11-19','2016-03-19','2015-06-19',60)
Insert into tblPeriod values (2,'2015-05-30','2015-06-15','2015-06-30',40)
Insert into tblPeriod values (3,'2015-05-30','2015-06-15','2015-06-30',90)

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

insert into tblContract values('customer', 2, '2015-07-21',1,200000000, 1000000000, 200000000, 800000000,0)

create table tblPayment
(
contract_id int references tblContract(con_id),
payment_time Datetime,
paid int
)

select*from tblPayment

insert into tblPayment values(1,'2015-07-22',200000000)

create table tblStampDuty
(
stamp_price int primary key
)

select*from tblPayment
inner join tblContract
on tblPayment.contract_id = tblContract.con_id
where tblContract.username = 'customer'