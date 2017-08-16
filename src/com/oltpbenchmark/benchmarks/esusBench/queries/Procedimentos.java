package com.oltpbenchmark.benchmarks.esusBench.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.SQLStmt;

public class Procedimentos extends Procedure {

	// -----------------------------------------------------------------
	// STATEMENTS
	// -----------------------------------------------------------------

	public SQLStmt selectActor = new SQLStmt(
			"select "
					+ "	tb_dado_rel_processamento.co_ator_papel, "
					+ "	tb_dado_rel_processamento.co_dim_tempo_final, "
					+ "	tb_dado_rel_processamento.co_dim_tempo_inicio, "
					+ "	tb_dado_rel_processamento.co_seq_dado_rel_processamento, "
					+ "	tb_dado_rel_processamento.dt_inicio_processamento, "
					+ "	tb_dado_rel_processamento.dt_marca_reprocessamento, "
					+ "	tb_dado_rel_processamento.dt_processamento, "
					+ "	tb_dado_rel_processamento.dt_processamento_cadastros,"
					+ "	tb_dado_rel_processamento.dt_processamento_cnss_rel_op "
					+ "from "
					+ "	tb_dado_rel_processamento tb_dado_rel_processamento "
					+ "where "
					+ "	tb_dado_rel_processamento.co_seq_dado_rel_processamento =( "
					+ "		select "
					+ "			max( tb_dado_rel_processamento.co_seq_dado_rel_processamento ) "
					+ "		from "
					+ "			tb_dado_rel_processamento tb_dado_rel_processamento "
					+ "	)");

	// 1 - coDimMunicipio
	// 2 - codimTempoInicial
	// 3 - CoDimTempoFinal
	public SQLStmt quadroConfigRelatorioProcedimentoTotalizador = new SQLStmt(
			"select "
					+ "coalesce(sum(case when fato.nuCns is not null then 1 else 0 end),0), "
					+ "coalesce(sum(case when fato.nuCns is null then 1 else 0 end),0) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   tb_fat_proced_atend.co_fat_procedimento as coFatProcedimento, "
					+ "   tb_fat_proced_atend.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_proced_atend.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_proced_atend.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_proced_atend.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_proced_atend.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_proced_atend.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_proced_atend.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_proced_atend.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_proced_atend.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "   tb_fat_proced_atend.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "   tb_fat_proced_atend.co_dim_sexo as coDimSexo, "
					+ "   tb_fat_proced_atend.co_dim_turno as coDimTurno, "
					+ "   tb_fat_proced_atend.dt_nascimento as dtNascimento, "
					+ "   tb_fat_proced_atend.nu_cns as nuCns, "
					+ "   tb_fat_proced_atend.nu_atendimento as nuAtendimento, "
					+ "   tb_fat_proced_atend.st_escuta_inicial as stEscutaInicial "
					+ "   from tb_fat_proced_atend tb_fat_proced_atend "
					+ "   where tb_fat_proced_atend.co_dim_municipio = ? "
					+ "   and tb_fat_proced_atend.co_dim_tempo between ? "
					+ "   and ? " + ") " + " fato");

	public SQLStmt quadroConfigFichaProcedimentoTurno = new SQLStmt(
			"select "
					+ "tb_dim_turno.co_ordem,tb_dim_turno.ds_turno,count(fato.nuUuidFicha) "
					+ "from tb_dim_turno tb_dim_turno "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_proced_atend.co_fat_procedimento as coFatProcedimento, "
					+ "   tb_fat_proced_atend.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_proced_atend.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_proced_atend.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_proced_atend.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_proced_atend.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_proced_atend.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_proced_atend.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_proced_atend.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_proced_atend.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "   tb_fat_proced_atend.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "   tb_fat_proced_atend.co_dim_sexo as coDimSexo, "
					+ "   tb_fat_proced_atend.co_dim_turno as coDimTurno, "
					+ "   tb_fat_proced_atend.dt_nascimento as dtNascimento, "
					+ "   tb_fat_proced_atend.nu_cns as nuCns, "
					+ "   tb_fat_proced_atend.nu_atendimento as nuAtendimento, "
					+ "   tb_fat_proced_atend.st_escuta_inicial as stEscutaInicial "
					+ "   from tb_fat_proced_atend tb_fat_proced_atend "
					+ "   where tb_fat_proced_atend.co_dim_municipio = ? "
					+ "   and tb_fat_proced_atend.co_dim_tempo between ? "
					+ "   and ? "
					+ ") "
					+ " fato on tb_dim_turno.co_seq_dim_turno = fato.coDimTurno "
					+ "group by tb_dim_turno.co_ordem,tb_dim_turno.ds_turno "
					+ "order by tb_dim_turno.co_ordem asc");

