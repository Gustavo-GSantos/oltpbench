package com.oltpbenchmark.benchmarks.esusBench.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.SQLStmt;

public class Producao extends Procedure {

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
	public SQLStmt quadroConfig01 = new SQLStmt(
			"select "
					+ "coalesce(sum(case when (fact.nuUuidFicha = fact.nuUuidFichaOrigem and fact.stRecusaCadastro = 0) then 1 else 0 end),0), "
					+ "coalesce(sum(case when (fact.nuUuidFicha != fact.nuUuidFichaOrigem and fact.stRecusaCadastro = 1) then 1 else 0 end),0), "
					+ "coalesce(sum(fact.stRecusaCadastro),0) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro "
					+ "   from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "   left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "   where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "   and tb_fat_cad_domiciliar.co_dim_tempo between ? "
					+ "   and ? " + ") " + " fact  ");

	public SQLStmt quadroConfig02 = new SQLStmt(
			"select "
					+ "coalesce(sum(case when (fact.stRecusaCadastro = ? and fact.nuUuidFicha = fact.nuUuidFichaOrigem) then 1 else 0 end),0), "
					+ "coalesce(sum(case when (fact.nuCns != ? and (fact.stRecusaCadastro = ? and fact.nuUuidFicha = fact.nuUuidFichaOrigem)) then 1 else 0 end),0), "
					+ "coalesce(sum(case when (fact.nuCns = ? and (fact.stRecusaCadastro = ? and fact.nuUuidFicha = fact.nuUuidFichaOrigem)) then 1 else 0 end),0), "
					+ "coalesce(sum(case when (fact.stRecusaCadastro = ? and fact.nuUuidFicha != fact.nuUuidFichaOrigem) then 1 else 0 end),0), "
					+ "coalesce(sum(case when (fact.nuCns != ? and (fact.stRecusaCadastro = ? and fact.nuUuidFicha != fact.nuUuidFichaOrigem)) then 1 else 0 end),0), "
					+ "coalesce(sum(case when (fact.nuCns = ? and (fact.stRecusaCadastro = ? and fact.nuUuidFicha != fact.nuUuidFichaOrigem)) then 1 else 0 end),0), "
					+ "coalesce(sum(fact.stRecusaCadastro),0) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_individual.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_individual.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_individual.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_individual.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_individual.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_individual.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_individual.co_seq_fat_cad_individual as coSeqFatCadIndividual, "
					+ "   tb_fat_cad_individual.nu_cns as nuCns, "
					+ "   tb_fat_cad_individual.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_cad_individual.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_individual.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_individual.st_recusa_cadastro as stRecusaCadastro "
					+ "   from tb_fat_cad_individual tb_fat_cad_individual "
					+ "   where tb_fat_cad_individual.co_dim_municipio = ? "
					+ "   and tb_fat_cad_individual.co_dim_tempo between ? "
					+ "   and ? " + ") " + " fact  ");

