# August 6th, 2021

file = open(f"{__file__}/../input.txt", "r")
seats = [ [ c for c in x.strip() ] for x in file.readlines() ]
file.close()

def adjacentOccupied(i, j):
	adj = [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]
	count = 0
	for x in adj:
		p = (x[0] + i, x[1] + j)
		while p[0] >= 0 and p[0] < len(seats) and p[1] >= 0 and p[1] < len(seats[0]):
			if seats[p[0]][p[1]] == '#':
				count += 1
				break
			elif seats[p[0]][p[1]] == 'L':
				break
			p = (p[0] + x[0], p[1] + x[1])
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
			elif seats[i][j] == '#' and adjacentOccupied(i, j) >= 5:
				new[i][j] = 'L'
				b = True
	seats = new

count = 0
for row in seats:
	for x in row:
		if x == '#':
			count += 1
print(count)