	public SQLStmt quadroConfigFichaProcedimentoFaixaEtaria = new SQLStmt(
			"select "
					+ "tb_dim_faixa_etaria.ds_faixa_etaria, "
					+ "sum(case when fato.coDimSexo = 1 then 1 else 0 end), "
					+ "sum(case when fato.coDimSexo = 0 then 1 else 0 end), "
					+ "sum(case when (fato.coDimSexo != 1 and fato.coDimSexo != 0) then 1 else 0 end) "
					+ "from tb_dim_faixa_etaria tb_dim_faixa_etaria "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   distinct fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimFaixaEtaria as coDimFaixaEtaria, "
					+ "   fact.coDimLocalAtendimento as coDimLocalAtendimento, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimSexo as coDimSexo, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.dtNascimento as dtNascimento, "
					+ "   fact.nuCns as nuCns, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   fact.nuAtendimento as nuAtendimento, "
					+ "   fact.stEscutaInicial as stEscutaInicial "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_proced_atend.co_fat_procedimento as coFatProcedimento, "
					+ "      tb_fat_proced_atend.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_proced_atend.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_proced_atend.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_proced_atend.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_proced_atend.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_proced_atend.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_proced_atend.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_proced_atend.nu_uuid_ficha as nuUuidFicha, "
					+ "      tb_fat_proced_atend.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "      tb_fat_proced_atend.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "      tb_fat_proced_atend.co_dim_sexo as coDimSexo, "
					+ "      tb_fat_proced_atend.co_dim_turno as coDimTurno, "
					+ "      tb_fat_proced_atend.dt_nascimento as dtNascimento, "
					+ "      tb_fat_proced_atend.nu_cns as nuCns, "
					+ "      tb_fat_proced_atend.nu_atendimento as nuAtendimento, "
					+ "      tb_fat_proced_atend.st_escuta_inicial as stEscutaInicial "
					+ "      from tb_fat_proced_atend tb_fat_proced_atend "
					+ "      where tb_fat_proced_atend.co_dim_municipio = ? "
					+ "      and tb_fat_proced_atend.co_dim_tempo between ? "
					+ "      and ? "
					+ "   ) "
					+ "    fact "
					+ ") "
					+ " fato on tb_dim_faixa_etaria.co_seq_dim_faixa_etaria = fato.coDimFaixaEtaria "
					+ "group by tb_dim_faixa_etaria.co_seq_dim_faixa_etaria, "
					+ "tb_dim_faixa_etaria.ds_faixa_etaria "
					+ "order by tb_dim_faixa_etaria.co_seq_dim_faixa_etaria asc ");

	public SQLStmt quadroConfigFichaProcedimentoSexo = new SQLStmt(
			"select "
					+ "tb_dim_sexo.ds_sexo,count(fato.nuUuidFicha) "
					+ "from tb_dim_sexo tb_dim_sexo "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_proced_atend.co_fat_procedimento as coFatProcedimento, "
					+ "   tb_fat_proced_atend.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_proced_atend.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_proced_atend.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_proced_atend.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_proced_atend.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_proced_atend.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_proced_atend.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_proced_atend.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_proced_atend.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "   tb_fat_proced_atend.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "   tb_fat_proced_atend.co_dim_sexo as coDimSexo, "
					+ "   tb_fat_proced_atend.co_dim_turno as coDimTurno, "
					+ "   tb_fat_proced_atend.dt_nascimento as dtNascimento, "
					+ "   tb_fat_proced_atend.nu_cns as nuCns, "
					+ "   tb_fat_proced_atend.nu_atendimento as nuAtendimento, "
					+ "   tb_fat_proced_atend.st_escuta_inicial as stEscutaInicial "
					+ "   from tb_fat_proced_atend tb_fat_proced_atend "
					+ "   where tb_fat_proced_atend.co_dim_municipio = ? "
					+ "   and tb_fat_proced_atend.co_dim_tempo between ? "
					+ "   and ? "
					+ ") "
					+ " fato on tb_dim_sexo.co_seq_dim_sexo = fato.coDimSexo "
					+ "group by tb_dim_sexo.co_ordem,tb_dim_sexo.ds_sexo "
					+ "order by tb_dim_sexo.co_ordem asc, "
					+ "tb_dim_sexo.ds_sexo asc ");

