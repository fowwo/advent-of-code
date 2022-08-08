# August 8th, 2022

file = open(f"{__file__}/../input.txt", "r")

rules = dict()
line = file.readline().strip()
while line != '':
	s = line.split(": ")
	r = s[1].split(" | ")
	l = []
	for x in r:
		x = x.split(' ')
		for i in range(len(x)):
			if x[i].isnumeric():
				x[i] = int(x[i])
			else:
				x[i] = x[i][1:-1]
		l.append(tuple(x))
	if len(l) == 1: l = l[0] # List to tuple
	if len(l) == 1: l = l[0] # Tuple to int/char
	rules[int(s[0])] = l
	line = file.readline().strip()
messages = [ line.strip() for line in file ]
file.close()

def recursiveGen(string, rule):
	while isinstance(rules[rule], int):
		rule = rules[rule]
	if isinstance(rules[rule], str):
		return set([ string + rules[rule] ])
	elif isinstance(rules[rule], tuple):
		return genTuple(string, rules[rule])
	else:
		s = set()
		for x in rules[rule]:
			if isinstance(x, str):
				s.add(string + x)
			elif isinstance(x, int):
				s = s.union(recursiveGen(string, x))
			else:
				s = s.union(genTuple(string, x))
		return s
def genTuple(string, tuple):
	s = set([ string ])
	for x in tuple:
		if isinstance(x, str):
			s = set(y + x for y in s)
		else:
			n = recursiveGen("", x)
			s = set(y + z for y in s for z in n)
	return s

s = recursiveGen("", 0)
c = 0
for message in messages:
	if message in s:
		c += 1
print(c)
