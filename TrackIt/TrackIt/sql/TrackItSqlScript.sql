use TrackIt;

create table Entry (
	entryId int not null auto_increment,
    entryInfoId int not null, 
    entryDate date not null,
    primary key(entryId)
);

create table EntryInfo (
	entryInfoId int not null auto_increment,
    amount decimal not null,
    categoryId int not null,
    entryTypeId int not null,
    primary key(entryInfoId)
);

create table Category (
	categoryId int not null auto_increment,
    categoryName varchar(150) not null,
    primary key(categoryId)
    );

create table EntryType (
	entryTypeId int not null auto_increment,
    isExpense boolean not null, 
    primary key(entryTypeId)
    );
    
    
insert into EntryType (isExpense) values (true),(false);

alter table entry add constraint Entry_fk0 foreign key (entryInfoId) references
entryInfo (entryInfoId);

alter table entryInfo add constraint EntryInfo_fk0 foreign key(categoryId) references
category (categoryId);

alter table entryInfo add constraint EntryInfo_fk1 foreign key(entryTypeId) references
entrytype(entrytypeId);
