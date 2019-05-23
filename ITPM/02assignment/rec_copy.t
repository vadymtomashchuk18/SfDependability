import numpy as np
from pathlib import Path
import re
from fractions import Fraction
#init criterias
#class AHPcalculating(object):

number_of_criterias = None
number_of_alternatives = None
alternatives = []
criterias = []
comp_matrix = []

def splitDataArray(arr_of_all_data):
    global number_of_criterias
    global criterias
    global number_of_alternatives
    global alternatives
    global comp_matrix
    if (isinstance(int(arr_of_all_data[0]), int)):
        number_of_criterias = int(arr_of_all_data[0])
        criterias = re.sub(r"\s+", "", arr_of_all_data[1]).split(',')
    else:
        print("Number of criterias is not int")
    if (isinstance(int(arr_of_all_data[2]), int)):
        number_of_alternatives = int(arr_of_all_data[2])
        alternatives = re.sub(r"\s+", "", arr_of_all_data[3]).split(',')
    else:
        print("Number of criterias is not int")
    # With regexp multiple spaces changed to one space
    # Split string on space ' '
    # Filter list of empty elements ''
    # Every fraction converted to float and put into matrix
    comp_matrix = [[float(Fraction(n)) for n in list(filter(None, re.sub(r"\s+", ' ', x).split(' ')))] for x in arr_of_all_data[4:]]
    return comp_matrix


dict_of_cr_matrixes = None
def algo_of_read_matrixes:
    global dict_of_cr_matrixes
    for i in range(0, number_of_criterias):
        dict_of_cr_matrixes[criterias[i]] = #read matrix from position (4+num_of_criterias)+i*num_of_criterias OR (read from position 4+num_of_criterias AND delete (num_of_alternatives) lines


def init_matrix_from_file(filename) :
    if(isinstance(filename, str) and Path(filename).is_file()):
        data = np.loadtxt(filename, delimiter='\n', dtype='str')
        return splitDataArray(data)

def calc_priority_vector(matrix) :
    priority_vector = []
    # sum of each row
    sum_by_criteria = np.sum(matrix, axis=0)
    # matrix of weights & priority vector
    for i in range(0, len(matrix)) :
        for j in range(0, len(matrix)) :
            matrix[i][j] = matrix[i][j]/sum_by_criteria[j]
        priority_vector = np.sum(matrix, axis=1)/len(matrix)
    print('Sum by criteria: ', sum_by_criteria)
    print('Weight matrix: ', np.matrix(matrix))
    print('Priority vector: ', priority_vector)
    return priority_vector


#=========== MAIN ============
flnm01 = "test01.out"
flnm02 = "test02.txt"
init_matrix_from_file(flnm01)
calc_priority_vector(init_matrix_from_file(flnm01))
#print(number_of_criterias, ' criterias: ',criterias,'\n',number_of_alternatives, ' alternatives: ', alternatives, '\n',np.matrix(comp_matrix))

                                                        70,0-1        Bot