	public SQLStmt quadroConfigFichaProcedimentoLocalAtendimento = new SQLStmt(
			"select "
					+ "tb_dim_local_atendimento.ds_local_atendimento, "
					+ "case tb_dim_local_atendimento.nu_identificador "
					+ "when '4' then '1' when '5' then '2' when '8' then '3' when '7' then '4' "
					+ "when '3' then '5' when '1' then '6' when '2' then '7' when '9' then '8' "
					+ "when '10' then '9' when '6' then '10' else '11' end, "
					+ "count(fato.nuUuidFicha) as quantidade "
					+ "from tb_dim_local_atendimento tb_dim_local_atendimento "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   distinct fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimFaixaEtaria as coDimFaixaEtaria, "
					+ "   fact.coDimLocalAtendimento as coDimLocalAtendimento, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimSexo as coDimSexo, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.dtNascimento as dtNascimento, "
					+ "   fact.nuCns as nuCns, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   fact.nuAtendimento as nuAtendimento, "
					+ "   fact.stEscutaInicial as stEscutaInicial "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_proced_atend.co_fat_procedimento as coFatProcedimento, "
					+ "      tb_fat_proced_atend.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_proced_atend.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_proced_atend.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_proced_atend.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_proced_atend.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_proced_atend.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_proced_atend.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_proced_atend.nu_uuid_ficha as nuUuidFicha, "
					+ "      tb_fat_proced_atend.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "      tb_fat_proced_atend.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "      tb_fat_proced_atend.co_dim_sexo as coDimSexo, "
					+ "      tb_fat_proced_atend.co_dim_turno as coDimTurno, "
					+ "      tb_fat_proced_atend.dt_nascimento as dtNascimento, "
					+ "      tb_fat_proced_atend.nu_cns as nuCns, "
					+ "      tb_fat_proced_atend.nu_atendimento as nuAtendimento, "
					+ "      tb_fat_proced_atend.st_escuta_inicial as stEscutaInicial "
					+ "      from tb_fat_proced_atend tb_fat_proced_atend "
					+ "      where tb_fat_proced_atend.co_dim_municipio = ? "
					+ "      and tb_fat_proced_atend.co_dim_tempo between ? "
					+ "      and ? "
					+ "   ) "
					+ "    fact "
					+ ") "
					+ " fato on tb_dim_local_atendimento.co_seq_dim_local_atendimento = fato.coDimLocalAtendimento "
					+ "where tb_dim_local_atendimento.nu_identificador in ( '4', '5', '8', '7', '3', '1', '2', '9', '10', '6') "
					+ "group by tb_dim_local_atendimento.nu_identificador, "
					+ "tb_dim_local_atendimento.ds_local_atendimento "
					+ "order by case tb_dim_local_atendimento.nu_identificador when '4' then '1' when '5' then '2' when '8' then '3' when '7' then '4' "
					+ "when '3' then '5' when '1' then '6' when '2' then '7' when '9' then '8' "
					+ "when '10' then '9' when '6' then '10' else '11' end asc ");

