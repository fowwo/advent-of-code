# August 9th, 2022

file = open(f"{__file__}/../input.txt", "r")
file.readline()
a = []
line = file.readline().strip()
while line != '':
	a.append(int(line))
	line = file.readline().strip()
file.readline()
b = [ int(line) for line in file ]
file.close()

memo = dict()
def play(a, b):
	rounds = set()
	while len(a) and len(b):
		round = ("".join(map(str, a)), "".join(map(str, b)))
		if round in memo:
			a = list(map(int, memo[round][1][0]))
			b = list(map(int, memo[round][1][1]))
			return memo[round][0]
		if round in rounds:
			for x in rounds:
				memo[x] = (True, round)
			return True
		rounds.add(round)
		x = a.pop(0)
		y = b.pop(0)
		winner = x > y
		if x <= len(a) and y <= len(b):
			winner = play(list(a[:x]), list(b[:y]))
		if winner:
			a.append(x)
			a.append(y)
		else:
			b.append(y)
			b.append(x)
	for x in rounds:
		memo[x] = (len(a) > 0, round)
	return len(a) > 0
play(a, b)

s = 0
n = a if len(a) else b
for i in range(len(n)):
	s += n[i] * (len(n) - i)
print(s)
