package com.oltpbenchmark.benchmarks.esusBench.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.SQLStmt;

public class CadastroIndividual extends Procedure {
	
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
				 + "	) "
    );
			
	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadrosDadosGerais = new SQLStmt(
			"select " +
					"count(fact.coSeqFatCadIndividual) " +
					"from " +
					"( " +
					"   select " +
					"   tb_fat_cad_individual.co_dim_cbo as coDimCbo, " +
					"   tb_fat_cad_individual.co_dim_cbo_cidadao as coDimCboCidadao, " +
					"   tb_fat_cad_individual.co_dim_equipe as coDimEquipe, " +
					"   tb_fat_cad_individual.co_dim_etnia as coDimEtnia, " +
					"   tb_fat_cad_individual.co_dim_frequencia_alimentacao as coDimFrequenciaAlimentacao, " +
					"   tb_fat_cad_individual.co_dim_identidade_genero as coDimIdentidadeGenero, " +
					"   tb_fat_cad_individual.co_dim_municipio as coDimMunicipio, " +
					"   tb_fat_cad_individual.co_dim_municipio_cidadao as coDimMunicipioCidadao, " +
					"   tb_fat_cad_individual.co_dim_nacionalidade as coDimNacionalidade, " +
					"   tb_fat_cad_individual.co_dim_pais_nascimento as coDimPaisNascimento, " +
					"   tb_fat_cad_individual.co_dim_profissional as coDimProfissional, " +
					"   tb_fat_cad_individual.co_dim_raca_cor as coDimRacaCor, " +
					"   tb_fat_cad_individual.co_dim_sexo as coDimSexo, " +
					"   tb_fat_cad_individual.co_dim_situacao_trabalho as coDimSituacaoTrabalho, " +
					"   tb_fat_cad_individual.co_dim_tempo as coDimTempo, " +
					"   tb_fat_cad_individual.co_dim_tempo_morador_rua as coDimTempoMoradorRua, " +
					"   tb_fat_cad_individual.co_dim_tempo_validade as coDimTempoValidade, " +
					"   tb_fat_cad_individual.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, " +
					"   tb_fat_cad_individual.co_dim_tipo_condicao_peso as coDimTipoCondicaoPeso, " +
					"   tb_fat_cad_individual.co_dim_tipo_escolaridade as coDimTipoEscolaridade, " +
					"   tb_fat_cad_individual.co_dim_tipo_ficha as coDimTipoFicha, " +
					"   tb_fat_cad_individual.co_dim_tipo_orientacao_sexual as coDimTipoOrientacaoSexual, " +
					"   tb_fat_cad_individual.co_dim_tipo_parentesco as coDimTipoParentesco, " +
					"   tb_fat_cad_individual.co_dim_tipo_saida_cadastro as coDimTipoSaidaCadastro, " +
					"   tb_fat_cad_individual.co_dim_unidade_saude as coDimUnidadeSaude, " +
					"   tb_fat_cad_individual.co_dim_faixa_etaria as coDimFaixaEtaria, " +
					"   tb_fat_cad_individual.co_seq_fat_cad_individual as coSeqFatCadIndividual, " +
					"   tb_fat_cad_individual.dt_entrada_brasil as dtEntradaBrasil, " +
					"   tb_fat_cad_individual.dt_nascimento as dtNascimento, " +
					"   tb_fat_cad_individual.dt_naturalizacao as dtNaturalizacao, " +
					"   tb_fat_cad_individual.dt_obito as dtObito, " +
					"   tb_fat_cad_individual.nu_cns as nuCns, " +
					"   tb_fat_cad_individual.nu_cns_responsavel as nuCnsResponsavel, " +
					"   tb_fat_cad_individual.nu_micro_area as nuMicroArea, " +
					"   tb_fat_cad_individual.nu_uuid_ficha as nuUuidFicha, " +
					"   tb_fat_cad_individual.nu_uuid_ficha_origem as nuUuidFichaOrigem, " +
					"   tb_fat_cad_individual.st_acamado as stAcamado, " +
					"   tb_fat_cad_individual.st_acompanhado_instituicao as stAcompanhadoInstituicao, " +
					"   tb_fat_cad_individual.st_alcool as stAlcool, " +
					"   tb_fat_cad_individual.st_avc as stAvc, " +
					"   tb_fat_cad_individual.st_cancer as stCancer, " +
					"   tb_fat_cad_individual.st_comunidade_tradicional as stComunidadeTradicional, " +
					"   tb_fat_cad_individual.st_defi_auditiva as stDefiAuditiva, " +
					"   tb_fat_cad_individual.st_deficiencia as stDeficiencia, " +
					"   tb_fat_cad_individual.st_defi_fisica as stDefiFisica, " +
					"   tb_fat_cad_individual.st_defi_intelectual_cognitiva as stDefiIntelectualCognitiva, " +
					"   tb_fat_cad_individual.st_defi_outra as stDefiOutra, " +
					"   tb_fat_cad_individual.st_defi_visual as stDefiVisual, " +
					"   tb_fat_cad_individual.st_desconhece_mae as stDesconheceMae, " +
					"   tb_fat_cad_individual.st_desconhece_pai as stDesconhecePai, " +
					"   tb_fat_cad_individual.st_diabete as stDiabete, " +
					"   tb_fat_cad_individual.st_doenca_cardiaca as stDoencaCardiaca, " +
					"   tb_fat_cad_individual.st_doenca_card_insuficiencia as stDoencaCardInsuficiencia, " +
					"   tb_fat_cad_individual.st_doenca_card_n_sabe as stDoencaCardNSabe, " +
					"   tb_fat_cad_individual.st_doenca_card_outro as stDoencaCardOutro, " +
					"   tb_fat_cad_individual.st_doenca_respira_asma as stDoencaRespiraAsma, " +
					"   tb_fat_cad_individual.st_doenca_respira_dpoc_enfisem as stDoencaRespiraDpocEnfisem, " +
					"   tb_fat_cad_individual.st_doenca_respira_n_sabe as stDoencaRespiraNSabe, " +
					"   tb_fat_cad_individual.st_doenca_respira_outra as stDoencaRespiraOutra, " +
					"   tb_fat_cad_individual.st_doenca_respiratoria as stDoencaRespiratoria, " +
					"   tb_fat_cad_individual.st_domiciliado as stDomiciliado, " +
					"   tb_fat_cad_individual.st_frequenta_creche as stFrequentaCreche, " +
					"   tb_fat_cad_individual.st_frequenta_cuidador as stFrequentaCuidador, " +
					"   tb_fat_cad_individual.st_fumante as stFumante, " +
					"   tb_fat_cad_individual.st_gestante as stGestante, " +
					"   tb_fat_cad_individual.st_hanseniase as stHanseniase, " +
					"   tb_fat_cad_individual.st_higiene_pessoal_acesso as stHigienePessoalAcesso, " +
					"   tb_fat_cad_individual.st_hig_pess_banho as stHigPessBanho, " +
					"   tb_fat_cad_individual.st_hig_pess_higiene_bucal as stHigPessHigieneBucal, " +
					"   tb_fat_cad_individual.st_hig_pess_outros as stHigPessOutros, " +
					"   tb_fat_cad_individual.st_hig_pess_sanitario as stHigPessSanitario, " +
					"   tb_fat_cad_individual.st_hipertensao_arterial as stHipertensaoArterial, " +
					"   tb_fat_cad_individual.st_infarto as stInfarto, " +
					"   tb_fat_cad_individual.st_informar_identidade_genero as stInformarIdentidadeGenero, " +
					"   tb_fat_cad_individual.st_informar_orientacao_sexual as stInformarOrientacaoSexual, " +
					"   tb_fat_cad_individual.st_internacao_12 as stInternacao12, " +
					"   tb_fat_cad_individual.st_morador_rua as stMoradorRua, " +
					"   tb_fat_cad_individual.st_orig_alimen_doacao_popular as stOrigAlimenDoacaoPopular, " +
					"   tb_fat_cad_individual.st_orig_alimen_doacao_reli as stOrigAlimenDoacaoReli, " +
					"   tb_fat_cad_individual.st_orig_alimen_doacao_rest as stOrigAlimenDoacaoRest, " +
					"   tb_fat_cad_individual.st_orig_alimen_outros as stOrigAlimenOutros, " +
					"   tb_fat_cad_individual.st_orig_alimen_restaurante_pop as stOrigAlimenRestaurantePop, " +
					"   tb_fat_cad_individual.st_outra_droga as stOutraDroga, " +
					"   tb_fat_cad_individual.st_participa_grupo_comunitario as stParticipaGrupoComunitario, " +
					"   tb_fat_cad_individual.st_pic as stPic, " +
					"   tb_fat_cad_individual.st_plano_saude_privado as stPlanoSaudePrivado, " +
					"   tb_fat_cad_individual.st_problema_rins as stProblemaRins, " +
					"   tb_fat_cad_individual.st_problema_rins_insuficiencia as stProblemaRinsInsuficiencia, " +
					"   tb_fat_cad_individual.st_problema_rins_nao_sabe as stProblemaRinsNaoSabe, " +
					"   tb_fat_cad_individual.st_problema_rins_outro as stProblemaRinsOutro, " +
					"   tb_fat_cad_individual.st_recebe_beneficio as stRecebeBeneficio, " +
					"   tb_fat_cad_individual.st_recusa_cadastro as stRecusaCadastro, " +
					"   tb_fat_cad_individual.st_referencia_familiar as stReferenciaFamiliar, " +
					"   tb_fat_cad_individual.st_responsavel_familiar as stResponsavelFamiliar, " +
					"   tb_fat_cad_individual.st_tipo_cuidado_crianca_1 as stTipoCuidadoCrianca1, " +
					"   tb_fat_cad_individual.st_tipo_cuidado_crianca_2 as stTipoCuidadoCrianca2, " +
					"   tb_fat_cad_individual.st_tipo_cuidado_crianca_3 as stTipoCuidadoCrianca3, " +
					"   tb_fat_cad_individual.st_tipo_cuidado_crianca_4 as stTipoCuidadoCrianca4, " +
					"   tb_fat_cad_individual.st_tipo_cuidado_crianca_5 as stTipoCuidadoCrianca5, " +
					"   tb_fat_cad_individual.st_tipo_cuidado_crianca_6 as stTipoCuidadoCrianca6, " +
					"   tb_fat_cad_individual.st_tratamento_psiquiatra as stTratamentoPsiquiatra, " +
					"   tb_fat_cad_individual.st_tuberculose as stTuberculose, " +
					"   tb_fat_cad_individual.st_usa_planta_medicinal as stUsaPlantaMedicinal, " +
					"   tb_fat_cad_individual.st_visita_familiar_frequente as stVisitaFamiliarFrequente " +
					"   from tb_fat_cad_individual tb_fat_cad_individual " +
					"   left join tb_fat_cidadao tb_fat_cidadao on tb_fat_cad_individual.co_seq_fat_cad_individual = tb_fat_cidadao.co_fat_cad_individual " +
//					"   where tb_fat_cad_individual.co_dim_municipio = 2 " +
//					"   and " +
//					"   ( " +
//					"      tb_fat_cidadao.co_dim_tempo_valdd_municipio >= 30001231 " +
//					"      and tb_fat_cidadao.co_dim_tempo <= 20170712 " +
"   where tb_fat_cad_individual.co_dim_municipio = ? " +
"   and " +
"   ( " +
"      tb_fat_cidadao.co_dim_tempo_valdd_municipio >= ? " +
"      and tb_fat_cidadao.co_dim_tempo <= ? " +
					"   ) " +
					") " +
					" fact "
    );

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	// 4 - dataCalculoIdade
	// 5 - dataCalculoIdade
	public SQLStmt quadroFaixaEtaria = new SQLStmt(
			"select "
					+ "	tb_dim_faixa_etaria.ds_faixa_etaria, "
					+ "	sum( case when fato.coDimSexo =1 then 1 else 0 end ), "
					+ "	sum( case when fato.coDimSexo =2 then 1 else 0 end ), "
					+ "	sum( case when( fato.coDimSexo !=1 and fato.coDimSexo !=2) then 1 else 0 end ) "
					+ "from "
					+ "	tb_dim_faixa_etaria tb_dim_faixa_etaria left join( "
					+ "		select "
					+ "			tb_fat_cad_individual.co_dim_cbo as coDimCbo, "
					+ "			tb_fat_cad_individual.co_dim_cbo_cidadao as coDimCboCidadao, "
					+ "			tb_fat_cad_individual.co_dim_equipe as coDimEquipe, "
					+ "			tb_fat_cad_individual.co_dim_etnia as coDimEtnia, "
					+ "			tb_fat_cad_individual.co_dim_frequencia_alimentacao as coDimFrequenciaAlimentacao, "
					+ "			tb_fat_cad_individual.co_dim_identidade_genero as coDimIdentidadeGenero, "
					+ "			tb_fat_cad_individual.co_dim_municipio as coDimMunicipio, "
					+ "			tb_fat_cad_individual.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "			tb_fat_cad_individual.co_dim_nacionalidade as coDimNacionalidade, "
					+ "			tb_fat_cad_individual.co_dim_pais_nascimento as coDimPaisNascimento, "
					+ "			tb_fat_cad_individual.co_dim_profissional as coDimProfissional, "
					+ "			tb_fat_cad_individual.co_dim_raca_cor as coDimRacaCor, "
					+ "			tb_fat_cad_individual.co_dim_sexo as coDimSexo, "
					+ "			tb_fat_cad_individual.co_dim_situacao_trabalho as coDimSituacaoTrabalho, "
					+ "			tb_fat_cad_individual.co_dim_tempo as coDimTempo, "
					+ "			tb_fat_cad_individual.co_dim_tempo_morador_rua as coDimTempoMoradorRua, "
					+ "			tb_fat_cad_individual.co_dim_tempo_validade as coDimTempoValidade, "
					+ "			tb_fat_cad_individual.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "			tb_fat_cad_individual.co_dim_tipo_condicao_peso as coDimTipoCondicaoPeso, "
					+ "			tb_fat_cad_individual.co_dim_tipo_escolaridade as coDimTipoEscolaridade, "
					+ "			tb_fat_cad_individual.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "			tb_fat_cad_individual.co_dim_tipo_orientacao_sexual as coDimTipoOrientacaoSexual, "
					+ "			tb_fat_cad_individual.co_dim_tipo_parentesco as coDimTipoParentesco, "
					+ "			tb_fat_cad_individual.co_dim_tipo_saida_cadastro as coDimTipoSaidaCadastro, "
					+ "			tb_fat_cad_individual.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "			tb_fat_cad_individual.co_dim_faixa_etaria as coDimFaixaEtaria, "
					+ "			tb_fat_cad_individual.co_seq_fat_cad_individual as coSeqFatCadIndividual, "
					+ "			tb_fat_cad_individual.dt_entrada_brasil as dtEntradaBrasil, "
					+ "			tb_fat_cad_individual.dt_nascimento as dtNascimento, "
					+ "			tb_fat_cad_individual.dt_naturalizacao as dtNaturalizacao, "
					+ "			tb_fat_cad_individual.dt_obito as dtObito, "
					+ "			tb_fat_cad_individual.nu_cns as nuCns, "
					+ "			tb_fat_cad_individual.nu_cns_responsavel as nuCnsResponsavel, "
					+ "			tb_fat_cad_individual.nu_micro_area as nuMicroArea, "
					+ "			tb_fat_cad_individual.nu_uuid_ficha as nuUuidFicha, "
					+ "			tb_fat_cad_individual.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "			tb_fat_cad_individual.st_acamado as stAcamado, "
					+ "			tb_fat_cad_individual.st_acompanhado_instituicao as stAcompanhadoInstituicao, "
					+ "			tb_fat_cad_individual.st_alcool as stAlcool, "
					+ "			tb_fat_cad_individual.st_avc as stAvc, "
					+ "			tb_fat_cad_individual.st_cancer as stCancer, "
					+ "			tb_fat_cad_individual.st_comunidade_tradicional as stComunidadeTradicional, "
					+ "			tb_fat_cad_individual.st_defi_auditiva as stDefiAuditiva, "
					+ "			tb_fat_cad_individual.st_deficiencia as stDeficiencia, "
					+ "			tb_fat_cad_individual.st_defi_fisica as stDefiFisica, "
					+ "			tb_fat_cad_individual.st_defi_intelectual_cognitiva as stDefiIntelectualCognitiva, "
					+ "			tb_fat_cad_individual.st_defi_outra as stDefiOutra, "
					+ "			tb_fat_cad_individual.st_defi_visual as stDefiVisual, "
					+ "			tb_fat_cad_individual.st_desconhece_mae as stDesconheceMae, "
					+ "			tb_fat_cad_individual.st_desconhece_pai as stDesconhecePai, "
					+ "			tb_fat_cad_individual.st_diabete as stDiabete, "
					+ "			tb_fat_cad_individual.st_doenca_cardiaca as stDoencaCardiaca, "
					+ "			tb_fat_cad_individual.st_doenca_card_insuficiencia as stDoencaCardInsuficiencia, "
					+ "			tb_fat_cad_individual.st_doenca_card_n_sabe as stDoencaCardNSabe, "
					+ "			tb_fat_cad_individual.st_doenca_card_outro as stDoencaCardOutro, "
					+ "			tb_fat_cad_individual.st_doenca_respira_asma as stDoencaRespiraAsma, "
					+ "			tb_fat_cad_individual.st_doenca_respira_dpoc_enfisem as stDoencaRespiraDpocEnfisem, "
					+ "			tb_fat_cad_individual.st_doenca_respira_n_sabe as stDoencaRespiraNSabe, "
					+ "			tb_fat_cad_individual.st_doenca_respira_outra as stDoencaRespiraOutra, "
					+ "			tb_fat_cad_individual.st_doenca_respiratoria as stDoencaRespiratoria, "
					+ "			tb_fat_cad_individual.st_domiciliado as stDomiciliado, "
					+ "			tb_fat_cad_individual.st_frequenta_creche as stFrequentaCreche, "
					+ "			tb_fat_cad_individual.st_frequenta_cuidador as stFrequentaCuidador, "
					+ "			tb_fat_cad_individual.st_fumante as stFumante, "
					+ "			tb_fat_cad_individual.st_gestante as stGestante, "
					+ "			tb_fat_cad_individual.st_hanseniase as stHanseniase, "
					+ "			tb_fat_cad_individual.st_higiene_pessoal_acesso as stHigienePessoalAcesso, "
					+ "			tb_fat_cad_individual.st_hig_pess_banho as stHigPessBanho, "
					+ "			tb_fat_cad_individual.st_hig_pess_higiene_bucal as stHigPessHigieneBucal, "
					+ "			tb_fat_cad_individual.st_hig_pess_outros as stHigPessOutros, "
					+ "			tb_fat_cad_individual.st_hig_pess_sanitario as stHigPessSanitario, "
					+ "			tb_fat_cad_individual.st_hipertensao_arterial as stHipertensaoArterial, "
					+ "			tb_fat_cad_individual.st_infarto as stInfarto, "
					+ "			tb_fat_cad_individual.st_informar_identidade_genero as stInformarIdentidadeGenero, "
					+ "			tb_fat_cad_individual.st_informar_orientacao_sexual as stInformarOrientacaoSexual, "
					+ "			tb_fat_cad_individual.st_internacao_12 as stInternacao12, "
					+ "			tb_fat_cad_individual.st_morador_rua as stMoradorRua, "
					+ "			tb_fat_cad_individual.st_orig_alimen_doacao_popular as stOrigAlimenDoacaoPopular, "
					+ "			tb_fat_cad_individual.st_orig_alimen_doacao_reli as stOrigAlimenDoacaoReli, "
					+ "			tb_fat_cad_individual.st_orig_alimen_doacao_rest as stOrigAlimenDoacaoRest, "
					+ "			tb_fat_cad_individual.st_orig_alimen_outros as stOrigAlimenOutros, "
					+ "			tb_fat_cad_individual.st_orig_alimen_restaurante_pop as stOrigAlimenRestaurantePop, "
					+ "			tb_fat_cad_individual.st_outra_droga as stOutraDroga, "
					+ "			tb_fat_cad_individual.st_participa_grupo_comunitario as stParticipaGrupoComunitario, "
					+ "			tb_fat_cad_individual.st_pic as stPic, "
					+ "			tb_fat_cad_individual.st_plano_saude_privado as stPlanoSaudePrivado, "
					+ "			tb_fat_cad_individual.st_problema_rins as stProblemaRins, "
					+ "			tb_fat_cad_individual.st_problema_rins_insuficiencia as stProblemaRinsInsuficiencia, "
					+ "			tb_fat_cad_individual.st_problema_rins_nao_sabe as stProblemaRinsNaoSabe, "
					+ "			tb_fat_cad_individual.st_problema_rins_outro as stProblemaRinsOutro, "
					+ "			tb_fat_cad_individual.st_recebe_beneficio as stRecebeBeneficio, "
					+ "			tb_fat_cad_individual.st_recusa_cadastro as stRecusaCadastro, "
					+ "			tb_fat_cad_individual.st_referencia_familiar as stReferenciaFamiliar, "
					+ "			tb_fat_cad_individual.st_responsavel_familiar as stResponsavelFamiliar, "
					+ "			tb_fat_cad_individual.st_tipo_cuidado_crianca_1 as stTipoCuidadoCrianca1, "
					+ "			tb_fat_cad_individual.st_tipo_cuidado_crianca_2 as stTipoCuidadoCrianca2, "
					+ "			tb_fat_cad_individual.st_tipo_cuidado_crianca_3 as stTipoCuidadoCrianca3, "
					+ "			tb_fat_cad_individual.st_tipo_cuidado_crianca_4 as stTipoCuidadoCrianca4, "
					+ "			tb_fat_cad_individual.st_tipo_cuidado_crianca_5 as stTipoCuidadoCrianca5, "
					+ "			tb_fat_cad_individual.st_tipo_cuidado_crianca_6 as stTipoCuidadoCrianca6, "
					+ "			tb_fat_cad_individual.st_tratamento_psiquiatra as stTratamentoPsiquiatra, "
					+ "			tb_fat_cad_individual.st_tuberculose as stTuberculose, "
					+ "			tb_fat_cad_individual.st_usa_planta_medicinal as stUsaPlantaMedicinal, "
					+ "			tb_fat_cad_individual.st_visita_familiar_frequente as stVisitaFamiliarFrequente "
					+ "		from "
					+ "			tb_fat_cad_individual tb_fat_cad_individual left join tb_fat_cidadao tb_fat_cidadao on "
					+ "			tb_fat_cad_individual.co_seq_fat_cad_individual = tb_fat_cidadao.co_fat_cad_individual "
					+ "		where "
//					+ "			tb_fat_cad_individual.co_dim_municipio =2 "
//					+ "			and( "
//					+ "				tb_fat_cidadao.co_dim_tempo_valdd_municipio >= 30001231 "
//					+ "				and tb_fat_cidadao.co_dim_tempo <= 20170712 "
//					+ "			) "
//					+ "	) fato on "
//					+ "	( "
//					+ "		case "
//					+ "			when fato.dtNascimento is not null then date_part( "
//					+ "				'year', "
//					+ "				age( '2017-07-12' "
//					+ "					, "
//					+ "					fato.dtNascimento "
//					+ "				) "
//					+ "			) "
//					+ "			else 0 "
//					+ "		end "
//					+ "	)>= tb_dim_faixa_etaria.nu_faixa_inicial_anos "
//					+ "	and( "
//					+ "		case "
//					+ "			when fato.dtNascimento is not null then date_part( "
//					+ "				'year', "
//					+ "				age( '2017-07-12' "
					+ "			tb_fat_cad_individual.co_dim_municipio =? "
					+ "			and( "
					+ "				tb_fat_cidadao.co_dim_tempo_valdd_municipio >= ? "
					+ "				and tb_fat_cidadao.co_dim_tempo <= ? "
					+ "			) "
					+ "	) fato on "
					+ "	( "
					+ "		case "
					+ "			when fato.dtNascimento is not null then date_part( "
					+ "				'year', "
					+ "				age( ? "
					+ "					, "
					+ "					fato.dtNascimento "
					+ "				) "
					+ "			) "
					+ "			else 0 "
					+ "		end "
					+ "	)>= tb_dim_faixa_etaria.nu_faixa_inicial_anos "
					+ "	and( "
					+ "		case "
					+ "			when fato.dtNascimento is not null then date_part( "
					+ "				'year', "
					+ "				age( ? "
					+ "					, "
					+ "					fato.dtNascimento "
					+ "				) "
					+ "			) "
					+ "			else 0 "
					+ "		end "
					+ "	)< tb_dim_faixa_etaria.nu_faixa_final_anos "
					+ "group by "
					+ "	tb_dim_faixa_etaria.co_seq_dim_faixa_etaria, "
					+ "	tb_dim_faixa_etaria.ds_faixa_etaria "
					+ "order by "
					+ "	tb_dim_faixa_etaria.co_seq_dim_faixa_etaria asc ");
	
	// 1 - coDimMunicipio
		// 2 - codimTempoValidade
		// 3 - CoDimTempo
	public SQLStmt quadroCidadao = new SQLStmt(
			"select " +
					"coalesce(sum(case when fact.stDesconheceMae = 1 then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stDesconheceMae = 0 then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stDesconhecePai = 1 then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stDesconhecePai = 0 then 1 else 0 end),0), " +
					"coalesce(sum(case when fact.stResponsavelFamiliar = 1 then 0 else 0 end),0), " +
					"coalesce(sum(case when fact.stResponsavelFamiliar = 0 then 0 else 0 end),0), " +
					"coalesce(sum(case when fact.stResponsavelFamiliar is null then 0 else 0 end),0) " +
					"from " +
					"( " +
					"   select " +
					"   tb_fat_cad_individual.co_dim_cbo as coDimCbo, " +
					"   tb_fat_cad_individual.co_dim_cbo_cidadao as coDimCboCidadao, " +
					"   tb_fat_cad_individual.co_dim_equipe as coDimEquipe, " +
					"   tb_fat_cad_individual.co_dim_etnia as coDimEtnia, " +
					"   tb_fat_cad_individual.co_dim_frequencia_alimentacao as coDimFrequenciaAlimentacao, " +
					"   tb_fat_cad_individual.co_dim_identidade_genero as coDimIdentidadeGenero, " +
					"   tb_fat_cad_individual.co_dim_municipio as coDimMunicipio, " +
					"   tb_fat_cad_individual.co_dim_municipio_cidadao as coDimMunicipioCidadao, " +
					"   tb_fat_cad_individual.co_dim_nacionalidade as coDimNacionalidade, " +
					"   tb_fat_cad_individual.co_dim_pais_nascimento as coDimPaisNascimento, " +
					"   tb_fat_cad_individual.co_dim_profissional as coDimProfissional, " +
					"   tb_fat_cad_individual.co_dim_raca_cor as coDimRacaCor, " +
					"   tb_fat_cad_individual.co_dim_sexo as coDimSexo, " +
					"   tb_fat_cad_individual.co_dim_situacao_trabalho as coDimSituacaoTrabalho, " +
					"   tb_fat_cad_individual.co_dim_tempo as coDimTempo, " +
					"   tb_fat_cad_individual.co_dim_tempo_morador_rua as coDimTempoMoradorRua, " +
					"   tb_fat_cad_individual.co_dim_tempo_validade as coDimTempoValidade, " +
					"   tb_fat_cad_individual.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, " +
					"   tb_fat_cad_individual.co_dim_tipo_condicao_peso as coDimTipoCondicaoPeso, " +
					"   tb_fat_cad_individual.co_dim_tipo_escolaridade as coDimTipoEscolaridade, " +
					"   tb_fat_cad_individual.co_dim_tipo_ficha as coDimTipoFicha, " +
					"   tb_fat_cad_individual.co_dim_tipo_orientacao_sexual as coDimTipoOrientacaoSexual, " +
					"   tb_fat_cad_individual.co_dim_tipo_parentesco as coDimTipoParentesco, " +
					"   tb_fat_cad_individual.co_dim_tipo_saida_cadastro as coDimTipoSaidaCadastro, " +
					"   tb_fat_cad_individual.co_dim_unidade_saude as coDimUnidadeSaude, " +
					"   tb_fat_cad_individual.co_dim_faixa_etaria as coDimFaixaEtaria, " +
					"   tb_fat_cad_individual.co_seq_fat_cad_individual as coSeqFatCadIndividual, " +
					"   tb_fat_cad_individual.dt_entrada_brasil as dtEntradaBrasil, " +
					"   tb_fat_cad_individual.dt_nascimento as dtNascimento, " +
					"   tb_fat_cad_individual.dt_naturalizacao as dtNaturalizacao, " +
					"   tb_fat_cad_individual.dt_obito as dtObito, " +
					"   tb_fat_cad_individual.nu_cns as nuCns, " +
					"   tb_fat_cad_individual.nu_cns_responsavel as nuCnsResponsavel, " +
					"   tb_fat_cad_individual.nu_micro_area as nuMicroArea, " +
					"   tb_fat_cad_individual.nu_uuid_ficha as nuUuidFicha, " +
					"   tb_fat_cad_individual.nu_uuid_ficha_origem as nuUuidFichaOrigem, " +
					"   tb_fat_cad_individual.st_acamado as stAcamado, " +
					"   tb_fat_cad_individual.st_acompanhado_instituicao as stAcompanhadoInstituicao, " +
					"   tb_fat_cad_individual.st_alcool as stAlcool, " +
					"   tb_fat_cad_individual.st_avc as stAvc, " +
					"   tb_fat_cad_individual.st_cancer as stCancer, " +
					"   tb_fat_cad_individual.st_comunidade_tradicional as stComunidadeTradicional, " +
					"   tb_fat_cad_individual.st_defi_auditiva as stDefiAuditiva, " +
					"   tb_fat_cad_individual.st_deficiencia as stDeficiencia, " +
					"   tb_fat_cad_individual.st_defi_fisica as stDefiFisica, " +
					"   tb_fat_cad_individual.st_defi_intelectual_cognitiva as stDefiIntelectualCognitiva, " +
					"   tb_fat_cad_individual.st_defi_outra as stDefiOutra, " +
					"   tb_fat_cad_individual.st_defi_visual as stDefiVisual, " +
					"   tb_fat_cad_individual.st_desconhece_mae as stDesconheceMae, " +
					"   tb_fat_cad_individual.st_desconhece_pai as stDesconhecePai, " +
					"   tb_fat_cad_individual.st_diabete as stDiabete, " +
					"   tb_fat_cad_individual.st_doenca_cardiaca as stDoencaCardiaca, " +
					"   tb_fat_cad_individual.st_doenca_card_insuficiencia as stDoencaCardInsuficiencia, " +
					"   tb_fat_cad_individual.st_doenca_card_n_sabe as stDoencaCardNSabe, " +
					"   tb_fat_cad_individual.st_doenca_card_outro as stDoencaCardOutro, " +
					"   tb_fat_cad_individual.st_doenca_respira_asma as stDoencaRespiraAsma, " +
					"   tb_fat_cad_individual.st_doenca_respira_dpoc_enfisem as stDoencaRespiraDpocEnfisem, " +
					"   tb_fat_cad_individual.st_doenca_respira_n_sabe as stDoencaRespiraNSabe, " +
					"   tb_fat_cad_individual.st_doenca_respira_outra as stDoencaRespiraOutra, " +
					"   tb_fat_cad_individual.st_doenca_respiratoria as stDoencaRespiratoria, " +
					"   tb_fat_cad_individual.st_domiciliado as stDomiciliado, " +
					"   tb_fat_cad_individual.st_frequenta_creche as stFrequentaCreche, " +
					"   tb_fat_cad_individual.st_frequenta_cuidador as stFrequentaCuidador, " +
					"   tb_fat_cad_individual.st_fumante as stFumante, " +
					"   tb_fat_cad_individual.st_gestante as stGestante, " +
					"   tb_fat_cad_individual.st_hanseniase as stHanseniase, " +
					"   tb_fat_cad_individual.st_higiene_pessoal_acesso as stHigienePessoalAcesso, " +
					"   tb_fat_cad_individual.st_hig_pess_banho as stHigPessBanho, " +
					"   tb_fat_cad_individual.st_hig_pess_higiene_bucal as stHigPessHigieneBucal, " +
					"   tb_fat_cad_individual.st_hig_pess_outros as stHigPessOutros, " +
					"   tb_fat_cad_individual.st_hig_pess_sanitario as stHigPessSanitario, " +
					"   tb_fat_cad_individual.st_hipertensao_arterial as stHipertensaoArterial, " +
					"   tb_fat_cad_individual.st_infarto as stInfarto, " +
					"   tb_fat_cad_individual.st_informar_identidade_genero as stInformarIdentidadeGenero, " +
					"   tb_fat_cad_individual.st_informar_orientacao_sexual as stInformarOrientacaoSexual, " +
					"   tb_fat_cad_individual.st_internacao_12 as stInternacao12, " +
					"   tb_fat_cad_individual.st_morador_rua as stMoradorRua, " +
					"   tb_fat_cad_individual.st_orig_alimen_doacao_popular as stOrigAlimenDoacaoPopular, " +
					"   tb_fat_cad_individual.st_orig_alimen_doacao_reli as stOrigAlimenDoacaoReli, " +
					"   tb_fat_cad_individual.st_orig_alimen_doacao_rest as stOrigAlimenDoacaoRest, " +
					"   tb_fat_cad_individual.st_orig_alimen_outros as stOrigAlimenOutros, " +
					"   tb_fat_cad_individual.st_orig_alimen_restaurante_pop as stOrigAlimenRestaurantePop, " +
					"   tb_fat_cad_individual.st_outra_droga as stOutraDroga, " +
					"   tb_fat_cad_individual.st_participa_grupo_comunitario as stParticipaGrupoComunitario, " +
					"   tb_fat_cad_individual.st_pic as stPic, " +
					"   tb_fat_cad_individual.st_plano_saude_privado as stPlanoSaudePrivado, " +
					"   tb_fat_cad_individual.st_problema_rins as stProblemaRins, " +
					"   tb_fat_cad_individual.st_problema_rins_insuficiencia as stProblemaRinsInsuficiencia, " +
					"   tb_fat_cad_individual.st_problema_rins_nao_sabe as stProblemaRinsNaoSabe, " +
					"   tb_fat_cad_individual.st_problema_rins_outro as stProblemaRinsOutro, " +
					"   tb_fat_cad_individual.st_recebe_beneficio as stRecebeBeneficio, " +
					"   tb_fat_cad_individual.st_recusa_cadastro as stRecusaCadastro, " +
					"   tb_fat_cad_individual.st_referencia_familiar as stReferenciaFamiliar, " +
					"   tb_fat_cad_individual.st_responsavel_familiar as stResponsavelFamiliar, " +
					"   tb_fat_cad_individual.st_tipo_cuidado_crianca_1 as stTipoCuidadoCrianca1, " +
					"   tb_fat_cad_individual.st_tipo_cuidado_crianca_2 as stTipoCuidadoCrianca2, " +
					"   tb_fat_cad_individual.st_tipo_cuidado_crianca_3 as stTipoCuidadoCrianca3, " +
					"   tb_fat_cad_individual.st_tipo_cuidado_crianca_4 as stTipoCuidadoCrianca4, " +
					"   tb_fat_cad_individual.st_tipo_cuidado_crianca_5 as stTipoCuidadoCrianca5, " +
					"   tb_fat_cad_individual.st_tipo_cuidado_crianca_6 as stTipoCuidadoCrianca6, " +
					"   tb_fat_cad_individual.st_tratamento_psiquiatra as stTratamentoPsiquiatra, " +
					"   tb_fat_cad_individual.st_tuberculose as stTuberculose, " +
					"   tb_fat_cad_individual.st_usa_planta_medicinal as stUsaPlantaMedicinal, " +
					"   tb_fat_cad_individual.st_visita_familiar_frequente as stVisitaFamiliarFrequente " +
					"   from tb_fat_cad_individual tb_fat_cad_individual " +
					"   left join tb_fat_cidadao tb_fat_cidadao on tb_fat_cad_individual.co_seq_fat_cad_individual = tb_fat_cidadao.co_fat_cad_individual " +
//					"   where tb_fat_cad_individual.co_dim_municipio = 2 " +
//					"   and " +
//					"   ( " +
//					"      tb_fat_cidadao.co_dim_tempo_valdd_municipio >= 30001231 " +

					"   where tb_fat_cad_individual.co_dim_municipio = ? " +
					"   and " +
					"   ( " +
					"      tb_fat_cidadao.co_dim_tempo_valdd_municipio >= ? " +
					"      and tb_fat_cidadao.co_dim_tempo <= ? " +
					"   ) " +
					") " +
					" fact "
    );
	

	// -----------------------------------------------------------------
    // RUN
    // -----------------------------------------------------------------
	
	public ResultSet run(Connection conn, boolean forSelect, int coDimMunicipio,
			                            int dateFinal, int validade) throws UserAbortException, SQLException {		
	    int param = 1;
	    
		PreparedStatement st = this.getPreparedStatement(conn, this.selectActor);
        //st.setInt(1, pageNamespace);
        //st.setString(2, pageTitle);
        ResultSet rs = st.executeQuery();
        if (!rs.next()) {
           throw new UserAbortException("Sem atores cadastrados!");
        }
        rs.close();

        
        st = this.getPreparedStatement(conn, this.quadrosDadosGerais);
        st.setInt(1, coDimMunicipio);
        st.setInt(2, validade);
        st.setInt(3, dateFinal);
        rs = st.executeQuery();
        while (rs.next()) {
            byte[] pr_type = rs.getBytes(1);
            assert(pr_type != null);
        } // WHILE
        rs.close();
        // check using blocking of a user by either the IP address or the
        // user_name

//        st = this.getPreparedStatement(conn, this.quadroFaixaEtaria);
//        st.setInt(1, coDimMunicipio);
//        st.setInt(2, validade);
//        st.setInt(3, dateFinal);
//        
//        java.sql.Date date = new java.sql.Date((new Date()).getTime());
//		st.setDate(4, date);
//        st.setDate(5, date);
//        try ( ResultSet rs2 = st.executeQuery()){
//        	while (rs2.next()) {
//                //do nothing
//             } // WHILE
//        }
//       
//        rs.close();

        st = this.getPreparedStatement(conn, this.quadroCidadao);
        st.setInt(1, coDimMunicipio);
        st.setInt(2, validade);
        st.setInt(3, dateFinal);
        rs = st.executeQuery();
        if (rs.next()) {
           
        }
        rs.close();
        
        return null;
    }

}
