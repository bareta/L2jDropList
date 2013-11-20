@echo off
java -cp h2-1.3.168.jar org.h2.tools.ConvertTraceFile -traceFile "droplist.trace.db" -script "droplist.sql"