	public SQLStmt quadroConfig03 = new SQLStmt(
			"select "
					+ "coalesce(sum(case when fato.nuCns is not null then 1 else 0 end),0), "
					+ "coalesce(sum(case when fato.nuCns is null then 1 else 0 end),0) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   distinct fact.coDimCbo1 as coDimCbo1, "
					+ "   fact.coDimCbo2 as coDimCbo2, "
					+ "   fact.coDimEquipe1 as coDimEquipe1, "
					+ "   fact.coDimEquipe2 as coDimEquipe2, "
					+ "   fact.coDimLocalAtendimento as coDimLocalAtendimento, "
					+ "   fact.coDimModalidadeAd as coDimModalidadeAd, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimProfissional1 as coDimProfissional1, "
					+ "   fact.coDimProfissional2 as coDimProfissional2, "
					+ "   fact.coDimSexo as coDimSexo, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoAtendimento as coDimTipoAtendimento, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnidadeSaude1 as coDimUnidadeSaude1, "
					+ "   fact.coDimUnidadeSaude2 as coDimUnidadeSaude2, "
					+ "   fact.coDimFaixaEtaria as coDimFaixaEtaria, "
					+ "   fact.dtNascimento as dtNascimento, "
					+ "   fact.nuCns as nuCns, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   fact.nuAtendimento as nuAtendimento, "
					+ "   fact.stCondicaoAvaliada1 as stCondicaoAvaliada1, "
					+ "   fact.stCondicaoAvaliada10 as stCondicaoAvaliada10, "
					+ "   fact.stCondicaoAvaliada11 as stCondicaoAvaliada11, "
					+ "   fact.stCondicaoAvaliada12 as stCondicaoAvaliada12, "
					+ "   fact.stCondicaoAvaliada13 as stCondicaoAvaliada13, "
					+ "   fact.stCondicaoAvaliada14 as stCondicaoAvaliada14, "
					+ "   fact.stCondicaoAvaliada15 as stCondicaoAvaliada15, "
					+ "   fact.stCondicaoAvaliada16 as stCondicaoAvaliada16, "
					+ "   fact.stCondicaoAvaliada17 as stCondicaoAvaliada17, "
					+ "   fact.stCondicaoAvaliada18 as stCondicaoAvaliada18, "
					+ "   fact.stCondicaoAvaliada19 as stCondicaoAvaliada19, "
					+ "   fact.stCondicaoAvaliada2 as stCondicaoAvaliada2, "
					+ "   fact.stCondicaoAvaliada20 as stCondicaoAvaliada20, "
					+ "   fact.stCondicaoAvaliada21 as stCondicaoAvaliada21, "
					+ "   fact.stCondicaoAvaliada22 as stCondicaoAvaliada22, "
					+ "   fact.stCondicaoAvaliada23 as stCondicaoAvaliada23, "
					+ "   fact.stCondicaoAvaliada24 as stCondicaoAvaliada24, "
					+ "   fact.stCondicaoAvaliada3 as stCondicaoAvaliada3, "
					+ "   fact.stCondicaoAvaliada4 as stCondicaoAvaliada4, "
					+ "   fact.stCondicaoAvaliada5 as stCondicaoAvaliada5, "
					+ "   fact.stCondicaoAvaliada6 as stCondicaoAvaliada6, "
					+ "   fact.stCondicaoAvaliada7 as stCondicaoAvaliada7, "
					+ "   fact.stCondicaoAvaliada8 as stCondicaoAvaliada8, "
					+ "   fact.stCondicaoAvaliada9 as stCondicaoAvaliada9, "
					+ "   fact.stCondutaMtvSaida1 as stCondutaMtvSaida1, "
					+ "   fact.stCondutaMtvSaida2 as stCondutaMtvSaida2, "
					+ "   fact.stCondutaMtvSaida3 as stCondutaMtvSaida3, "
					+ "   fact.stCondutaMtvSaida4 as stCondutaMtvSaida4, "
					+ "   fact.stCondutaMtvSaida5 as stCondutaMtvSaida5, "
					+ "   fact.stCondutaMtvSaida6 as stCondutaMtvSaida6, "
					+ "   fact.stCondutaMtvSaida7 as stCondutaMtvSaida7 "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_atendimento_domiciliar.co_seq_fat_atend_domiciliar as coSeqFatAtendDomiciliar, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_cbo_1 as coDimCbo1, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_cbo_2 as coDimCbo2, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_equipe_1 as coDimEquipe1, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_equipe_2 as coDimEquipe2, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_modalidade_ad as coDimModalidadeAd, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_profissional_1 as coDimProfissional1, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_profissional_2 as coDimProfissional2, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_sexo as coDimSexo, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_tipo_atendimento as coDimTipoAtendimento, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_turno as coDimTurno, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_unidade_saude_1 as coDimUnidadeSaude1, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_unidade_saude_2 as coDimUnidadeSaude2, "
					+ "      tb_fat_atendimento_domiciliar.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "      tb_fat_atendimento_domiciliar.dt_nascimento as dtNascimento, "
					+ "      tb_fat_atendimento_domiciliar.nu_cns as nuCns, "
					+ "      tb_fat_atendimento_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "      tb_fat_atendimento_domiciliar.nu_atendimento as nuAtendimento, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_1 as stCondicaoAvaliada1, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_10 as stCondicaoAvaliada10, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_11 as stCondicaoAvaliada11, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_12 as stCondicaoAvaliada12, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_13 as stCondicaoAvaliada13, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_14 as stCondicaoAvaliada14, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_15 as stCondicaoAvaliada15, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_16 as stCondicaoAvaliada16, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_17 as stCondicaoAvaliada17, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_18 as stCondicaoAvaliada18, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_19 as stCondicaoAvaliada19, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_2 as stCondicaoAvaliada2, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_20 as stCondicaoAvaliada20, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_21 as stCondicaoAvaliada21, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_22 as stCondicaoAvaliada22, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_23 as stCondicaoAvaliada23, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_24 as stCondicaoAvaliada24, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_3 as stCondicaoAvaliada3, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_4 as stCondicaoAvaliada4, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_5 as stCondicaoAvaliada5, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_6 as stCondicaoAvaliada6, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_7 as stCondicaoAvaliada7, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_8 as stCondicaoAvaliada8, "
					+ "      tb_fat_atendimento_domiciliar.st_condicao_avaliada_9 as stCondicaoAvaliada9, "
					+ "      tb_fat_atendimento_domiciliar.st_conduta_mtv_saida_1 as stCondutaMtvSaida1, "
					+ "      tb_fat_atendimento_domiciliar.st_conduta_mtv_saida_2 as stCondutaMtvSaida2, "
					+ "      tb_fat_atendimento_domiciliar.st_conduta_mtv_saida_3 as stCondutaMtvSaida3, "
					+ "      tb_fat_atendimento_domiciliar.st_conduta_mtv_saida_4 as stCondutaMtvSaida4, "
					+ "      tb_fat_atendimento_domiciliar.st_conduta_mtv_saida_5 as stCondutaMtvSaida5, "
					+ "      tb_fat_atendimento_domiciliar.st_conduta_mtv_saida_6 as stCondutaMtvSaida6, "
					+ "      tb_fat_atendimento_domiciliar.st_conduta_mtv_saida_7 as stCondutaMtvSaida7 "
					+ "      from tb_fat_atendimento_domiciliar tb_fat_atendimento_domiciliar "
					+ "      where tb_fat_atendimento_domiciliar.co_dim_municipio = ? "
					+ "      and tb_fat_atendimento_domiciliar.co_dim_tempo between ? "
					+ "      and ? " + "   ) " + "    fact " + ") " + " fato  ");

