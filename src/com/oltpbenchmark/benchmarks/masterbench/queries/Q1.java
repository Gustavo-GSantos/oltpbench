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

import com.oltpbenchmark.api.SQLStmt;
import com.oltpbenchmark.api.Procedure;

import org.apache.log4j.Logger;

public class Q1 extends Procedure {

	private static final Logger LOG = Logger.getLogger(Q1.class);
	
    public final SQLStmt query_stmt = new SQLStmt(
              "SELECT "
              + 	"r.ID, "
              + 	"r.start_time, "
              + 	"r.end_time, "
              + 	"r.confidence, "
              + 	"r.description, "
              + 	"p1.description, "
              + 	"p1.path, "
              + 	"p1.order_s, "
              + 	"p1.parenthesis_type, "
              + 	"p1.parenthesis_amount, "
              + 	"p1.logical_operator, "
              + 	"p2.description, "
              + 	"p2.path, p2.order_s, "
              + 	"p2.parenthesis_type, "
              + 	"p2.parenthesis_amount, "
              + 	"p2.logical_operator "
              + "FROM Dependency_Rule r "
              + 	"JOIN Predicate p1 ON p1.determinant = r.ID "
              + 	"JOIN Predicate p2 ON p2.determined = r.ID "
              + 	"JOIN Rule_Dataset rd ON r.ID = rd.rule "
              + 	"JOIN Dataset d ON rd.dataset = d.ID "
              + "WHERE d.URL = ?;"            		  
        );

    public ResultSet run(Connection conn, String parametro) throws UserAbortException,
	SQLException {
    	
    	//LOG.info("Q1");

    	PreparedStatement st = this.getPreparedStatement(conn, this.query_stmt);
    	st.setString(1, parametro);
    	ResultSet rs = st.executeQuery();
    	while (rs.next()) {

    	} // WHILE
    	rs.close();

    	return null;
    }
}
