package com.oltpbenchmark.benchmarks.esusBench.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.SQLStmt;

public class Complementar extends Procedure {

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
	public SQLStmt quadroConfigRelatorioComplementarTotalizador = new SQLStmt(
			"select " +
					"count(fato.nuUuidFicha) " +
					"from " +
					"( " +
					"   select " +
					"   tb_fat_complementar.co_dim_cbo as coDimCbo, " +
					"   tb_fat_complementar.co_dim_equipe as coDimEquipe, " +
					"   tb_fat_complementar.co_dim_municipio as coDimMunicipio, " +
					"   tb_fat_complementar.co_dim_profissional as coDimProfissional, " +
					"   tb_fat_complementar.co_dim_tempo as coDimTempo, " +
					"   tb_fat_complementar.co_dim_tipo_ficha as coDimTipoFicha, " +
					"   tb_fat_complementar.co_dim_turno as coDimTurno, " +
					"   tb_fat_complementar.co_dim_unidade_saude as coDimUnidadeSaude, " +
					"   tb_fat_complementar.co_seq_fat_complementar as coSeqFatComplementar, " +
					"   tb_fat_complementar.dt_exame_fundo_olho as dtExameFundoOlho, " +
					"   tb_fat_complementar.dt_ressonancia as dtRessonancia, " +
					"   tb_fat_complementar.dt_teste_olhinho as dtTesteOlhinho, " +
					"   tb_fat_complementar.dt_teste_orelhinha as dtTesteOrelhinha, " +
					"   tb_fat_complementar.dt_tomografia as dtTomografia, " +
					"   tb_fat_complementar.dt_transfontanela as dtTransfontanela, " +
					"   tb_fat_complementar.nu_cns as nuCns, " +
					"   tb_fat_complementar.nu_cns_responsavel as nuCnsResponsavel, " +
					"   tb_fat_complementar.nu_uuid_ficha as nuUuidFicha, " +
					"   tb_fat_complementar.st_exame_fundo_olho as stExameFundoOlho, " +
					"   tb_fat_complementar.st_ressonancia as stRessonancia, " +
					"   tb_fat_complementar.st_teste_olhinho as stTesteOlhinho, " +
					"   tb_fat_complementar.st_teste_orelhinha as stTesteOrelhinha, " +
					"   tb_fat_complementar.st_tomografia as stTomografia, " +
					"   tb_fat_complementar.st_transfontanela as stTransfontanela " +
					"   from tb_fat_complementar tb_fat_complementar " +
					"   where tb_fat_complementar.co_dim_municipio = ? " +
					"   and tb_fat_complementar.co_dim_tempo between ? " +
					"   and ? " +
					") " +
					"as fato "
	);

	public SQLStmt quadroConfigRelatorioComplementarTurno = new SQLStmt(
			"select " +
					"tb_dim_turno.co_ordem,tb_dim_turno.ds_turno,count(distinct fato.nuUuidFicha) " +
					"from tb_dim_turno tb_dim_turno " +
					"left join " +
					"( " +
					"   select " +
					"   tb_fat_complementar.co_dim_cbo as coDimCbo, " +
					"   tb_fat_complementar.co_dim_equipe as coDimEquipe, " +
					"   tb_fat_complementar.co_dim_municipio as coDimMunicipio, " +
					"   tb_fat_complementar.co_dim_profissional as coDimProfissional, " +
					"   tb_fat_complementar.co_dim_tempo as coDimTempo, " +
					"   tb_fat_complementar.co_dim_tipo_ficha as coDimTipoFicha, " +
					"   tb_fat_complementar.co_dim_turno as coDimTurno, " +
					"   tb_fat_complementar.co_dim_unidade_saude as coDimUnidadeSaude, " +
					"   tb_fat_complementar.co_seq_fat_complementar as coSeqFatComplementar, " +
					"   tb_fat_complementar.dt_exame_fundo_olho as dtExameFundoOlho, " +
					"   tb_fat_complementar.dt_ressonancia as dtRessonancia, " +
					"   tb_fat_complementar.dt_teste_olhinho as dtTesteOlhinho, " +
					"   tb_fat_complementar.dt_teste_orelhinha as dtTesteOrelhinha, " +
					"   tb_fat_complementar.dt_tomografia as dtTomografia, " +
					"   tb_fat_complementar.dt_transfontanela as dtTransfontanela, " +
					"   tb_fat_complementar.nu_cns as nuCns, " +
					"   tb_fat_complementar.nu_cns_responsavel as nuCnsResponsavel, " +
					"   tb_fat_complementar.nu_uuid_ficha as nuUuidFicha, " +
					"   tb_fat_complementar.st_exame_fundo_olho as stExameFundoOlho, " +
					"   tb_fat_complementar.st_ressonancia as stRessonancia, " +
					"   tb_fat_complementar.st_teste_olhinho as stTesteOlhinho, " +
					"   tb_fat_complementar.st_teste_orelhinha as stTesteOrelhinha, " +
					"   tb_fat_complementar.st_tomografia as stTomografia, " +
					"   tb_fat_complementar.st_transfontanela as stTransfontanela " +
					"   from tb_fat_complementar tb_fat_complementar " +
					"   where tb_fat_complementar.co_dim_municipio = ? " +
					"   and tb_fat_complementar.co_dim_tempo between ? " +
					"   and ? " +
					") " +
					"as fato on tb_dim_turno.co_seq_dim_turno = fato.coDimTurno " +
					"where tb_dim_turno.nu_identificador != '-' " +
					"group by tb_dim_turno.co_ordem,tb_dim_turno.ds_turno " +
					"order by tb_dim_turno.co_ordem asc "
	);