	public SQLStmt quadroConfig04 = new SQLStmt(
			"select "
					+ "coalesce(sum(case when fato.nuCns is not null then 1 else 0 end),0), "
					+ "coalesce(sum(case when fato.nuCns is null then 1 else 0 end),0) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   distinct fact.coDimAleitamento as coDimAleitamento, "
					+ "   fact.coDimCbo1 as coDimCbo1, "
					+ "   fact.coDimCbo2 as coDimCbo2, "
					+ "   fact.coDimEquipe1 as coDimEquipe1, "
					+ "   fact.coDimEquipe2 as coDimEquipe2, "
					+ "   fact.coDimFaixaEtaria as coDimFaixaEtaria, "
					+ "   fact.coDimLocalAtendimento as coDimLocalAtendimento, "
					+ "   fact.coDimModalidadeAd as coDimModalidadeAd, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimProfissional1 as coDimProfissional1, "
					+ "   fact.coDimProfissional2 as coDimProfissional2, "
					+ "   fact.coDimRacionalidadeSaude as coDimRacionalidadeSaude, "
					+ "   fact.coDimSexo as coDimSexo, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTempoDum as coDimTempoDum, "
					+ "   fact.coDimTipoAtendimento as coDimTipoAtendimento, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnidadeSaude1 as coDimUnidadeSaude1, "
					+ "   fact.coDimUnidadeSaude2 as coDimUnidadeSaude2, "
					+ "   fact.dtNascimento as dtNascimento, "
					+ "   fact.nuAltura as nuAltura, "
					+ "   fact.nuCns as nuCns, "
					+ "   fact.nuGestasPrevias as nuGestasPrevias, "
					+ "   fact.nuIdadeGestacionalSemanas as nuIdadeGestacionalSemanas, "
					+ "   fact.nuPartos as nuPartos, "
					+ "   fact.nuPerimetroCefalico as nuPerimetroCefalico, "
					+ "   fact.nuPeso as nuPeso, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   fact.nuAtendimento as nuAtendimento, "
					+ "   fact.stConduta1 as stConduta1, "
					+ "   fact.stConduta2 as stConduta2, "
					+ "   fact.stConduta3 as stConduta3, "
					+ "   fact.stConduta4 as stConduta4, "
					+ "   fact.stConduta5 as stConduta5, "
					+ "   fact.stCondutaEncaminhamento1 as stCondutaEncaminhamento1, "
					+ "   fact.stCondutaEncaminhamento2 as stCondutaEncaminhamento2, "
					+ "   fact.stCondutaEncaminhamento3 as stCondutaEncaminhamento3, "
					+ "   fact.stCondutaEncaminhamento4 as stCondutaEncaminhamento4, "
					+ "   fact.stCondutaEncaminhamento5 as stCondutaEncaminhamento5, "
					+ "   fact.stCondutaEncaminhamento6 as stCondutaEncaminhamento6, "
					+ "   fact.stCondutaEncaminhamento7 as stCondutaEncaminhamento7, "
					+ "   fact.stFicouEmObservacao as stFicouEmObservacao, "
					+ "   fact.stGravidezPlanejada as stGravidezPlanejada, "
					+ "   fact.stNasf1 as stNasf1, "
					+ "   fact.stNasf2 as stNasf2, "
					+ "   fact.stNasf3 as stNasf3, "
					+ "   fact.stVacinacaoEmDia as stVacinacaoEmDia "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_atendimento_individual.co_seq_fat_atd_ind as coSeqFatAtdInd, "
					+ "      tb_fat_atendimento_individual.co_dim_aleitamento as coDimAleitamento, "
					+ "      tb_fat_atendimento_individual.co_dim_cbo_1 as coDimCbo1, "
					+ "      tb_fat_atendimento_individual.co_dim_cbo_2 as coDimCbo2, "
					+ "      tb_fat_atendimento_individual.co_dim_equipe_1 as coDimEquipe1, "
					+ "      tb_fat_atendimento_individual.co_dim_equipe_2 as coDimEquipe2, "
					+ "      tb_fat_atendimento_individual.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "      tb_fat_atendimento_individual.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "      tb_fat_atendimento_individual.co_dim_modalidade_ad as coDimModalidadeAd, "
					+ "      tb_fat_atendimento_individual.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_atendimento_individual.co_dim_profissional_1 as coDimProfissional1, "
					+ "      tb_fat_atendimento_individual.co_dim_profissional_2 as coDimProfissional2, "
					+ "      tb_fat_atendimento_individual.co_dim_racionalidade_saude as coDimRacionalidadeSaude, "
					+ "      tb_fat_atendimento_individual.co_dim_sexo as coDimSexo, "
					+ "      tb_fat_atendimento_individual.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_atendimento_individual.co_dim_tempo_dum as coDimTempoDum, "
					+ "      tb_fat_atendimento_individual.co_dim_tipo_atendimento as coDimTipoAtendimento, "
					+ "      tb_fat_atendimento_individual.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_atendimento_individual.co_dim_turno as coDimTurno, "
					+ "      tb_fat_atendimento_individual.co_dim_unidade_saude_1 as coDimUnidadeSaude1, "
					+ "      tb_fat_atendimento_individual.co_dim_unidade_saude_2 as coDimUnidadeSaude2, "
					+ "      tb_fat_atendimento_individual.dt_nascimento as dtNascimento, "
					+ "      tb_fat_atendimento_individual.nu_altura as nuAltura, "
					+ "      tb_fat_atendimento_individual.nu_cns as nuCns, "
					+ "      tb_fat_atendimento_individual.nu_gestas_previas as nuGestasPrevias, "
					+ "      tb_fat_atendimento_individual.nu_idade_gestacional_semanas as nuIdadeGestacionalSemanas, "
					+ "      tb_fat_atendimento_individual.nu_partos as nuPartos, "
					+ "      tb_fat_atendimento_individual.nu_perimetro_cefalico as nuPerimetroCefalico, "
					+ "      tb_fat_atendimento_individual.nu_peso as nuPeso, "
					+ "      tb_fat_atendimento_individual.nu_uuid_ficha as nuUuidFicha, "
					+ "      tb_fat_atendimento_individual.nu_atendimento as nuAtendimento, "
					+ "      tb_fat_atendimento_individual.st_conduta_1 as stConduta1, "
					+ "      tb_fat_atendimento_individual.st_conduta_2 as stConduta2, "
					+ "      tb_fat_atendimento_individual.st_conduta_3 as stConduta3, "
					+ "      tb_fat_atendimento_individual.st_conduta_4 as stConduta4, "
					+ "      tb_fat_atendimento_individual.st_conduta_5 as stConduta5, "
					+ "      tb_fat_atendimento_individual.st_conduta_encaminhamento_1 as stCondutaEncaminhamento1, "
					+ "      tb_fat_atendimento_individual.st_conduta_encaminhamento_2 as stCondutaEncaminhamento2, "
					+ "      tb_fat_atendimento_individual.st_conduta_encaminhamento_3 as stCondutaEncaminhamento3, "
					+ "      tb_fat_atendimento_individual.st_conduta_encaminhamento_4 as stCondutaEncaminhamento4, "
					+ "      tb_fat_atendimento_individual.st_conduta_encaminhamento_5 as stCondutaEncaminhamento5, "
					+ "      tb_fat_atendimento_individual.st_conduta_encaminhamento_6 as stCondutaEncaminhamento6, "
					+ "      tb_fat_atendimento_individual.st_conduta_encaminhamento_7 as stCondutaEncaminhamento7, "
					+ "      tb_fat_atendimento_individual.st_ficou_em_observacao as stFicouEmObservacao, "
					+ "      tb_fat_atendimento_individual.st_gravidez_planejada as stGravidezPlanejada, "
					+ "      tb_fat_atendimento_individual.st_nasf_1 as stNasf1, "
					+ "      tb_fat_atendimento_individual.st_nasf_2 as stNasf2, "
					+ "      tb_fat_atendimento_individual.st_nasf_3 as stNasf3, "
					+ "      tb_fat_atendimento_individual.st_vacinacao_em_dia as stVacinacaoEmDia "
					+ "      from tb_fat_atendimento_individual tb_fat_atendimento_individual "
					+ "      where tb_fat_atendimento_individual.co_dim_municipio = ? "
					+ "      and tb_fat_atendimento_individual.co_dim_tempo between ? "
					+ "      and ? " + "   ) " + "    fact " + ") " + " fato  ");

