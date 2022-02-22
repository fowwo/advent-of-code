# February 22nd, 2022

file = open("input/2017/16.txt", "r")
moves = file.readline().strip().split(",")
file.close()

line = [ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p' ]
for move in moves:
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
	else:
		# Partner
		split = move[1:].split("/")
		a = line.index(split[0])
		b = line.index(split[1])
		t = line[a]
		line[a] = line[b]
		line[b] = t
print("".join(line))
