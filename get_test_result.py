import xml.etree.ElementTree as ET
import re

file_path = './test-reports/TEST-junit-jupiter.xml'

tree = ET.parse(file_path)
root = tree.getroot()

# List to store CDATA content of failed tests
failed_tests_cdata = []

# Find all testcase elements
for testcase in root.findall('.//testcase'):
    # Check if the testcase has a failure element
    if testcase.find('failure') is not None:
        system_out = testcase.find('system-out')
        if system_out is not None:
            cdata_text = system_out.text.strip()  # Get the CDATA text and strip leading/trailing whitespace
            failed_tests_cdata.append(cdata_text)

def extract_display_name(text):
    pattern = re.compile(r"display-name: (.+)")
    match = pattern.search(text)
    return match.group(1) if match else None


# Print the results
print(len(failed_tests_cdata))
foo = list(set(map(extract_display_name, failed_tests_cdata)))
# print(failed_tests_cdata[0])
for cdata in foo:
    print(cdata)
    # pass