	public SQLStmt quadroConfig05 = new SQLStmt(
			"select "
					+ "coalesce(sum(case when fato.nuCns is not null then 1 else 0 end),0), "
					+ "coalesce(sum(case when fato.nuCns is null then 1 else 0 end),0) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   distinct fact.coDimCbo1 as coDimCbo1, "
					+ "   fact.coDimCbo2 as coDimCbo2, "
					+ "   fact.coDimEquipe1 as coDimEquipe1, "
					+ "   fact.coDimEquipe2 as coDimEquipe2, "
					+ "   fact.coDimLocalAtendimento as coDimLocalAtendimento, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimProfissional1 as coDimProfissional1, "
					+ "   fact.coDimProfissional2 as coDimProfissional2, "
					+ "   fact.coDimSexo as coDimSexo, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTipoAtendimento as coDimTipoAtendimento, "
					+ "   fact.coDimTipoConsulta as coDimTipoConsulta, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTurno as coDimTurno, "
					+ "   fact.coDimUnidadeSaude1 as coDimUnidadeSaude1, "
					+ "   fact.coDimUnidadeSaude2 as coDimUnidadeSaude2, "
					+ "   fact.dtNascimento as dtNascimento, "
					+ "   fact.coDimFaixaEtaria as coDimFaixaEtaria, "
					+ "   fact.nuCns as nuCns, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   fact.nuAtendimento as nuAtendimento, "
					+ "   fact.stConduta1 as stConduta1, "
					+ "   fact.stConduta2 as stConduta2, "
					+ "   fact.stConduta3 as stConduta3, "
					+ "   fact.stConduta4 as stConduta4, "
					+ "   fact.stConduta5 as stConduta5, "
					+ "   fact.stConduta6 as stConduta6, "
					+ "   fact.stCondutaAtendimento1 as stCondutaAtendimento1, "
					+ "   fact.stCondutaAtendimento10 as stCondutaAtendimento10, "
					+ "   fact.stCondutaAtendimento11 as stCondutaAtendimento11, "
					+ "   fact.stCondutaAtendimento2 as stCondutaAtendimento2, "
					+ "   fact.stCondutaAtendimento3 as stCondutaAtendimento3, "
					+ "   fact.stCondutaAtendimento4 as stCondutaAtendimento4, "
					+ "   fact.stCondutaAtendimento5 as stCondutaAtendimento5, "
					+ "   fact.stCondutaAtendimento6 as stCondutaAtendimento6, "
					+ "   fact.stCondutaAtendimento7 as stCondutaAtendimento7, "
					+ "   fact.stCondutaAtendimento8 as stCondutaAtendimento8, "
					+ "   fact.stCondutaAtendimento9 as stCondutaAtendimento9, "
					+ "   fact.stCondutaAtendimento99 as stCondutaAtendimento99, "
					+ "   fact.stFornecimento1 as stFornecimento1, "
					+ "   fact.stFornecimento2 as stFornecimento2, "
					+ "   fact.stFornecimento3 as stFornecimento3, "
					+ "   fact.stGestante as stGestante, "
					+ "   fact.stPacienteNecessidadesEspec as stPacienteNecessidadesEspec, "
					+ "   fact.stVigilanciaSaudeBucal1 as stVigilanciaSaudeBucal1, "
					+ "   fact.stVigilanciaSaudeBucal2 as stVigilanciaSaudeBucal2, "
					+ "   fact.stVigilanciaSaudeBucal3 as stVigilanciaSaudeBucal3, "
					+ "   fact.stVigilanciaSaudeBucal4 as stVigilanciaSaudeBucal4, "
					+ "   fact.stVigilanciaSaudeBucal5 as stVigilanciaSaudeBucal5, "
					+ "   fact.stVigilanciaSaudeBucal6 as stVigilanciaSaudeBucal6, "
					+ "   fact.stVigilanciaSaudeBucal7 as stVigilanciaSaudeBucal7 "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_atendimento_odonto.co_seq_fat_atd_odnt as coSeqFatAtdOdnt, "
					+ "      tb_fat_atendimento_odonto.co_dim_cbo_1 as coDimCbo1, "
					+ "      tb_fat_atendimento_odonto.co_dim_cbo_2 as coDimCbo2, "
					+ "      tb_fat_atendimento_odonto.co_dim_equipe_1 as coDimEquipe1, "
					+ "      tb_fat_atendimento_odonto.co_dim_equipe_2 as coDimEquipe2, "
					+ "      tb_fat_atendimento_odonto.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "      tb_fat_atendimento_odonto.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_atendimento_odonto.co_dim_profissional_1 as coDimProfissional1, "
					+ "      tb_fat_atendimento_odonto.co_dim_profissional_2 as coDimProfissional2, "
					+ "      tb_fat_atendimento_odonto.co_dim_sexo as coDimSexo, "
					+ "      tb_fat_atendimento_odonto.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_atendimento_odonto.co_dim_tipo_atendimento as coDimTipoAtendimento, "
					+ "      tb_fat_atendimento_odonto.co_dim_tipo_consulta as coDimTipoConsulta, "
					+ "      tb_fat_atendimento_odonto.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_atendimento_odonto.co_dim_turno as coDimTurno, "
					+ "      tb_fat_atendimento_odonto.co_dim_unidade_saude_1 as coDimUnidadeSaude1, "
					+ "      tb_fat_atendimento_odonto.co_dim_unidade_saude_2 as coDimUnidadeSaude2, "
					+ "      tb_fat_atendimento_odonto.dt_nascimento as dtNascimento, "
					+ "      tb_fat_atendimento_odonto.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "      tb_fat_atendimento_odonto.nu_cns as nuCns, "
					+ "      tb_fat_atendimento_odonto.nu_uuid_ficha as nuUuidFicha, "
					+ "      tb_fat_atendimento_odonto.nu_atendimento as nuAtendimento, "
					+ "      tb_fat_atendimento_odonto.st_conduta_1 as stConduta1, "
					+ "      tb_fat_atendimento_odonto.st_conduta_2 as stConduta2, "
					+ "      tb_fat_atendimento_odonto.st_conduta_3 as stConduta3, "
					+ "      tb_fat_atendimento_odonto.st_conduta_4 as stConduta4, "
					+ "      tb_fat_atendimento_odonto.st_conduta_5 as stConduta5, "
					+ "      tb_fat_atendimento_odonto.st_conduta_6 as stConduta6, "
					+ "      tb_fat_atendimento_odonto.st_conduta_atendimento_1 as stCondutaAtendimento1, "
					+ "      tb_fat_atendimento_odonto.st_conduta_atendimento_10 as stCondutaAtendimento10, "
					+ "      tb_fat_atendimento_odonto.st_conduta_atendimento_11 as stCondutaAtendimento11, "
					+ "      tb_fat_atendimento_odonto.st_conduta_atendimento_2 as stCondutaAtendimento2, "
					+ "      tb_fat_atendimento_odonto.st_conduta_atendimento_3 as stCondutaAtendimento3, "
					+ "      tb_fat_atendimento_odonto.st_conduta_atendimento_4 as stCondutaAtendimento4, "
					+ "      tb_fat_atendimento_odonto.st_conduta_atendimento_5 as stCondutaAtendimento5, "
					+ "      tb_fat_atendimento_odonto.st_conduta_atendimento_6 as stCondutaAtendimento6, "
					+ "      tb_fat_atendimento_odonto.st_conduta_atendimento_7 as stCondutaAtendimento7, "
					+ "      tb_fat_atendimento_odonto.st_conduta_atendimento_8 as stCondutaAtendimento8, "
					+ "      tb_fat_atendimento_odonto.st_conduta_atendimento_9 as stCondutaAtendimento9, "
					+ "      tb_fat_atendimento_odonto.st_conduta_atendimento_99 as stCondutaAtendimento99, "
					+ "      tb_fat_atendimento_odonto.st_fornecimento_1 as stFornecimento1, "
					+ "      tb_fat_atendimento_odonto.st_fornecimento_2 as stFornecimento2, "
					+ "      tb_fat_atendimento_odonto.st_fornecimento_3 as stFornecimento3, "
					+ "      tb_fat_atendimento_odonto.st_gestante as stGestante, "
					+ "      tb_fat_atendimento_odonto.st_paciente_necessidades_espec as stPacienteNecessidadesEspec, "
					+ "      tb_fat_atendimento_odonto.st_vigilancia_saude_bucal_1 as stVigilanciaSaudeBucal1, "
					+ "      tb_fat_atendimento_odonto.st_vigilancia_saude_bucal_2 as stVigilanciaSaudeBucal2, "
					+ "      tb_fat_atendimento_odonto.st_vigilancia_saude_bucal_3 as stVigilanciaSaudeBucal3, "
					+ "      tb_fat_atendimento_odonto.st_vigilancia_saude_bucal_4 as stVigilanciaSaudeBucal4, "
					+ "      tb_fat_atendimento_odonto.st_vigilancia_saude_bucal_5 as stVigilanciaSaudeBucal5, "
					+ "      tb_fat_atendimento_odonto.st_vigilancia_saude_bucal_6 as stVigilanciaSaudeBucal6, "
					+ "      tb_fat_atendimento_odonto.st_vigilancia_saude_bucal_7 as stVigilanciaSaudeBucal7 "
					+ "      from tb_fat_atendimento_odonto tb_fat_atendimento_odonto "
					+ "      where tb_fat_atendimento_odonto.co_dim_municipio = ? "
					+ "      and tb_fat_atendimento_odonto.co_dim_tempo between ? "
					+ "      and ? " + "   ) " + "    fact " + ") " + " fato  ");

