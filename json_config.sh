#!/bin/bash
set -e

echo
echo "Sourcing environment file: ${env_file}"
echo
echo "Using stub files in: ${directory}"

files="${directory}/*.json"
files_list=($files)
numbers_of_files=${#files_list[@]}
files_loaded=0
