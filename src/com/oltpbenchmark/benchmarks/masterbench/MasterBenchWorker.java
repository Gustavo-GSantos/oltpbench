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

package com.oltpbenchmark.benchmarks.masterbench;

import java.sql.SQLException;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.Procedure.UserAbortException;
import com.oltpbenchmark.api.TransactionType;
import com.oltpbenchmark.api.Worker;
import com.oltpbenchmark.benchmarks.masterbench.queries.GenericQuery;
import com.oltpbenchmark.benchmarks.masterbench.queries.Q1;
import com.oltpbenchmark.benchmarks.masterbench.queries.Q2;
import com.oltpbenchmark.benchmarks.masterbench.queries.Q3;
import com.oltpbenchmark.benchmarks.masterbench.queries.Q4;
import com.oltpbenchmark.benchmarks.masterbench.queries.Q5;
import com.oltpbenchmark.types.TransactionStatus;

public class MasterBenchWorker extends Worker<MasterBench> {
	public MasterBenchWorker(MasterBench benchmarkModule, int id) {
		super(benchmarkModule, id);
	}

	@Override
	protected TransactionStatus executeWork(TransactionType nextTransaction) throws UserAbortException, SQLException {
		try {
			
			Class<? extends Procedure> procedureClass = nextTransaction.getProcedureClass();
			if (procedureClass.equals(Q1.class)){
				Q1 proc = this.getProcedure(Q1.class);
				if (proc != null)
					proc.run(conn, "oJOfvweKjYSRdpGzztxudLqTBUXsnUVTzKabHxUfr");
				
			} else if (procedureClass.equals(Q2.class)){
				Q2 proc = this.getProcedure(Q2.class);
				if (proc != null){
					proc.run(conn, "OGicKYRMZHdKpMxsvTvyDfJEQXxjCIzoJYfjGIxs", "OGicKYRMZHdKpMxsvTvyDfJEQXxjCIzoJYfjGIxs");

				}
			
			} else if (procedureClass.equals(Q3.class)){
				Q3 proc = this.getProcedure(Q3.class);
				if (proc != null){
					proc.run(conn, "ymgOHPeTrLnLHsoJdlaqwnjqMdYmOmDLFUNEgKQG", "iXgQkIGpGSOYRrSHvlYDMqFSjFAHHOwxscgUGUvS");
				}
				
			} else if (procedureClass.equals(Q4.class)){
				Q4 proc = this.getProcedure(Q4.class);
				if (proc != null){
					proc.run(conn);
				}
				
			} else if (procedureClass.equals(Q5.class)){
				Q5 proc = this.getProcedure(Q5.class);
				if (proc != null){
					proc.run(conn);
				}
				
			}else{
			
			GenericQuery proc = (GenericQuery) this.getProcedure(nextTransaction.getProcedureClass());
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
}
