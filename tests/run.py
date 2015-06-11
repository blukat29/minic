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
    try:
        os.remove("code.T")
    except OSError:
        pass
    p = Popen(["make", "run"], stdin=PIPE, stdout=PIPE, stderr=PIPE)
    return p.communicate(input_data)

def check_execution(expected, user_input=None):
    p = Popen(["./machine/run-T", "code.T"], stdout=PIPE, stderr=PIPE, stdin=PIPE)
    out, err = p.communicate(user_input)

    result = []
    begin = False
    for line in out.splitlines():
        if not begin and "//// START" in line:
            begin = True
        elif begin and "////" in line:
            break
        elif begin and line != "":
            if "." in line:
                result.append(int(float(line) * 1000 + 0.5) / 1000.0)
            else:
                result.append(int(line))
    return (expected == result)

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