	public SQLStmt quadroConfigRelatorioComplementarTesteOlhinho = new SQLStmt(
			"select " +
					"coalesce(sum(case when fact.stTesteOlhinho = 1 then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stTesteOlhinho = 0 then 1 else 0 end),0) " +
					"from " +
					"( " +
					"   select " +
					"   tb_fat_complementar.co_dim_cbo as coDimCbo, " +
					"   tb_fat_complementar.co_dim_equipe as coDimEquipe, " +
					"   tb_fat_complementar.co_dim_municipio as coDimMunicipio, " +
					"   tb_fat_complementar.co_dim_profissional as coDimProfissional, " +
					"   tb_fat_complementar.co_dim_tempo as coDimTempo, " +
					"   tb_fat_complementar.co_dim_tipo_ficha as coDimTipoFicha, " +
					"   tb_fat_complementar.co_dim_turno as coDimTurno, " +
					"   tb_fat_complementar.co_dim_unidade_saude as coDimUnidadeSaude, " +
					"   tb_fat_complementar.co_seq_fat_complementar as coSeqFatComplementar, " +
					"   tb_fat_complementar.dt_exame_fundo_olho as dtExameFundoOlho, " +
					"   tb_fat_complementar.dt_ressonancia as dtRessonancia, " +
					"   tb_fat_complementar.dt_teste_olhinho as dtTesteOlhinho, " +
					"   tb_fat_complementar.dt_teste_orelhinha as dtTesteOrelhinha, " +
					"   tb_fat_complementar.dt_tomografia as dtTomografia, " +
					"   tb_fat_complementar.dt_transfontanela as dtTransfontanela, " +
					"   tb_fat_complementar.nu_cns as nuCns, " +
					"   tb_fat_complementar.nu_cns_responsavel as nuCnsResponsavel, " +
					"   tb_fat_complementar.nu_uuid_ficha as nuUuidFicha, " +
					"   tb_fat_complementar.st_exame_fundo_olho as stExameFundoOlho, " +
					"   tb_fat_complementar.st_ressonancia as stRessonancia, " +
					"   tb_fat_complementar.st_teste_olhinho as stTesteOlhinho, " +
					"   tb_fat_complementar.st_teste_orelhinha as stTesteOrelhinha, " +
					"   tb_fat_complementar.st_tomografia as stTomografia, " +
					"   tb_fat_complementar.st_transfontanela as stTransfontanela " +
					"   from tb_fat_complementar tb_fat_complementar " +
					"   where tb_fat_complementar.co_dim_municipio = ? " +
					"   and tb_fat_complementar.co_dim_tempo between ? " +
					"   and ? " +
					") " +
					"as fact "
	);

