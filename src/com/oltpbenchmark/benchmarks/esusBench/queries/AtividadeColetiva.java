package com.oltpbenchmark.benchmarks.esusBench.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.SQLStmt;

public class AtividadeColetiva extends Procedure {

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
	public SQLStmt quadroConfigRelatorioAtividadeColetivaTotalizador = new SQLStmt(
			"select "
					+ "count(fato.nuUuidFicha) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   tb_fat_atividade_coletiva.co_seq_fat_atividade_coletiva as coFatAtividadeColetiva, "
					+ "   tb_fat_atividade_coletiva.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_atividade_coletiva.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_atividade_coletiva.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_atividade_coletiva.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_atividade_coletiva.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_atividade_coletiva.co_dim_tipo_atividade as coDimTipoAtividade, "
					+ "   tb_fat_atividade_coletiva.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_atividade_coletiva.co_dim_turno as coDimTurno, "
					+ "   tb_fat_atividade_coletiva.co_dim_undd_sade_acdm_sade as coDimUnddSadeAcdmSade, "
					+ "   tb_fat_atividade_coletiva.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_atividade_coletiva.nu_avaliacoes_alteradas as nuAvaliacoesAlteradas, "
					+ "   tb_fat_atividade_coletiva.nu_participantes_registrados as nuParticipantesRegistrados, "
					+ "   tb_fat_atividade_coletiva.nu_inep as nuInep, "
					+ "   tb_fat_atividade_coletiva.nu_participantes as nuParticipantes, "
					+ "   tb_fat_atividade_coletiva.nu_uuid_ficha as nuUuidFicha "
					+ "   from tb_fat_atividade_coletiva tb_fat_atividade_coletiva "
					+ "   where tb_fat_atividade_coletiva.co_dim_municipio = ? "
					+ "   and tb_fat_atividade_coletiva.co_dim_tempo between ? "
					+ "   and ? " + ") " + "fato ");

	public SQLStmt quadroConfigRelatorioAtividadeColetivaTurno = new SQLStmt(
			"select "
					+ "tb_dim_turno.co_ordem,tb_dim_turno.ds_turno,coalesce(count(fato.nuUuidFicha),0) "
					+ "from tb_dim_turno tb_dim_turno "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_atividade_coletiva.co_seq_fat_atividade_coletiva as coFatAtividadeColetiva, "
					+ "   tb_fat_atividade_coletiva.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_atividade_coletiva.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_atividade_coletiva.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_atividade_coletiva.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_atividade_coletiva.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_atividade_coletiva.co_dim_tipo_atividade as coDimTipoAtividade, "
					+ "   tb_fat_atividade_coletiva.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_atividade_coletiva.co_dim_turno as coDimTurno, "
					+ "   tb_fat_atividade_coletiva.co_dim_undd_sade_acdm_sade as coDimUnddSadeAcdmSade, "
					+ "   tb_fat_atividade_coletiva.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_atividade_coletiva.nu_avaliacoes_alteradas as nuAvaliacoesAlteradas, "
					+ "   tb_fat_atividade_coletiva.nu_participantes_registrados as nuParticipantesRegistrados, "
					+ "   tb_fat_atividade_coletiva.nu_inep as nuInep, "
					+ "   tb_fat_atividade_coletiva.nu_participantes as nuParticipantes, "
					+ "   tb_fat_atividade_coletiva.nu_uuid_ficha as nuUuidFicha "
					+ "   from tb_fat_atividade_coletiva tb_fat_atividade_coletiva "
					+ "   where tb_fat_atividade_coletiva.co_dim_municipio = ? "
					+ "   and tb_fat_atividade_coletiva.co_dim_tempo between ? "
					+ "   and ? "
					+ ") "
					+ "fato on tb_dim_turno.co_seq_dim_turno = fato.coDimTurno "
					+ "group by tb_dim_turno.co_ordem,tb_dim_turno.ds_turno "
					+ "order by tb_dim_turno.co_ordem asc "

	);

