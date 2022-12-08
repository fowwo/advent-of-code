# December 7th, 2022

file = open(f"{__file__}/../input.txt", "r")

trees = [ [ int(x) for x in line.strip() ] for line in file ]

s = set()
for i in range(len(trees)):
	height = -1
	for j in range(len(trees)):
		tree = trees[i][j]
		if tree > height:
			s.add((i, j))
			height = tree
	height = -1
	for j in range(len(trees)):
		tree = trees[i][len(trees) - j - 1]
		if tree > height:
			s.add((i, len(trees) - j - 1))
			height = tree
	height = -1
	for j in range(len(trees)):
		tree = trees[j][i]
		if tree > height:
			s.add((j, i))
			height = tree
	height = -1
	for j in range(len(trees)):
		tree = trees[len(trees) - j - 1][i]
		if tree > height:
			s.add((len(trees) - j - 1, i))
			height = tree
print(len(s))
