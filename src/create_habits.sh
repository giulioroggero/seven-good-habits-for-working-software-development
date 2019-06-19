#!/bin/bash
DNAME=habit
SEV_HABITS=( 'autorepair' 'informagics' 'balance_tech_debt-time_to_market-tco' 'all_as_code' 'good_design' 'pareto' 'evolutionary_design' )
for hab in "${SEV_HABITS[@]}"
do
  DIR=${hab}_$DNAME
  echo "Making dir ${DIR}"
  mkdir ${DIR}
  cd ${DIR}
  echo "# {hab}" > README.md
  cd ..
done