	public SQLStmt quadroConfigRelatorioAtividadeColetivaAtividade = new SQLStmt(
			"select "
					+ "tb_dim_tipo_atividade.co_ordem, "
					+ "tb_dim_tipo_atividade.ds_tipo_atividade, "
					+ "count(distinct fato.nuUuidFicha) "
					+ "from tb_dim_tipo_atividade tb_dim_tipo_atividade "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_atividade_coletiva.co_seq_fat_atividade_coletiva as coFatAtividadeColetiva, "
					+ "   tb_fat_atividade_coletiva.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_atividade_coletiva.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_atividade_coletiva.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_atividade_coletiva.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_atividade_coletiva.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_atividade_coletiva.co_dim_tipo_atividade as coDimTipoAtividade, "
					+ "   tb_fat_atividade_coletiva.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_atividade_coletiva.co_dim_turno as coDimTurno, "
					+ "   tb_fat_atividade_coletiva.co_dim_undd_sade_acdm_sade as coDimUnddSadeAcdmSade, "
					+ "   tb_fat_atividade_coletiva.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_atividade_coletiva.nu_avaliacoes_alteradas as nuAvaliacoesAlteradas, "
					+ "   tb_fat_atividade_coletiva.nu_participantes_registrados as nuParticipantesRegistrados, "
					+ "   tb_fat_atividade_coletiva.nu_inep as nuInep, "
					+ "   tb_fat_atividade_coletiva.nu_participantes as nuParticipantes, "
					+ "   tb_fat_atividade_coletiva.nu_uuid_ficha as nuUuidFicha "
					+ "   from tb_fat_atividade_coletiva tb_fat_atividade_coletiva "
					+ "   where tb_fat_atividade_coletiva.co_dim_municipio = ? "
					+ "   and tb_fat_atividade_coletiva.co_dim_tempo between ? "
					+ "   and ? "
					+ ") "
					+ "fato on tb_dim_tipo_atividade.co_seq_dim_tipo_atividade = fato.coDimTipoAtividade "
					+ "group by tb_dim_tipo_atividade.co_ordem,tb_dim_tipo_atividade.ds_tipo_atividade "
					+ "order by tb_dim_tipo_atividade.co_ordem asc ");

