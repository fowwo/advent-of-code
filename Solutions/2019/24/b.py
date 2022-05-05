# May 5th, 2022

file = open("input.txt", 'r')
scan = [[x for x in line.strip()] for line in file]
file.close()

layer = [[[x for x in '.....'] for j in range(5)] for i in range(401)]
layer.insert(200, scan)

for m in range(200):
	copy = [[[x for x in y] for y in z] for z in layer]
	for n in range(-m - 1, m + 2):
		sub = copy[200 + n]
		inner = copy[200 + n - 1]
		outer = copy[200 + n + 1]
		for i in range(0, 5):
			for j in range(0, 5):
				bugs = 0

				# Outer
				if i == 0 and outer[1][2] == '#': bugs += 1
				elif i == 4 and outer[3][2] == '#': bugs += 1
				if j == 0 and outer[2][1] == '#': bugs += 1
				elif j == 4 and outer[2][3] == '#': bugs += 1

				# Inner
				if i == 1 and j == 2:
					for k in inner[0]:
						if k == '#': bugs += 1
				elif i == 3 and j == 2:
					for k in inner[4]:
						if k == '#': bugs += 1
				elif i == 2 and j == 1:
					for k in [x[0] for x in inner]:
						if k == '#': bugs += 1
				elif i == 2 and j == 3:
					for k in [x[4] for x in inner]:
						if k == '#': bugs += 1

				# Remaining
				if i > 0 and sub[i - 1][j] == '#': bugs += 1
				if i < 4 and sub[i + 1][j] == '#': bugs += 1
				if j > 0 and sub[i][j - 1] == '#': bugs += 1
				if j < 4 and sub[i][j + 1] == '#': bugs += 1

				# Convert
				if sub[i][j] == '#' and bugs != 1: layer[200 + n][i][j] = '.'
				elif sub[i][j] == '.' and (bugs == 1 or bugs == 2) and (i != 2 or j != 2): layer[200 + n][i][j] = '#'

bugs = 0
for scan in layer:
	for row in scan:
		for x in row:
			if x == '#': bugs += 1
print(bugs)
