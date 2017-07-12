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


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.oltpbenchmark.WorkloadConfiguration;
import com.oltpbenchmark.api.BenchmarkModule;
import com.oltpbenchmark.api.Loader;
import com.oltpbenchmark.api.Worker;
import com.oltpbenchmark.benchmarks.esusBench.queries.Q1;

public class EsusBench extends BenchmarkModule {
	private static final Logger LOG = Logger.getLogger(EsusBench.class);
	
	public EsusBench(WorkloadConfiguration workConf) {
		super("esusbench", workConf, true);
	}
	
	protected Package getProcedurePackageImpl() {
		return (Q1.class.getPackage());
	}
	
	/**
	 * @param Bool
	 */
	@Override
	protected List<Worker<? extends BenchmarkModule>> makeWorkersImpl(boolean verbose) throws IOException {
		// HACK: Turn off terminal messages
		List<Worker<? extends BenchmarkModule>> workers = new ArrayList<Worker<? extends BenchmarkModule>>();

		int numTerminals = workConf.getTerminals();
		LOG.info(String.format("Creating %d workers for Esus", numTerminals));
        for (int i = 0; i < numTerminals; i++)
            workers.add(new EsusBenchWorker(this, i));
        LOG.info(String.format("Workers created for Esus"));
        

		return workers;
	}
	
	protected Loader<EsusBench> makeLoaderImpl(Connection conn) throws SQLException {
		return new EsusBenchLoader(this, conn);
	}
	
}