	public SQLStmt quadroConfigRelatorioAtividadeColetivaPublicoAlvo = new SQLStmt(
			"select "
					+ "coalesce(sum(fato.stPublicoAlvo1),0), "
					+ "coalesce(sum(fato.stPublicoAlvo2),0), "
					+ "coalesce(sum(fato.stPublicoAlvo3),0), "
					+ "coalesce(sum(fato.stPublicoAlvo4),0), "
					+ "coalesce(sum(fato.stPublicoAlvo5),0), "
					+ "coalesce(sum(fato.stPublicoAlvo6),0), "
					+ "coalesce(sum(fato.stPublicoAlvo7),0), "
					+ "coalesce(sum(fato.stPublicoAlvo8),0), "
					+ "coalesce(sum(fato.stPublicoAlvo9),0), "
					+ "coalesce(sum(fato.stPublicoAlvo10),0), "
					+ "coalesce(sum(fato.stPublicoAlvo12),0), "
					+ "coalesce(sum(fato.stPublicoAlvo13),0), "
					+ "coalesce(sum(fato.stPublicoAlvo14),0), "
					+ "coalesce(sum(fato.stPublicoAlvo15),0), "
					+ "coalesce(sum(fato.stPublicoAlvo16),0), "
					+ "coalesce(sum(fato.stPublicoAlvo17),0), "
					+ "coalesce(sum(fato.stPublicoAlvo18),0), "
					+ "coalesce "
					+ "( "
					+ "   sum(case when fato.stPublicoAlvo1 + fato.stPublicoAlvo2 + fato.stPublicoAlvo3 + fato.stPublicoAlvo4 + fato.stPublicoAlvo5 + fato.stPublicoAlvo6 + fato.stPublicoAlvo7 + fato.stPublicoAlvo8 + fato.stPublicoAlvo9 + fato.stPublicoAlvo10 + fato.stPublicoAlvo12 + fato.stPublicoAlvo13 + fato.stPublicoAlvo14 + fato.stPublicoAlvo15 + fato.stPublicoAlvo16 + fato.stPublicoAlvo17 + fato.stPublicoAlvo18 = 0 then 1 else 0 end), "
					+ "   0 "
					+ ") "
					+ "from "
					+ "( "
					+ "   select "
					+ "   fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoAtividade as coDimTipoAtividade, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnddSadeAcdmSade as coDimUnddSadeAcdmSade, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.nuAvaliacoesAlteradas as nuAvaliacoesAlteradas, "
					+ "   fact.nuInep as nuInep, "
					+ "   fact.nuParticipantes as nuParticipantes, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   tb_fat_atvdd_coletiva_ext.co_dim_procedimento as coDimProcedimento, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_1 as stPraticasEmSaude1, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_10 as stPraticasEmSaude10, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_11 as stPraticasEmSaude11, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_12 as stPraticasEmSaude12, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_13 as stPraticasEmSaude13, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_14 as stPraticasEmSaude14, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_2 as stPraticasEmSaude2, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_3 as stPraticasEmSaude3, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_4 as stPraticasEmSaude4, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_5 as stPraticasEmSaude5, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_6 as stPraticasEmSaude6, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_7 as stPraticasEmSaude7, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_8 as stPraticasEmSaude8, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_9 as stPraticasEmSaude9, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_1 as stPublicoAlvo1, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_10 as stPublicoAlvo10, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_12 as stPublicoAlvo12, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_13 as stPublicoAlvo13, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_14 as stPublicoAlvo14, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_15 as stPublicoAlvo15, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_16 as stPublicoAlvo16, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_17 as stPublicoAlvo17, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_18 as stPublicoAlvo18, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_2 as stPublicoAlvo2, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_3 as stPublicoAlvo3, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_4 as stPublicoAlvo4, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_5 as stPublicoAlvo5, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_6 as stPublicoAlvo6, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_7 as stPublicoAlvo7, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_8 as stPublicoAlvo8, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_9 as stPublicoAlvo9, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_1 as stTemasParaSaude1, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_10 as stTemasParaSaude10, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_11 as stTemasParaSaude11, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_12 as stTemasParaSaude12, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_13 as stTemasParaSaude13, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_14 as stTemasParaSaude14, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_15 as stTemasParaSaude15, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_16 as stTemasParaSaude16, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_2 as stTemasParaSaude2, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_3 as stTemasParaSaude3, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_4 as stTemasParaSaude4, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_5 as stTemasParaSaude5, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_6 as stTemasParaSaude6, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_7 as stTemasParaSaude7, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_8 as stTemasParaSaude8, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_9 as stTemasParaSaude9 "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_atividade_coletiva.co_seq_fat_atividade_coletiva as coFatAtividadeColetiva, "
					+ "      tb_fat_atividade_coletiva.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_atividade_coletiva.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_atividade_coletiva.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_atividade_coletiva.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_atividade_coletiva.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_atividade_coletiva.co_dim_tipo_atividade as coDimTipoAtividade, "
					+ "      tb_fat_atividade_coletiva.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_atividade_coletiva.co_dim_turno as coDimTurno, "
					+ "      tb_fat_atividade_coletiva.co_dim_undd_sade_acdm_sade as coDimUnddSadeAcdmSade, "
					+ "      tb_fat_atividade_coletiva.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_atividade_coletiva.nu_avaliacoes_alteradas as nuAvaliacoesAlteradas, "
					+ "      tb_fat_atividade_coletiva.nu_participantes_registrados as nuParticipantesRegistrados, "
					+ "      tb_fat_atividade_coletiva.nu_inep as nuInep, "
					+ "      tb_fat_atividade_coletiva.nu_participantes as nuParticipantes, "
					+ "      tb_fat_atividade_coletiva.nu_uuid_ficha as nuUuidFicha "
					+ "      from tb_fat_atividade_coletiva tb_fat_atividade_coletiva "
					+ "      where tb_fat_atividade_coletiva.co_dim_municipio = ? "
					+ "      and tb_fat_atividade_coletiva.co_dim_tempo between ? "
					+ "      and ? "
					+ "   ) "
					+ "   fact "
					+ "   join tb_fat_atvdd_coletiva_ext tb_fat_atvdd_coletiva_ext on fact.coFatAtividadeColetiva = tb_fat_atvdd_coletiva_ext.co_fat_atividade_coletiva "
					+ ") " + "fato");

