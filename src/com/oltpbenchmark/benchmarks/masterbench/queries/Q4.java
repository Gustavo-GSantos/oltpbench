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

public class Q4 extends GenericQuery {
	
    public final SQLStmt query_stmt = new SQLStmt(
                  		 "select "
    				 + "	at.ID, at, description "
    				 + "from Aspect_Type at "
    				 + "	join Predicate_Aspect_Type pat on at.ID = pat.aspect_type "
    				 + "	join Predicate p on  pat.predicate = p.ID "
    				 + "where "
    				 + "	p.ID in (select p1.ID "
    				 + "		from Predicate p1 "
    				 + "			join Dependency_Rule dr on p1.determinant = dr.ID "
    				 + "			join Rule_MO rmo on dr.ID = rmo.rule "
    				 + "			join Moving_Object mo on rmo.MO = mo.ID "
    				 + "			join Moving_Object_Type mot on mo.type = mot.ID "
    				 + "		where mot.description = ? ) "
    				 + "	or "
    				 + "	p.ID in (select p1.ID  "
    				 + "		from Predicate p1 "
    				 + "			join Dependency_Rule dr on p1.determined = dr.ID "
    				 + "			join Rule_MO rmo on dr.ID = rmo.rule "
    				 + "			join Moving_Object mo on rmo.MO = mo.ID "
    				 + "			join Moving_Object_Type mot on mo.type = mot.ID "
    				 + "		where mot.description = ? ) "

        );

		protected SQLStmt get_query() {
	    return query_stmt;
	}
}