	public SQLStmt quadroConfigFichaProcedimentoDadoGeral = new SQLStmt(
			"select "
					+ "'Escuta inicial',coalesce(count(proced.nuUuidFicha),0) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   distinct fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimFaixaEtaria as coDimFaixaEtaria, "
					+ "   fact.coDimLocalAtendimento as coDimLocalAtendimento, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimSexo as coDimSexo, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.dtNascimento as dtNascimento, "
					+ "   fact.nuCns as nuCns, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   fact.nuAtendimento as nuAtendimento, "
					+ "   fact.stEscutaInicial as stEscutaInicial "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_proced_atend.co_fat_procedimento as coFatProcedimento, "
					+ "      tb_fat_proced_atend.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_proced_atend.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_proced_atend.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_proced_atend.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_proced_atend.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_proced_atend.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_proced_atend.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_proced_atend.nu_uuid_ficha as nuUuidFicha, "
					+ "      tb_fat_proced_atend.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "      tb_fat_proced_atend.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "      tb_fat_proced_atend.co_dim_sexo as coDimSexo, "
					+ "      tb_fat_proced_atend.co_dim_turno as coDimTurno, "
					+ "      tb_fat_proced_atend.dt_nascimento as dtNascimento, "
					+ "      tb_fat_proced_atend.nu_cns as nuCns, "
					+ "      tb_fat_proced_atend.nu_atendimento as nuAtendimento, "
					+ "      tb_fat_proced_atend.st_escuta_inicial as stEscutaInicial "
					+ "      from tb_fat_proced_atend tb_fat_proced_atend "
					+ "      where tb_fat_proced_atend.co_dim_municipio = ? "
					+ "      and tb_fat_proced_atend.co_dim_tempo between ? "
					+ "      and ? " + "   ) fact ) "
					+ " proced where proced.stEscutaInicial = 1 ");

	public SQLStmt quadroConfigFichaProcedimentoPequenasCirurgias = new SQLStmt(
			"select "
					+ "coalesce(p2.co_proced,tb_dim_procedimento.co_proced), "
					+ "min(tb_dim_procedimento.ds_proced), "
					+ "coalesce(count(fato.nuUuidFicha),0) "
					+ "from tb_dim_procedimento tb_dim_procedimento "
					+ "left join tb_dim_procedimento p2 on tb_dim_procedimento.co_seq_dim_proced_ref_ab = p2.co_seq_dim_procedimento "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   distinct fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimLocalAtendimento as coDimLocalAtendimento, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   tb_fat_proced_atend_proced.co_dim_procedimento as coDimProcedimento, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimSexo as coDimSexo, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.dtNascimento as dtNascimento, "
					+ "   fact.nuCns as nuCns, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   fact.nuAtendimento as nuAtendimento, "
					+ "   fact.stEscutaInicial as stEscutaInicial "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_proced_atend.co_fat_procedimento as coFatProcedimento, "
					+ "      tb_fat_proced_atend.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_proced_atend.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_proced_atend.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_proced_atend.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_proced_atend.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_proced_atend.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_proced_atend.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_proced_atend.nu_uuid_ficha as nuUuidFicha, "
					+ "      tb_fat_proced_atend.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "      tb_fat_proced_atend.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "      tb_fat_proced_atend.co_dim_sexo as coDimSexo, "
					+ "      tb_fat_proced_atend.co_dim_turno as coDimTurno, "
					+ "      tb_fat_proced_atend.dt_nascimento as dtNascimento, "
					+ "      tb_fat_proced_atend.nu_cns as nuCns, "
					+ "      tb_fat_proced_atend.nu_atendimento as nuAtendimento, "
					+ "      tb_fat_proced_atend.st_escuta_inicial as stEscutaInicial "
					+ "      from tb_fat_proced_atend tb_fat_proced_atend "
					+ "      where tb_fat_proced_atend.co_dim_municipio = ? "
					+ "      and tb_fat_proced_atend.co_dim_tempo between ? "
					+ "      and ? "
					+ "   ) "
					+ "    fact "
					+ "   join tb_fat_proced_atend_proced tb_fat_proced_atend_proced on tb_fat_proced_atend_proced.co_fat_procedimento = fact.coFatProcedimento "
					+ "   and tb_fat_proced_atend_proced.nu_atendimento = fact.nuAtendimento "
					+ ") "
					+ " fato on tb_dim_procedimento.co_seq_dim_procedimento = fato.coDimProcedimento "
					+ "where tb_dim_procedimento.co_proced in "
					+ "( "
					+ "   'ABPG001', '0309050022', 'ABPG002', '0101040059', 'ABPG003', '0301100047', 'ABPG004', '0303080019', 'ABPG005', '0401020177', 'ABPG006', '0301100063', 'ABPG007', 'ABPG008', '0401010031', 'ABEX004', '0211020036', 'ABPG010', '0201020033', 'ABPG011', '0301040095', 'ABPG012', '0401010074', 'ABPG013', '0211060100', 'ABPG014', '0303090030', 'ABPG015', '0404010300', 'ABPG016', '0401010112', 'ABPG017', 'ABPG018', '0301100152', 'ABPG019', '0401010066', 'ABPG020', '0211060275', 'ABPG021', '0404010342' "
					+ ") " + "group by coalesce " + "( "
					+ "   p2.co_proced,tb_dim_procedimento.co_proced " + ") ");