	public SQLStmt quadroConfig06 = new SQLStmt(
			"select "
					+ "coalesce(sum(case when fato.nuCns is not null then 1 else 0 end),0), "
					+ "coalesce(sum(case when fato.nuCns is null then 1 else 0 end),0) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   tb_fat_marca_consumo_alimnt.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_marca_consumo_alimnt.co_dim_local_atendimento as coDimLocalAtendimento, "
					+ "   tb_fat_marca_consumo_alimnt.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_marca_consumo_alimnt.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_marca_consumo_alimnt.co_dim_sexo as coDimSexo, "
					+ "   tb_fat_marca_consumo_alimnt.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_marca_consumo_alimnt.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_marca_consumo_alimnt.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_marca_consumo_alimnt.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "   tb_fat_marca_consumo_alimnt.dt_nascimento as dtNascimento, "
					+ "   tb_fat_marca_consumo_alimnt.nu_cns as nuCns, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_2_anos_ou_mais_1 as nuResp2AnosOuMais1, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_2_anos_ou_mais_3 as nuResp2AnosOuMais3, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_2_anos_ou_mais_4 as nuResp2AnosOuMais4, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_2_anos_ou_mais_5 as nuResp2AnosOuMais5, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_2_anos_ou_mais_6 as nuResp2AnosOuMais6, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_2_anos_ou_mais_7 as nuResp2AnosOuMais7, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_2_anos_ou_mais_8 as nuResp2AnosOuMais8, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_2_anos_ou_mais_9 as nuResp2AnosOuMais9, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_1 as nuRespDe6A23Meses1, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_10 as nuRespDe6A23Meses10, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_11 as nuRespDe6A23Meses11, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_12 as nuRespDe6A23Meses12, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_13 as nuRespDe6A23Meses13, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_14 as nuRespDe6A23Meses14, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_15 as nuRespDe6A23Meses15, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_16 as nuRespDe6A23Meses16, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_17 as nuRespDe6A23Meses17, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_18 as nuRespDe6A23Meses18, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_19 as nuRespDe6A23Meses19, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_2 as nuRespDe6A23Meses2, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_20 as nuRespDe6A23Meses20, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_3 as nuRespDe6A23Meses3, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_4 as nuRespDe6A23Meses4, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_5 as nuRespDe6A23Meses5, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_6 as nuRespDe6A23Meses6, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_7 as nuRespDe6A23Meses7, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_8 as nuRespDe6A23Meses8, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_de_6_a_23_meses_9 as nuRespDe6A23Meses9, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_menor_6_meses_1 as nuRespMenor6Meses1, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_menor_6_meses_2 as nuRespMenor6Meses2, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_menor_6_meses_3 as nuRespMenor6Meses3, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_menor_6_meses_4 as nuRespMenor6Meses4, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_menor_6_meses_5 as nuRespMenor6Meses5, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_menor_6_meses_6 as nuRespMenor6Meses6, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_menor_6_meses_7 as nuRespMenor6Meses7, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_menor_6_meses_8 as nuRespMenor6Meses8, "
					+ "   tb_fat_marca_consumo_alimnt.nu_resp_menor_6_meses_9 as nuRespMenor6Meses9, "
					+ "   tb_fat_marca_consumo_alimnt.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_marca_consumo_alimnt.st_resp_2_anos_ou_mais_2_1 as stResp2AnosOuMais21, "
					+ "   tb_fat_marca_consumo_alimnt.st_resp_2_anos_ou_mais_2_2 as stResp2AnosOuMais22, "
					+ "   tb_fat_marca_consumo_alimnt.st_resp_2_anos_ou_mais_2_3 as stResp2AnosOuMais23, "
					+ "   tb_fat_marca_consumo_alimnt.st_resp_2_anos_ou_mais_2_4 as stResp2AnosOuMais24, "
					+ "   tb_fat_marca_consumo_alimnt.st_resp_2_anos_ou_mais_2_5 as stResp2AnosOuMais25, "
					+ "   tb_fat_marca_consumo_alimnt.st_resp_2_anos_ou_mais_2_6 as stResp2AnosOuMais26 "
					+ "   from tb_fat_marca_consumo_alimnt tb_fat_marca_consumo_alimnt "
					+ "   where tb_fat_marca_consumo_alimnt.co_dim_municipio = ? "
					+ "   and tb_fat_marca_consumo_alimnt.co_dim_tempo between ? "
					+ "   and ? " + ") " + " fato  ");

