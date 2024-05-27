import xml.etree.ElementTree as ET
import re

file_path = './test-reports/TEST-junit-jupiter.xml'

tree = ET.parse(file_path)
root = tree.getroot()

def extract_display_name(text):
    pattern = re.compile(r"display-name: (.+)")
    match = pattern.search(text)
    return match.group(1) if match else None

failure_errors = root.findall('.//testcase[failure]/system-out')
failure_descriptions = map(lambda x: extract_display_name(x.text), failure_errors)
failed_tests = list(set(failure_descriptions))

for failed in failed_tests:
    print(failed)
