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

package com.oltpbenchmark.benchmarks.esusBench;

import java.sql.SQLException;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.Procedure.UserAbortException;
import com.oltpbenchmark.api.TransactionType;
import com.oltpbenchmark.api.Worker;
import com.oltpbenchmark.benchmarks.esusBench.queries.AtendimentoDomiciliar;
import com.oltpbenchmark.benchmarks.esusBench.queries.AtendimentoIndividual;
import com.oltpbenchmark.benchmarks.esusBench.queries.AtendimentoOdontologico;
import com.oltpbenchmark.benchmarks.esusBench.queries.AtividadeColetiva;
import com.oltpbenchmark.benchmarks.esusBench.queries.AvaliacaoElegibilidade;
import com.oltpbenchmark.benchmarks.esusBench.queries.CadastroDomiciliar;
import com.oltpbenchmark.benchmarks.esusBench.queries.CadastroIndividual;
import com.oltpbenchmark.benchmarks.esusBench.queries.Complementar;
import com.oltpbenchmark.benchmarks.esusBench.queries.GenericQuery;
import com.oltpbenchmark.benchmarks.esusBench.queries.MarcadoresConsumo;
import com.oltpbenchmark.benchmarks.esusBench.queries.Procedimentos;
import com.oltpbenchmark.benchmarks.esusBench.queries.ProcedimentosConsolidados;
import com.oltpbenchmark.types.TransactionStatus;
import com.oltpbenchmark.util.RandomGenerator;

public class EsusBenchWorker extends Worker<EsusBench> {
	private static final RandomGenerator ran = new RandomGenerator(0);
	
	public EsusBenchWorker(EsusBench benchmarkModule, int id) {
		super(benchmarkModule, id);
	}
	
	@Override
	protected TransactionStatus executeWork(TransactionType nextTransaction) throws UserAbortException, SQLException {
		try {
			
			
			Class<? extends Procedure> procedureClass = nextTransaction.getProcedureClass();
			if (procedureClass.equals(CadastroIndividual.class)){
				CadastroIndividual proc = this.getProcedure(CadastroIndividual.class);
				if (proc != null)
					proc.run(conn, true, 2, 20170712, 30001231);
			} else if (procedureClass.equals(CadastroDomiciliar.class)){
				CadastroDomiciliar proc = this.getProcedure(CadastroDomiciliar.class);
				if (proc != null){
					proc.run(conn, (Math.abs(ran.nextInt())%14+1), 2, generateDate("20100712", "20170712"), 30001231);
				}
				
			}else if (procedureClass.equals(AtendimentoIndividual.class)){
				AtendimentoIndividual proc = this.getProcedure(AtendimentoIndividual.class);
				if (proc != null){
					proc.run(conn, (Math.abs(ran.nextInt())%21+1), 2, generateDate("20140110", "20160708"), 20160708);
				}
			} else if (procedureClass.equals(AtendimentoOdontologico.class)){
				AtendimentoOdontologico proc = this.getProcedure(AtendimentoOdontologico.class);
				if (proc != null){
					proc.run(conn, (Math.abs(ran.nextInt())%16+1), 2, generateDate("20140110", "20151223"), 30001231);
				}
				
			} else if (procedureClass.equals(AtividadeColetiva.class)){
				AtividadeColetiva proc = this.getProcedure(AtividadeColetiva.class);
				if (proc != null){
					proc.run(conn, (Math.abs(ran.nextInt())%8+1), 2, generateDate("20140110", "20151223"), 30001231);
				}
				
			} else if (procedureClass.equals(AtendimentoDomiciliar.class)){
				AtendimentoDomiciliar proc = this.getProcedure(AtendimentoDomiciliar.class);
				if (proc != null){
					proc.run(conn, (Math.abs(ran.nextInt())%13+1), 2, generateDate("20140110", "20151223"), 30001231);
				}
				
			} else if (procedureClass.equals(Complementar.class)){
				Complementar proc = this.getProcedure(Complementar.class);
				if (proc != null){
					proc.run(conn, (Math.abs(ran.nextInt())%13+1), 2, generateDate("20140110", "20151223"), 30001231);
				}
				
			} else if (procedureClass.equals(MarcadoresConsumo.class)){
				MarcadoresConsumo proc = this.getProcedure(MarcadoresConsumo.class);
				if (proc != null){
					proc.run(conn, (Math.abs(ran.nextInt())%13+1), 2, generateDate("20140110", "20151223"), 30001231);
				}
				
			} else if (procedureClass.equals(AvaliacaoElegibilidade.class)){
				AvaliacaoElegibilidade proc = this.getProcedure(AvaliacaoElegibilidade.class);
				if (proc != null){
					proc.run(conn, (Math.abs(ran.nextInt())%15+1), 2, generateDate("20140110", "20151223"), 30001231);
				}
				
			} else if (procedureClass.equals(Procedimentos.class)){
				Procedimentos proc = this.getProcedure(Procedimentos.class);
				if (proc != null){
					proc.run(conn, (Math.abs(ran.nextInt())%10+1), 2, generateDate("20150101", "20151231"), 30001231);
				}
				
			} else if (procedureClass.equals(ProcedimentosConsolidados.class)){
				ProcedimentosConsolidados proc = this.getProcedure(ProcedimentosConsolidados.class);
				if (proc != null){
					proc.run(conn, (Math.abs(ran.nextInt())%1+1), 2, generateDate("20150101", "20151231"), 30001231);
				}
				
			} else if (procedureClass.equals(ProcedimentosConsolidados.class)){
				ProcedimentosConsolidados proc = this.getProcedure(ProcedimentosConsolidados.class);
				if (proc != null){
					proc.run(conn, 11, 2, generateDate("20150101", "20151231"), 30001231);
				}
				
			} else{
				GenericQuery proc = (GenericQuery) this.getProcedure(procedureClass);
				proc.setOwner(this);
	        	proc.run(conn);
        	}
			
		} catch (ClassCastException e) {
        	System.err.println("We have been invoked with an INVALID transactionType?!");
        	throw new RuntimeException("Bad transaction type = "+ nextTransaction);
		}

        conn.commit();
        return (TransactionStatus.SUCCESS);

	}
	
	private int generateDate (String startDate, String finalDate){
		String generatedDate = "";
		
		int sYear = Integer.parseInt(startDate.substring(0, 4));
		int sMonth = Integer.parseInt(startDate.substring(4, 6));
		int sDay = Integer.parseInt(startDate.substring(6, 8));
		
		//System.out.println("\n --> "+ "y: "+sYear + "m: "+sMonth+ "d: "+sDay  +"\n");
		
		int fYear = Integer.parseInt(finalDate.substring(0, 4));
		int fMonth = Integer.parseInt(finalDate.substring(4, 6));
		int fDay = Integer.parseInt(finalDate.substring(6, 8));
		
		generatedDate = String.valueOf(sYear+(Math.abs(ran.nextInt())%(fYear-sYear + 1)));
		generatedDate += String.format("%02d", (sMonth+(Math.abs(ran.nextInt())%(fMonth-sMonth + 1))%12)+1);
		generatedDate += String.format("%02d",(sDay+(Math.abs(ran.nextInt())%(fDay-sDay + 1))%28)+1);
		
		if (generatedDate.length() < 8){
			System.err.println("Data gerada errado!");
			generatedDate = finalDate;
		}
		return Integer.parseInt(generatedDate);
	}
	
}