	public SQLStmt quadroConfigFichaProcedimentoTesteRapido = new SQLStmt(
			"select "
					+ "coalesce(p2.co_proced,tb_dim_procedimento.co_proced), "
					+ "min(tb_dim_procedimento.ds_proced), "
					+ "coalesce(count(proced.nuUuidFicha),0) "
					+ "from tb_dim_procedimento tb_dim_procedimento "
					+ "left join tb_dim_procedimento p2 on tb_dim_procedimento.co_seq_dim_proced_ref_ab = p2.co_seq_dim_procedimento "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   distinct fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimLocalAtendimento as coDimLocalAtendimento, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   tb_fat_proced_atend_proced.co_dim_procedimento as coDimProcedimento, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimSexo as coDimSexo, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.dtNascimento as dtNascimento, "
					+ "   fact.nuCns as nuCns, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   fact.nuAtendimento as nuAtendimento, "
					+ "   fact.stEscutaInicial as stEscutaInicial "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_proced_atend.co_fat_procedimento as coFatProcedimento, "
					+ "      tb_fat_proced_atend.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_proced_atend.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_proced_atend.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_proced_atend.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_proced_atend.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_proced_atend.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_proced_atend.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_proced_atend.nu_uuid_ficha as nuUuidFicha, "
					+ "      tb_fat_proced_atend.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "      tb_fat_proced_atend.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "      tb_fat_proced_atend.co_dim_sexo as coDimSexo, "
					+ "      tb_fat_proced_atend.co_dim_turno as coDimTurno, "
					+ "      tb_fat_proced_atend.dt_nascimento as dtNascimento, "
					+ "      tb_fat_proced_atend.nu_cns as nuCns, "
					+ "      tb_fat_proced_atend.nu_atendimento as nuAtendimento, "
					+ "      tb_fat_proced_atend.st_escuta_inicial as stEscutaInicial "
					+ "      from tb_fat_proced_atend tb_fat_proced_atend "
					+ "      where tb_fat_proced_atend.co_dim_municipio = ? "
					+ "      and tb_fat_proced_atend.co_dim_tempo between ? "
					+ "      and ? "
					+ "   ) "
					+ "    fact "
					+ "   join tb_fat_proced_atend_proced tb_fat_proced_atend_proced on tb_fat_proced_atend_proced.co_fat_procedimento = fact.coFatProcedimento "
					+ "   and tb_fat_proced_atend_proced.nu_atendimento = fact.nuAtendimento "
					+ ") "
					+ " proced on tb_dim_procedimento.co_seq_dim_procedimento = proced.coDimProcedimento "
					+ "where tb_dim_procedimento.co_proced in ('ABPG022', '0214010066', 'ABPG040', 'ABPG024', '0214010058', 'ABPG025', '0214010090', 'ABPG026', '0214010074') "
					+ "group by coalesce " + "( "
					+ "   p2.co_proced,tb_dim_procedimento.co_proced " + ") ");

