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

import com.oltpbenchmark.api.SQLStmt;

public class Q1 extends GenericQuery {
	
    public final SQLStmt query_stmt = new SQLStmt(
                  		 "select "
    				 + "	r.ID, r.start_time, r.end_time, r.confidence, r.description, "
    				 + "	p1.description, p1.path, p1.order, p1.parenthesis_type, "
    				 + "	p1.parenthesis_amount, p1. logical_operator, "
    				 + "	p2.description, p2.path, p2.order, p2.parenthesis_type, "
    				 + "	p2.parenthesis_amount, p2. logical_operator "
    				 + "from Dependency_Rule r "
    				 + "	join Predicate p1 on p1.determinant = r.ID "
    				 + "	join Predicate p2 on p2.determined = r.ID "
    				 + "	join Rule_Dataset rd on r.ID = rd.rule "
    				 + "	join Dataset d on rd.dataset = d.ID "
    				 + "where "
    				 + "	d.URL = ? "

        );

		protected SQLStmt get_query() {
	    return query_stmt;
	}
}
