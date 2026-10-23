@echo off
javac QuizMaster.java
if errorlevel 1 exit /b 1
java QuizMaster
