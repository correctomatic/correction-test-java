#!/bin/bash

# Set the classpath to include all dependencies and compiled classes
CLASSPATH=$(find /app/dependency -type f -name "*.jar" | tr '\n' ':')/app/classes:/app/test-classes:/app
RESULT_FILE=/tmp/test_results.txt

# Copy the student's code to the correct directory
PACKAGE=empleados
FILE_PATH=/app/classes/$PACKAGE
cp /tmp/exercise $FILE_PATH/EmpleadoBR.java

# Compile the student's code, replacing the stubs
javac -cp $CLASSPATH $FILE_PATH/EmpleadoBR.java


# Run the JUnit Console Launcher to execute the tests
java -jar junit-platform-console-standalone.jar --class-path $CLASSPATH --scan-class-path --details=tree > "$RESULT_FILE"
# java -jar junit-platform-console-standalone.jar --class-path $CLASSPATH --scan-class-path --details=tree

# Check if tests were successful
if grep -q "Test run finished after" "$RESULT_FILE"; then
    success=true
else
    success=false
fi

# Extract test results
results=$(grep -A 50 "Test run finished after" "$RESULT_FILE")

# Create the JSON output
echo "{ \"success\": $success, \"results\": \"$results\" }"
