set -e

dir="$PWD"
target="$dir/target"
version=1.0.0
file="stubby-stub-examples-$version.tar.gz"

mkdir -p $dir/target

rm -rf $dir/target/*
rm -rf $dir/core/target/*
rm -rf $dir/project/target/*
rm -rf $dir/project/project/target/*

tar -zcvf $target/$file * --exclude='functional-test' --exclude='target' --exclude='test'

echo
echo "Your artifact has been built and is available in $target/$file"
