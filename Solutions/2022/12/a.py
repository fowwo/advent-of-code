# December 11th, 2022

file = open(f"{__file__}/../input.txt", "r")

grid = dict()
S = None
E = None

i = 0
for line in file:
	line = line.strip()
	for j in range(len(line)):
		grid[(i, j)] = line[j]
		if line[j] == "S":
			S = (i, j)
		elif line[j] == "E":
			E = (i, j)
	i += 1

def height(x):
	return "SabcdefghijklmnopqrstuvwxyzE".find(grid[(x[0], x[1])])

m = [ S ]
memo = set()
steps = 0
while E not in m:
	new = []
	for x in m:
		for d in [ (-1, 0), (0, -1), (1, 0), (0, 1) ]:
			y = (x[0] + d[0], x[1] + d[1])
			if y in grid and y not in memo and height(y) - height(x) <= 1:
				new.append(y)
				memo.add(y)
	m = new
	steps += 1
print(steps)