	public SQLStmt quadroConfigRelatorioAtividadeColetivaTemasParaSaude = new SQLStmt(
			"select "
					+ "coalesce(sum(fato.stTemasParaSaude1),0), "
					+ "coalesce(sum(fato.stTemasParaSaude2),0), "
					+ "coalesce(sum(fato.stTemasParaSaude3),0), "
					+ "coalesce(sum(fato.stTemasParaSaude4),0), "
					+ "coalesce(sum(fato.stTemasParaSaude5),0), "
					+ "coalesce(sum(fato.stTemasParaSaude6),0), "
					+ "coalesce(sum(fato.stTemasParaSaude7),0), "
					+ "coalesce(sum(fato.stTemasParaSaude8),0), "
					+ "coalesce(sum(fato.stTemasParaSaude9),0), "
					+ "coalesce(sum(fato.stTemasParaSaude10),0), "
					+ "coalesce(sum(fato.stTemasParaSaude11),0), "
					+ "coalesce(sum(fato.stTemasParaSaude12),0), "
					+ "coalesce(sum(fato.stTemasParaSaude13),0), "
					+ "coalesce(sum(fato.stTemasParaSaude14),0), "
					+ "coalesce(sum(fato.stTemasParaSaude15),0), "
					+ "coalesce(sum(fato.stTemasParaSaude16),0), "
					+ "coalesce "
					+ "( "
					+ "   sum(case when fato.stTemasParaSaude1 + fato.stTemasParaSaude2 + fato.stTemasParaSaude3 + fato.stTemasParaSaude4 + fato.stTemasParaSaude5 + fato.stTemasParaSaude6 + fato.stTemasParaSaude7 + fato.stTemasParaSaude8 + fato.stTemasParaSaude9 + fato.stTemasParaSaude10 + fato.stTemasParaSaude11 + fato.stTemasParaSaude12 + fato.stTemasParaSaude13 + fato.stTemasParaSaude14 + fato.stTemasParaSaude15 + fato.stTemasParaSaude16 = 0 then 1 else 0 end), "
					+ "   0 "
					+ ") "
					+ "from "
					+ "( "
					+ "   select "
					+ "   fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoAtividade as coDimTipoAtividade, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnddSadeAcdmSade as coDimUnddSadeAcdmSade, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.nuAvaliacoesAlteradas as nuAvaliacoesAlteradas, "
					+ "   fact.nuInep as nuInep, "
					+ "   fact.nuParticipantes as nuParticipantes, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   tb_fat_atvdd_coletiva_ext.co_dim_procedimento as coDimProcedimento, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_1 as stPraticasEmSaude1, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_10 as stPraticasEmSaude10, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_11 as stPraticasEmSaude11, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_12 as stPraticasEmSaude12, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_13 as stPraticasEmSaude13, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_14 as stPraticasEmSaude14, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_2 as stPraticasEmSaude2, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_3 as stPraticasEmSaude3, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_4 as stPraticasEmSaude4, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_5 as stPraticasEmSaude5, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_6 as stPraticasEmSaude6, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_7 as stPraticasEmSaude7, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_8 as stPraticasEmSaude8, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_9 as stPraticasEmSaude9, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_1 as stPublicoAlvo1, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_10 as stPublicoAlvo10, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_12 as stPublicoAlvo12, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_13 as stPublicoAlvo13, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_14 as stPublicoAlvo14, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_15 as stPublicoAlvo15, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_16 as stPublicoAlvo16, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_17 as stPublicoAlvo17, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_18 as stPublicoAlvo18, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_2 as stPublicoAlvo2, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_3 as stPublicoAlvo3, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_4 as stPublicoAlvo4, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_5 as stPublicoAlvo5, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_6 as stPublicoAlvo6, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_7 as stPublicoAlvo7, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_8 as stPublicoAlvo8, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_9 as stPublicoAlvo9, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_1 as stTemasParaSaude1, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_10 as stTemasParaSaude10, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_11 as stTemasParaSaude11, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_12 as stTemasParaSaude12, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_13 as stTemasParaSaude13, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_14 as stTemasParaSaude14, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_15 as stTemasParaSaude15, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_16 as stTemasParaSaude16, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_2 as stTemasParaSaude2, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_3 as stTemasParaSaude3, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_4 as stTemasParaSaude4, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_5 as stTemasParaSaude5, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_6 as stTemasParaSaude6, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_7 as stTemasParaSaude7, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_8 as stTemasParaSaude8, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_9 as stTemasParaSaude9 "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_atividade_coletiva.co_seq_fat_atividade_coletiva as coFatAtividadeColetiva, "
					+ "      tb_fat_atividade_coletiva.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_atividade_coletiva.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_atividade_coletiva.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_atividade_coletiva.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_atividade_coletiva.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_atividade_coletiva.co_dim_tipo_atividade as coDimTipoAtividade, "
					+ "      tb_fat_atividade_coletiva.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_atividade_coletiva.co_dim_turno as coDimTurno, "
					+ "      tb_fat_atividade_coletiva.co_dim_undd_sade_acdm_sade as coDimUnddSadeAcdmSade, "
					+ "      tb_fat_atividade_coletiva.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_atividade_coletiva.nu_avaliacoes_alteradas as nuAvaliacoesAlteradas, "
					+ "      tb_fat_atividade_coletiva.nu_participantes_registrados as nuParticipantesRegistrados, "
					+ "      tb_fat_atividade_coletiva.nu_inep as nuInep, "
					+ "      tb_fat_atividade_coletiva.nu_participantes as nuParticipantes, "
					+ "      tb_fat_atividade_coletiva.nu_uuid_ficha as nuUuidFicha "
					+ "      from tb_fat_atividade_coletiva tb_fat_atividade_coletiva "
					+ "      where tb_fat_atividade_coletiva.co_dim_municipio = ? "
					+ "      and tb_fat_atividade_coletiva.co_dim_tempo between ? "
					+ "      and ? "
					+ "   ) "
					+ "   fact "
					+ "   join tb_fat_atvdd_coletiva_ext tb_fat_atvdd_coletiva_ext on fact.coFatAtividadeColetiva = tb_fat_atvdd_coletiva_ext.co_fat_atividade_coletiva "
					+ ") " + "fato");

