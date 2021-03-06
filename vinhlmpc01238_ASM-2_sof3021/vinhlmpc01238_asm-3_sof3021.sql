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
    [username] varchar(64)  not null,
    [email] varchar(128) not null,
    [password] varchar(64) not null,
    [avatar] nvarchar(32) null,
    [phone] char(10) null,
    [fullname] nvarchar(64) null,
    [is_man] bit null,
    [address] nvarchar(384) null,
    [is_enable] bit not null,
    [is_admin] bit not null,
    constraint uq_username unique ([username]),
    constraint uq_email unique ([email])
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
        N'L?? Minh Vinh A1', 'img1.jpg', '0856538112', N'Tr?????ng ti???u h???c V??nh Th???i 3, V??nh Th???i, Lai Vung, ?????ng Th??p', 1
    ),
    ( /* id account */ 'vinh_ur', 'viur', 'mvinhle22@gmail.com', 1, 0,
        N'L?? Minh Vinh u1', 'img2.jpg', '0856538113', N'Tr?????ng trung h???c c?? s??? H??a Long, H??a Long, Lai Vung, ?????ng Th??p', 1
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
        N'ur4', 'img3.jpg', '0856538112', N'Tr?????ng trung h???c ph??? th??ng Lai Vung 1, th??? tr???n Lai Vung, Lai Vung, ?????ng Th??p', 0
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
    (/* id */ N'??? ??i???n demo 1', 100000, N'??i???n l???nh viba', 3, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 1 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 2', 200000, N'??i???n t??? viba', 24, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 2 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 3', 300000, N'??i???n t??? viba', 1, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 3 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 4', 400000, N'??i???n t??? Vibadio', 0, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 4 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 5', 500000, N'Viba''odin', 1, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 5 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 6', 600000, N'??i???n l???nh viba', 3, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 6 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 7', 700000, N'??i???n l???nh viba', 4, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 7 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 8', 800000, N'??i???n l???nh viba', 5, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 8 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 9', 900000, N'??i???n l???nh viba', 3, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 9 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 10', 1000000, N'??i???n l???nh viba', 2, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 10 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 11', 1100000, N'??i???n l???nh viba', 1, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 11 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 12', 1200000, N'??i???n l???nh viba', 12, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 12 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 13', 1300000, N'??i???n l???nh viba', 12, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 13 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 14', 1400000, N'??i???n l???nh viba', 24, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 14 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 15', 1500000, N'??i???n l???nh viba', 5, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 15 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 16', 1600000, N'??i???n l???nh viba', 6, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 16 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 17', 1700000, N'??i???n l???nh viba', 4, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 17 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 18', 1800000, N'??i???n l???nh viba', 2, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 18 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 19', 1900000, N'??i???n l???nh viba', 1, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 19 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???'),
    (/* id */ N'??? ??i???n demo 20', 2000000, N'??i???n l???nh viba', 0, N'Ninh Ki???u, C???n Th??', N'Chi???c ??? ??i???n demo 20 c?? kh??? n??ng ch???ng n?????c, ch???ng gi???t, an to??n-hi???u qu???')
go
insert into [product_images] 
    (/*[id_image],*/ [id_product], [name_image], [alternative])
values 
    (/*  */ 1, 'demo1.jpg', N'H??nh demo 1'),
    (/*  */ 1, 'demo2.jpg', N'H??nh demo 2'),
    (/*  */ 1, 'demo3.jpg', N'H??nh demo 3'),
    (/*  */ 1, 'demo4.jpg', N'H??nh demo 4')
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
    (/* id */ 1, N'Ch???ng n?????c'),
    (/* id */ 1, N'Ch???ng gi???t'),
    (/* id */ 2, N'Ch???ng n?????c'),
    (/* id */ 1, N'Ch???ng n??ng'),
    (/* id */ 3, N'Ch???ng n?????c'),
    (/* id */ 4, N'Ch???ng n??ng'),
    (/* id */ 3, N'Ch???ng n??ng'),
    (/* id */ 6, N'Ch???ng n?????c'),
    (/* id */ 7, N'Ch???ng gi???t');
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