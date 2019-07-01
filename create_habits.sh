#!/bin/bash
SRC_DIR=src
SUFFIX=habit
SEV_HABITS=( 'autorepair' 'informagics' 'balance_tech_debt-time_to_market-tco' 'all_as_code' 'good_design' 'pareto' 'evolutionary_design' )
COUNT=1
for hab in "${SEV_HABITS[@]}"
do
  DIR=${SRC_DIR}/${COUNT}_${hab}_$SUFFIX
  echo "Making dir ${DIR}"
  mkdir ${DIR}
  cd ${DIR}
  echo "# {hab}" > README.md
  cd -
  let COUNT+=1
done
