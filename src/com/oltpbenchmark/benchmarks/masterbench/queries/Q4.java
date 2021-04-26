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

public class Q4 extends Procedure {

	private static final Logger LOG = Logger.getLogger(Q4.class);
	
    public final SQLStmt query_stmt = new SQLStmt(
    		"SELECT at.ID, at, AT.description "+
    			"FROM Aspect_Type at "+
    			"	JOIN Predicate_Aspect_Type pat ON at.ID = pat.aspect_type "+
    			"	JOIN Predicate p ON pat.predicate = p.ID "+
    			"WHERE "+
    			"p.ID IN (SELECT p1.ID "+
    			"	FROM Predicate p1 "+
    			"	JOIN Dependency_Rule dr ON p1.determinant = dr.ID "+
    			"	JOIN Rule_MO rmo ON dr.ID = rmo.RULE "+
    			"	JOIN Moving_Object mo ON rmo.MO = mo.ID "+
    			"	JOIN Moving_Object_Type mot ON mo.MO_Type = mot.ID "+
    			"	WHERE mot.description = 'KHHaSNQjwAXrGPiTpgglUXgCPzcjHLKBKsbRwovZ') "+
    			"OR "+
    			"p.ID IN (SELECT p1.ID "+
    			"	FROM Predicate p1 "+
    			"	JOIN Dependency_Rule dr ON p1.determined = dr.ID "+
    			"	JOIN Rule_MO rmo ON dr.ID = rmo.RULE "+
    			"	JOIN Moving_Object mo ON rmo.MO = mo.ID "+
    			"	JOIN Moving_Object_Type mot ON mo.MO_Type = mot.ID "+
    			"	WHERE mot.description = 'KHHaSNQjwAXrGPiTpgglUXgCPzcjHLKBKsbRwovZ') "         		  
        );

    public ResultSet run(Connection conn) throws UserAbortException,
	SQLException {
    	
    	//LOG.info("Q4");

		PreparedStatement st = this.getPreparedStatement(conn, this.query_stmt);
		//st.setString(1, parametro1);
		//st.setString(2, parametro2);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {

		} // WHILE
		rs.close();

		return null;
	}
}
