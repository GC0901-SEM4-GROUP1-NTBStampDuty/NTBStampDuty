create database StampDuty
go
use StampDuty
go
create table tblEmp
(
username nvarchar(50) primary key,
[password] nvarchar(50),
[role] int
)

create table tblCustomer
(
username nvarchar(50) primary key,
[password] nvarchar(50)
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
WITH limt_land AS
  ( SELECT land_id,size,address_id,building_types,building_plan,built_status,img, ROW_NUMBER() OVER (ORDER BY land_id ASC) AS [row_number]
    FROM tblLand
  )
SELECT land_id,size,address_id,building_types,building_plan,built_status,img FROM limt_land WHERE [row_number] >2 AND [row_number] <=4

select round(count(*)/3+0.5) from tblLand

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
land_id int references tblLand(land_id),
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
bulding_name nvarchar(50),
floors int,
rooms int,
houses int,
shops int,
date_contructed Datetime,
completed_percent int
)

insert into tblBuildingDetails values (1, 1, 'New Buidling', 10, 0, 60, 0, null, 10)
insert into tblBuildingDetails values (2, 2, 'Newer Buidling', 10, 60, 0, 0, null, 10)
insert into tblBuildingDetails values (3, 3, 'New Shop Buidling', 10, 0, 0, 60, null, 10)
insert into tblBuildingDetails values (4, 2, 'House Buidling', 10, 60, 0, 0, null, 10)
insert into tblBuildingDetails values (5, 1, 'Office Buidling', 10, 0, 60, 0, null, 10)
insert into tblBuildingDetails values (6, 1, 'New Office Buidling', 10, 0, 60, 0, null, 10)

select * from tblBuildingDetails

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

create table tblInvoice
(
bill_id int primary key identity,
username nvarchar(50) references tblCustomer(username),
room_id int references tblRoomDetails(room_id),
payment_id int references tblPaymentType(payment_id),
first_paid_date Datetime,
created_date Datetime,
total_paid int,
total_due int,
invoice_status int
)

create table tblStampDuty
(
stamp_price int primary key
)