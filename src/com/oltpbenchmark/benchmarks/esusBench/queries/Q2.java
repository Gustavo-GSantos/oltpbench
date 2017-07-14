/******************************************************************************
 *  Copyright 2015 by OLTPBenchmark Project                                   *
 *                                                                            *
 *  Licensed under the Apache License, Version 2.0 (the "License");           *
 *  you may not use this file except in compliance with the License.          *
 *  You may obtain a copy of the License at                                   *
 *                                                                            *
 *    http://www.apache.org/licenses/LICENSE-2.0                              *
 *                                                                            *
 *  Unless required by applicable law or agreed to in writing, software       *
 *  distributed under the License is distributed on an "AS IS" BASIS,         *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 *  See the License for the specific language governing permissions and       *
 *  limitations under the License.                                            *
 ******************************************************************************/

package com.oltpbenchmark.benchmarks.esusBench.queries;

import com.oltpbenchmark.api.SQLStmt;

public class Q2 extends GenericQuery {
	
    public final SQLStmt query_stmt = new SQLStmt(
    		"select "
    				+ "	tb_dim_tipo_imovel.co_ordem, "
    				+ "	tb_dim_tipo_imovel.ds_tipo_imovel, "
    				+ "	count( distinct fato.nuUuidFicha ) "
    				+ "from "
    				+ "	tb_dim_tipo_imovel tb_dim_tipo_imovel left join( "
    				+ "		select "
    				+ "			tb_fat_cad_domiciliar.co_dim_cbo as coDimCbo, "
    				+ "			tb_fat_cad_domiciliar.co_dim_equipe as coDimEquipe, "
    				+ "			tb_fat_cad_domiciliar.co_dim_municipio as coDimMunicipio, "
    				+ "			tb_fat_cad_domiciliar.co_dim_municipio_cidadao as coDimMunicipioCidadao, "
    				+ "			tb_fat_cad_domiciliar.co_dim_profissional as coDimProfissional, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tempo as coDimTempo, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tempo_validade as coDimTempoValidade, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tempo_validade_recusa as coDimTempoValidadeRecusa, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tipo_abastecimento_agua as coDimTipoAbastecimentoAgua, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tipo_acesso_domicilio as coDimTipoAcessoDomicilio, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tipo_destino_lixo as coDimTipoDestinoLixo, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tipo_domicilio as coDimTipoDomicilio, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tipo_escoamento_sanitar as coDimTipoEscoamentoSanitar, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tipo_ficha as coDimTipoFicha, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tipo_imovel as coDimTipoImovel, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tipo_localizacao as coDimTipoLocalizacao, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tipo_logradouro as coDimTipoLogradouro, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tipo_material_parede as coDimTipoMaterialParede, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tipo_posse_terra as coDimTipoPosseTerra, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tipo_situacao_moradia as coDimTipoSituacaoMoradia, "
    				+ "			tb_fat_cad_domiciliar.co_dim_tipo_tratamento_agua as coDimTipoTratamentoAgua, "
    				+ "			tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar as coSeqFatCadDomiciliar, "
    				+ "			tb_fat_cad_domiciliar.co_dim_unidade_saude as coDimUnidadeSaude, "
    				+ "			tb_fat_cad_domiciliar.nu_comodo as nuComodo, "
    				+ "			tb_fat_cad_domiciliar.nu_micro_area as nuMicroArea, "
    				+ "			tb_fat_cad_domiciliar.nu_uuid_ficha as nuUuidFicha, "
    				+ "			tb_fat_cad_domiciliar.nu_uuid_ficha_origem as nuUuidFichaOrigem, "
    				+ "			tb_fat_cad_domiciliar.qt_animal_domiciliar as qtAnimalDomiciliar, "
    				+ "			tb_fat_cad_domiciliar.qt_morador as qtMorador, "
    				+ "			tb_fat_cad_domiciliar.st_animal_cachorro as stAnimalCachorro, "
    				+ "			tb_fat_cad_domiciliar.st_animal_domiciliar as stAnimalDomiciliar, "
    				+ "			tb_fat_cad_domiciliar.st_animal_gato as stAnimalGato, "
    				+ "			tb_fat_cad_domiciliar.st_animal_outros as stAnimalOutros, "
    				+ "			tb_fat_cad_domiciliar.st_animal_passaro as stAnimalPassaro, "
    				+ "			tb_fat_cad_domiciliar.st_disp_energia as stDispEnergia, "
    				+ "			tb_fat_cad_domiciliar.st_outros_prof_vinclds as stOutrosProfVinclds, "
    				+ "			tb_fat_cad_domiciliar.st_recusa_cadastro as stRecusaCadastro, "
    				+ "			tb_fat_cad_domiciliar.st_familias as stFamilias, "
    				+ "			tb_fat_cad_dom_familia.co_dim_tipo_renda_familiar as coDimTipoRendaFamiliar, "
    				+ "			tb_fat_cad_dom_familia.co_seq_fat_cad_dom_familia as coSeqFatCadDomFamilia, "
    				+ "			tb_fat_cad_dom_familia.dt_inicio_residencia as dtInicioResidencia, "
    				+ "			tb_fat_cad_dom_familia.dt_nascimento as dtNascimento, "
    				+ "			tb_fat_cad_dom_familia.nu_cns_responsavel as nuCnsResponsavel, "
    				+ "			tb_fat_cad_dom_familia.qt_membro_familiar as qtMembroFamiliar, "
    				+ "			tb_fat_cad_dom_familia.st_mudou as stMudou "
    				+ "		from "
    				+ "			tb_fat_cad_domiciliar tb_fat_cad_domiciliar left join tb_fat_cad_dom_familia tb_fat_cad_dom_familia on "
    				+ "			tb_fat_cad_dom_familia.co_fat_cad_domiciliar = tb_fat_cad_domiciliar.co_seq_fat_cad_domiciliar "
    				+ "		where "
    				+ "			tb_fat_cad_domiciliar.co_dim_municipio = 2 "
    				+ "			and( "
    				+ "				tb_fat_cad_domiciliar.co_dim_tempo_validade > 30001231 "
    				+ "				and tb_fat_cad_domiciliar.co_dim_tempo <= 20170712 "
    				+ "			) "
    				+ "	) as fato on "
    				+ "	tb_dim_tipo_imovel.co_seq_dim_tipo_imovel = fato.coDimTipoImovel "
    				+ "group by "
    				+ "	tb_dim_tipo_imovel.co_ordem, "
    				+ "	tb_dim_tipo_imovel.ds_tipo_imovel "
    				+ "order by "
    				+ "	tb_dim_tipo_imovel.co_ordem asc "
        );
	
		protected SQLStmt get_query() {
	    return query_stmt;
	}
}
