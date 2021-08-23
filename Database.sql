/* 
    Only Microsoft SQL Server
    Author: vinh
*/
-- Drop database if exists
if (db_id('vibaodin') is not null) begin
    use [master];
    drop database [vibaodin];
end;
go

-- Crate database
create database [vibaodin];
go

-- Create table and set primary key and index
use [vibaodin];

create table [accounts] (
    [id_account] integer not null primary key identity(1,1),
    [username] varchar(64)  not null unique,
    [email] varchar(128) not null unique,
    [password] varchar(64) not null,
    [avatar] nvarchar(32) null,
    [phone] char(10) null,
    [fullname] nvarchar(64) null,
    [is_man] bit null,
    [address] nvarchar(384) null,
    [is_enable] bit not null,
    [is_admin] bit not null
);
go
create table [products] (
    [id_product] integer not null primary key identity(1,1),
    [title] nvarchar(256) not null,
    [price] float not null,
    [trademark] nvarchar(256) not null,
    [guarantee] float not null,
    [delivery_address] nvarchar(384) not null,
    [detail] nvarchar(2048) not null
);
go
create table [bills] (
    [id_bill] integer not null primary key identity(1,1),
    [id_account] integer not null,
    [date_export] datetime not null
);
go
create table [product_bills] (
    [id_bill] integer not null,
    [id_product] integer not null,
    [quantity] integer not null,
    [unit_price] float not null,
    primary key ([id_bill], [id_product])
);
go
create table [baskets] (
    [id_account] integer not null,
    [id_product] integer not null,
    [quantity] integer not null,
    [push_date] datetime not null,
    primary key([id_account], [id_product])
);
go
create table [product_images] (
    [id_image] integer not null primary key identity(1,1),
    [id_product] integer not null,
    [name_image] varchar(32) not null,
    [alternative] nvarchar(256) null
);
go
create table [product_types] (
    [id_type] integer not null primary key identity(1,1),
    [id_product] integer not null,
    [name] nvarchar(128) not null
);
go

-- insert constraints of table
use [vibaodin];

alter table [bills] add constraint [fk_bills_accounts] foreign key ([id_account]) references [accounts]([id_account]) on delete cascade;
alter table [product_bills] add constraint [fk_product_bills_bills] foreign key ([id_bill]) references [bills]([id_bill])  on delete cascade;
alter table [product_bills] add constraint [fk_product_bills_products] foreign key ([id_product]) references [products]([id_product])  on delete cascade;
alter table [product_images] add constraint [fk_product_images_products] foreign key ([id_product]) references [products]([id_product]) on delete cascade;
alter table [product_types] add constraint [fk_product_types_products] foreign key ([id_product]) references [products]([id_product]) on delete cascade;
alter table [baskets] add constraint [fk_baskets_products] foreign key ([id_product]) references [products]([id_product]) on delete cascade;
alter table [baskets] add constraint [fk_baskets_accounts] foreign key ([id_account]) references [accounts]([id_account]) on delete cascade;
go

-- insert data demo in table
use [vibaodin]

insert into [accounts] 
    ( /*[id_account]*/ [username], [password], [email], [is_enable], [is_admin], 
        [fullname], [avatar], [phone], [address], [is_man]
    )
values 
    ( /* id account */ 'vinh_ad', 'viad', 'vinhlmpc01238@fpt.edu.vn', 1, 1,
        N'Lê Minh Vinh A1', 'img1.jpg', '0856538112', N'Trường tiểu học Vĩnh Thới 3, Vĩnh Thới, Lai Vung, Đồng Tháp', 1
    ),
    ( /* id account */ 'vinh_ur', 'viur', 'mvinhle22@gmail.com', 1, 0,
        N'Lê Minh Vinh u1', 'img2.jpg', '0856538113', N'Trường trung học cơ sỡ Hòa Long, Hòa Long, Lai Vung, Đồng Tháp', 1
    ),
    ( /* id account */ 'account1', 'ac1', 'ac1@gmail.com', 1, 0,
        null, null, null, null, null
    ),
    ( /* id account */ 'account2', 'ac2', 'ac2@gmail.com', 0, 1,
        null, null, null, null, null
    ),
    ( /* id account */ 'account3', 'ac3', 'ac3@gmail.com', 0, 0,
        null, null, null, null, null
    ),
    ( /* id account */ 'account4', 'ac4', 'ac4@gmail.com', 1, 1,
        N'ur4', 'img3.jpg', '0856538112', N'Trường trung học phổ thông Lai Vung 1, thị trấn Lai Vung, Lai Vung, Đồng Tháp', 0
    ),
    ( /* id account */ 'account5', 'ac5', 'ac5@gmail.com', 1, 1,
        null, null, null, null, null
    ),
    ( /* id account */ 'account6', 'ac6', 'ac6@gmail.com', 1, 1,
        null, null, null, null, null
    ),
    ( /* id account */ 'account7', 'ac7', 'ac7@gmail.com', 1, 1,
        null, null, null, null, null
    );
