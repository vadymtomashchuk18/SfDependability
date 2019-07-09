import numpy as np
from pathlib import Path
import re
from fractions import Fraction

class AHP:
    num_of_criterias = None
    num_of_alternatives = None
    all_data = []

    def __init__(self, filename):
        self.filename = filename
        self.read_data_from_file() 
        self.calc_main_matrix()

    # With regexp multiple spaces changed to one space
    # Split string on space ' '
    # Filter list of empty elements ''
    # Every fraction converted to float and put into matrix
    def read_matrix_by_lines(self, first_line, last_line) : 
        return [[float(Fraction(n)) for n in list(filter(None, re.sub(r"\s+", ' ', x).split(' ')))] for x in all_data[first_line:last_line]]

    def read_data_from_file(self) :
        global all_data
        if(isinstance(self.filename, str) and Path(self.filename).is_file()):
            all_data = np.loadtxt(self.filename, delimiter='\n', dtype='str')
        return all_data

    def calc_main_matrix(self):
        global num_of_criterias 
        global num_of_alternatives
        comp_matrix = []
        if (isinstance(int(all_data[0]), int)):
            num_of_criterias = int(all_data[0])
        else:
            print("Number of criterias is not int")
        if (isinstance(int(all_data[2]), int)):
            num_of_alternatives = int(all_data[2])
        else:
            print("Number of alternatives is not int")   
        comp_matrix = self.read_matrix_by_lines(4, 4+num_of_criterias)
        return comp_matrix

    def def_criterias(self, all_data):
        criterias = re.sub(r"\s+", "", all_data[1]).split(',')
        return criterias

    def def_alternatives(self, all_data):
        alternatives = re.sub(r"\s+", "", all_data[3]).split(',')
        return alternatives 

    def matrixes_by_criterias(self):
        dict_of_cr_matrixes = {}
        criterias = self.def_criterias(all_data)
        for i in range(0, num_of_criterias):
            first_line = 4 + num_of_criterias * (i+1)
            last_line = first_line + num_of_criterias
            dict_of_cr_matrixes[criterias[i]] = self.read_matrix_by_lines(first_line, last_line)
        return dict_of_cr_matrixes
    #read matrix from position (4+num_of_criterias)+i*num_of_criterias

    def calc_priority_vector(self, matrix) :
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
        print('Priority vector: ', priority_vector, "\n\n")
        return priority_vector

    def calc_decision_matrix(self):
        weights_matrixes = self.matrixes_by_criterias()
        criterias_vectors = {}
        for criteria, matrix in weights_matrixes.items():
            criterias_vectors[criteria] = self.calc_priority_vector(matrix)
        # structurize weights by alternatives
        decision_matrix = {}
        criterias = self.def_criterias()
        alternatives = self.def_alternatives()
        for i in range(0, num_of_alternatives):
            array_of_vectors = []
            for j in range(0, num_of_criterias):
                criteria_array = 
                array_of_vectors.append()
            decision_matrix[alternatives[i]] = array_of_vectors
        return criterias_vectors

#=========== MAIN ============
flnm01 = "test01.out"
flnm02 = "test02.txt"
tra = AHP(flnm01)
#read_data_from_file(flnm01)
main_matrix = tra.calc_main_matrix()
print(main_matrix)
tra.calc_priority_vector(main_matrix)
print(tra.matrixes_by_criterias())
print(tra.calc_decision_matrix())
