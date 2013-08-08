#!/bin/bash
set -e

source config.sh
source json_config.sh

echo
echo "Found" $number_of_files "files to process."
echo

for file in $files
do
  $cmd @$file $url $content_type 
  echo "   -->" $file
  let files_loaded=$files_loaded+1
done

echo
echo $files_loaded "completed loading files."
