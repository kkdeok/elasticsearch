rm -rf build
gradle assemble
cp ./build/distributions/analysis-karrot-7.15.1-SNAPSHOT.zip ../../../resource/model/
