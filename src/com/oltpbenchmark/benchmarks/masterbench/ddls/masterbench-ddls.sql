DROP TABLE IF EXISTS tb_doida;
create table tb_doida (
   r_regionkey int not null,
   r_name char(55) not null,
   r_comment char(152) not null,
   PRIMARY KEY ( r_regionkey )
); 

