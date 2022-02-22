# February 22nd, 2022

file = open("input/2017/16.txt", "r")
moves = file.readline().strip().split(",")
file.close()

line = [ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p' ]

np = [move for move in moves if move[0] != 'p']
p = [move for move in moves if move[0] == 'p']

# Performing 15 dances without partner moves results in the initial line
for dance in range(0, 1000000000 % 15):
	for move in np:
		if move[0] == 's':
			# Spin
			for i in range(0, int(move[1:])):
				t = line[15]
				for j in range(0, 15):
					line[15 - j] = line[14 - j]
				line[0] = t
		elif move[0] == 'x':
			# Exchange
			split = [int(x) for x in move[1:].split("/")]
			t = line[split[0]]
			line[split[0]] = line[split[1]]
			line[split[1]] = t

# Performing 12 dances with only partner moves results in the initial line
for dance in range(0, 1000000000 % 12):
	for move in p:
		# Partner
		split = move[1:].split("/")
		a = line.index(split[0])
		b = line.index(split[1])
		t = line[a]
		line[a] = line[b]
		line[b] = t

print("".join(line))