	public SQLStmt quadroConfig07 = new SQLStmt(
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
					+ "   and ? " + ") " + " fato  ");

	public SQLStmt quadroConfig08 = new SQLStmt(
			"select "
					+ "coalesce(sum(case when fato.nuCns is not null then 1 else 0 end),0), "
					+ "coalesce(sum(case when fato.nuCns is null then 1 else 0 end),0) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   tb_fat_visita_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_visita_domiciliar.co_dim_desfecho_visita as coDimDesfechoVisita, "
					+ "   tb_fat_visita_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_visita_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_visita_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_visita_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_visita_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_visita_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "   tb_fat_visita_domiciliar.co_dim_sexo as coDimSexo, "
					+ "   tb_fat_visita_domiciliar.co_dim_turno as coDimTurno, "
					+ "   tb_fat_visita_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_visita_domiciliar.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "   tb_fat_visita_domiciliar.dt_nascimento as dtNascimento, "
					+ "   tb_fat_visita_domiciliar.nu_altura as nuAltura, "
					+ "   tb_fat_visita_domiciliar.nu_cns as nuCns, "
					+ "   tb_fat_visita_domiciliar.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_visita_domiciliar.nu_peso as nuPeso, "
					+ "   tb_fat_visita_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento as stMotVisAcompanhamento, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_1 as stMotVisAcompanhamento1, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_10 as stMotVisAcompanhamento10, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_11 as stMotVisAcompanhamento11, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_12 as stMotVisAcompanhamento12, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_13 as stMotVisAcompanhamento13, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_14 as stMotVisAcompanhamento14, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_15 as stMotVisAcompanhamento15, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_16 as stMotVisAcompanhamento16, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_17 as stMotVisAcompanhamento17, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_18 as stMotVisAcompanhamento18, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_19 as stMotVisAcompanhamento19, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_2 as stMotVisAcompanhamento2, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_20 as stMotVisAcompanhamento20, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_21 as stMotVisAcompanhamento21, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_22 as stMotVisAcompanhamento22, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_3 as stMotVisAcompanhamento3, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_4 as stMotVisAcompanhamento4, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_5 as stMotVisAcompanhamento5, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_6 as stMotVisAcompanhamento6, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_7 as stMotVisAcompanhamento7, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_8 as stMotVisAcompanhamento8, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_acompanhamento_9 as stMotVisAcompanhamento9, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_busca_ativa as stMotVisBuscaAtiva, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_busca_ativa_1 as stMotVisBuscaAtiva1, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_busca_ativa_2 as stMotVisBuscaAtiva2, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_busca_ativa_3 as stMotVisBuscaAtiva3, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_busca_ativa_4 as stMotVisBuscaAtiva4, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_cad_att as stMotVisCadAtt, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_convte_atvidd_cltva as stMotVisConvteAtviddCltva, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_ctrl_ambnte_vetor as stMotVisCtrlAmbnteVetor, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_engresso_internacao as stMotVisEngressoInternacao, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_orintacao_prevncao as stMotVisOrintacaoPrevncao, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_outros as stMotVisOutros, "
					+ "   tb_fat_visita_domiciliar.st_mot_vis_visita_periodica as stMotVisVisitaPeriodica, "
					+ "   tb_fat_visita_domiciliar.st_mt_vs_ctrl_ambnte_vetor_1 as stMtVsCtrlAmbnteVetor1, "
					+ "   tb_fat_visita_domiciliar.st_mt_vs_ctrl_ambnte_vetor_2 as stMtVsCtrlAmbnteVetor2, "
					+ "   tb_fat_visita_domiciliar.st_mt_vs_ctrl_ambnte_vetor_3 as stMtVsCtrlAmbnteVetor3, "
					+ "   tb_fat_visita_domiciliar.st_mt_vs_ctrl_ambnte_vetor_4 as stMtVsCtrlAmbnteVetor4, "
					+ "   tb_fat_visita_domiciliar.st_visita_compartilhada as stVisitaCompartilhada "
					+ "   from tb_fat_visita_domiciliar tb_fat_visita_domiciliar "
					+ "   where tb_fat_visita_domiciliar.co_dim_municipio = ? "
					+ "   and tb_fat_visita_domiciliar.co_dim_tempo between ? "
					+ "   and ? " + ") " + " fato  ");

