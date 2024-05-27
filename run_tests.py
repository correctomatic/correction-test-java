import os
import subprocess
import json

import xml.etree.ElementTree as ET

DEBUG = 'S'

def log(message):
    if DEBUG == 'S':
        print(message)

# Set the classpath to include all dependencies and compiled classes
classpath = ':'.join(
    [os.path.join(dp, f) for dp, dn, filenames in os.walk('/app/dependency') for f in filenames if f.endswith('.jar')]
) + ':/app/classes:/app/test-classes:/app'
result_file = '/tmp/test_results.txt'

log("Copying the student's code...")

# Copy the student's code to the correct directory
PACKAGE = 'empleados'
FILE_PATH = f'/app/classes/{PACKAGE}'
os.makedirs(FILE_PATH, exist_ok=True)
result = subprocess.run(['cp', '/tmp/exercise', f'{FILE_PATH}/EmpleadoBR.java'], check=True)
if result.returncode != 0:
    error_message = {
        "success": False,
        "error": "Failed to copy the student's code"
    }
    print(json.dumps(error_message))
    # Exit with an error code, the correction failed
    exit(1)

log("Compiling the student's code...")

# Compile the student's code, replacing the stubs
compile_error_file = '/tmp/compile_errors.txt'
compile_command = [
    'javac', '-cp', classpath, f'{FILE_PATH}/EmpleadoBR.java',
    '-sourcepath', '/app/classes'
]

compile_result = subprocess.run(compile_command, stderr=subprocess.PIPE, text=True)
if compile_result.returncode != 0:
    compile_errors = compile_result.stderr.replace(f'{FILE_PATH}/', '')

    error_message = {
        "success": False,
        "error": f"Compilation failed: {compile_errors}"
    }
    print(json.dumps(error_message))

    # Exit with an OK code, since the container succeeded but the correction failed
    exit(0)

log("Compilation successful!")
log("Running the tests...")

# Run the JUnit Console Launcher to execute the tests
test_command = [
    'java', '-jar', 'junit-platform-console-standalone.jar',
    '--class-path', classpath,
    '--scan-class-path',
    '--details=tree'
]

test_command = [
    'java', '-jar', 'BANANA junit-platform-console-standalone.jar',
    '--class-path', classpath,
    '--scan-class-path',
    '--details=tree',
    '--reports-dir=test-reports',
]


with open(result_file, 'w') as f:
    run_result = subprocess.run(test_command, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)

print('*******************************')
print(run_result.returncode)
print('*******************************')
# Check if tests were successful
with open(result_file, 'r') as f:
    results = f.read()


def parse_junit_xml(file_path):
    tree = ET.parse(file_path)
    root = tree.getroot()

    # Find all the <testcase> elements that contain a <failure> child
    failed_tests = root.findall(".//testcase[failure]")

    unique_failures = set()

    for testcase in failed_tests:
        failure_element = testcase.find("failure")
        if failure_element is not None:
            # Get the description of the failure
            description = failure_element.get("message")
            if description:
                unique_failures.add(description)

    # Number of failed tests
    num_failed_tests = len(failed_tests)

    return num_failed_tests, list(unique_failures)

num_failed, failures =  parse_junit_xml('/app/test-reports/TEST-junit-jupiter.xml')
print(f"Failed tests: {num_failed}")
print(f"Failures: {failures}")

success = "Test run finished after" in results

# Extract test results
results_summary = results.split("Test run finished after", 1)[-1].strip()

# Create the JSON output
success_message = {
    "success": True,
    "results": f'{results_summary}'
}
print(json.dumps(success_message))