	public SQLStmt quadroConfigRelatorioComplementarExameFundoOlho = new SQLStmt(
			"select " +
					"coalesce(sum(case when fact.stExameFundoOlho = 1 then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stExameFundoOlho = 0 then 1 else 0 end),0) " +
					"from " +
					"( " +
					"   select " +
					"   tb_fat_complementar.co_dim_cbo as coDimCbo, " +
					"   tb_fat_complementar.co_dim_equipe as coDimEquipe, " +
					"   tb_fat_complementar.co_dim_municipio as coDimMunicipio, " +
					"   tb_fat_complementar.co_dim_profissional as coDimProfissional, " +
					"   tb_fat_complementar.co_dim_tempo as coDimTempo, " +
					"   tb_fat_complementar.co_dim_tipo_ficha as coDimTipoFicha, " +
					"   tb_fat_complementar.co_dim_turno as coDimTurno, " +
					"   tb_fat_complementar.co_dim_unidade_saude as coDimUnidadeSaude, " +
					"   tb_fat_complementar.co_seq_fat_complementar as coSeqFatComplementar, " +
					"   tb_fat_complementar.dt_exame_fundo_olho as dtExameFundoOlho, " +
					"   tb_fat_complementar.dt_ressonancia as dtRessonancia, " +
					"   tb_fat_complementar.dt_teste_olhinho as dtTesteOlhinho, " +
					"   tb_fat_complementar.dt_teste_orelhinha as dtTesteOrelhinha, " +
					"   tb_fat_complementar.dt_tomografia as dtTomografia, " +
					"   tb_fat_complementar.dt_transfontanela as dtTransfontanela, " +
					"   tb_fat_complementar.nu_cns as nuCns, " +
					"   tb_fat_complementar.nu_cns_responsavel as nuCnsResponsavel, " +
					"   tb_fat_complementar.nu_uuid_ficha as nuUuidFicha, " +
					"   tb_fat_complementar.st_exame_fundo_olho as stExameFundoOlho, " +
					"   tb_fat_complementar.st_ressonancia as stRessonancia, " +
					"   tb_fat_complementar.st_teste_olhinho as stTesteOlhinho, " +
					"   tb_fat_complementar.st_teste_orelhinha as stTesteOrelhinha, " +
					"   tb_fat_complementar.st_tomografia as stTomografia, " +
					"   tb_fat_complementar.st_transfontanela as stTransfontanela " +
					"   from tb_fat_complementar tb_fat_complementar " +
					"   where tb_fat_complementar.co_dim_municipio = ? " +
					"   and tb_fat_complementar.co_dim_tempo between ? " +
					"   and ? " +
					") " +
					"as fact "
	);

	public SQLStmt quadroConfigRelatorioComplementarTesteDaOrelhinha = new SQLStmt(
			"select " +
					"coalesce(sum(case when fact.stTesteOrelhinha = 1 then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stTesteOrelhinha = 0 then 1 else 0 end),0) " +
					"from " +
					"( " +
					"   select " +
					"   tb_fat_complementar.co_dim_cbo as coDimCbo, " +
					"   tb_fat_complementar.co_dim_equipe as coDimEquipe, " +
					"   tb_fat_complementar.co_dim_municipio as coDimMunicipio, " +
					"   tb_fat_complementar.co_dim_profissional as coDimProfissional, " +
					"   tb_fat_complementar.co_dim_tempo as coDimTempo, " +
					"   tb_fat_complementar.co_dim_tipo_ficha as coDimTipoFicha, " +
					"   tb_fat_complementar.co_dim_turno as coDimTurno, " +
					"   tb_fat_complementar.co_dim_unidade_saude as coDimUnidadeSaude, " +
					"   tb_fat_complementar.co_seq_fat_complementar as coSeqFatComplementar, " +
					"   tb_fat_complementar.dt_exame_fundo_olho as dtExameFundoOlho, " +
					"   tb_fat_complementar.dt_ressonancia as dtRessonancia, " +
					"   tb_fat_complementar.dt_teste_olhinho as dtTesteOlhinho, " +
					"   tb_fat_complementar.dt_teste_orelhinha as dtTesteOrelhinha, " +
					"   tb_fat_complementar.dt_tomografia as dtTomografia, " +
					"   tb_fat_complementar.dt_transfontanela as dtTransfontanela, " +
					"   tb_fat_complementar.nu_cns as nuCns, " +
					"   tb_fat_complementar.nu_cns_responsavel as nuCnsResponsavel, " +
					"   tb_fat_complementar.nu_uuid_ficha as nuUuidFicha, " +
					"   tb_fat_complementar.st_exame_fundo_olho as stExameFundoOlho, " +
					"   tb_fat_complementar.st_ressonancia as stRessonancia, " +
					"   tb_fat_complementar.st_teste_olhinho as stTesteOlhinho, " +
					"   tb_fat_complementar.st_teste_orelhinha as stTesteOrelhinha, " +
					"   tb_fat_complementar.st_tomografia as stTomografia, " +
					"   tb_fat_complementar.st_transfontanela as stTransfontanela " +
					"   from tb_fat_complementar tb_fat_complementar " +
					"   where tb_fat_complementar.co_dim_municipio = ? " +
					"   and tb_fat_complementar.co_dim_tempo between ? " +
					"   and ? " +
					") " +
					"as fact "
	);

