# May 5th, 2022

file = open("input.txt", 'r')

scan = []
for line in file:
	for x in line.strip():
		scan.append(x)
file.close()

scan = tuple(scan)
memo = set()
while scan not in memo:
	memo.add(scan)
	copy = list(scan)
	for i in range(0, 5):
		for j in range(0, 5):
			bugs = 0
			if i > 0 and scan[5 * (i - 1) + j] == '#': bugs += 1
			if i < 4 and scan[5 * (i + 1) + j] == '#': bugs += 1
			if j > 0 and scan[5 * i + j - 1] == '#': bugs += 1
			if j < 4 and scan[5 * i + j + 1] == '#': bugs += 1

			if scan[5 * i + j] == '#' and bugs != 1: copy[5 * i + j] = '.'
			elif scan[5 * i + j] == '.' and (bugs == 1 or bugs == 2): copy[5 * i + j] = '#'
	scan = tuple(copy)

rating = 0
for i in range(len(scan)):
	if scan[i] == '#': rating += pow(2, i)
print(rating)
