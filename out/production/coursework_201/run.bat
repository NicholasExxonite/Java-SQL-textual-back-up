@echo off
set CLASSPATH=%CLASSPATH%;\%~dp0\sqlite-jdbc-3.7.2.jar

@echo on
java  Main > output.sql University.db
pause
