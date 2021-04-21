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


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.oltpbenchmark.api.Loader;
import com.oltpbenchmark.api.Loader.LoaderThread;
import com.oltpbenchmark.benchmarks.chbenchmark.pojo.Nation;
import com.oltpbenchmark.benchmarks.chbenchmark.pojo.Region;
import com.oltpbenchmark.benchmarks.chbenchmark.pojo.Supplier;
import com.oltpbenchmark.util.RandomGenerator;

public class MasterBenchLoader extends Loader<MasterBench> {
	private static final Logger LOG = Logger.getLogger(MasterBenchLoader.class);

	private static final RandomGenerator ran = new RandomGenerator(0);
	private static PreparedStatement tbDim;

	private static Date now;
	private static long lastTimeMS;
	//private static Connection conn;


	public MasterBenchLoader(MasterBench benchmark) {
		super(benchmark);
		//conn =c;
	}

	@Override
	public List<LoaderThread> createLoaderThreads() throws SQLException {
	    List<LoaderThread> threads = new ArrayList<LoaderThread>();
	    
	    // Load Teste
	    threads.add(new LoaderThread() {
            @Override
            public void load(Connection conn) throws SQLException {
                MasterBenchLoader.this.load(conn);
            }
        });
	    
	    return threads;
	}

	public void load(Connection conn) throws SQLException {

		try {
			tbDim = conn.prepareStatement("INSERT INTO tb_dim_sexo "
					+ " (co_seq_dim_sexo, nu_identificador, ds_sexo, sg_sexo, co_ordem) "
					+ "VALUES (?, ?, ?, ?, ?)");

		} catch (SQLException se) {
			LOG.debug(se.getMessage());
			conn.rollback();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();

		} // end try

		loadSexo(conn);

		conn.commit();
	}

	static int loadSexo(Connection conn) throws SQLException {

		int k = 0;
		int t = 0;
		BufferedReader br = null;

		try {
			now = new java.util.Date();
			LOG.info("\nStart load Sexo @ " + now
					+ " ...");

			int i = 0;
			while(++i<10){
				tbDim.setLong(1, 5+i);
				tbDim.setLong(2, 5+i);
				tbDim.setString(3, "sexo_"+ran.nextInt());
				tbDim.setString(4, "sexo_"+ran.nextInt());
				tbDim.setLong(5, ran.nextInt()%100);

				tbDim.addBatch();
			}

			long tmpTime = new java.util.Date().getTime();
			String etStr = "  Elasped Time(ms): "
					+ ((tmpTime - lastTimeMS) / 1000.000)
					+ "                    ";
			LOG.info(etStr.substring(0, 30)
					+ "  Writing record " + k + " of " + t);
			lastTimeMS = tmpTime;

			tbDim.executeBatch();
			tbDim.clearBatch();
			conn.commit();

		} catch (SQLException se) {
			LOG.info(se.getMessage());
			conn.rollback();
		}  catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


		return (k);

	} // end loadNations()



}
