# FailingLivySparkJob

 - `mvn clean install` will output the dependent jars in <roo_directory>/lib/
 - Copy over `TestCSV.csv` into hdfs at location /transient/TestCSV.csv
 - Running this against Spark 2.3 will cause it to fail but have tried to run against Spark 2.4 with the same output
