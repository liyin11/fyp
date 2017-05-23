
 
outfilename1 = "Zh.dev"
outfilename2 = "Zh.test"
import random

with open('C:/Users/liyin11/Documents/workspace/crawler/20.txt','r') as f:
    lines = f.readlines()

numlines = int(len(lines)*0.50)

with open(outfilename1, 'w') as f:
    f.writelines(lines[:numlines])
with open(outfilename2, 'w') as f:
    f.writelines(lines[numlines:])