	public SQLStmt quadroConfigRelatorioAtividadeColetivaPraticasEmSaude = new SQLStmt(
			"select "
					+ "coalesce(sum(fato.stPraticasEmSaude1),0), "
					+ "coalesce(sum(fato.stPraticasEmSaude2),0), "
					+ "coalesce(sum(fato.stPraticasEmSaude3),0), "
					+ "coalesce(sum(fato.stPraticasEmSaude4),0), "
					+ "coalesce(sum(fato.stPraticasEmSaude5),0), "
					+ "coalesce(sum(fato.stPraticasEmSaude6),0), "
					+ "coalesce(sum(fato.stPraticasEmSaude7),0), "
					+ "coalesce(sum(fato.stPraticasEmSaude8),0), "
					+ "coalesce(sum(fato.stPraticasEmSaude9),0), "
					+ "coalesce(sum(fato.stPraticasEmSaude10),0), "
					+ "coalesce(sum(fato.stPraticasEmSaude11),0), "
					+ "coalesce(sum(fato.stPraticasEmSaude12),0), "
					+ "coalesce(sum(fato.stPraticasEmSaude13),0), "
					+ "coalesce(sum(fato.stPraticasEmSaude14),0), "
					+ "coalesce "
					+ "( "
					+ "   sum(case when fato.stPraticasEmSaude1 + fato.stPraticasEmSaude2 + fato.stPraticasEmSaude3 + fato.stPraticasEmSaude4 + fato.stPraticasEmSaude5 + fato.stPraticasEmSaude6 + fato.stPraticasEmSaude7 + fato.stPraticasEmSaude8 + fato.stPraticasEmSaude9 + fato.stPraticasEmSaude10 + fato.stPraticasEmSaude11 + fato.stPraticasEmSaude12 + fato.stPraticasEmSaude13 + fato.stPraticasEmSaude14 = 0 then 1 else 0 end), "
					+ "   0 "
					+ ") "
					+ "from "
					+ "( "
					+ "   select "
					+ "   fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoAtividade as coDimTipoAtividade, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnddSadeAcdmSade as coDimUnddSadeAcdmSade, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.nuAvaliacoesAlteradas as nuAvaliacoesAlteradas, "
					+ "   fact.nuInep as nuInep, "
					+ "   fact.nuParticipantes as nuParticipantes, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   tb_fat_atvdd_coletiva_ext.co_dim_procedimento as coDimProcedimento, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_1 as stPraticasEmSaude1, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_10 as stPraticasEmSaude10, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_11 as stPraticasEmSaude11, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_12 as stPraticasEmSaude12, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_13 as stPraticasEmSaude13, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_14 as stPraticasEmSaude14, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_2 as stPraticasEmSaude2, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_3 as stPraticasEmSaude3, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_4 as stPraticasEmSaude4, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_5 as stPraticasEmSaude5, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_6 as stPraticasEmSaude6, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_7 as stPraticasEmSaude7, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_8 as stPraticasEmSaude8, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_9 as stPraticasEmSaude9, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_1 as stPublicoAlvo1, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_10 as stPublicoAlvo10, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_12 as stPublicoAlvo12, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_13 as stPublicoAlvo13, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_14 as stPublicoAlvo14, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_15 as stPublicoAlvo15, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_16 as stPublicoAlvo16, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_17 as stPublicoAlvo17, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_18 as stPublicoAlvo18, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_2 as stPublicoAlvo2, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_3 as stPublicoAlvo3, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_4 as stPublicoAlvo4, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_5 as stPublicoAlvo5, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_6 as stPublicoAlvo6, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_7 as stPublicoAlvo7, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_8 as stPublicoAlvo8, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_9 as stPublicoAlvo9, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_1 as stTemasParaSaude1, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_10 as stTemasParaSaude10, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_11 as stTemasParaSaude11, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_12 as stTemasParaSaude12, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_13 as stTemasParaSaude13, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_14 as stTemasParaSaude14, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_15 as stTemasParaSaude15, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_16 as stTemasParaSaude16, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_2 as stTemasParaSaude2, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_3 as stTemasParaSaude3, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_4 as stTemasParaSaude4, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_5 as stTemasParaSaude5, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_6 as stTemasParaSaude6, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_7 as stTemasParaSaude7, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_8 as stTemasParaSaude8, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_9 as stTemasParaSaude9 "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_atividade_coletiva.co_seq_fat_atividade_coletiva as coFatAtividadeColetiva, "
					+ "      tb_fat_atividade_coletiva.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_atividade_coletiva.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_atividade_coletiva.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_atividade_coletiva.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_atividade_coletiva.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_atividade_coletiva.co_dim_tipo_atividade as coDimTipoAtividade, "
					+ "      tb_fat_atividade_coletiva.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_atividade_coletiva.co_dim_turno as coDimTurno, "
					+ "      tb_fat_atividade_coletiva.co_dim_undd_sade_acdm_sade as coDimUnddSadeAcdmSade, "
					+ "      tb_fat_atividade_coletiva.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_atividade_coletiva.nu_avaliacoes_alteradas as nuAvaliacoesAlteradas, "
					+ "      tb_fat_atividade_coletiva.nu_participantes_registrados as nuParticipantesRegistrados, "
					+ "      tb_fat_atividade_coletiva.nu_inep as nuInep, "
					+ "      tb_fat_atividade_coletiva.nu_participantes as nuParticipantes, "
					+ "      tb_fat_atividade_coletiva.nu_uuid_ficha as nuUuidFicha "
					+ "      from tb_fat_atividade_coletiva tb_fat_atividade_coletiva "
					+ "      where tb_fat_atividade_coletiva.co_dim_municipio = ? "
					+ "      and tb_fat_atividade_coletiva.co_dim_tempo between ? "
					+ "      and ? "
					+ "   ) "
					+ "   fact "
					+ "   join tb_fat_atvdd_coletiva_ext tb_fat_atvdd_coletiva_ext on fact.coFatAtividadeColetiva = tb_fat_atvdd_coletiva_ext.co_fat_atividade_coletiva "
					+ ") " + "fato ");

