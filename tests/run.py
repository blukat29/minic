#!/usr/bin/env python
import re
import os
import sys
from subprocess import Popen, PIPE

base_dir = os.path.abspath(os.path.dirname(__file__))
inputs_dir = os.path.join(base_dir, "inputs")
outputs_dir = os.path.join(base_dir, "outputs")
os.chdir(os.path.join(base_dir, ".."))

tests = open(os.path.join(base_dir, "tests.txt"), "r").read()
tests = filter(None, tests.split('\n'))

def compile(input_data):
    p = Popen(["make", "run"], stdin=PIPE, stdout=PIPE, stderr=PIPE, shell=False)
    return p.communicate(input_data)

def run_single_test(name):
    input_file = open(os.path.join(inputs_dir, name + ".c"), "r")
    output_file = open(os.path.join(outputs_dir, name + ".py"), "r")
    input_code = input_file.read()
    check_code = "(" + output_file.read() + ")"
    input_file.close()
    output_file.close()

    out, err = compile(input_code)
    success = eval(check_code)
    if "Exception" in err:
        success = False

    if not success:
        print "==input=========================="
        print input_code
        print "==check=========================="
        print check_code
        print "==error=========================="
        print err
    return success

if __name__ == '__main__':
    pass_cnt = 0
    fail_cnt = 0
    results = []
    for case in tests:
        success = run_single_test(case)
        results.append(success)
        if success:
            print "\x1b[1;32m", "pass", case, "\x1b[0m"
            pass_cnt += 1
        else:
            print "\x1b[1;31m", "FAIL", case, "\x1b[0m"
            fail_cnt += 1
    print "pass %d fail %d" % (pass_cnt, fail_cnt)