	public SQLStmt quadroConfigRelatorioComplementarExamesImagemTransforntanela = new SQLStmt(
			"select " +
					"coalesce(sum(case when fact.stTransfontanela = '7' then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stTransfontanela = '8' then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stTransfontanela = '9' then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stTransfontanela = '10' then 1 else 0 end),0) " +
					"from " +
					"( " +
					"   select " +
					"   tb_fat_complementar.co_dim_cbo as coDimCbo, " +
					"   tb_fat_complementar.co_dim_equipe as coDimEquipe, " +
					"   tb_fat_complementar.co_dim_municipio as coDimMunicipio, " +
					"   tb_fat_complementar.co_dim_profissional as coDimProfissional, " +
					"   tb_fat_complementar.co_dim_tempo as coDimTempo, " +
					"   tb_fat_complementar.co_dim_tipo_ficha as coDimTipoFicha, " +
					"   tb_fat_complementar.co_dim_turno as coDimTurno, " +
					"   tb_fat_complementar.co_dim_unidade_saude as coDimUnidadeSaude, " +
					"   tb_fat_complementar.co_seq_fat_complementar as coSeqFatComplementar, " +
					"   tb_fat_complementar.dt_exame_fundo_olho as dtExameFundoOlho, " +
					"   tb_fat_complementar.dt_ressonancia as dtRessonancia, " +
					"   tb_fat_complementar.dt_teste_olhinho as dtTesteOlhinho, " +
					"   tb_fat_complementar.dt_teste_orelhinha as dtTesteOrelhinha, " +
					"   tb_fat_complementar.dt_tomografia as dtTomografia, " +
					"   tb_fat_complementar.dt_transfontanela as dtTransfontanela, " +
					"   tb_fat_complementar.nu_cns as nuCns, " +
					"   tb_fat_complementar.nu_cns_responsavel as nuCnsResponsavel, " +
					"   tb_fat_complementar.nu_uuid_ficha as nuUuidFicha, " +
					"   tb_fat_complementar.st_exame_fundo_olho as stExameFundoOlho, " +
					"   tb_fat_complementar.st_ressonancia as stRessonancia, " +
					"   tb_fat_complementar.st_teste_olhinho as stTesteOlhinho, " +
					"   tb_fat_complementar.st_teste_orelhinha as stTesteOrelhinha, " +
					"   tb_fat_complementar.st_tomografia as stTomografia, " +
					"   tb_fat_complementar.st_transfontanela as stTransfontanela " +
					"   from tb_fat_complementar tb_fat_complementar " +
					"   where tb_fat_complementar.co_dim_municipio = ? " +
					"   and tb_fat_complementar.co_dim_tempo between ? " +
					"   and ? " +
					") " +
					"as fact "
	);

	public SQLStmt quadroConfigRelatorioComplementarExamesImagemTomografiaComputadorizada = new SQLStmt(
			"select " +
					"coalesce(sum(case when fact.stTomografia = '7' then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stTomografia = '8' then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stTomografia = '9' then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stTomografia = '10' then 1 else 0 end),0) " +
					"from " +
					"( " +
					"   select " +
					"   tb_fat_complementar.co_dim_cbo as coDimCbo, " +
					"   tb_fat_complementar.co_dim_equipe as coDimEquipe, " +
					"   tb_fat_complementar.co_dim_municipio as coDimMunicipio, " +
					"   tb_fat_complementar.co_dim_profissional as coDimProfissional, " +
					"   tb_fat_complementar.co_dim_tempo as coDimTempo, " +
					"   tb_fat_complementar.co_dim_tipo_ficha as coDimTipoFicha, " +
					"   tb_fat_complementar.co_dim_turno as coDimTurno, " +
					"   tb_fat_complementar.co_dim_unidade_saude as coDimUnidadeSaude, " +
					"   tb_fat_complementar.co_seq_fat_complementar as coSeqFatComplementar, " +
					"   tb_fat_complementar.dt_exame_fundo_olho as dtExameFundoOlho, " +
					"   tb_fat_complementar.dt_ressonancia as dtRessonancia, " +
					"   tb_fat_complementar.dt_teste_olhinho as dtTesteOlhinho, " +
					"   tb_fat_complementar.dt_teste_orelhinha as dtTesteOrelhinha, " +
					"   tb_fat_complementar.dt_tomografia as dtTomografia, " +
					"   tb_fat_complementar.dt_transfontanela as dtTransfontanela, " +
					"   tb_fat_complementar.nu_cns as nuCns, " +
					"   tb_fat_complementar.nu_cns_responsavel as nuCnsResponsavel, " +
					"   tb_fat_complementar.nu_uuid_ficha as nuUuidFicha, " +
					"   tb_fat_complementar.st_exame_fundo_olho as stExameFundoOlho, " +
					"   tb_fat_complementar.st_ressonancia as stRessonancia, " +
					"   tb_fat_complementar.st_teste_olhinho as stTesteOlhinho, " +
					"   tb_fat_complementar.st_teste_orelhinha as stTesteOrelhinha, " +
					"   tb_fat_complementar.st_tomografia as stTomografia, " +
					"   tb_fat_complementar.st_transfontanela as stTransfontanela " +
					"   from tb_fat_complementar tb_fat_complementar " +
					"   where tb_fat_complementar.co_dim_municipio = ? " +
					"   and tb_fat_complementar.co_dim_tempo between ? " +
					"   and ? " +
					") " +
					"as fact "
	);

