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

public class Q5 extends GenericQuery {
	
    public final SQLStmt query_stmt = new SQLStmt(
                  		 "select "
    				 + "	a.ID, a.description "
    				 + "from Aspect a "
    				 + "	join Point_Aspect pa on a.ID = pa.aspect "
    				 + "	join Point p on pa.point = p.ID "
    				 + "	join Rule_Point rp on p.ID = rp.point "
    				 + "	join Dependency_Rule dr on rp.rule = dr. ID "
    				 + "where "
    				 + "	dr.description = ? "

        );

		protected SQLStmt get_query() {
	    return query_stmt;
	}
}
