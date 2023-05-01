<!--
Copyright (c) 2014 - 2015 YCSB contributors. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License"); you
may not use this file except in compliance with the License. You
may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing
permissions and limitations under the License. See accompanying
LICENSE file.
-->

## Quick Start

This section describes how to run YCSB on Hermes. 

### 1. Start Hermes

### 2. Install Java and Maven

### 3. Set Up YCSB

Setup paths and compile
```
spack load --only dependencies hermes
export HERMES_ROOT=/home/lukemartinlogan/Documents/Projects/PhD/hermes
export PATH=$JAVA_HOME/bin:$PATH
export LD_LIBRARY_PATH=$HERMES_ROOT/cmake-build-debug-gcc-no-sanitize/bin:$LD_LIBRARY_PATH
mvn -pl site.ycsb:hermes-binding -am clean package
```

### 4. Load data and run tests

Load the data:

    ./bin/ycsb load hermes -s -P workloads/workloada > outputLoad.txt

Run the workload test:

    ./bin/ycsb run hermes -s -P workloads/workloada > outputRun.txt