	public SQLStmt quadroConfigFichaProcedimentoAdmMedicamento = new SQLStmt(
			"select "
					+ "coalesce(p2.co_proced,tb_dim_procedimento.co_proced), "
					+ "min(tb_dim_procedimento.ds_proced), "
					+ "count(proced.nuUuidFicha) "
					+ "from tb_dim_procedimento tb_dim_procedimento "
					+ "left join tb_dim_procedimento p2 on tb_dim_procedimento.co_seq_dim_proced_ref_ab = p2.co_seq_dim_procedimento "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   distinct fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimLocalAtendimento as coDimLocalAtendimento, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   tb_fat_proced_atend_proced.co_dim_procedimento as coDimProcedimento, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimSexo as coDimSexo, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.dtNascimento as dtNascimento, "
					+ "   fact.nuCns as nuCns, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   fact.nuAtendimento as nuAtendimento, "
					+ "   fact.stEscutaInicial as stEscutaInicial "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_proced_atend.co_fat_procedimento as coFatProcedimento, "
					+ "      tb_fat_proced_atend.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_proced_atend.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_proced_atend.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_proced_atend.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_proced_atend.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_proced_atend.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_proced_atend.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_proced_atend.nu_uuid_ficha as nuUuidFicha, "
					+ "      tb_fat_proced_atend.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "      tb_fat_proced_atend.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "      tb_fat_proced_atend.co_dim_sexo as coDimSexo, "
					+ "      tb_fat_proced_atend.co_dim_turno as coDimTurno, "
					+ "      tb_fat_proced_atend.dt_nascimento as dtNascimento, "
					+ "      tb_fat_proced_atend.nu_cns as nuCns, "
					+ "      tb_fat_proced_atend.nu_atendimento as nuAtendimento, "
					+ "      tb_fat_proced_atend.st_escuta_inicial as stEscutaInicial "
					+ "      from tb_fat_proced_atend tb_fat_proced_atend "
					+ "      where tb_fat_proced_atend.co_dim_municipio = ? "
					+ "      and tb_fat_proced_atend.co_dim_tempo between ? "
					+ "      and ? "
					+ "   ) "
					+ "    fact "
					+ "   join tb_fat_proced_atend_proced tb_fat_proced_atend_proced on tb_fat_proced_atend_proced.co_fat_procedimento = fact.coFatProcedimento "
					+ "   and tb_fat_proced_atend_proced.nu_atendimento = fact.nuAtendimento "
					+ ") "
					+ " proced on tb_dim_procedimento.co_seq_dim_procedimento = proced.coDimProcedimento "
					+ "where tb_dim_procedimento.co_proced in ('ABPG022', '0214010066', 'ABPG040', 'ABPG024', '0214010058', 'ABPG025', '0214010090', 'ABPG026', '0214010074') "
					+ "group by coalesce ( p2.co_proced,tb_dim_procedimento.co_proced ) ");

