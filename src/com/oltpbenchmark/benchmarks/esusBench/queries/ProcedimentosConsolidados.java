package com.oltpbenchmark.benchmarks.esusBench.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.SQLStmt;

public class ProcedimentosConsolidados extends Procedure {

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
			"select " +
					"coalesce(sum(proced.nrProcedimentoConsolidado1),0), " +
					"coalesce(sum(proced.nrProcedimentoConsolidado2),0), " +
					"coalesce(sum(proced.nrProcedimentoConsolidado3),0), " +
					"coalesce(sum(proced.nrProcedimentoConsolidado4),0), " +
					"coalesce(sum(proced.nrProcedimentoConsolidado5),0), " +
					"coalesce(sum(proced.nrProcedimentoConsolidado6),0), " +
					"coalesce(sum(proced.nrProcedimentoConsolidado7),0) " +
					"from " +
					"( " +
					"   select " +
					"   tb_fat_procedimento.co_seq_fat_procedimento as coSeqFatProcedimento, " +
					"   tb_fat_procedimento.co_dim_cbo as coDimCbo, " +
					"   tb_fat_procedimento.co_dim_equipe as coDimEquipe, " +
					"   tb_fat_procedimento.co_dim_municipio as coDimMunicipio, " +
					"   tb_fat_procedimento.co_dim_profissional as coDimProfissional, " +
					"   tb_fat_procedimento.co_dim_tempo as coDimTempo, " +
					"   tb_fat_procedimento.co_dim_tipo_ficha as coDimTipoFicha, " +
					"   tb_fat_procedimento.co_dim_unidade_saude as coDimUnidadeSaude, " +
					"   tb_fat_procedimento.nr_procedimento_consolidado_1 as nrProcedimentoConsolidado1, " +
					"   tb_fat_procedimento.nr_procedimento_consolidado_2 as nrProcedimentoConsolidado2, " +
					"   tb_fat_procedimento.nr_procedimento_consolidado_3 as nrProcedimentoConsolidado3, " +
					"   tb_fat_procedimento.nr_procedimento_consolidado_4 as nrProcedimentoConsolidado4, " +
					"   tb_fat_procedimento.nr_procedimento_consolidado_5 as nrProcedimentoConsolidado5, " +
					"   tb_fat_procedimento.nr_procedimento_consolidado_6 as nrProcedimentoConsolidado6, " +
					"   tb_fat_procedimento.nr_procedimento_consolidado_7 as nrProcedimentoConsolidado7, " +
					"   tb_fat_procedimento.nu_uuid_ficha as nuUuidFicha " +
					"   from tb_fat_procedimento tb_fat_procedimento " +
					"   where tb_fat_procedimento.co_dim_municipio = ? " +
					"   and tb_fat_procedimento.co_dim_tempo between ? " +
					"   and ? " +
					") " +
					" proced ");

	

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
		
		return null;
	}

}
