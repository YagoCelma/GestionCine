javac -d bin -cp .;lib/mysql-connector-j-9.2.0.jar;lib/json-20140107.jar ./src/main/*.java ./src/dao/*.java ./src/view/*.java ./src/model/*.java
java -cp "./bin;./lib/mysql-connector-j-9.2.0.jar;./lib/json-20140107.jar" main.App
