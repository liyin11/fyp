
 
outfilename1 = "20.txt"
outfilename2 = "Zh.train"
import random

with open('C:/Users/liyin11/Documents/workspace/crawler/Zh.txt','r') as f:
    lines = f.readlines()

numlines = int(len(lines)*0.20)

with open(outfilename1, 'w') as f:
    f.writelines(lines[:numlines])
with open(outfilename2, 'w') as f:
    f.writelines(lines[numlines:])