import argparse
from github import Github


'''

This script is intended to be run in conjuction with https://github.com/EnricoMi/publish-unit-test-result-action to upload trimmed
test results from the output to the checks api "text" field for further analysis.

The script takes as input the filename of the json output by the aforementioned action, trims it, and uploads it to the "text" field
of the check run provided in the json file.

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
    print(d)


if __name__ == '__main__':
    main()