	public SQLStmt quadroConfig09 = new SQLStmt(
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
					+ "   and ? " + ") " + " fato ");

	public SQLStmt quadroConfig10 = new SQLStmt(
			"select "
					+ "count(fato.nuUuidFicha) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_cbo_1 as coDimCbo1, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_cbo_2 as coDimCbo2, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_cid_principal as coDimCidPrincipal, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_cid_sec_1 as coDimCidSec1, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_cid_sec_2 as coDimCidSec2, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_conclusao_modalidade_ad as coDimConclusaoModalidadeAd, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_cuidador as coDimCuidador, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_equipe_1 as coDimEquipe1, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_equipe_2 as coDimEquipe2, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_etnia as coDimEtnia, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_municipio_residencia as coDimMunicipioResidencia, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_nacionalidade as coDimNacionalidade, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_pais_nascimento as coDimPaisNascimento, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_procedencia_origem as coDimProcedenciaOrigem, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_profissional_1 as coDimProfissional1, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_profissional_2 as coDimProfissional2, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_raca_cor as coDimRacaCor, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_sexo as coDimSexo, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_tipo_elegibilidade as coDimTipoElegibilidade, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_turno as coDimTurno, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_unidade_saude_1 as coDimUnidadeSaude1, "
					+ "   tb_fat_avaliacao_elegibilidade.co_dim_unidade_saude_2 as coDimUnidadeSaude2, "
					+ "   tb_fat_avaliacao_elegibilidade.co_seq_fat_avaliacao_elegibldd as coSeqFatAvaliacaoElegibldd, "
					+ "   tb_fat_avaliacao_elegibilidade.dt_entrada_brasil as dtEntradaBrasil, "
					+ "   tb_fat_avaliacao_elegibilidade.dt_nascimento as dtNascimento, "
					+ "   tb_fat_avaliacao_elegibilidade.dt_naturalizacao as dtNaturalizacao, "
					+ "   tb_fat_avaliacao_elegibilidade.nu_cns as nuCns, "
					+ "   tb_fat_avaliacao_elegibilidade.nu_cns_cuidador as nuCnsCuidador, "
					+ "   tb_fat_avaliacao_elegibilidade.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_1 as stCondAvaliada1, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_10 as stCondAvaliada10, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_11 as stCondAvaliada11, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_12 as stCondAvaliada12, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_13 as stCondAvaliada13, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_14 as stCondAvaliada14, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_15 as stCondAvaliada15, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_16 as stCondAvaliada16, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_17 as stCondAvaliada17, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_18 as stCondAvaliada18, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_19 as stCondAvaliada19, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_2 as stCondAvaliada2, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_20 as stCondAvaliada20, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_21 as stCondAvaliada21, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_22 as stCondAvaliada22, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_23 as stCondAvaliada23, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_24 as stCondAvaliada24, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_3 as stCondAvaliada3, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_4 as stCondAvaliada4, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_5 as stCondAvaliada5, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_6 as stCondAvaliada6, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_7 as stCondAvaliada7, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_8 as stCondAvaliada8, "
					+ "   tb_fat_avaliacao_elegibilidade.st_cond_avaliada_9 as stCondAvaliada9, "
					+ "   tb_fat_avaliacao_elegibilidade.st_desconhece_nome_mae as stDesconheceNomeMae, "
					+ "   tb_fat_avaliacao_elegibilidade.st_desconhece_nome_pai as stDesconheceNomePai, "
					+ "   tb_fat_avaliacao_elegibilidade.st_inelegibilidade_1 as stInelegibilidade1, "
					+ "   tb_fat_avaliacao_elegibilidade.st_inelegibilidade_2 as stInelegibilidade2, "
					+ "   tb_fat_avaliacao_elegibilidade.st_inelegibilidade_3 as stInelegibilidade3, "
					+ "   tb_fat_avaliacao_elegibilidade.st_inelegibilidade_4 as stInelegibilidade4, "
					+ "   tb_fat_avaliacao_elegibilidade.st_inelegibilidade_5 as stInelegibilidade5 "
					+ "   from tb_fat_avaliacao_elegibilidade tb_fat_avaliacao_elegibilidade "
					+ "   where tb_fat_avaliacao_elegibilidade.co_dim_municipio = ? "
					+ "   and tb_fat_avaliacao_elegibilidade.co_dim_tempo between ? "
					+ "   and ? " + ") " + " fato ");

