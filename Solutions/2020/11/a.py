# August 6th, 2022

file = open(f"{__file__}/../input.txt", "r")
seats = [ [ c for c in x.strip() ] for x in file.readlines() ]
file.close()

def adjacentOccupied(i, j):
	adj = [ x for x in [(i - 1, j - 1), (i - 1, j), (i - 1, j + 1), (i, j - 1), (i, j + 1), (i + 1, j - 1), (i + 1, j), (i + 1, j + 1)] if x[0] >= 0 and x[0] < len(seats) and x[1] >= 0 and x[1] < len(seats[0]) ]
	count = 0
	for x in adj:
		if seats[x[0]][x[1]] == '#':
			count += 1
	return count

b = True
while b:
	b = False
	new = [ [ x for x in y ] for y in seats ]
	for i in range(len(seats)):
		for j in range(len(seats[0])):
			if seats[i][j] == 'L' and adjacentOccupied(i, j) == 0:
				new[i][j] = '#'
				b = True
			elif seats[i][j] == '#' and adjacentOccupied(i, j) >= 4:
				new[i][j] = 'L'
				b = True
	seats = new

count = 0
for row in seats:
	for x in row:
		if x == '#':
			count += 1
print(count)
