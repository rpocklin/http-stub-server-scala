#!/bin/bash
set -e

source config.sh

$clear_cmd $clear_logs_url $content_type

echo
echo "All logs cleared."