	public SQLStmt quadroConfigFichaProcedimentoOutro = new SQLStmt(
			"select "
					+ "tb_dim_procedimento.co_proced, "
					+ "tb_dim_procedimento.ds_proced, "
					+ "count(proced.nuUuidFicha) as quantidade "
					+ "from tb_dim_procedimento tb_dim_procedimento "
					+ "right join "
					+ "( "
					+ "   select "
					+ "   distinct fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimLocalAtendimento as coDimLocalAtendimento, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   tb_fat_proced_atend_proced.co_dim_procedimento as coDimProcedimento, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimSexo as coDimSexo, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.dtNascimento as dtNascimento, "
					+ "   fact.nuCns as nuCns, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   fact.nuAtendimento as nuAtendimento, "
					+ "   fact.stEscutaInicial as stEscutaInicial "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_proced_atend.co_fat_procedimento as coFatProcedimento, "
					+ "      tb_fat_proced_atend.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_proced_atend.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_proced_atend.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_proced_atend.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_proced_atend.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_proced_atend.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_proced_atend.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_proced_atend.nu_uuid_ficha as nuUuidFicha, "
					+ "      tb_fat_proced_atend.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "      tb_fat_proced_atend.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "      tb_fat_proced_atend.co_dim_sexo as coDimSexo, "
					+ "      tb_fat_proced_atend.co_dim_turno as coDimTurno, "
					+ "      tb_fat_proced_atend.dt_nascimento as dtNascimento, "
					+ "      tb_fat_proced_atend.nu_cns as nuCns, "
					+ "      tb_fat_proced_atend.nu_atendimento as nuAtendimento, "
					+ "      tb_fat_proced_atend.st_escuta_inicial as stEscutaInicial "
					+ "      from tb_fat_proced_atend tb_fat_proced_atend "
					+ "      where tb_fat_proced_atend.co_dim_municipio = ? "
					+ "      and tb_fat_proced_atend.co_dim_tempo between ? "
					+ "      and ? "
					+ "   ) "
					+ "    fact "
					+ "   join tb_fat_proced_atend_proced tb_fat_proced_atend_proced on tb_fat_proced_atend_proced.co_fat_procedimento = fact.coFatProcedimento "
					+ "   and tb_fat_proced_atend_proced.nu_atendimento = fact.nuAtendimento "
					+ ") "
					+ " proced on tb_dim_procedimento.co_seq_dim_procedimento = proced.coDimProcedimento "
					+ "where tb_dim_procedimento.co_proced not in "
					+ "( 'ABPG013', '0211060100', 'ABPG041', 'ABPG031', 'ABPG032', 'ABPG011', '0301040095', 'ABPG017', 'ABPG025', '0214010090', 'ABPG003', '0301100047', 'ABPG021', '0404010342', 'ABPG027', 'ABPG012', '0401010074', 'ABPG014', '0303090030', 'ABPG001', '0309050022', 'ABPG008', '0401010031', 'ABPG015', '0404010300', 'ABPG018', '0301100152', 'ABPG024', '0214010058', 'ABPG026', '0214010074', 'ABPG022', '0214010066', 'ABPG007', 'ABPG005', '0401020177', 'ABPG020', '0211060275', 'ABPG016', '0401010112', 'ABPG029', 'ABPG028', 'ABEX004', '0211020036', 'ABPG010', '0201020033', 'ABPG002', '0101040059', 'ABPG030', '0301100101', 'ABPG040', 'ABPG004', '0303080019', 'ABPG019', '0401010066', 'ABPG006', '0301100063' ) "
					+ "group by tb_dim_procedimento.co_proced,tb_dim_procedimento.ds_proced "
					+ "order by tb_dim_procedimento.co_proced asc,tb_dim_procedimento.ds_proced asc");

	// -----------------------------------------------------------------
	// RUN
	// -----------------------------------------------------------------

	public ResultSet run(Connection conn, int numQuadros, int coDimMunicipio,
			int startDate, int finalDate) throws UserAbortException,
			SQLException {

		PreparedStatement st = this
				.getPreparedStatement(conn, this.selectActor);
		// st.setInt(1, pageNamespace);
		// st.setString(2, pageTitle);
		ResultSet rs = st.executeQuery();
		if (!rs.next()) {
			throw new UserAbortException("Sem atores cadastrados!");
		}
		rs.close();

		if (numQuadros >= 1) {
			st = this.getPreparedStatement(conn,
					this.quadroConfigRelatorioProcedimentoTotalizador);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}
		if (numQuadros >= 2) {
			st = this.getPreparedStatement(conn,
					this.quadroConfigFichaProcedimentoTurno);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}
		if (numQuadros >= 3) {
			st = this.getPreparedStatement(conn,
					this.quadroConfigFichaProcedimentoFaixaEtaria);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}
		if (numQuadros >= 4) {
			st = this.getPreparedStatement(conn,
					this.quadroConfigFichaProcedimentoSexo);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}
		if (numQuadros >= 5) {
			st = this.getPreparedStatement(conn,
					this.quadroConfigFichaProcedimentoLocalAtendimento);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}
		if (numQuadros >= 6) {
			st = this.getPreparedStatement(conn,
					this.quadroConfigFichaProcedimentoDadoGeral);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}
		if (numQuadros >= 7) {
			st = this.getPreparedStatement(conn,
					this.quadroConfigFichaProcedimentoPequenasCirurgias);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}
		if (numQuadros >= 8) {
			st = this.getPreparedStatement(conn,
					this.quadroConfigFichaProcedimentoTesteRapido);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}
		if (numQuadros >= 9) {
			st = this.getPreparedStatement(conn,
					this.quadroConfigFichaProcedimentoAdmMedicamento);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}
		if (numQuadros >= 10) {
			st = this.getPreparedStatement(conn,
					this.quadroConfigFichaProcedimentoOutro);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}

		return null;
	}

}
