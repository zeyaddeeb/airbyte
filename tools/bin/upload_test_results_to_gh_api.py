import argparse
import json
import os
from github import Github


'''

This script is intended to be run in conjuction with https://github.com/EnricoMi/publish-unit-test-result-action to upload trimmed
test results from the output to the checks api "text" field for further analysis.

The script takes as input the filename of the json output by the aforementioned action, trims it, and uploads it to the "text" field
of the check run provided in the json file.

Note that there is a limit of ~65k characters in the check run API text field - so we do some trimming of the json to ensure that size
is respected.

'''

# Initiate the parser
parser = argparse.ArgumentParser()

# Add long and short argument
parser.add_argument("--json", "-j", help="Path to the result json output by https://github.com/EnricoMi/publish-unit-test-result-action")

def main():
    # Read arguments from the command line
    args = parser.parse_args()

    token = os.getenv('GITHUB_TOKEN')

    f = open(args.json)
    d = json.load(f)
    out = {'success': {}, 'failure': {}, 'skipped': {}}
    for elem in d['cases']:
        if 'success' in elem['states']:
            for i in range(len(elem['states']['success'])):
                out['success'][elem['states']['success'][i]['test_name']]=elem['states']['success'][i]['time']
        if 'failure' in elem['states']:
            for i in range(len(elem['states']['failure'])):
                out['failure'][elem['states']['success'][i]['test_name']]=elem['states']['failure'][i]['time']
        if 'skipped' in elem['states']:
            for i in range(len(elem['states']['skipped'])):
                out['skipped'][elem['states']['skipped'][i]['test_name']]=elem['states']['skipped'][i]['time']

    print(out)


if __name__ == '__main__':
    main()