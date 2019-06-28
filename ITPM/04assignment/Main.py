#from 04_3wdTomashchuk 
import sys
sys.path.append("../04assignment/")
from twdTomashchuk import TWD
import pandas as pd

def input_params():
    filename = input('Enter filename: ')
    data = pd.read_csv(filename, index_col=0)

    collist = data.columns
    for i in range(0, len(collist)):
        print(i + 1, collist[i])
    
    decision_attribute_index = int(input('\nEnter the number of the DECISION ATTRIBUTE: ')) - 1
    if decision_attribute_index >= len(collist):
        print('\nIndex should be in the range of ', np.arange(1, len(collist) + 1))
    decision_attr = data.columns[decision_attribute_index]

    decision_attribute_value = list(data.iloc[:, decision_attribute_index].unique())
    for i in range(0, len(decision_attribute_value)):
        print(i + 1, decision_attribute_value[i])
    decision_conc = decision_attribute_value[int(input('Enter number of decision concept: '))-1]


    cond_indexes = [int(i) - 1 for i in input('\nEnter one or more indexes your CONDITIONAL  ATTRIBUTE(s) (seperated by \',\'): ').split(',')]
    for i in cond_indexes:
        if i > len(collist):
            print('\nThe conditional attribute index(s) should be in the range of ', np.arange(1, len(collist) + 1))
        elif i == decision_attribute_index:
            print('\nThe conditional attribute index can not be the same as that of decision attribute')

    alfa = float(input('\nEnter the value of ALFA (0 < alfa <= 1): '))
    beta = float(input('\nEnter the value of BETA (0 <= beta <= alfa): '))

    return filename, decision_attr, decision_conc, cond_indexes, alfa, beta

#============ Tester ============
flnm1 = "table1.csv"
flnm2 = "table2.csv"

filename, decision_attr, decision_conc, cond_attr, alfa, beta = input_params()
twd = TWD(filename, decision_attr, decision_conc, cond_attr, alfa, beta)
print('Indiscernability: ', twd._ind)
print('des attr = ', twd.decision_attr)
print('Concepts: ', twd.concepts())
print('Lower approximation: ', twd.lower_apr())
print('Upper approximation: ', twd.upper_apr())
print('Lower and upper probability approximation: \n', twd.prob_low_upp_apr(), '\n\n')
print('=== POS set: ===\n', twd.prob_pos_set())
print('=== BND set: ===\n', twd.prob_bnd_set())
print('=== NEG set: ===\n', twd.prob_neg_set())

