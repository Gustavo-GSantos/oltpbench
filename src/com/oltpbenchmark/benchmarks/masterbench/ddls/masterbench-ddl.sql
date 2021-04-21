DROP TABLE IF EXISTS tb_dim_sexo;
create table tb_dim_sexo (
   co_seq_dim_sexo integer not null,
   nu_identificador integer not null,
   ds_sexo char(55) not null,
   sg_sexo char(55) not null,
   co_ordem integer not null,
   PRIMARY KEY ( co_seq_dim_sexo )
); 
