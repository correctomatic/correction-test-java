import xml.etree.ElementTree as ET
import re
import os

print(os.getcwd())

file_path = f'{os.getcwd()}/test-reports/TEST-junit-jupyter.xml'
file_path = '/home/alvaro/Software/correctomatic/correction-test-java/test-reports/TEST-junit-jupyter.xml'
print(file_path)
tree = ET.parse(file_path)
root = tree.getroot()

# List to store CDATA content of failed tests
failed_tests_cdata = []

# # Find all failure elements and extract CDATA content
# for testcase in root.findall('.//testcase'):
#     failure = testcase.find('failure')
#     if failure is not None:
#         cdata_text = failure.text.strip()  # Get the CDATA text and strip leading/trailing whitespace
#         failed_tests_cdata.append(cdata_text)

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
foo = map(extract_display_name, failed_tests_cdata)
# print(failed_tests_cdata[0])
for cdata in foo:
    print(cdata)
    # pass