	public SQLStmt quadroConfigRelatorioAtividadeColetivaPraticasEmSaudeOutroProcedimento = new SQLStmt(
			"select "
					+ "tb_dim_procedimento.co_proced, "
					+ "tb_dim_procedimento.ds_proced, "
					+ "count(proced.nuUuidFicha) as quantidade "
					+ "from tb_dim_procedimento tb_dim_procedimento "
					+ "join "
					+ "( "
					+ "   select "
					+ "   fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoAtividade as coDimTipoAtividade, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnddSadeAcdmSade as coDimUnddSadeAcdmSade, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.nuAvaliacoesAlteradas as nuAvaliacoesAlteradas, "
					+ "   fact.nuInep as nuInep, "
					+ "   fact.nuParticipantes as nuParticipantes, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   tb_fat_atvdd_coletiva_ext.co_dim_procedimento as coDimProcedimento, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_1 as stPraticasEmSaude1, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_10 as stPraticasEmSaude10, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_11 as stPraticasEmSaude11, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_12 as stPraticasEmSaude12, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_13 as stPraticasEmSaude13, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_14 as stPraticasEmSaude14, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_2 as stPraticasEmSaude2, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_3 as stPraticasEmSaude3, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_4 as stPraticasEmSaude4, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_5 as stPraticasEmSaude5, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_6 as stPraticasEmSaude6, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_7 as stPraticasEmSaude7, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_8 as stPraticasEmSaude8, "
					+ "   tb_fat_atvdd_coletiva_ext.st_praticas_em_saude_9 as stPraticasEmSaude9, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_1 as stPublicoAlvo1, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_10 as stPublicoAlvo10, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_12 as stPublicoAlvo12, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_13 as stPublicoAlvo13, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_14 as stPublicoAlvo14, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_15 as stPublicoAlvo15, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_16 as stPublicoAlvo16, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_17 as stPublicoAlvo17, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_18 as stPublicoAlvo18, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_2 as stPublicoAlvo2, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_3 as stPublicoAlvo3, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_4 as stPublicoAlvo4, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_5 as stPublicoAlvo5, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_6 as stPublicoAlvo6, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_7 as stPublicoAlvo7, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_8 as stPublicoAlvo8, "
					+ "   tb_fat_atvdd_coletiva_ext.st_publico_alvo_9 as stPublicoAlvo9, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_1 as stTemasParaSaude1, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_10 as stTemasParaSaude10, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_11 as stTemasParaSaude11, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_12 as stTemasParaSaude12, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_13 as stTemasParaSaude13, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_14 as stTemasParaSaude14, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_15 as stTemasParaSaude15, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_16 as stTemasParaSaude16, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_2 as stTemasParaSaude2, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_3 as stTemasParaSaude3, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_4 as stTemasParaSaude4, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_5 as stTemasParaSaude5, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_6 as stTemasParaSaude6, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_7 as stTemasParaSaude7, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_8 as stTemasParaSaude8, "
					+ "   tb_fat_atvdd_coletiva_ext.st_temas_para_saude_9 as stTemasParaSaude9 "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_atividade_coletiva.co_seq_fat_atividade_coletiva as coFatAtividadeColetiva, "
					+ "      tb_fat_atividade_coletiva.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_atividade_coletiva.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_atividade_coletiva.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_atividade_coletiva.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_atividade_coletiva.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_atividade_coletiva.co_dim_tipo_atividade as coDimTipoAtividade, "
					+ "      tb_fat_atividade_coletiva.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_atividade_coletiva.co_dim_turno as coDimTurno, "
					+ "      tb_fat_atividade_coletiva.co_dim_undd_sade_acdm_sade as coDimUnddSadeAcdmSade, "
					+ "      tb_fat_atividade_coletiva.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_atividade_coletiva.nu_avaliacoes_alteradas as nuAvaliacoesAlteradas, "
					+ "      tb_fat_atividade_coletiva.nu_participantes_registrados as nuParticipantesRegistrados, "
					+ "      tb_fat_atividade_coletiva.nu_inep as nuInep, "
					+ "      tb_fat_atividade_coletiva.nu_participantes as nuParticipantes, "
					+ "      tb_fat_atividade_coletiva.nu_uuid_ficha as nuUuidFicha "
					+ "      from tb_fat_atividade_coletiva tb_fat_atividade_coletiva "
					+ "      where tb_fat_atividade_coletiva.co_dim_municipio = ? "
					+ "      and tb_fat_atividade_coletiva.co_dim_tempo between ? "
					+ "      and ? "
					+ "   ) "
					+ "   fact "
					+ "   join tb_fat_atvdd_coletiva_ext tb_fat_atvdd_coletiva_ext on fact.coFatAtividadeColetiva = tb_fat_atvdd_coletiva_ext.co_fat_atividade_coletiva "
					+ ") "
					+ " proced on tb_dim_procedimento.co_seq_dim_procedimento = proced.coDimProcedimento "
					+ "where proced.stPraticasEmSaude14 = '1' "
					+ "group by tb_dim_procedimento.co_proced,tb_dim_procedimento.ds_proced "
					+ "order by tb_dim_procedimento.co_proced asc,tb_dim_procedimento.ds_proced asc ");

