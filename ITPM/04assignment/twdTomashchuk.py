import numpy as np
import pandas as pd
from functools import reduce

class TWD:
    data = []
    deciison_attr = None
    decision_conc = None
    alfa = None
    beta = None
    concept_of_apr = ''
    _ind = []

# input initialization
    def __init__(self, filename, decision_attr, decision_conc, cond_attributes, alfa=1.0, beta=0.0):
        self.filename = filename
        self.read_data()
        self.decision_attr = decision_attr
        self.decision_conc = decision_conc
        self._ind = self.ind(cond_attributes)
        self.alfa = alfa
        self.beta = beta

    def read_data(self):
        global data
        data = pd.read_csv(self.filename, index_col=0)
        return data

    def ind(self, cond_attributes):
        global data
        global _ind
        collist = data.columns
        cp_data = data[collist[cond_attributes]]
        _ind = cp_data.groupby(collist[cond_attributes].tolist()).apply(lambda x: list(x.index)).tolist()
        return _ind

# objects in which we are interested according to decision
    def concepts(self):
        global data
        collist = data.columns.tolist()
        conc_indexes = data[data[self.decision_attr].isin([self.decision_conc])].groupby(collist).apply(lambda x: x.index.tolist()).tolist()
        return self.flatten_list(conc_indexes)

    def lower_apr(self):
        global _ind
        _concepts = self.concepts()
        low_apr = []
        for i in range(0, len(_ind)):
            in_conc = True
            for j in range(0, len(_ind[i])):
                if (_ind[i][j] not in _concepts):
                    in_conc = False
            if (in_conc):
                low_apr.append(_ind[i])
        return self.flatten_list(low_apr)

    def upper_apr(self):
        global _ind
        _concepts = self.concepts()
        upp_apr = []
        for i in range(0, len(_ind)):
            in_conc = False
            for j in range(0, len(_ind[i])):
                if (_ind[i][j] in _concepts):
                    in_conc = True
            if (in_conc):
                upp_apr.append(_ind[i])
        return self.flatten_list(upp_apr)

    def pos_set(self):
        print("\nset of positive region: ")
        return self.indexes_to_rows(self.lower_apr())

    def bnd_set(self):
        bnd_indexes = self.diff(self.upper_apr(), self.lower_apr())
        print("\nset of boundary region ")
        return self.indexes_to_rows(bnd_indexes)

    def neg_set(self):
        global _ind
        print("\nset of negative region ",)
        neg_indexes = self.diff(self.flatten_list(_ind), self.upper_apr())
        return self.indexes_to_rows(neg_indexes)

# ====== Probability Rough Sets ======

    def prob_low_upp_apr(self):
        global _ind
        set_of_concepts = self.concepts()
        probability = 0.0
        upp_apr = []
        low_apr = []
        for equiv in _ind:
            count = 0
            for elem in equiv:
                if elem in set_of_concepts:
                    count += 1
            probability = count/len(equiv)
            if probability > self.beta:
                upp_apr.extend(equiv)
            if probability >= self.alfa:
                low_apr.extend(equiv)
        return low_apr, upp_apr

    def prob_pos_set(self):
        lower_apr, upper_apr = self.prob_low_upp_apr()
        return self.indexes_to_rows(lower_apr)

    def prob_bnd_set(self):
        lower_apr, upper_apr = self.prob_low_upp_apr()
        bnd_indexes = self.diff(upper_apr, lower_apr)
        return self.indexes_to_rows(bnd_indexes)

    def prob_neg_set(self):
        global _ind
        lower_apr, upper_apr = self.prob_low_upp_apr()
        neg_indexes = self.diff(self.flatten_list(_ind), upper_apr)
        return self.indexes_to_rows(neg_indexes)

# helper-functions
    def flatten_list(self, list_fl):
        return reduce(lambda x,y: x+y, list_fl)

    def intersection(self, lst1, lst2):
        return set(lst1).intersection(lst2)

    def diff(self, li1, li2):
        return (list(set(li1) - set(li2)))

    def indexes_to_rows(self, lst_of_indexes):
        global data
        collist = data.columns.tolist()
        return data.loc[lst_of_indexes]