	public SQLStmt quadroConfigRelatorioComplementarExamesImagemRessonanciaMagnetica = new SQLStmt(
			"select " +
					"coalesce(sum(case when fact.stRessonancia = '7' then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stRessonancia = '8' then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stRessonancia = '9' then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stRessonancia = '10' then 1 else 0 end),0) " +
					"from " +
					"( " +
					"   select " +
					"   tb_fat_complementar.co_dim_cbo as coDimCbo, " +
					"   tb_fat_complementar.co_dim_equipe as coDimEquipe, " +
					"   tb_fat_complementar.co_dim_municipio as coDimMunicipio, " +
					"   tb_fat_complementar.co_dim_profissional as coDimProfissional, " +
					"   tb_fat_complementar.co_dim_tempo as coDimTempo, " +
					"   tb_fat_complementar.co_dim_tipo_ficha as coDimTipoFicha, " +
					"   tb_fat_complementar.co_dim_turno as coDimTurno, " +
					"   tb_fat_complementar.co_dim_unidade_saude as coDimUnidadeSaude, " +
					"   tb_fat_complementar.co_seq_fat_complementar as coSeqFatComplementar, " +
					"   tb_fat_complementar.dt_exame_fundo_olho as dtExameFundoOlho, " +
					"   tb_fat_complementar.dt_ressonancia as dtRessonancia, " +
					"   tb_fat_complementar.dt_teste_olhinho as dtTesteOlhinho, " +
					"   tb_fat_complementar.dt_teste_orelhinha as dtTesteOrelhinha, " +
					"   tb_fat_complementar.dt_tomografia as dtTomografia, " +
					"   tb_fat_complementar.dt_transfontanela as dtTransfontanela, " +
					"   tb_fat_complementar.nu_cns as nuCns, " +
					"   tb_fat_complementar.nu_cns_responsavel as nuCnsResponsavel, " +
					"   tb_fat_complementar.nu_uuid_ficha as nuUuidFicha, " +
					"   tb_fat_complementar.st_exame_fundo_olho as stExameFundoOlho, " +
					"   tb_fat_complementar.st_ressonancia as stRessonancia, " +
					"   tb_fat_complementar.st_teste_olhinho as stTesteOlhinho, " +
					"   tb_fat_complementar.st_teste_orelhinha as stTesteOrelhinha, " +
					"   tb_fat_complementar.st_tomografia as stTomografia, " +
					"   tb_fat_complementar.st_transfontanela as stTransfontanela " +
					"   from tb_fat_complementar tb_fat_complementar " +
					"   where tb_fat_complementar.co_dim_municipio = ? " +
					"   and tb_fat_complementar.co_dim_tempo between ? " +
					"   and ? " +
					") " +
					"as fact "
	);

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
					this.quadroConfigRelatorioComplementarTotalizador);
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
					this.quadroConfigRelatorioComplementarTurno);
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
					this.quadroConfigRelatorioComplementarTesteOlhinho);
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
					this.quadroConfigRelatorioComplementarExameFundoOlho);
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
					this.quadroConfigRelatorioComplementarTesteDaOrelhinha);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}
		if (numQuadros >= 6) {
			st = this
					.getPreparedStatement(
							conn,
							this.quadroConfigRelatorioComplementarExamesImagemTransforntanela);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}
		if (numQuadros >= 7) {
			st = this
					.getPreparedStatement(
							conn,
							this.quadroConfigRelatorioComplementarExamesImagemTomografiaComputadorizada);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}
		if (numQuadros >= 8) {
			st = this
					.getPreparedStatement(
							conn,
							this.quadroConfigRelatorioComplementarExamesImagemRessonanciaMagnetica);
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
