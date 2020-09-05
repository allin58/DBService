@ECHO OFF
cd %cd%\target
java -jar dbservice-0.0.1-SNAPSHOT.jar search data/input.json data/output.json