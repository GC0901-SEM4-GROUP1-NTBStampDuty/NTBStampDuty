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

create table tblUserDetail
(
	username nvarchar(50) primary key references tblUser(username),
	fullname nvarchar(50),
	age int,
	gender int,
	phone nvarchar(15),
	day_of_birth datetime,
	[address] nvarchar(50)
)

insert into tblUserDetail values('admin','Tao La Admin',100,0,0696969,null,'19/23 Giang Van Minh')
insert into tblUserDetail values('employee','Tao La Employee',20,0,01234567,null,'123 Giang Vo')
insert into tblUserDetail values('customer','Tao La Customer',10,1,0869968,null,'321 Mam non Hoa Sen')

WITH limt_user AS
( select tblUser.username, [password], [role], fullname, age, gender, phone, day_of_birth, [address], ROW_NUMBER() OVER (ORDER BY tblUser.username ASC) AS [row_number]
from tblUser
inner join tblUserDetail
on tblUser.username = tblUserDetail.username)
select  username, [password], [role], fullname, age, gender, phone, day_of_birth, [address] FROM limt_user WHERE [row_number]> 0 AND [row_number]<= 5

create table tblProject
(
project_id int primary key,
project_name nvarchar(50)
)

create table tblLocation
(
address_id int primary key,
name nvarchar(50),
price int
)
insert into tblLocation values(1,'Le Thai To', 5500)
insert into tblLocation values(2,'Giang Van Minh', 3000)
insert into tblLocation values(3,'Nhat Tan', 2500)

create table tblLand
(
land_id int IDENTITY(1,1) primary key,
size int,
address_id int references tblLocation(address_id),
building_types nvarchar(50),
building_plan nvarchar(50),
built_status int,
img nvarchar(100)
)


insert into tblLand values(200, 1, 'Office', '', 0, '')
insert into tblLand values(300, 3, 'House', '', 1, '')
insert into tblLand values(500, 3, 'Office', '', 0, '')
insert into tblLand values(100, 2, 'House', '', 1, '')
insert into tblLand values(1000, 3, 'Garage ', '', 1, '')
insert into tblLand values(1000, 3, 'Garage ', '', 2, '')
insert into tblLand values(200, 1, 'Office', '', 0, '')
insert into tblLand values(300, 3, 'House', '', 1, '')
insert into tblLand values(500, 3, 'Office', '', 0, '')
insert into tblLand values(100, 2, 'House', '', 1, '')
insert into tblLand values(1000, 3, 'Garage ', '', 1, '')
insert into tblLand values(1000, 3, 'Garage ', '', 2, '')
insert into tblLand values(200, 1, 'Office', '', 0, '')
insert into tblLand values(300, 3, 'House', '', 1, '')
insert into tblLand values(500, 3, 'Office', '', 0, '')
insert into tblLand values(100, 2, 'House', '', 1, '')
insert into tblLand values(1000, 3, 'Garage ', '', 1, '')
insert into tblLand values(1000, 3, 'Garage ', '', 2, '')

create table tblBuildingType
(
buildingType_id int primary key,
buildingType_name nvarchar(50)
)

insert into tblBuildingType values(1, 'Official')
insert into tblBuildingType values(2, 'Residental')
insert into tblBuildingType values(3, 'Shopping')

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
date_contructed Datetime,
img nvarchar(100)
)

insert into tblBuildingDetails values (1, 1, 'New Buidling', 10, 0, 60, 0, null,'')
insert into tblBuildingDetails values (2, 2, 'Newer Buidling', 10, 60, 0, 0, null,'')
insert into tblBuildingDetails values (3, 3, 'New Shop Buidling', 10, 0, 0, 60, null,'')
insert into tblBuildingDetails values (4, 2, 'House Buidling', 10, 60, 0, 0, null,'')
insert into tblBuildingDetails values (5, 1, 'Office Buidling', 10, 0, 60, 0, null,'')
insert into tblBuildingDetails values (6, 1, 'New Office Buidling', 10, 0, 60, 0, null,'')

select * from tblBuildingDetails

Create table tblProjects(
proj_id int primary key identity(1,1),
proj_name nvarchar(50),
building_id int references tblBuildingDetails(building_id),
complete_percent int,
created_date Datetime,
finish_date Datetime,
period int,
)

insert into tblProjects values('Project1', 1, 0, '2015-05-30', '2015-06-30', 1)
insert into tblProjects values('Project2', 2, 0, '2015-05-30', '2015-06-30', 1)
insert into tblProjects values('Project3', 3, 0, '2015-05-30', '2015-06-30', 1)
insert into tblProjects values('Project4', 4, 0, '2015-05-30', '2015-06-30', 1)
insert into tblProjects values('Project5', 5, 0, '2015-05-30', '2015-06-30', 1)
insert into tblProjects values('Project6', 6, 0, '2015-05-30', '2015-06-30', 1)

select * from tblProjects

Create table tblPeriod(
proj_id int references tblProjects(proj_id),
period_1 datetime,
period_2 datetime,
period_3 datetime,
)

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
[type_name] nvarchar(50) 
)

create table tblRoomDetails
(
room_id int primary key identity,
building_id int references tblBuildingDetails(building_id),
[type_id] int references tblRoomType([type_id]),
room_size int,
[floor] int,
room_price int
)

create table tblPaymentType
(
payment_id int primary key identity,
payment_name nvarchar(50),
payment_time int,
interest int
)

create table tblContract
(
con_id int primary key identity,
username nvarchar(50) references tblUser(username),
room_id int references tblRoomDetails(room_id),
payment_id int references tblPaymentType(payment_id),
created_date Datetime,
total_payment int,
total_paid int,
total_due int,
invoice_status int
)

create table tblStampDuty
(
stamp_price int primary key
)