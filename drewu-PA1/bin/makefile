switch: run_tests

run_tests: compile
	java -cp ".;junit-4.12.jar;hamcrest-core-1.3.jar" org.junit.runner.JUnitCore AllTests

compile: *.java
	javac -cp ".;junit-4.12.jar;hamcrest-core-1.3.jar" *.java