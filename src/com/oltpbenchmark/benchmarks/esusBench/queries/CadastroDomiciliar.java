package com.oltpbenchmark.benchmarks.esusBench.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.SQLStmt;

public class CadastroDomiciliar extends Procedure {

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
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt tipoImovel = new SQLStmt(
			"select "
					+ "tb_dim_tipo_imovel.co_ordem, "
					+ "tb_dim_tipo_imovel.ds_tipo_imovel, "
					+ "count(distinct fato.nuUuidFicha) "
					+ "from tb_dim_tipo_imovel tb_dim_tipo_imovel "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "   tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "   tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "   tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "   tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "   tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "   tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "   tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "   tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "   tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "   tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "   tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "   tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "   tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "   tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "   tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "   tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "   tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "   from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "   left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "   where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "   and "
					+ "   ( "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "      and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "   ) "
					+ ") "
					+ "fato on tb_dim_tipo_imovel.co_seq_dim_tipo_imovel = fato.coDimTipoImovel "
					+ "group by tb_dim_tipo_imovel.co_ordem,tb_dim_tipo_imovel.ds_tipo_imovel "
					+ "order by tb_dim_tipo_imovel.co_ordem asc ");

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadroCondicoesMoradia = new SQLStmt(
			"select "
					+ "tb_dim_tipo_situacao_moradia.co_ordem, "
					+ "tb_dim_tipo_situacao_moradia.ds_tipo_situacao_moradia, "
					+ "count(distinct fato.nuUuidFicha) "
					+ "from tb_dim_tipo_situacao_moradia tb_dim_tipo_situacao_moradia "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "   tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "   tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "   tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "   tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "   tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "   tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "   tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "   tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "   tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "   tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "   tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "   tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "   tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "   tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "   tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "   tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "   tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "   from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "   left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "   where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "   and "
					+ "   ( "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "      and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "   ) "
					+ ") "
					+ "fato on tb_dim_tipo_situacao_moradia.co_seq_dim_tipo_situacao_morad = fato.coDimTipoSituacaoMoradia "
					+ "group by tb_dim_tipo_situacao_moradia.co_ordem, "
					+ "tb_dim_tipo_situacao_moradia.ds_tipo_situacao_moradia "
					+ "order by tb_dim_tipo_situacao_moradia.co_ordem asc ");

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadroCondicaoMoradiaLocalizacao = new SQLStmt(
			"select "
					+ "tb_dim_tipo_localizacao.co_ordem, "
					+ "tb_dim_tipo_localizacao.ds_tipo_localizacao, "
					+ "count(distinct fato.nuUuidFicha) "
					+ "from tb_dim_tipo_localizacao tb_dim_tipo_localizacao "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "   tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "   tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "   tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "   tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "   tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "   tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "   tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "   tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "   tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "   tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "   tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "   tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "   tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "   tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "   tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "   tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "   tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "   from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "   left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "   where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "   and "
					+ "   ( "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "      and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "   ) "
					+ ") "
					+ "fato on tb_dim_tipo_localizacao.co_seq_dim_tipo_localizacao = fato.coDimTipoLocalizacao "
					+ "group by tb_dim_tipo_localizacao.co_ordem, "
					+ "tb_dim_tipo_localizacao.ds_tipo_localizacao "
					+ "order by tb_dim_tipo_localizacao.co_ordem asc ");

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadroCondicoesMoradiaTipoDomicilio = new SQLStmt(
			"select "
					+ "tb_dim_tipo_domicilio.co_ordem, "
					+ "tb_dim_tipo_domicilio.ds_tipo_domicilio, "
					+ "count(distinct fato.nuUuidFicha) "
					+ "from tb_dim_tipo_domicilio tb_dim_tipo_domicilio "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "   tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "   tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "   tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "   tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "   tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "   tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "   tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "   tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "   tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "   tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "   tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "   tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "   tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "   tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "   tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "   tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "   tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "   from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "   left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "   where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "   and "
					+ "   ( "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "      and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "   ) "
					+ ") "
					+ "fato on tb_dim_tipo_domicilio.co_seq_dim_tipo_domicilio = fato.coDimTipoDomicilio "
					+ "group by tb_dim_tipo_domicilio.co_ordem,tb_dim_tipo_domicilio.ds_tipo_domicilio "
					+ "order by tb_dim_tipo_domicilio.co_ordem asc ");

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadroConfigRelatorioCadastroDomiciliarTipoPosseTerra = new SQLStmt(
			"select "
					+ "tb_dim_tipo_posse_terra.co_ordem, "
					+ "tb_dim_tipo_posse_terra.ds_tipo_posse_terra, "
					+ "count(distinct fato.nuUuidFicha) "
					+ "from tb_dim_tipo_posse_terra tb_dim_tipo_posse_terra "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "   tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "   tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "   tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "   tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "   tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "   tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "   tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "   tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "   tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "   tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "   tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "   tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "   tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "   tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "   tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "   tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "   tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "   from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "   left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "   where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "   and "
					+ "   ( "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "      and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "   ) "
					+ ") "
					+ "fato on tb_dim_tipo_posse_terra.co_seq_dim_tipo_posse_terra = fato.coDimTipoPosseTerra "
					+ "group by tb_dim_tipo_posse_terra.co_ordem, "
					+ "tb_dim_tipo_posse_terra.ds_tipo_posse_terra "
					+ "order by tb_dim_tipo_posse_terra.co_ordem asc ");

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadroConfigRelatorioCadastroDomiciliarTipoAcessoDomicilio = new SQLStmt(
			"select "
					+ "tb_dim_tipo_acesso_domicilio.ds_tipo_acesso_domicilio, "
					+ "count(distinct fato.nuUuidFicha) "
					+ "from tb_dim_tipo_acesso_domicilio tb_dim_tipo_acesso_domicilio "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   distinct fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimMunicipioCidadao as coDimMunicipioCidadao, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTempoValidade as coDimTempoValidade, "
					+ "   fact.coDimTempoValidadeRecusa as coDimTempoValidadeRecusa, "
					+ "   fact.coDimTipoAbastecimentoAgua as coDimTipoAbastecimentoAgua, "
					+ "   fact.coDimTipoAcessoDomicilio as coDimTipoAcessoDomicilio, "
					+ "   fact.coDimTipoDestinoLixo as coDimTipoDestinoLixo, "
					+ "   fact.coDimTipoDomicilio as coDimTipoDomicilio, "
					+ "   fact.coDimTipoEscoamentoSanitar as coDimTipoEscoamentoSanitar, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTipoImovel as coDimTipoImovel, "
					+ "   fact.coDimTipoLocalizacao as coDimTipoLocalizacao, "
					+ "   fact.coDimTipoLogradouro as coDimTipoLogradouro, "
					+ "   fact.coDimTipoMaterialParede as coDimTipoMaterialParede, "
					+ "   fact.coDimTipoPosseTerra as coDimTipoPosseTerra, "
					+ "   fact.coDimTipoSituacaoMoradia as coDimTipoSituacaoMoradia, "
					+ "   fact.coDimTipoTratamentoAgua as coDimTipoTratamentoAgua, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.nuComodo as nuComodo, "
					+ "   fact.nuMicroArea as nuMicroArea, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   fact.nuUuidFichaOrigem as nuUuidFichaOrigem, "
					+ "   fact.qtAnimalDomiciliar as qtAnimalDomiciliar, "
					+ "   fact.qtMorador as qtMorador, "
					+ "   fact.stAnimalCachorro as stAnimalCachorro, "
					+ "   fact.stAnimalDomiciliar as stAnimalDomiciliar, "
					+ "   fact.stAnimalGato as stAnimalGato, "
					+ "   fact.stAnimalOutros as stAnimalOutros, "
					+ "   fact.stAnimalPassaro as stAnimalPassaro, "
					+ "   fact.stDispEnergia as stDispEnergia, "
					+ "   fact.stOutrosProfVinclds as stOutrosProfVinclds, "
					+ "   fact.stRecusaCadastro as stRecusaCadastro "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "      tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "      tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "      tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "      tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "      tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "      tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "      tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "      tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "      tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "      tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "      tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "      tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "      tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "      tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "      tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "      tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "      tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "      tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "      tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "      tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "      tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "      tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "      tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "      tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "      from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "      left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "      where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "      and "
					+ "      ( "
					+ "         tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "         and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "      ) "
					+ "   ) "
					+ "   fact  "
					+ ") "
					+ "fato on tb_dim_tipo_acesso_domicilio.co_seq_dim_tipo_acesso_domicil = fato.coDimTipoAcessoDomicilio "
					+ "group by tb_dim_tipo_acesso_domicilio.co_ordem, "
					+ "tb_dim_tipo_acesso_domicilio.ds_tipo_acesso_domicilio "
					+ "order by tb_dim_tipo_acesso_domicilio.co_ordem asc, "
					+ "tb_dim_tipo_acesso_domicilio.ds_tipo_acesso_domicilio asc ");

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadroConfigRelatorioCadastroDomiciliarTipoMaterialParede = new SQLStmt(
			"select "
					+ "tb_dim_tipo_material_parede.co_ordem, "
					+ "tb_dim_tipo_material_parede.ds_tipo_material_parede, "
					+ "count(distinct fato.nuUuidFicha) "
					+ "from tb_dim_tipo_material_parede tb_dim_tipo_material_parede "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "   tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "   tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "   tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "   tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "   tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "   tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "   tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "   tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "   tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "   tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "   tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "   tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "   tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "   tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "   tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "   tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "   tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "   from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "   left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "   where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "   and "
					+ "   ( "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "      and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "   ) "
					+ ") "
					+ "fato on tb_dim_tipo_material_parede.co_seq_dim_tipo_material_pared = fato.coDimTipoMaterialParede "
					+ "group by tb_dim_tipo_material_parede.co_ordem, "
					+ "tb_dim_tipo_material_parede.ds_tipo_material_parede "
					+ "order by tb_dim_tipo_material_parede.co_ordem asc ");

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadroConfigRelatorioCadastroDomiciliarDisponibilidadeEnergiaEletrica = new SQLStmt(
			"select "
					+ "coalesce(sum(case when fato.stDispEnergia = 1 then 1 else 0 end),0), "
					+ "coalesce(sum(case when fato.stDispEnergia = 0 then 1 else 0 end),0), "
					+ "coalesce(sum(case when fato.stDispEnergia is null then 1 else 0 end),0) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   distinct fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimMunicipioCidadao as coDimMunicipioCidadao, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTempoValidade as coDimTempoValidade, "
					+ "   fact.coDimTempoValidadeRecusa as coDimTempoValidadeRecusa, "
					+ "   fact.coDimTipoAbastecimentoAgua as coDimTipoAbastecimentoAgua, "
					+ "   fact.coDimTipoAcessoDomicilio as coDimTipoAcessoDomicilio, "
					+ "   fact.coDimTipoDestinoLixo as coDimTipoDestinoLixo, "
					+ "   fact.coDimTipoDomicilio as coDimTipoDomicilio, "
					+ "   fact.coDimTipoEscoamentoSanitar as coDimTipoEscoamentoSanitar, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTipoImovel as coDimTipoImovel, "
					+ "   fact.coDimTipoLocalizacao as coDimTipoLocalizacao, "
					+ "   fact.coDimTipoLogradouro as coDimTipoLogradouro, "
					+ "   fact.coDimTipoMaterialParede as coDimTipoMaterialParede, "
					+ "   fact.coDimTipoPosseTerra as coDimTipoPosseTerra, "
					+ "   fact.coDimTipoSituacaoMoradia as coDimTipoSituacaoMoradia, "
					+ "   fact.coDimTipoTratamentoAgua as coDimTipoTratamentoAgua, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.nuComodo as nuComodo, "
					+ "   fact.nuMicroArea as nuMicroArea, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   fact.nuUuidFichaOrigem as nuUuidFichaOrigem, "
					+ "   fact.qtAnimalDomiciliar as qtAnimalDomiciliar, "
					+ "   fact.qtMorador as qtMorador, "
					+ "   fact.stAnimalCachorro as stAnimalCachorro, "
					+ "   fact.stAnimalDomiciliar as stAnimalDomiciliar, "
					+ "   fact.stAnimalGato as stAnimalGato, "
					+ "   fact.stAnimalOutros as stAnimalOutros, "
					+ "   fact.stAnimalPassaro as stAnimalPassaro, "
					+ "   fact.stDispEnergia as stDispEnergia, "
					+ "   fact.stOutrosProfVinclds as stOutrosProfVinclds, "
					+ "   fact.stRecusaCadastro as stRecusaCadastro "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "      tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "      tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "      tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "      tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "      tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "      tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "      tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "      tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "      tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "      tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "      tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "      tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "      tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "      tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "      tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "      tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "      tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "      tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "      tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "      tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "      tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "      tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "      tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "      tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "      from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "      left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "      where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "      and "
					+ "      ( "
					+ "         tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "         and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "      ) " + "   ) " + "   fact  " + ") " + "fato ");

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadroConfigRelatorioCadastroDomiciliarTipoAbastecimentoAgua = new SQLStmt(
			"select "
					+ "tb_dim_tipo_abastecimento_agua.co_ordem, "
					+ "tb_dim_tipo_abastecimento_agua.ds_tipo_abastecimento_agua, "
					+ "count(distinct fato.nuUuidFicha) "
					+ "from tb_dim_tipo_abastecimento_agua tb_dim_tipo_abastecimento_agua "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "   tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "   tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "   tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "   tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "   tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "   tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "   tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "   tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "   tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "   tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "   tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "   tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "   tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "   tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "   tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "   tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "   tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "   from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "   left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "   where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "   and "
					+ "   ( "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "      and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "   ) "
					+ ") "
					+ "fato on tb_dim_tipo_abastecimento_agua.co_seq_dim_tipo_abastec_agua = fato.coDimTipoAbastecimentoAgua "
					+ "group by tb_dim_tipo_abastecimento_agua.co_ordem, "
					+ "tb_dim_tipo_abastecimento_agua.ds_tipo_abastecimento_agua "
					+ "order by tb_dim_tipo_abastecimento_agua.co_ordem asc ");

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadroConfigRelatorioCadastroDomiciliarAguaParaConsumoDomicilio = new SQLStmt(
			"select "
					+ "tb_dim_tipo_tratamento_agua.co_ordem, "
					+ "tb_dim_tipo_tratamento_agua.ds_tipo_tratamento_agua, "
					+ "count(distinct fato.nuUuidFicha) "
					+ "from tb_dim_tipo_tratamento_agua tb_dim_tipo_tratamento_agua "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "   tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "   tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "   tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "   tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "   tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "   tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "   tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "   tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "   tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "   tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "   tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "   tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "   tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "   tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "   tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "   tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "   tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "   from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "   left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "   where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "   and "
					+ "   ( "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "      and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "   ) "
					+ ") "
					+ "fato on tb_dim_tipo_tratamento_agua.co_seq_dim_tipo_tratament_agua = fato.coDimTipoTratamentoAgua "
					+ "group by tb_dim_tipo_tratamento_agua.co_ordem, "
					+ "tb_dim_tipo_tratamento_agua.ds_tipo_tratamento_agua "
					+ "order by tb_dim_tipo_tratamento_agua.co_ordem asc ");

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadroConfigRelatorioCadastroDomiciliarEscoamentoBanheiroSanitario = new SQLStmt(
			"select "
					+ "tb_dim_tipo_escoamento_sanitar.co_ordem, "
					+ "tb_dim_tipo_escoamento_sanitar.ds_tipo_escoamento_sanitario, "
					+ "count(distinct fato.nuUuidFicha) "
					+ "from tb_dim_tipo_escoamento_sanitar tb_dim_tipo_escoamento_sanitar "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "   tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "   tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "   tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "   tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "   tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "   tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "   tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "   tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "   tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "   tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "   tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "   tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "   tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "   tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "   tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "   tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "   tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "   from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "   left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "   where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "   and "
					+ "   ( "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "      and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "   ) "
					+ ") "
					+ "fato on tb_dim_tipo_escoamento_sanitar.co_seq_dim_tipo_escoamento_snt = fato.coDimTipoEscoamentoSanitar "
					+ "group by tb_dim_tipo_escoamento_sanitar.co_ordem, "
					+ "tb_dim_tipo_escoamento_sanitar.ds_tipo_escoamento_sanitario "
					+ "order by tb_dim_tipo_escoamento_sanitar.co_ordem asc ");

	public SQLStmt quadroConfigRelatorioCadastroDomiciliarDestinoLixo = new SQLStmt(
			"select "
					+ "tb_dim_tipo_destino_lixo.co_ordem, "
					+ "tb_dim_tipo_destino_lixo.ds_tipo_destino_lixo, "
					+ "count(distinct fato.nuUuidFicha) "
					+ "from tb_dim_tipo_destino_lixo tb_dim_tipo_destino_lixo "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "   tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "   tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "   tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "   tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "   tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "   tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "   tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "   tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "   tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "   tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "   tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "   tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "   tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "   tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "   tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "   tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "   tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "   from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "   left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "   where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "   and "
					+ "   ( "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "      and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "   ) "
					+ ") "
					+ "fato on tb_dim_tipo_destino_lixo.co_seq_dim_tipo_destino_lixo = fato.coDimTipoDestinoLixo "
					+ "group by tb_dim_tipo_destino_lixo.co_ordem, "
					+ "tb_dim_tipo_destino_lixo.ds_tipo_destino_lixo "
					+ "order by tb_dim_tipo_destino_lixo.co_ordem asc ");

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadroConfigRelatorioCadastroDomiciliarAnimaisDomicilio = new SQLStmt(
			"select "
					+ "coalesce(sum(case when fato.stAnimalDomiciliar = 1 then 1 else 0 end),0), "
					+ "coalesce(sum(case when fato.stAnimalDomiciliar = 0 then 1 else 0 end),0) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   distinct fact.coDimCbo as coDimCbo, "
					+ "   fact.coDimEquipe as coDimEquipe, "
					+ "   fact.coDimMunicipio as coDimMunicipio, "
					+ "   fact.coDimMunicipioCidadao as coDimMunicipioCidadao, "
					+ "   fact.coDimProfissional as coDimProfissional, "
					+ "   fact.coDimTempo as coDimTempo, "
					+ "   fact.coDimTempoValidade as coDimTempoValidade, "
					+ "   fact.coDimTempoValidadeRecusa as coDimTempoValidadeRecusa, "
					+ "   fact.coDimTipoAbastecimentoAgua as coDimTipoAbastecimentoAgua, "
					+ "   fact.coDimTipoAcessoDomicilio as coDimTipoAcessoDomicilio, "
					+ "   fact.coDimTipoDestinoLixo as coDimTipoDestinoLixo, "
					+ "   fact.coDimTipoDomicilio as coDimTipoDomicilio, "
					+ "   fact.coDimTipoEscoamentoSanitar as coDimTipoEscoamentoSanitar, "
					+ "   fact.coDimTipoFicha as coDimTipoFicha, "
					+ "   fact.coDimTipoImovel as coDimTipoImovel, "
					+ "   fact.coDimTipoLocalizacao as coDimTipoLocalizacao, "
					+ "   fact.coDimTipoLogradouro as coDimTipoLogradouro, "
					+ "   fact.coDimTipoMaterialParede as coDimTipoMaterialParede, "
					+ "   fact.coDimTipoPosseTerra as coDimTipoPosseTerra, "
					+ "   fact.coDimTipoSituacaoMoradia as coDimTipoSituacaoMoradia, "
					+ "   fact.coDimTipoTratamentoAgua as coDimTipoTratamentoAgua, "
					+ "   fact.coDimUnidadeSaude as coDimUnidadeSaude, "
					+ "   fact.nuComodo as nuComodo, "
					+ "   fact.nuMicroArea as nuMicroArea, "
					+ "   fact.nuUuidFicha as nuUuidFicha, "
					+ "   fact.nuUuidFichaOrigem as nuUuidFichaOrigem, "
					+ "   fact.qtAnimalDomiciliar as qtAnimalDomiciliar, "
					+ "   fact.qtMorador as qtMorador, "
					+ "   fact.stAnimalCachorro as stAnimalCachorro, "
					+ "   fact.stAnimalDomiciliar as stAnimalDomiciliar, "
					+ "   fact.stAnimalGato as stAnimalGato, "
					+ "   fact.stAnimalOutros as stAnimalOutros, "
					+ "   fact.stAnimalPassaro as stAnimalPassaro, "
					+ "   fact.stDispEnergia as stDispEnergia, "
					+ "   fact.stOutrosProfVinclds as stOutrosProfVinclds, "
					+ "   fact.stRecusaCadastro as stRecusaCadastro "
					+ "   from "
					+ "   ( "
					+ "      select "
					+ "      tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "      tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "      tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "      tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "      tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "      tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "      tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "      tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "      tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "      tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "      tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "      tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "      tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "      tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "      tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "      tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "      tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "      tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "      tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "      tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "      tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "      tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "      tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "      tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "      tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "      tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "      tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "      tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "      tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "      tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "      from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "      left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "      where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "      and "
					+ "      ( "
					+ "         tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "         and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "      ) " + "   ) " + "   fact  " + ") " + "fato ");

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadroQuantosAnimaisDomicilio = new SQLStmt(
			"select "
					+ "coalesce(sum(fato.stAnimalGato),0), "
					+ "coalesce(sum(fato.stAnimalCachorro),0), "
					+ "coalesce(sum(fato.stAnimalPassaro),0), "
					+ "coalesce(sum(fato.stAnimalOutros),0) "
					+ "from "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "   tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "   tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "   tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "   tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "   tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "   tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "   tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "   tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "   tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "   tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "   tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "   tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "   tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "   tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "   tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "   tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "   tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "   from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "   left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "   where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "   and " + "   ( "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "      and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "   ) " + ") " + "fato");

	// 1 - coDimMunicipio
	// 2 - codimTempoValidade
	// 3 - CoDimTempo
	public SQLStmt quadroConfigRelatorioCadastroDomiciliarRendaFamiliar = new SQLStmt(
			"select "
					+ "tb_dim_tipo_renda_familiar.co_ordem, "
					+ "tb_dim_tipo_renda_familiar.ds_tipo_renda_familiar, "
					+ "count(distinct fato.nuUuidFicha) "
					+ "from tb_dim_tipo_renda_familiar tb_dim_tipo_renda_familiar "
					+ "left join "
					+ "( "
					+ "   select "
					+ "   tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
					+ "   tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
					+ "   tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
					+ "   tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
					+ "   tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
					+ "   tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
					+ "   tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
					+ "   tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
					+ "   tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
					+ "   tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
					+ "   tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
					+ "   tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.qt_morador as qtMorador, "
					+ "   tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
					+ "   tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
					+ "   tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
					+ "   tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
					+ "   tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
					+ "   tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
					+ "   tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
					+ "   tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
					+ "   tb_fat_cad_domiciliar.st_familias as stFamilias, "
					+ "   tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
					+ "   tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
					+ "   tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
					+ "   tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
					+ "   tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
					+ "   tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
					+ "   tb_fat_cad_dom_familia.st_mudou as stMudou "
					+ "   from tb_fat_cad_domiciliar tb_fat_cad_domiciliar "
					+ "   left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
					+ "   where tb_fat_cad_domiciliar.co_dim_municipio = ? "
					+ "   and "
					+ "   ( "
					+ "      tb_fat_cad_domiciliar.co_dim_tempo_validade > ? "
					+ "      and tb_fat_cad_domiciliar.co_dim_tempo <= ? "
					+ "   ) "
					+ ") "
					+ "fato on tb_dim_tipo_renda_familiar.co_seq_dim_tipo_renda_familiar = fato.coDimTipoRendaFamiliar "
					+ "group by tb_dim_tipo_renda_familiar.co_ordem, "
					+ "tb_dim_tipo_renda_familiar.ds_tipo_renda_familiar "
					+ "order by tb_dim_tipo_renda_familiar.co_ordem asc ");

	// -----------------------------------------------------------------
	// RUN
	// -----------------------------------------------------------------

	public ResultSet run(Connection conn, int numQuadros, int coDimMunicipio,
			int dateFinal, int validade) throws UserAbortException,
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
			st = this.getPreparedStatement(conn, this.tipoImovel);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			rs = st.executeQuery();
			while (rs.next()) {
				byte[] pr_type = rs.getBytes(1);
				assert (pr_type != null);
			} // WHILE
			rs.close();
		}
		if (numQuadros >= 2) {
			st = this.getPreparedStatement(conn, this.quadroCondicoesMoradia);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			try (ResultSet rs2 = st.executeQuery()) {
				while (rs2.next()) {
					// do nothing
				} // WHILE
			}
		}
		if (numQuadros >= 3) {
			st = this.getPreparedStatement(conn,
					this.quadroCondicaoMoradiaLocalizacao);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			rs = st.executeQuery();
			if (rs.next()) {

			}
			rs.close();
		}
		if (numQuadros >= 4) {
			st = this.getPreparedStatement(conn,
					this.quadroConfigRelatorioCadastroDomiciliarTipoPosseTerra);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			rs = st.executeQuery();
			while (rs.next()) {
				// Do Nothing
			} // WHILE
			rs.close();
		}
		if (numQuadros >= 5) {
			st = this
					.getPreparedStatement(
							conn,
							this.quadroConfigRelatorioCadastroDomiciliarTipoAcessoDomicilio);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			rs = st.executeQuery();
			while (rs.next()) {
				// Do Nothing
			} // WHILE
			rs.close();
		}
		if (numQuadros >= 6) {
			st = this
					.getPreparedStatement(
							conn,
							this.quadroConfigRelatorioCadastroDomiciliarTipoMaterialParede);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			rs = st.executeQuery();
			while (rs.next()) {
				// Do Nothing
			} // WHILE
			rs.close();
		}
		if (numQuadros >= 7) {
			st = this
					.getPreparedStatement(
							conn,
							this.quadroConfigRelatorioCadastroDomiciliarDisponibilidadeEnergiaEletrica);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			rs = st.executeQuery();
			while (rs.next()) {
				// Do Nothing
			} // WHILE
			rs.close();
		}
		if (numQuadros >= 8) {
			st = this
					.getPreparedStatement(
							conn,
							this.quadroConfigRelatorioCadastroDomiciliarTipoAbastecimentoAgua);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			rs = st.executeQuery();
			while (rs.next()) {
				// Do Nothing
			} // WHILE
			rs.close();
		}
		st = this
				.getPreparedStatement(
						conn,
						this.quadroConfigRelatorioCadastroDomiciliarAguaParaConsumoDomicilio);
		st.setInt(1, coDimMunicipio);
		st.setInt(2, validade);
		st.setInt(3, dateFinal);
		rs = st.executeQuery();
		while (rs.next()) {
			// Do Nothing
		} // WHILE
		rs.close();
		if (numQuadros >= 9) {
			st = this
					.getPreparedStatement(
							conn,
							this.quadroConfigRelatorioCadastroDomiciliarAguaParaConsumoDomicilio);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			rs = st.executeQuery();
			while (rs.next()) {
				// Do Nothing
			} // WHILE
			rs.close();
		}
		if (numQuadros >= 10) {
			st = this
					.getPreparedStatement(
							conn,
							this.quadroConfigRelatorioCadastroDomiciliarEscoamentoBanheiroSanitario);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			rs = st.executeQuery();
			while (rs.next()) {
				// Do Nothing
			} // WHILE
			rs.close();
		}
		if (numQuadros >= 11) {
			st = this.getPreparedStatement(conn,
					this.quadroConfigRelatorioCadastroDomiciliarDestinoLixo);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			rs = st.executeQuery();
			while (rs.next()) {
				// Do Nothing
			} // WHILE
			rs.close();
		}
		if (numQuadros >= 12) {
			st = this
					.getPreparedStatement(
							conn,
							this.quadroConfigRelatorioCadastroDomiciliarAnimaisDomicilio);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			rs = st.executeQuery();
			while (rs.next()) {
				// Do Nothing
			} // WHILE
			rs.close();

		}
		if (numQuadros >= 13) {
			st = this.getPreparedStatement(conn,
					this.quadroQuantosAnimaisDomicilio);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			rs = st.executeQuery();
			while (rs.next()) {
				// Do Nothing
			} // WHILE
			rs.close();
		}
		if (numQuadros >= 14) {

			st = this.getPreparedStatement(conn,
					this.quadroConfigRelatorioCadastroDomiciliarRendaFamiliar);
			st.setInt(1, coDimMunicipio);
			st.setInt(2, validade);
			st.setInt(3, dateFinal);
			rs = st.executeQuery();
			while (rs.next()) {
				// Do Nothing
			} // WHILE
			rs.close();
		}

		return null;
	}

}