	public SQLStmt quadroConfigRelatorioAtividadeColetivaTemasReuniao = new SQLStmt(
			"select "
					+ "coalesce(sum(fato.stTemaReuniao1),0), "
					+ "coalesce(sum(fato.stTemaReuniao2),0), "
					+ "coalesce(sum(fato.stTemaReuniao3),0), "
					+ "coalesce(sum(fato.stTemaReuniao4),0), "
					+ "coalesce(sum(fato.stTemaReuniao5),0), "
					+ "coalesce(sum(fato.stTemaReuniao6),0), "
					+ "coalesce(sum(fato.stTemaReuniao7),0), "
					+ "coalesce "
					+ "( "
					+ "   sum(case when fato.stTemaReuniao1 + fato.stTemaReuniao2 + fato.stTemaReuniao3 + fato.stTemaReuniao4 + fato.stTemaReuniao5 + fato.stTemaReuniao6 + fato.stTemaReuniao7 = 0 then 1 else 0 end), "
					+ "   0 "
					+ ") "
					+ "from "
					+ "( "
					+ "   select "
					+ "   fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoAtividade as coDimTipoAtividade, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnddSadeAcdmSade as coDimUnddSadeAcdmSade, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.nuAvaliacoesAlteradas as nuAvaliacoesAlteradas, "
					+ "   fact.nuInep as nuInep, "
					+ "   fact.nuParticipantes as nuParticipantes, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   tb_fat_atvdd_coletiva_int.st_tema_reuniao_1 as stTemaReuniao1, "
					+ "   tb_fat_atvdd_coletiva_int.st_tema_reuniao_2 as stTemaReuniao2, "
					+ "   tb_fat_atvdd_coletiva_int.st_tema_reuniao_3 as stTemaReuniao3, "
					+ "   tb_fat_atvdd_coletiva_int.st_tema_reuniao_4 as stTemaReuniao4, "
					+ "   tb_fat_atvdd_coletiva_int.st_tema_reuniao_5 as stTemaReuniao5, "
					+ "   tb_fat_atvdd_coletiva_int.st_tema_reuniao_6 as stTemaReuniao6, "
					+ "   tb_fat_atvdd_coletiva_int.st_tema_reuniao_7 as stTemaReuniao7 "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_atividade_coletiva.co_seq_fat_atividade_coletiva as coFatAtividadeColetiva, "
					+ "      tb_fat_atividade_coletiva.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_atividade_coletiva.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_atividade_coletiva.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_atividade_coletiva.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_atividade_coletiva.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_atividade_coletiva.co_dim_tipo_atividade as coDimTipoAtividade, "
					+ "      tb_fat_atividade_coletiva.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_atividade_coletiva.co_dim_turno as coDimTurno, "
					+ "      tb_fat_atividade_coletiva.co_dim_undd_sade_acdm_sade as coDimUnddSadeAcdmSade, "
					+ "      tb_fat_atividade_coletiva.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_atividade_coletiva.nu_avaliacoes_alteradas as nuAvaliacoesAlteradas, "
					+ "      tb_fat_atividade_coletiva.nu_participantes_registrados as nuParticipantesRegistrados, "
					+ "      tb_fat_atividade_coletiva.nu_inep as nuInep, "
					+ "      tb_fat_atividade_coletiva.nu_participantes as nuParticipantes, "
					+ "      tb_fat_atividade_coletiva.nu_uuid_ficha as nuUuidFicha "
					+ "      from tb_fat_atividade_coletiva tb_fat_atividade_coletiva "
					+ "      where tb_fat_atividade_coletiva.co_dim_municipio = ? "
					+ "      and tb_fat_atividade_coletiva.co_dim_tempo between ? "
					+ "      and ? "
					+ "   ) "
					+ "   fact "
					+ "   join tb_fat_atvdd_coletiva_int tb_fat_atvdd_coletiva_int on fact.coFatAtividadeColetiva = tb_fat_atvdd_coletiva_int.co_fat_atividade_coletiva "
					+ ") " + "fato ");

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
					this.quadroConfigRelatorioAtividadeColetivaTotalizador);
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
					this.quadroConfigRelatorioAtividadeColetivaTurno);
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
					this.quadroConfigRelatorioAtividadeColetivaAtividade);
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
					this.quadroConfigRelatorioAtividadeColetivaPublicoAlvo);
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
					this.quadroConfigRelatorioAtividadeColetivaTemasParaSaude);
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
					this.quadroConfigRelatorioAtividadeColetivaPraticasEmSaude);
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
							this.quadroConfigRelatorioAtividadeColetivaPraticasEmSaudeOutroProcedimento);
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
					this.quadroConfigRelatorioAtividadeColetivaTemasReuniao);
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
