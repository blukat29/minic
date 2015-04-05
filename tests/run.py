#!/usr/bin/env python
import re
import os
import sys
from subprocess import Popen, PIPE

base_dir = os.path.abspath(os.path.dirname(__file__))
inputs_dir = os.path.join(base_dir, "inputs")
outputs_dir = os.path.join(base_dir, "outputs")
os.chdir(os.path.join(base_dir, ".."))

tests = """
decl_list
decl_list_bad
"""
tests = filter(None, tests.splitlines())

def compile(input_data):
    p = Popen(["make", "run"], stdin=PIPE, stdout=PIPE, stderr=PIPE, shell=False)
    return p.communicate(input_data)

def run_single_test(name):
    input_file = open(os.path.join(inputs_dir, name + ".c"), "r")
    output_file = open(os.path.join(outputs_dir, name + ".py"), "r")
    check_code = "(" + output_file.read() + ")"
    out, err = compile(input_file.read())
    success = eval(check_code)
    return success

if __name__ == '__main__':
    for case in tests:
        success = run_single_test(case)
        if success:
            print "pass", case
        else:
            print "FAIL", case


