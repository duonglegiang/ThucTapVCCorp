import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.model_selection import cross_val_score
from sklearn import tree
from sklearn.model_selection import GridSearchCV
import matplotlib as mpl
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.preprocessing import StandardScaler
from sklearn.decomposition import PCA
from sklearn.linear_model import LogisticRegression
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import *
from sklearn import *
from sklearn.metrics import accuracy_score
from pandas import *
from datetime import date
from sklearn.feature_extraction.text import *
from sklearn.feature_extraction.text import *
from sklearn.feature_extraction.text import *
from sklearn.naive_bayes import *
from sklearn.metrics import *
from gensim import models
from gensim.models import *

# load the data
df = pd.read_csv('/home/giang/Documents/BTL_Khaiphadulieu/BTL1/bbc.csv')

docs=["the house had a tiny little mouse",
      "the cat saw the mouse",
      "the mouse ran away from the house",
      "the cat finally ate the mouse",
      "the end of the mouse story"
     ]
# word2vec
model = Word2Vec(docs, size=150, window=10, min_count=2, workers=4, sg=0)
model.wv.save("word2vec_skipgram.model")
X_train = getAvgFeatureVecs(docs, model, 150)
# X_train