go
insert into [products] 
    (/*[id_product],*/ [title], [price], [trademark], [guarantee], [delivery_address], [detail])
values 
    (/* id */ N'Ổ điện demo 1', 100000, N'Điện lạnh viba', 3, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 1 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 2', 200000, N'Điện tử viba', 24, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 2 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 3', 300000, N'Điện từ viba', 1, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 3 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 4', 400000, N'Điện tử Vibadio', 0, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 4 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 5', 500000, N'Viba''odin', 1, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 5 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 6', 600000, N'Điện lạnh viba', 3, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 6 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 7', 700000, N'Điện lạnh viba', 4, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 7 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 8', 800000, N'Điện lạnh viba', 5, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 8 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 9', 900000, N'Điện lạnh viba', 3, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 9 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 10', 1000000, N'Điện lạnh viba', 2, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 10 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 11', 1100000, N'Điện lạnh viba', 1, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 11 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 12', 1200000, N'Điện lạnh viba', 12, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 12 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 13', 1300000, N'Điện lạnh viba', 12, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 13 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 14', 1400000, N'Điện lạnh viba', 24, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 14 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 15', 1500000, N'Điện lạnh viba', 5, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 15 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 16', 1600000, N'Điện lạnh viba', 6, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 16 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 17', 1700000, N'Điện lạnh viba', 4, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 17 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 18', 1800000, N'Điện lạnh viba', 2, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 18 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 19', 1900000, N'Điện lạnh viba', 1, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 19 có khả năng chống nước, chống giật, an toàn-hiệu quả'),
    (/* id */ N'Ổ điện demo 20', 2000000, N'Điện lạnh viba', 0, N'Ninh Kiều, Cần Thơ', N'Chiếc ổ điện demo 20 có khả năng chống nước, chống giật, an toàn-hiệu quả')
go
insert into [product_images] 
    (/*[id_image],*/ [id_product], [name_image], [alternative])
values 
    (/*  */ 1, 'demo1.jpg', N'Hình demo 1'),
    (/*  */ 1, 'demo2.jpg', N'Hình demo 2'),
    (/*  */ 1, 'demo3.jpg', N'Hình demo 3'),
    (/*  */ 1, 'demo4.jpg', N'Hình demo 4')
go
insert into [baskets] 
    ([id_account], [id_product], [quantity], [push_date])
values 
    (1, 1, 1, getdate()),
    (1, 2, 2, getdate()),
    (1, 3, 1, getdate()),
    (1, 4, 1, getdate()),
    (2, 3, 1, getdate()),
    (2, 4, 1, getdate());
go
insert into [product_types] (/* [id_type],*/ [id_product], [name])
values 
    (/* id */ 1, N'Chống nước'),
    (/* id */ 1, N'Chống giật'),
    (/* id */ 2, N'Chống nước'),
    (/* id */ 1, N'Chống nóng'),
    (/* id */ 3, N'Chống nước'),
    (/* id */ 4, N'Chống nóng'),
    (/* id */ 3, N'Chống nóng'),
    (/* id */ 6, N'Chống nước'),
    (/* id */ 7, N'Chống giật');
go
insert into [bills] 
    (/*[id_bill],*/ [id_account], [date_export])
values 
    (/* id */ 1 , dateadd(day, -5, getdate())),
    (/* id */ 1 , dateadd(day, -4, getdate())),
    (/* id */ 3 , dateadd(day, -3, getdate())),
    (/* id */ 4 , dateadd(day, -2, getdate())),
    (/* id */ 5 , dateadd(day, -1, getdate()));
go
insert into [product_bills] 
    ([id_bill], [id_product], [quantity], [unit_price])
values
    (1, 1, 1, 1000000),
    (1, 2, 3, 1000000),
    (1, 3, 4, 1000000),
    (2, 4, 1, 1000000),
    (3, 1, 2, 1000000),
    (4, 1, 3, 1000000),
    (5, 2, 4, 1000000);
go

-- select all table
use [vibaodin]

select * from [accounts]; --
select * from [products]; --
select * from [baskets]; --
select * from [bills]; --
select * from [product_bills]; --
select * from [product_types]; --
select * from [product_images]; --
go

-- delete from [product_types] where [id_product] = 1;