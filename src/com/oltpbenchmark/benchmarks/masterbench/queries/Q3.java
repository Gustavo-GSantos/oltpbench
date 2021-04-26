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

package com.oltpbenchmark.benchmarks.masterbench.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.SQLStmt;

import org.apache.log4j.Logger;

public class Q3 extends Procedure {

	private static final Logger LOG = Logger.getLogger(Q3.class);
	
    public final SQLStmt query_stmt = new SQLStmt(
    		"SELECT t.ID, t.description "+
    			"FROM Multiple_Aspect_Trajectory t "+
    			"JOIN Moving_Object mo ON t.MO = mo.ID "+
    			"JOIN Rule_MAT rm ON t.ID = rm.MAT "+
    			"JOIN Dependency_Rule dr ON dr.ID = rm.rule "+
    			"WHERE mo.description = ? AND dr.description = ?; "            		  
        );
    
    //+ "	JOIN Moving_Object mo ON t.owner = mo.ID "

    public ResultSet run(Connection conn, String parametro1, String parametro2) throws UserAbortException,
	SQLException {
    	
    	//LOG.info("Q3");

		PreparedStatement st = this.getPreparedStatement(conn,
			this.query_stmt);
		st.setString(1, parametro1);
		st.setString(2, parametro2);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {

		} // WHILE
		rs.close();

		return null;
	}
}
