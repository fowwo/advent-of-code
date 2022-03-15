# March 3rd, 2022

file = open("input.txt", 'r')

twoRules = {}
threeRules = {}
for line in file:
	split = line.strip().split(" => ")
	if len(split[0].split('/')) == 2:
		twoRules[split[0]] = split[1]
	else:
		threeRules[split[0]] = split[1]
file.close()

grid = [".#.","..#","###"]

def partition():
	global grid
	width = len(grid) % 2 + 2
	broken = []
	for i in range(0, int(len(grid) / width)):
		broken.append([])
		for j in range(0, int(len(grid) / width)):
			broken[i].append([])
			for k in range(0, width):
				broken[i][j].append("")
				for l in range(0, width):
					broken[i][j][k] += grid[width * i + k][width * j + l]
	grid = broken
def rotate(square):
	new = []
	for i in range(0, len(square)):
		new.append("")
		for j in range(0, len(square)):
			new[i] += square[len(square) - j - 1][i]
	return new
def flip(square):
	for i in range(0, int(len(square) / 2)):
		t = square[i]
		square[i] = square[len(square) - i - 1]
		square[len(square) - i - 1] = t
	return square
def getNextState(square):
	if len(square) % 2 == 0:
		rules = twoRules
	else:
		rules = threeRules
	for _ in range(0, 4):
		state = "/".join(square)
		if state in rules:
			return rules[state].split('/')
		square = rotate(square)
	square = flip(square)
	for _ in range(0, 4):
		state = "/".join(square)
		if state in rules:
			return rules[state].split('/')
		square = rotate(square)
def applyRules():
	global grid
	for i in range(0, len(grid)):
		for j in range(0, len(grid)):
			grid[i][j] = getNextState(grid[i][j])
def merge():
	global grid
	new = []
	for row in grid:
		for i in range(0, len(row[0])):
			string = ""
			for part in row:
				string += part[i]
			new.append(string)
	grid = new

for _ in range(0, 18):
	partition()
	applyRules()
	merge()

count = 0
for r in grid:
	for c in r:
		if c == '#':
			count += 1
print(count)