	public SQLStmt quadroConfig11 = new SQLStmt(
			"select "
					+ "count(fato.nuUuidFicha) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   tb_fat_complementar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_complementar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_complementar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_complementar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_complementar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_complementar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_complementar.co_dim_turno as coDimTurno, "
					+ "   tb_fat_complementar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_complementar.co_seq_fat_complementar as coSeqFatComplementar, "
					+ "   tb_fat_complementar.dt_exame_fundo_olho as dtExameFundoOlho, "
					+ "   tb_fat_complementar.dt_ressonancia as dtRessonancia, "
					+ "   tb_fat_complementar.dt_teste_olhinho as dtTesteOlhinho, "
					+ "   tb_fat_complementar.dt_teste_orelhinha as dtTesteOrelhinha, "
					+ "   tb_fat_complementar.dt_tomografia as dtTomografia, "
					+ "   tb_fat_complementar.dt_transfontanela as dtTransfontanela, "
					+ "   tb_fat_complementar.nu_cns as nuCns, "
					+ "   tb_fat_complementar.nu_cns_responsavel as nuCnsResponsavel, "
					+ "   tb_fat_complementar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_complementar.st_exame_fundo_olho as stExameFundoOlho, "
					+ "   tb_fat_complementar.st_ressonancia as stRessonancia, "
					+ "   tb_fat_complementar.st_teste_olhinho as stTesteOlhinho, "
					+ "   tb_fat_complementar.st_teste_orelhinha as stTesteOrelhinha, "
					+ "   tb_fat_complementar.st_tomografia as stTomografia, "
					+ "   tb_fat_complementar.st_transfontanela as stTransfontanela "
					+ "   from tb_fat_complementar tb_fat_complementar "
					+ "   where tb_fat_complementar.co_dim_municipio = ? "
					+ "   and tb_fat_complementar.co_dim_tempo between ? "
					+ "   and ? " + ") " + " fato ");

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

		if (numQuadros >= 01) {
			st = this.getPreparedStatement(conn, this.quadroConfig01);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}

		if (numQuadros >= 02) {
			st = this.getPreparedStatement(conn, this.quadroConfig02);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}

		if (numQuadros >= 03) {
			st = this.getPreparedStatement(conn, this.quadroConfig03);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}

		if (numQuadros >= 04) {
			st = this.getPreparedStatement(conn, this.quadroConfig04);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}

		if (numQuadros >= 05) {
			st = this.getPreparedStatement(conn, this.quadroConfig05);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}

		if (numQuadros >= 06) {
			st = this.getPreparedStatement(conn, this.quadroConfig06);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}

		if (numQuadros >= 07) {
			st = this.getPreparedStatement(conn, this.quadroConfig07);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}

		if (numQuadros >=  8) {
			st = this.getPreparedStatement(conn, this.quadroConfig08);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}

		if (numQuadros >= 9) {
			st = this.getPreparedStatement(conn, this.quadroConfig09);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}

		if (numQuadros >= 10) {
			st = this.getPreparedStatement(conn, this.quadroConfig10);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, startDate);
			st.setInt(3, finalDate);
			rs = st.executeQuery();
			while (rs.next()) {

			} // WHILE
			rs.close();
		}
		
		if (numQuadros >= 11) {
			st = this.getPreparedStatement(conn, this.quadroConfig11);
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
