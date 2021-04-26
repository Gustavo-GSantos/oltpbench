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

public class Q5 extends Procedure {

	private static final Logger LOG = Logger.getLogger(Q5.class);
	
	// Fazer uma query de teste
    public final SQLStmt query_stmt = new SQLStmt(
    		"SELECT  a.ID, a.description "+
    			"FROM Aspect a "+
    			"	JOIN Point_Aspect pa ON a.ID = pa.aspect "+
    			"	JOIN Point p ON pa.point = p.ID "+
    			"	JOIN Rule_Point rp ON p.ID = rp.point "+
   			"	JOIN Dependency_Rule dr ON rp.rule = dr.ID "+
   			"WHERE dr.description = 'TksVqRLYDGaxpRcERdIznmJmKNTtDFtBCmEYmwhw'; "
            		  
        );

    public ResultSet run(Connection conn) throws UserAbortException,
	SQLException {
    	
    	//LOG.info("Q5");

		PreparedStatement st = this.getPreparedStatement(conn, this.query_stmt);
		//st.setString(1, parametro1);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {

		} // WHILE
		rs.close();

		return null;
	}
}
