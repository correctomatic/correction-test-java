import subprocess
import xml.etree.ElementTree as ET
import json

def run_tests():
    # Run JUnit tests and capture XML output
    result = subprocess.run(["java", "-jar", "your-junit-tests.jar", "-xml", "/tmp/exercise"], capture_output=True, text=True)

    # Parse the XML output
    root = ET.fromstring(result.stdout)
    test_cases = root.findall('.//testcase')
    passed_tests = len(test_cases)

    # Calculate grade proportional to the number of passed tests
    total_tests = 10  # Replace with the total number of tests
    grade = (passed_tests / total_tests) * 100

    # Check if all tests passed
    if passed_tests == total_tests:
        response = {
            "success": True,
            "grade": round(grade, 2),
            "comments": ["All tests passed"]
        }
    else:
        response = {
            "success": False,
            "error": "Some tests failed"
        }

    return response

if __name__ == "__main__":
    response = run_tests()
    print(json.dumps(response, indent=2))
