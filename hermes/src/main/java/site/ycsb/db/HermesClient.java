/**
 * Copyright (c) 2012 YCSB contributors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License. See accompanying
 * LICENSE file.
 */

/**
 * Hermes client binding for YCSB.
 *
 * All YCSB records are mapped to a Hermes blob.
 */

package site.ycsb.db;

import site.ycsb.ByteIterator;
import site.ycsb.StringByteIterator;
import site.ycsb.DB;
import site.ycsb.DBException;
import site.ycsb.Status;

import hermes_kvstore.java.KVStore;
import hermes_kvstore.java.KVTable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * YCSB binding for <a href="https://github.com/HDFGroup/hermes/wiki">Redis</a>.
 *
 * See {@code redis/README.md} for details.
 */
public class HermesClient extends DB {
  public void init() throws DBException {
    KVStore.connect();
  }

  public void cleanup() throws DBException {
  }

  @Override
  public Status read(String table, String key, Set<String> fields,
      Map<String, ByteIterator> result) {
    try {
      KVTable kvTable = KVStore.getTable(table);
      Map<String, String> record = kvTable.read(key, fields);
      StringByteIterator.putAllAsByteIterators(result, record);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result.isEmpty() ? Status.ERROR : Status.OK;
  }

  @Override
  public Status insert(String table, String key,
      Map<String, ByteIterator> values) {
    try {
      KVTable kvTable = KVStore.getTable(table);
      Map<String, String> newValues = StringByteIterator.getStringMap(values);
      kvTable.insert(key, newValues);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Status.OK;
  }

  @Override
  public Status delete(String table, String key) {
    System.out.println("delete");
    KVTable kvTable = KVStore.getTable(table);
    kvTable.destroy();
    return Status.OK;
  }

  @Override
  public Status update(String table, String key,
      Map<String, ByteIterator> values) {
    try {
      KVTable kvTable = KVStore.getTable(table);
      Map<String, String> newValues = StringByteIterator.getStringMap(values);
      kvTable.update(key, newValues);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Status.OK;
  }

  @Override
  public Status scan(String table, String startkey, int recordcount,
      Set<String> fields, Vector<HashMap<String, ByteIterator>> result) {
    return Status.NOT_IMPLEMENTED;

    // TODO(llogan): How are keys named?
    /*HashMap<String, ByteIterator> values;
    for (String key : keys) {
      values = new HashMap<String, ByteIterator>();
      read(table, key, fields, values);
      result.add(values);
    }

    return Status.OK;*/
  }

}
