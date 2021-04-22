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

public class Q2 extends GenericQuery {
	
    public final SQLStmt query_stmt = new SQLStmt(
                  		 "select "
    				 + "	r.ID, r.description "
    				 + "from Dependency_rule r "
    				 + "	join Predicate p on r.ID = p.determinant " // = p.determined ,"Duvida em relação ao INTERSECT"
    				 + "	join Predicate_Aspect pa on p.ID = pa.predicate "
    				 + "	join Aspect a on pa.aspect = a.ID "
    				 + "where "
    				 + "	a. Description = ? "

        );

		protected SQLStmt get_query() {
	    return query_stmt;
	}
}
