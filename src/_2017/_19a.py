# March 2nd, 2022

file = open("input/2017/19.txt", 'r')

diagram = []
for line in file:
	diagram.append(line.replace('\n', ''))

# Find start
r = 0
c = 0
while diagram[0][c] != '|':
	c += 1

move = 'd'
alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
path = []
while r >= 0 and r < len(diagram) and c >= 0 and c < len(diagram[0]) and diagram[r][c] != ' ':
	if move == 'u':
		r -= 1
	elif move == 'd':
		r += 1
	elif move == 'l':
		c -= 1
	else:
		c += 1
	if diagram[r][c] in alphabet:
		path.append(diagram[r][c])
	elif diagram[r][c] == '+':
		if move == 'u' or move == 'd':
			if diagram[r][c - 1] == '-' or diagram[r][c - 1] in alphabet:
				move = 'l'
			elif diagram[r][c + 1] == '-' or diagram[r][c + 1] in alphabet:
				move = 'r'
			else:
				break
		else:
			if diagram[r - 1][c] == '|' or diagram[r - 1][c] in alphabet:
				move = 'u'
			elif diagram[r + 1][c] == '|' or diagram[r + 1][c] in alphabet:
				move = 'd'
			else:
				break
print("".join(path))
